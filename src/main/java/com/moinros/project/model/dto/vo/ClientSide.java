package com.moinros.project.model.dto.vo;

/**
 * 注释: 客户端数据
 *
 * @Author moinros
 * @WebSite www.moinros.com
 * @Date 2020/2/7 14:39
 * @Verison 1.0
 */
public class ClientSide {

    // 客户端IP地址
    private String clientIp;

    // 客户端系统版本信息
    private String clientOS;

    // 客户端浏览器信息
    private String browserInfo;

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getClientOS() {
        return clientOS;
    }

    public void setClientOS(String clientOS) {
        this.clientOS = clientOS;
    }

    public String getBrowserInfo() {
        return browserInfo;
    }

    public void setBrowserInfo(String browserInfo) {
        this.browserInfo = browserInfo;
    }
}
