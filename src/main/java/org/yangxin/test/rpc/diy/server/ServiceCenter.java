package org.yangxin.test.rpc.diy.server;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author yangxin
 * 2021/2/17 下午9:48
 */
@SuppressWarnings("InfiniteLoopStatement")
public class ServiceCenter implements Server {

    /**
     * 任务处理线程池
     */
    private static final ExecutorService executorService = new ThreadPoolExecutor(
            Runtime.getRuntime().availableProcessors(),
            Runtime.getRuntime().availableProcessors(),
            0L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(),
            r -> {
                Thread thread = new Thread(r);
                thread.setName("service-center-thread");

                return thread;
            }
    );

    private static final Map<String, Class<?>> SERVICE_REGISTRY = new HashMap<>();

    private static Integer port;

    public ServiceCenter(int port) {
        ServiceCenter.port = port;
    }

    @Override
    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(port));
        System.out.println("Server start.");

        try {
            while (true) {
                executorService.execute(new ServiceTask(serverSocket.accept()));
            }
        } finally {
            serverSocket.close();
        }
    }

    @Override
    public void register(Class<?> serviceInterface, Class<?> impl) {
        SERVICE_REGISTRY.put(serviceInterface.getName(), impl);
    }

    @SuppressWarnings("CallToPrintStackTrace")
    private static class ServiceTask implements Runnable {

        private final Socket client;

        public ServiceTask(Socket socket) {
            this.client = socket;
        }

        @Override
        public void run() {
            ObjectInputStream objectInputStream = null;
            ObjectOutputStream objectOutputStream = null;

            try {
                objectInputStream = new ObjectInputStream(client.getInputStream());
                String serviceName = objectInputStream.readUTF();
                String methodName = objectInputStream.readUTF();
                Class<?>[] parameterTypes = (Class<?>[]) objectInputStream.readObject();
                Object[] arguments = (Object[]) objectInputStream.readObject();

                Class<?> serviceClass = SERVICE_REGISTRY.get(serviceName);
                if (serviceClass == null) {
                    throw new ClassNotFoundException(serviceName + "not found!");
                }

                Method method = serviceClass.getMethod(methodName, parameterTypes);
                Object result = method.invoke(serviceClass.newInstance(), arguments);

                objectOutputStream = new ObjectOutputStream(client.getOutputStream());
                objectOutputStream.writeObject(result);
            } catch (IOException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException |
                     InstantiationException | InvocationTargetException e) {
                e.printStackTrace();
            } finally {
                if (objectOutputStream != null) {
                    try {
                        objectOutputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (objectInputStream != null) {
                    try {
                        objectInputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (client != null) {
                    try {
                        client.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
