package com.smo.openim.connector;

import org.apache.log4j.Logger;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;


/**
 * Created by jianjunyang on 15/3/25.
 */
public class ImTcpHandler extends SimpleChannelHandler {
    private Logger LOG = Logger.getLogger(ImTcpHandler.class);

    @Override
    public void messageReceived(
            ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        ctx.sendUpstream(e);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        LOG.error("Exception caught on server side! Close this connection now.", e.getCause());
        ctx.getChannel().close();
    }
}
