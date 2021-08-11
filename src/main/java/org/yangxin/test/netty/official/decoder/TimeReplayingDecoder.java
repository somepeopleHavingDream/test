package org.yangxin.test.netty.official.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import org.yangxin.test.netty.official.UnixTime;

import java.util.List;

/**
 * @author yangxin
 * 2021/8/4 10:45
 */
public class TimeReplayingDecoder extends ReplayingDecoder<TimeReplayingDecoder.TimeState> {

    public enum TimeState {

        /**
         * 实际值
         */
        VALUE
    }

    public TimeReplayingDecoder() {
        super(TimeState.VALUE);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (state() == TimeState.VALUE) {
            out.add(new UnixTime(in.readUnsignedInt()));
        } else {
            throw new IllegalStateException("invalid state: " + state());
        }
    }
}
