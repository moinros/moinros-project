package com.moinros.project.tool.web;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 类注释: 包含一些常用的与web工具类
 *
 * @Author moinros
 * @Date 2019年12月21日 下午5:49:14
 * @Version 1.0
 */
public final class NetworkUtil {

    private HttpServletRequest request = null;
    private UserAgent ua = null;
    private String header = null;

    public NetworkUtil(HttpServletRequest request) {
        this.request = request;

    }

    /**
     * 注释: 获取访问者IP</p>
     * 在一般情况下使用Request.getRemoteAddr()即可；但是经过nginx等反向代理软件后，这个方法会失效。
     *
     * @return String 请求主机的IP地址
     * @throws IOException 查找IP地址时可能出现的异常
     */
    public String getIpAddress() throws IOException {
        // 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 获取来访者的浏览器版本
     */
    public String getBrowserInfo() {
        if (ua == null) {
            getUserAgent();
        }
        Browser browser = ua.getBrowser();
        return browser.getName();
    }

    /**
     * 获取系统版本信息
     */
    public String getClientOS() {
        if (ua == null) {
            getUserAgent();
        }
        OperatingSystem os = ua.getOperatingSystem();
        return os.getName();
    }

    public String getHeader() {
        return header;
    }

    private void getUserAgent() {
        this.header = request.getHeader("User-Agent");
        this.ua = UserAgent.parseUserAgentString(header);
    }

}