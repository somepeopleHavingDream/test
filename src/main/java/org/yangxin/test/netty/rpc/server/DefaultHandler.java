package org.yangxin.test.netty.rpc.server;

import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.yangxin.test.netty.rpc.common.IMessageHandler;
import org.yangxin.test.netty.rpc.common.MessageInput;

/**
 * @author yangxin
 * 2021/8/10 11:04
 */
@Slf4j
public class DefaultHandler implements IMessageHandler<MessageInput> {

    @Override
    public void handle(ChannelHandlerContext channelHandlerContext, String requestId, MessageInput message) {
        log.error("unrecognized message type [{}] comes", message.getType());
        channelHandlerContext.close();
    }
}
