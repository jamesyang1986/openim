package com.smo.openim.protocol;

import org.jboss.netty.channel.Channel;

/**
 * Created by jianjunyang on 15/3/25.
 */
public class ClientSession {
    private String bussinessId;
    private String uid;
    private String clientIp;
    private Channel Channel;

    private long lastActiveTime;
    private ClientType type;
    private String lang;
    private int version;
    private SessionStatus status;


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public Channel getChannel() {
        return Channel;
    }

    public void setChannel(Channel channel) {
        Channel = channel;
    }

    public long getLastActiveTime() {
        return lastActiveTime;
    }

    public void setLastActiveTime(long lastActiveTime) {
        this.lastActiveTime = lastActiveTime;
    }

    public ClientType getType() {
        return type;
    }

    public void setType(ClientType type) {
        this.type = type;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public SessionStatus getStatus() {
        return status;
    }

    public void setStatus(SessionStatus status) {
        this.status = status;
    }

    public String getBussinessId() {
        return bussinessId;
    }

    public void setBussinessId(String bussinessId) {
        this.bussinessId = bussinessId;
    }
}
