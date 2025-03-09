package org.yangxin.test.rpc.diy;

import org.yangxin.test.rpc.diy.client.RpcClient;
import org.yangxin.test.rpc.diy.server.Server;
import org.yangxin.test.rpc.diy.server.ServiceCenter;
import org.yangxin.test.rpc.diy.server.producer.ServiceProducer;
import org.yangxin.test.rpc.diy.server.producer.ServiceProducerImpl;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author yangxin
 * 2021/2/17 下午10:36
 */
@SuppressWarnings("CallToPrintStackTrace")
public class RpcTest {

    public static void main(String[] args) {
        // 开启服务端，服务端注册服务
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1,
                1,
                0,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(),
                r -> {
                    Thread thread = new Thread(r);
                    thread.setName("RpcTest-thread");
                    return thread;
                });
        threadPoolExecutor.execute(() -> {
            Server server = new ServiceCenter(8088);
            server.register(ServiceProducer.class, ServiceProducerImpl.class);
            try {
                server.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // 服务请求者获得服务提供者远程代理对象，调用后获取结果
        ServiceProducer serviceProducer = RpcClient.getRemoteProxyObject(
                ServiceProducer.class, new InetSocketAddress("localhost", 8088)
        );
        String result = serviceProducer.sendData("test");
        System.out.println("result -> " + result);
    }
}
