package org.yangxin.test.netty.rpc.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import lombok.extern.slf4j.Slf4j;
import org.yangxin.test.netty.rpc.common.MessageEncoder;
import org.yangxin.test.netty.rpc.common.MessageOutput;
import org.yangxin.test.netty.rpc.common.MessageRegistry;
import org.yangxin.test.netty.rpc.common.RequestId;

import java.util.concurrent.TimeUnit;

/**
 * @author yangxin
 * 2021/8/10 17:32
 */
@Slf4j
public class RpcClient {

    private String ip;
    private Integer port;
    private Bootstrap bootstrap;
    private EventLoopGroup group;
    private MessageCollector collector;
    private boolean started;
    private boolean stopped;

    private MessageRegistry registry = new MessageRegistry();

    public RpcClient(String ip, Integer port) {
        this.ip = ip;
        this.port = port;

        this.init();
    }

    public RpcClient rpc(String type, Class<?> requestClass) {
        registry.register(type, requestClass);
        return this;
    }

    public <T> RpcFuture<T> sendAsync(String type, Object payload) {
        if (!started) {
            connect();
            started = true;
        }

        String requestId = RequestId.next();
        MessageOutput output = new MessageOutput(requestId, type, payload);
        return collector.send(output);
    }

    private void connect() {
        bootstrap.connect(ip, port).syncUninterruptibly();
    }

    private void init() {
        bootstrap = new Bootstrap();
        group = new NioEventLoopGroup(1);
        bootstrap.group(group);
        MessageEncoder messageEncoder = new MessageEncoder();
        collector = new MessageCollector(registry, this);
    }

    public void reconnect() {
        if (stopped) {
            return;
        }

        bootstrap.connect(ip, port)
                .addListener(future -> {
                    if (future.isSuccess()) {
                        return;
                    }

                    if (!stopped) {
                        group.schedule(this::reconnect, 1, TimeUnit.SECONDS);
                    }
                    log.error("connect [{}]:[{}] failure", ip, port, future.cause());
                });
    }
}
