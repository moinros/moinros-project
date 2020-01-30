package com.moinros.project.model.pojo.system;

/**
 * 注释: 访问权限IP列表
 *
 * @Author moinros
 * @WebSite www.moinros.com
 * @Date 2020/1/16 14:51
 * @Verison 1.0
 */
public class Access {

    private String clientIp;
    private String accessNote;

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getAccessNote() {
        return accessNote;
    }

    public void setAccessNote(String accessNote) {
        this.accessNote = accessNote;
    }

}
