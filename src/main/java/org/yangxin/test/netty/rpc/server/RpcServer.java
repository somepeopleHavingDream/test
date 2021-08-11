package org.yangxin.test.netty.rpc.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;
import lombok.extern.slf4j.Slf4j;
import org.yangxin.test.netty.rpc.common.*;

/**
 * @author yangxin
 * 2021/8/10 17:01
 */
@Slf4j
public class RpcServer {

    private final String ip;
    private final Integer port;
    private final Integer ioThreads;
    private final Integer workerThreads;
    private final MessageHandlers handlers = new MessageHandlers();
    private final MessageRegistry registry = new MessageRegistry();

    {
        handlers.defaultHandler(new DefaultHandler());
    }

    public RpcServer(String ip, Integer port, Integer ioThreads, Integer workerThreads) {
        this.ip = ip;
        this.port = port;
        this.ioThreads = ioThreads;
        this.workerThreads = workerThreads;
    }

    private EventLoopGroup group;
    private MessageCollector collector;
    private Channel serverChannel;

    public RpcServer service(String type, Class<?> requestClass, IMessageHandler<?> handler) {
        registry.register(type, requestClass);
        handlers.register(type, handler);
        return this;
    }

    public void start() {
        ServerBootstrap bootstrap = new ServerBootstrap();
        group = new NioEventLoopGroup(ioThreads);
        bootstrap.group(group);
        collector = new MessageCollector(handlers, registry, workerThreads);
        MessageEncoder encoder = new MessageEncoder();
        bootstrap.channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) {
                        ch.pipeline().addLast(new ReadTimeoutHandler(60))
                                .addLast(new MessageDecoder())
                                .addLast(encoder)
                                .addLast(collector);
                    }
                });
        bootstrap.option(ChannelOption.SO_BACKLOG, 100)
                .option(ChannelOption.SO_REUSEADDR, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .childOption(ChannelOption.SO_KEEPALIVE, true);
        serverChannel = bootstrap.bind(this.ip, this.port).channel();
        log.info("server started @ [{}]:[{}]", ip, port);
    }

    public void stop() {
        // 先关闭服务端套接字
        serverChannel.close();
        // 再斩断消息来源，停止io线程池
        group.shutdownGracefully();
        // 最后停止业务线程
        collector.closeGracefully();
    }
}
