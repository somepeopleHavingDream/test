package org.yangxin.test.netty.rpc.common;

import io.netty.channel.ChannelHandlerContext;

/**
 * @author yangxin
 * 2021/8/10 11:05
 */
@FunctionalInterface
public interface IMessageHandler<T> {

    /**
     * 处理消息
     *
     * @param channelHandlerContext 通道处理者上下文
     * @param requestId 请求Id
     * @param message 消息
     */
    void handle(ChannelHandlerContext channelHandlerContext, String requestId, T message);
}
