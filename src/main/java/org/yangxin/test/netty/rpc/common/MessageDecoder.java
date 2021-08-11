package org.yangxin.test.netty.rpc.common;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.ReplayingDecoder;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author yangxin
 * 2021/8/10 17:17
 */
@SuppressWarnings("AlibabaUndefineMagicConstant")
public class MessageDecoder extends ReplayingDecoder<MessageInput> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        String requestId = readStr(in);
        String type = readStr(in);
        String content = readStr(in);

        out.add(new MessageInput(type, requestId, content));
    }

    private String readStr(ByteBuf in) {
        int length = in.readInt();
        if (length < 0 || length > (1 << 20)) {
            throw new DecoderException("string too long len=" + length);
        }

        byte[] bytes = new byte[length];
        in.readBytes(bytes);
        return new String(bytes, StandardCharsets.UTF_8);
    }
}
