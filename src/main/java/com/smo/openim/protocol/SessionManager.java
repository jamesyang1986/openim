package com.smo.openim.protocol;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by jianjunyang on 15/3/25.
 */
public class SessionManager {
    private static Map<String, ClientSession> sessionMap = new ConcurrentHashMap<String, ClientSession>(100);


    public static void addClientSession(ClientSession session) {
        if (!sessionMap.containsKey(session.getUid())) {
            sessionMap.put(session.getUid(), session);
        } else {

        }

    }

    public static void removeClientSession(ClientSession session) {
        sessionMap.remove(session);
    }
}
