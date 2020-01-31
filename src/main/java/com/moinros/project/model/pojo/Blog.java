package com.moinros.project.model.pojo;

import com.moinros.project.common.annotation.base64.Base64Mark;

import java.io.Serializable;

/**
 * 注释: 博客数据实体
 *
 * @Author moinros
 * @WebSite www.moinros.com
 * @Date 2020/1/16 1:21
 * @Verison 1.0
 */
@Base64Mark
public class Blog implements Serializable {

    // 实现序列化接口
    private static final long serialVersionUID = 1L;

    /**
     * 注释：博客ID
     */
    private Integer blogId;

    /**
     * 注释：博客标题
     */
    private String blogSubject;

    /**
     * 注释：博客内容
     */
    @Base64Mark
    private String blogContent;

    /**
     * 注释：博客标签
     */
    private String blogTags;
    private String[] tagNames;
    private String[] tagMarks;

    /**
     * 注释：博客封面
     */
    private String blogCover;

    /**
     * 注释：简介
     */
    @Base64Mark
    private String blogIntro;

    /**
     * 注释：博客编辑时间
     */
    private String editTime;

    /**
     * 注释：最后一次修改时间
     */
    private String updateTime;

    /**
     * 注释：博客状态
     */
    private Integer blogState;

    /**
     * 注释：是否置顶[默认不置顶]
     */
    private Boolean isTop;

    /**
     * 注释：查看人数
     */
    private Integer visitorCount;

    /**
     * 注释：喜欢人数
     */
    private Integer likeCount;

    /**
     * 注释：
     */
    private Integer blogSub;

    // 构造方法
    public Blog() {
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public String getBlogSubject() {
        return blogSubject;
    }

    public void setBlogSubject(String blogSubject) {
        this.blogSubject = blogSubject;
    }

    public String getBlogContent() {
        return blogContent;
    }

    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent;
    }

    public String getBlogTags() {
        return blogTags;
    }

    public void setBlogTags(String blogTags) {
        this.blogTags = blogTags;
        this.tagMarks = blogTags.split(",");
    }

    public String[] getTagNames() {
        return tagNames;
    }

    public void setTagNames(String[] tagNames) {
       this.tagNames = tagNames;
    }

    public String[] getTagMarks() {
        return tagMarks;
    }

    public void setTagMarks(String[] tagMarks) {
        this.tagMarks = tagMarks;
    }

    public String getBlogCover() {
        return blogCover;
    }

    public void setBlogCover(String blogCover) {
        this.blogCover = blogCover;
    }

    public String getBlogIntro() {
        return blogIntro;
    }

    public void setBlogIntro(String blogIntro) {
        this.blogIntro = blogIntro;
    }

    public String getEditTime() {
        return editTime;
    }

    public void setEditTime(String editTime) {
        this.editTime = editTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getBlogState() {
        return blogState;
    }

    public void setBlogState(Integer blogState) {
        this.blogState = blogState;
    }

    public Boolean getIsTop() {
        return isTop;
    }

    public void setIsTop(Boolean top) {
        isTop = top;
    }

    public Integer getVisitorCount() {
        return visitorCount;
    }

    public void setVisitorCount(Integer visitorCount) {
        this.visitorCount = visitorCount;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getBlogSub() {
        return blogSub;
    }

    public void setBlogSub(Integer blogSub) {
        this.blogSub = blogSub;
    }
}