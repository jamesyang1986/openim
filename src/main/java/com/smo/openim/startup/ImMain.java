package com.smo.openim.startup;


import com.smo.openim.connector.ImTcpServer;
import org.apache.log4j.Logger;

/**
 * Created by jianjunyang on 15/3/25.
 */
public class ImMain {
    private static Logger logger = Logger.getLogger(ImMain.class);

    public static void main(String[] args) {
        if (args == null || args.length < 1) {
            logger.error("USAGE:port");
            System.exit(-1);
        }

        Integer port = Integer.parseInt(args[0]);
        ImTcpServer imServer = new ImTcpServer(port);
        imServer.start();

        logger.info(String.format("the server is start on {0}", port));

    }
}
