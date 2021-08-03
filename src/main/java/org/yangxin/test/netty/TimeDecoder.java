package org.yangxin.test.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author yangxin
 * 2021/8/3 16:48
 */
@SuppressWarnings("AlibabaUndefineMagicConstant")
public class TimeDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if (byteBuf.readableBytes() < 4) {
            return;
        }

        list.add(new UnixTime(byteBuf.readUnsignedInt()));
    }
}
