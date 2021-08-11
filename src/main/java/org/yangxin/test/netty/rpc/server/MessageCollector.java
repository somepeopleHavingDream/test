package org.yangxin.test.netty.rpc.server;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.yangxin.test.netty.rpc.common.IMessageHandler;
import org.yangxin.test.netty.rpc.common.MessageHandlers;
import org.yangxin.test.netty.rpc.common.MessageInput;
import org.yangxin.test.netty.rpc.common.MessageRegistry;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yangxin
 * 2021/8/10 11:11
 */
@SuppressWarnings({"NullableProblems", "ResultOfMethodCallIgnored", "unchecked"})
@ChannelHandler.Sharable
@Slf4j
public class MessageCollector extends ChannelInboundHandlerAdapter {

    private ThreadPoolExecutor executor;
    private MessageHandlers handlers;
    private MessageRegistry registry;

    public MessageCollector(MessageHandlers handlers, MessageRegistry registry, Integer workerThreads) {
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(1000);

        ThreadFactory factory = new ThreadFactory() {

            final AtomicInteger seq = new AtomicInteger();

            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("rpc-" + seq.getAndIncrement());

                return thread;
            }
        };

        this.executor = new ThreadPoolExecutor(1,
                workerThreads,
                30,
                TimeUnit.SECONDS,
                queue,
                factory,
                new ThreadPoolExecutor.CallerRunsPolicy());
        this.handlers = handlers;
        this.registry = registry;
    }

    public void closeGracefully() {
        this.executor.shutdown();
        try {
            this.executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException ignored) {
        }
        this.executor.shutdownNow();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("connection comes");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("connection leaves");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof MessageInput) {
            this.executor.execute(() -> this.handleMessage(ctx, (MessageInput) msg));
        }
    }

    private void handleMessage(ChannelHandlerContext ctx, MessageInput input) {
        // 业务逻辑在这里
        String type = input.getType();
        String requestId = input.getRequestId();

        Class<?> clazz = this.registry.get(type);
        if (clazz == null) {
            this.handlers.defaultHandler().handle(ctx, requestId, input);
            return;
        }

        Object payload = input.getPayload(clazz);
        IMessageHandler<Object> handler = (IMessageHandler<Object>) handlers.get(type);
        if (handler != null) {
            handler.handle(ctx, requestId, payload);
        } else {
            handlers.defaultHandler().handle(ctx, requestId, input);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info("connection error", cause);
    }
}
