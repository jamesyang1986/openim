package com.smo.openim.protocol;

import com.sun.deploy.util.SessionState;

/**
 * Created by jianjunyang on 15/3/25.
 */
public enum ClientType {
    IOS("ios"), ANDROID("android"), WP("wp"), EIOS("eios"), UNKNOWN("unknown");

    private String name;

    private ClientType(String name) {
        this.name = name;
    }

    public ClientType valueOfType(String name) {
        for (ClientType type : ClientType.values()) {
            if (type.name.equals(name)) {
                return type;
            }
        }
        return null;
    }

}
