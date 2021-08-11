package org.yangxin.test.netty.rpc.client;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.yangxin.test.netty.rpc.common.MessageOutput;
import org.yangxin.test.netty.rpc.common.MessageRegistry;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

/**
 * @author yangxin
 * 2021/8/10 17:30
 */
@ChannelHandler.Sharable
@Slf4j
public class MessageCollector extends ChannelInboundHandlerAdapter {

    private MessageRegistry registry;
    private RpcClient client;
    private ChannelHandlerContext context;
    private ConcurrentMap<String, RpcFuture<?>> pendingTaskMap = new ConcurrentHashMap<>();

    private Throwable connectionClosed = new Exception("rpc connection not active error");

    public MessageCollector(MessageRegistry registry, RpcClient client) {
        this.registry = registry;
        this.client = client;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.context = ctx;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        this.context = null;

        pendingTaskMap.forEach((arg1, future) -> future.fail(connectionClosed));
        pendingTaskMap.clear();

        // 尝试重连
        ctx.channel().eventLoop().schedule(() -> client.reconnect(), 1, TimeUnit.SECONDS);
    }

    public <T> RpcFuture<T> send(MessageOutput output) {
        return null;
    }
}
