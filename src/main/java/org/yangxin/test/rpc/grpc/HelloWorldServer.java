package org.yangxin.test.rpc.grpc;

import io.grpc.Grpc;
import io.grpc.InsecureServerCredentials;
import io.grpc.Server;
import io.grpc.stub.StreamObserver;
import org.yangxin.test.rpc.grpc.helloworld.GreeterGrpc;
import org.yangxin.test.rpc.grpc.helloworld.HelloReply;
import org.yangxin.test.rpc.grpc.helloworld.HelloRequest;

import java.io.IOException;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class HelloWorldServer {

    private static final int port = 50052;

    /**
     * grpc 服务实例
     */
    private Server server;

    public void start() throws IOException {
        server = Grpc.newServerBuilderForPort(port, InsecureServerCredentials.create())
                // 添加业务处理类
                .addService(new GreeterImpl(port))
                .build()
                .start();

        // 注册一个 jvm 关闭钩子（ Shutdown Hook ），
        // 当 Java 虚拟机（ JVM ）即将关闭时（无论是正常退出还是非正常退出，如接收到操作系统中断信号），
        // 所有已注册的关闭钩子都将被依次调用
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            // Use stderr here since the logger may have been reset by its JVM shutdown hook.
            System.err.println("*** shutting down gRPC server since JVM is shutting down");

            try {
                // 优雅地停止 gRPC 服务器实例
                HelloWorldServer.this.stop();
            } catch (InterruptedException e) {
                e.printStackTrace(System.err);
            }
            System.err.println("*** server shut down");
        }));
    }

    /**
     * 停止 grpc 实例
     *
     * @throws InterruptedException 被中断异常
     */
    private void stop() throws InterruptedException {
        if (Objects.nonNull(server)) {
            // 发起服务器的关闭流程，不再接受新的连接和请求，但允许现有连接继续完成请求处理
            server.shutdown()
                    // 给予服务器最长 30 秒的时间去完成所有待处理的工作，超过这个时间限制，程序将继续执行后续逻辑，即使服务器还有任务未完成
                    // 这样设计有助于在应用退出时确保资源得到释放，同时也能防止因某些原因导致的长时间无法关闭的问题
                    .awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    /**
     * 确保主线程或者其他调用者线程会在服务器完全关闭之前保持等待状态。
     * 在主线程上等待终止，因为 grpc 库使用守护线程
     *
     * @throws InterruptedException 被打断异常
     */
    public void blockUntilShutdown() throws InterruptedException {
        if (Objects.nonNull(server)) {
            server.awaitTermination();
        }
    }

    /**
     * 业务处理类
     */
    private static class GreeterImpl extends GreeterGrpc.GreeterImplBase {

        private final int port;

        public GreeterImpl(int port) {
            this.port = port;
        }

        @Override
        public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver) {
            try {
                Thread.sleep(500 + new Random().nextInt(1000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // 向客户端发送响应数据，即将创建好的 reply 对象推送给客户端
            HelloReply reply = HelloReply.newBuilder()
                    .setMessage("Hello " + request.getName() + this.port)
                    .build();
            responseObserver.onNext(reply);
            // 表示响应已经结束，没有更多的数据要发送给客户端
            responseObserver.onCompleted();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        final HelloWorldServer helloWorldServer = new HelloWorldServer();
        helloWorldServer.start();
        helloWorldServer.blockUntilShutdown();
    }
}
