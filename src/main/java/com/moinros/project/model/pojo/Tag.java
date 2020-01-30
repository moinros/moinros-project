package com.moinros.project.model.pojo;

import java.io.Serializable;

/**
 * 类注释:
 *
 * @Title: SysTag
 * @Author Administrator
 * @Blog https://www.moinros.com
 * @Date 2020-01-16 01:22:51
 * @Version 1.0
 */
public class Tag implements Serializable {

    // 实现序列化接口
    private static final long serialVersionUID = 1L;

    /**
     * 注释：标签Id
     */
    private Integer tid;

    /**
     * 注释：主键,用于标记和区分各个标签
     */
    private String tagMark;

    /**
     * 注释：标签名字
     */
    private String tagName;

    /**
     * 注释：标签说明
     */
    private String tagNote;

    /**
     * 注释：是否显示,默认1(显示)
     */
    private Boolean isShow;

    // 构造方法
    public Tag() {
    }

    /**
     * 注释：获取 标签Id
     */
    public Integer getTid() {
        return tid;
    }

    /**
     * 注释：设置 标签Id
     */
    public void setTid(Integer tid) {
        this.tid = tid;
    }

    /**
     * 注释：获取 主键,用于标记和区分各个标签
     */
    public String getTagMark() {
        return tagMark;
    }

    /**
     * 注释：设置 主键,用于标记和区分各个标签
     */
    public void setTagMark(String tagMark) {
        this.tagMark = tagMark;
    }

    /**
     * 注释：获取 标签名字
     */
    public String getTagName() {
        return tagName;
    }

    /**
     * 注释：设置 标签名字
     */
    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    /**
     * 注释：获取 标签说明
     */
    public String getTagNote() {
        return tagNote;
    }

    /**
     * 注释：设置 标签说明
     */
    public void setTagNote(String tagNote) {
        this.tagNote = tagNote;
    }

    /**
     * 注释：获取 是否显示,默认1(显示)
     */
    public Boolean getIsShow() {
        return isShow;
    }

    /**
     * 注释：设置 是否显示,默认1(显示)
     */
    public void setIsShow(Boolean isShow) {
        this.isShow = isShow;
    }
}