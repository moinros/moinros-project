package com.moinros.project.model.dto.vo;

import com.moinros.project.tool.util.date.DateInfo;

/**
 * 注释:
 *
 * @Author moinros
 * @WebSite www.moinros.com
 * @Date 2020/2/3 22:26
 * @Verison 1.0
 */
public class Article {

    private Integer id;
    private String subject;
    private DateInfo dateInfo;
    private String stamp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public DateInfo getDateInfo() {
        return dateInfo;
    }

    public void setDateInfo(DateInfo dateInfo) {
        this.dateInfo = dateInfo;
    }

    public String getStamp() {
        return stamp;
    }

    public void setStamp(String stamp) {
        this.stamp = stamp;
    }
}
