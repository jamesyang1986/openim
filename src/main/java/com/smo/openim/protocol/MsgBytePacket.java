package com.smo.openim.protocol;

/**
 * Created by jianjunyang on 15/3/25.
 */
public class MsgBytePacket {
    private int version;
    private int crc;
    private byte[] header;
    private byte[] body;


    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getCrc() {
        return crc;
    }

    public void setCrc(int crc) {
        this.crc = crc;
    }

    public byte[] getHeader() {
        return header;
    }

    public void setHeader(byte[] header) {
        this.header = header;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }
}
