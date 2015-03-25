package com.smo.openim.utils;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * Created by jianjunyang on 15/3/25.
 */
public class InetAddressUtils {
    private static final Logger LOG = Logger.getLogger(InetAddressUtils.class);

    private static String HOST_NAME;
    private static String LOCAL_ADDRESS;
    private static String INTERNET_ADDRESS;
    private static String[] INTERFACE_NAMES = new String[]{"bond", "em", "eth"};

    public static String getHostName() {
        if (HOST_NAME != null) {
            return HOST_NAME;
        }

        try {
            HOST_NAME = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            LOG.error("Get hostname error!", e);
        }

        return HOST_NAME;
    }

    public static String getInternetAddress() {
        if (INTERNET_ADDRESS != null) {
            return INTERNET_ADDRESS;
        }

        try {
            Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface ni = netInterfaces.nextElement();
                Enumeration<InetAddress> inetAddresses = ni.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = inetAddresses.nextElement();
                    if (inetAddress instanceof Inet6Address) {
                        continue;
                    }
                    if (inetAddress.isLoopbackAddress()) {
                        continue;
                    }

                    if (!inetAddress.isSiteLocalAddress() && !inetAddress.isLinkLocalAddress()) {
                        INTERNET_ADDRESS = inetAddress.getHostAddress();
                    }
                }
            }
        } catch (Exception e) {
            LOG.error("Get internet address error!", e);

        }

        return INTERNET_ADDRESS;
    }

    public static String getLocalAddress() {
        if (LOCAL_ADDRESS != null) {
            return LOCAL_ADDRESS;
        }

        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                // 获取网卡名称
                String name = networkInterface.getName();
                for (String interfaceName : INTERFACE_NAMES) {
                    if (StringUtils.startsWith(name, interfaceName)) {
                        LOCAL_ADDRESS = getHostAddress(networkInterface);
                        if (LOCAL_ADDRESS != null) {
                            return LOCAL_ADDRESS;
                        }
                    }
                }
            }

            // 如果通过名称没有获得到有效的地址。则不使用名称进行循环遍历。
            networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                LOCAL_ADDRESS = getHostAddress(networkInterface);
                if (LOCAL_ADDRESS != null) {
                    return LOCAL_ADDRESS;
                }
            }

            // 无法获得到有效的网络地址。
            if (StringUtils.isBlank(LOCAL_ADDRESS)) {
                LOCAL_ADDRESS = "127.0.0.1";
                throw new UnknownHostException("Can not find a valid ip ," + "for net interface ["
                        + ArrayUtils.toString(INTERFACE_NAMES) + "]");
            }

        } catch (Throwable t) {

        }

        return LOCAL_ADDRESS;
    }

    public static void main(String[] args) {
        System.out.println(getHostName());
        System.out.println(getLocalAddress());
        System.out.println(getInternetAddress());
    }

    /**
     * 获得网卡上的主机地址。
     */
    private static String getHostAddress(NetworkInterface networkInterface) {
        Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
        while (inetAddresses.hasMoreElements()) {
            InetAddress inetAddress = inetAddresses.nextElement();
            if (inetAddress instanceof Inet6Address) { // 忽略IPv6地址。
                continue;
            }
            if (inetAddress.isLoopbackAddress()) { // 忽略Loopback地址。
                continue;
            }
            if (!inetAddress.getHostAddress().startsWith("10.")
                    && !inetAddress.getHostAddress().startsWith("172.")) {
                continue;
            }

            if (inetAddress.isSiteLocalAddress() || inetAddress.isLinkLocalAddress()) {
                return inetAddress.getHostAddress();
            }
        }

        return null;
    }

    private InetAddressUtils() {
    }

}

