package org.yangxin.test.rpc.grpc;

import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.NameResolverRegistry;
import org.yangxin.test.rpc.grpc.loadbalance.LoadBalanceNameResolverProvider;

import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("CallToPrintStackTrace")
public class HelloWorldClient {

    /**
     * 客户端对服务 Greeter 进行同步 rpc 调用 blockingStub
     */
    private final org.yangxin.test.rpc.grpc.helloworld.GreeterGrpc.GreeterBlockingStub blockingStub;

    public HelloWorldClient(Channel channel) {
        // 创建一个新的阻塞式 stub ，支持对服务的一元和流输出调用
        blockingStub = org.yangxin.test.rpc.grpc.helloworld.GreeterGrpc.newBlockingStub(channel);
    }

    public void greet(String name) {
        org.yangxin.test.rpc.grpc.helloworld.HelloRequest request = org.yangxin.test.rpc.grpc.helloworld.HelloRequest
                .newBuilder()
                .setName(name)
                .build();
        org.yangxin.test.rpc.grpc.helloworld.HelloReply response;
        try {
            // 设置此次 rpc 调用的响应超时时间为 1 秒
            response = blockingStub.withDeadlineAfter(1, TimeUnit.SECONDS).sayHello(request);
            System.out.println(response.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 服务端连接地址
        List<InetSocketAddress> addressList = Arrays.asList(
                new InetSocketAddress("127.0.0.1", 50052),
                new InetSocketAddress("127.0.0.1", 50053)
        );

        // 注册 NameResolverProvider
        NameResolverRegistry.getDefaultRegistry()
                .register(new LoadBalanceNameResolverProvider(addressList));
        // 符合 namesolver 的有效 uri
        String target = String.format("%s:///%s", HelloWorldConstant.SCHEME, HelloWorldConstant.SERVICE_NAME);

        // 创建 channel
        ManagedChannel channel = ManagedChannelBuilder.forTarget(target)
                .defaultLoadBalancingPolicy("round_robin")
                // 使用明文连接到服务器，默认情况下，将使用安全连接机制，如 TLS
                // 应仅用于测试或 API 的使用或交换的数据不敏感的 API
                .usePlaintext()
                .disableRetry()
                .build();

        try {
            HelloWorldClient client = new HelloWorldClient(channel);
            long current = System.currentTimeMillis();
            for (int i = 0; i < 10; i++) {
                Thread.sleep(5000);
                client.greet("测试");
            }
            System.out.println(System.currentTimeMillis() - current);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 关闭 channel
            channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
        }
    }
}
