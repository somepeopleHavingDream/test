package org.yangxin.test.rpc;

import lombok.extern.slf4j.Slf4j;
import org.yangxin.test.rpc.client.RpcClient;
import org.yangxin.test.rpc.server.Server;
import org.yangxin.test.rpc.server.ServiceCenter;
import org.yangxin.test.rpc.server.producer.ServiceProducer;
import org.yangxin.test.rpc.server.producer.ServiceProducerImpl;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author yangxin
 * 2021/2/17 下午10:36
 */
@Slf4j
public class RpcTest {

    public static void main(String[] args) {
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

        ServiceProducer serviceProducer = RpcClient.getRemoteProxyObject(ServiceProducer.class,
                new InetSocketAddress("localhost", 8088));
        String result = serviceProducer.sendData("test");
        log.info("result: [{}]", result);
    }
}
