package com.smo.openim.connector;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder;

/**
 * Created by jianjunyang on 15/3/25.
 */
public class ImTcpDecoder extends LengthFieldBasedFrameDecoder {
    public ImTcpDecoder() {
        super(Integer.MAX_VALUE, 4, 4, 0, 0);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Object decode(ChannelHandlerContext ctx, Channel channel, ChannelBuffer buffer) throws Exception {
        ChannelBuffer frame = (ChannelBuffer) super.decode(ctx, channel, buffer);
        if (frame == null) {
            return null;
        }

        Byte version = frame.readByte();
        Byte crc = frame.readByte();
        Byte tmp = frame.readByte();
        frame.readByte();


        return null;

    }


}