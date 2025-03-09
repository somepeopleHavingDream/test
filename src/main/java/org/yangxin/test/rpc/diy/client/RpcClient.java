package org.yangxin.test.rpc.diy.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * rpc客户端
 *
 * @author yangxin
 * 2021/2/17 下午9:23
 */
@SuppressWarnings({"unchecked", "CallToPrintStackTrace"})
public class RpcClient {

    /**
     * 获得远程代理对象
     *
     * @param serviceInterface 服务接口
     * @param address 地址
     * @return 远程代理对象
     * @param <T> 代理对象的类型
     */
    public static <T> T getRemoteProxyObject(Class<?> serviceInterface, InetSocketAddress address) {
        // 将本地的接口调用转换成 JDK 的动态代理，在动态代理中实现接口的远程调用
        return (T) Proxy.newProxyInstance(
                serviceInterface.getClassLoader(),
                new Class<?>[]{serviceInterface},
                (proxy, method, args) -> handleInvocation(
                        serviceInterface, address, method, args
                )
        );
    }

    /**
     * 处理调用
     *
     * @param serviceInterface 服务接口
     * @param address 地址
     * @param method 方法
     * @param args 方法参数
     * @return 处理调用的结果
     * @throws IOException 输入输出异常
     */
    private static Object handleInvocation(
            Class<?> serviceInterface,
            InetSocketAddress address,
            Method method, Object[] args
    ) throws IOException {
        Socket socket = null;
        ObjectOutputStream objectOutputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            // 创建Socket客户端，根据指定地址连接远程服务提供者
            socket = new Socket();
            socket.connect(address);

            // 将远程服务调用所需要的接口类、方法名、参数列表等，编码后发送给服务端
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeUTF(serviceInterface.getName());
            objectOutputStream.writeUTF(method.getName());
            objectOutputStream.writeObject(method.getParameterTypes());
            objectOutputStream.writeObject(args);

            // 同步阻塞等待服务端返回应答，获取应答后返回
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            return objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (objectInputStream != null) {
                objectInputStream.close();
            }
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }
            if (socket != null) {
                socket.close();
            }
        }
    }
}
