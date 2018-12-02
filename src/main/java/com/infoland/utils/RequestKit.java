package com.infoland.utils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class RequestKit {
    static String[] mobileAgents = new String[]{"iphone", "android", "phone", "mobile", "wap", "netfront", "java",
            "opera mobi", "opera mini", "ucweb", "windows ce", "symbian", "series", "webos", "sony", "blackberry",
            "dopod", "nokia", "samsung", "palmsource", "xda", "pieplus", "meizu", "midp", "cldc", "motorola", "foma",
            "docomo", "up.browser", "up.link", "blazer", "helio", "hosin", "huawei", "novarra", "coolpad", "webos",
            "techfaith", "palmsource", "alcatel", "amoi", "ktouch", "nexian", "ericsson", "philips", "sagem", "wellcom",
            "bunjalloo", "maui", "smartphone", "iemobile", "spice", "bird", "zte-", "longcos", "pantech", "gionee",
            "portalmmm", "jig browser", "hiptop", "benq", "haier", "^lct", "320x320", "240x320", "176x220", "w3c ",
            "acs-", "alav", "alca", "amoi", "audi", "avan", "benq", "bird", "blac", "blaz", "brew", "cell", "cldc",
            "cmd-", "dang", "doco", "eric", "hipt", "inno", "ipaq", "java", "jigs", "kddi", "keji", "leno", "lg-c",
            "lg-d", "lg-g", "lge-", "maui", "maxo", "midp", "mits", "mmef", "mobi", "mot-", "moto", "mwbp", "nec-",
            "newt", "noki", "oper", "palm", "pana", "pant", "phil", "play", "port", "prox", "qwap", "sage", "sams",
            "sany", "sch-", "sec-", "send", "seri", "sgh-", "shar", "sie-", "siem", "smal", "smar", "sony", "sph-",
            "symb", "t-mo", "teli", "tim-", "tsm-", "upg1", "upsi", "vk-v", "voda", "wap-", "wapa", "wapi", "wapp",
            "wapr", "webc", "winw", "winw", "xda", "xda-", "googlebot-mobile"};

    public RequestKit() {
    }

    public static boolean isAjaxRequest(HttpServletRequest request) {
        String header = request.getHeader("X-Requested-With");
        return "XMLHttpRequest".equalsIgnoreCase(header);
    }

    public static boolean isMultipartRequest(HttpServletRequest request) {
        String contentType = request.getContentType();
        return contentType != null && contentType.toLowerCase().contains("multipart");
    }

    public static boolean isApplicationJsonRequest(HttpServletRequest request) {
        String contentType = request.getContentType();
        return contentType != null && contentType.toLowerCase().contains("application/json");
    }

    public static boolean isMoblieBrowser(HttpServletRequest request) {
        String ua = request.getHeader("User-Agent");
        if (ua == null) {
            return false;
        } else {
            ua = ua.toLowerCase();
            String[] var2 = mobileAgents;
            int var3 = var2.length;

            for (int var4 = 0; var4 < var3; ++var4) {
                String mobileAgent = var2[var4];
                if (ua.contains(mobileAgent)) {
                    return true;
                }
            }

            return false;
        }
    }

    public static boolean isWechatBrowser(HttpServletRequest request) {
        String ua = request.getHeader("User-Agent");
        if (ua == null) {
            return false;
        } else {
            ua = ua.toLowerCase();
            return ua.indexOf("micromessenger") > 0;
        }
    }

    public static boolean isWechatPcBrowser(HttpServletRequest request) {
        String ua = request.getHeader("User-Agent");
        if (ua == null) {
            return false;
        } else {
            ua = ua.toLowerCase();
            return ua.indexOf("windowswechat") > 0;
        }
    }

    public static boolean isIEBrowser(HttpServletRequest request) {
        String ua = request.getHeader("User-Agent");
        if (ua == null) {
            return false;
        } else {
            ua = ua.toLowerCase();
            if (ua.indexOf("msie") > 0) {
                return true;
            } else {
                return ua.indexOf("gecko") > 0 && ua.indexOf("rv:11") > 0;
            }
        }
    }

    public static String getUserAgent(HttpServletRequest request) {
        return request.getHeader("User-Agent");
    }

    public static String getReferer(HttpServletRequest request) {
        return request.getHeader("Referer");
    }

    public static String getIpAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
                // 根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress = inet.getHostAddress();
            }
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
            // = 15
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }
}
