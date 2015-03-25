package com.smo.openim.connector;


import com.smo.openim.utils.InetAddressUtils;
import com.smo.openim.utils.NamedThreadFactory;
import org.apache.log4j.Logger;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.group.ChannelGroup;
import org.jboss.netty.channel.group.DefaultChannelGroup;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by jianjunyang on 15/3/25.
 */
public class ImTcpServer {

    private Logger logger = Logger.getLogger(ImTcpServer.class);

    private ServerBootstrap bootstrap;
    private ChannelGroup channelGroup;

    private int port;

    public ImTcpServer(int port) {
        this.port = port;
    }


    public void start() {
        ExecutorService boss = Executors.newCachedThreadPool(new NamedThreadFactory("ImServer-Boss"));
        ExecutorService worker = Executors.newCachedThreadPool(new NamedThreadFactory("ImServer-Worker"));
        ChannelFactory channelFactory = new NioServerSocketChannelFactory(boss, worker);
        bootstrap = new ServerBootstrap(channelFactory);

        bootstrap.setOption("child.tcpNoDelay", true);
        bootstrap.setOption("child.keepAlive", true);
        bootstrap.setOption("sendBufferSize", 1024 * 1024);
        bootstrap.setOption("receiveBufferSize", 1024 * 1024);
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {

            @Override
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeline = Channels.pipeline();

                pipeline.addLast("encode", new ImTcpEncoder());
                pipeline.addLast("decode", new ImTcpDecoder());
                pipeline.addLast("handler", null);

                return pipeline;
            }
        });

        SocketAddress socketAddress = new InetSocketAddress(InetAddressUtils.getLocalAddress(), port);
        logger.warn("tcp server bind address " + socketAddress);

        Channel serverChannel = bootstrap.bind(socketAddress);
        channelGroup = new DefaultChannelGroup("ImServer-ChannelGroup");
        channelGroup.add(serverChannel);

        logger.info("Open IM TCP Server started at " + port + ".");

    }
}
