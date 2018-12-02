package com.infoland.utils;

import com.jfinal.kit.LogKit;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class IpKit {

    /**
     * 获取当前网络ip
     *
     * @return
     */
    public static String localIp() {
        InetAddress ia = null;
        try {
            ia = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            LogKit.warn(ExceptionKit.getStackTraceAsString(e));
        }
        return ia.getHostAddress();
    }

    @Deprecated
    public static String requestIp(HttpServletRequest request) {
        return RequestKit.getIpAddress(request);
    }
}
