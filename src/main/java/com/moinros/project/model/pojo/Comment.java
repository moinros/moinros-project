package com.moinros.project.model.pojo;

import com.moinros.project.common.annotation.base64.Base64Mark;
import com.moinros.project.model.dto.User;

import java.io.Serializable;

/**
 * 类注释:
 *
 * @Title: Comment
 * @Author Administrator
 * @Blog https://www.moinros.com
 * @Date 2020-02-07 19:59:57
 * @Version 1.0
 */
@Base64Mark
public class Comment extends User implements Serializable {

    // 实现序列化接口
    private static final long serialVersionUID = 1L;

    /**
     * 注释：评论ID
     */
    private Integer id;

    /**
     * 注释：回复的评论ID
     */
    private Integer replyId;

    /**
     * 注释：评论所属页面标记
     */
    private String pageMark;

    /**
     * 注释：评论文本内容
     */
    @Base64Mark
    private String replyContent;

    /**
     * 注释：查看人数
     */
    private Integer visitorCount;

    /**
     * 注释：点赞人数
     */
    private Integer likeCount;

    /**
     * 注释：回复时间
     */
    private String replyTime;

    /**
     * 注释：评论状态
     */
    private Short commentState;

    // 构造方法
    public Comment() {
    }

    /**
     * 注释：获取 评论ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 注释：设置 评论ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 注释：获取 回复的评论ID
     */
    public Integer getReplyId() {
        return replyId;
    }

    /**
     * 注释：设置 回复的评论ID
     */
    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    /**
     * 注释：获取 评论用户ID
     */
    public Integer getUserId() {
        return super.getUserId();
    }

    /**
     * 注释：设置 评论用户ID
     */
    public void setUserId(Integer userId) {
        super.setUserId(userId);
    }

    /**
     * 注释：获取 评论所属页面标记
     */
    public String getPageMark() {
        return pageMark;
    }

    /**
     * 注释：设置 评论所属页面标记
     */
    public void setPageMark(String pageMark) {
        this.pageMark = pageMark;
    }

    /**
     * 注释：获取 评论文本内容
     */
    public String getReplyContent() {
        return replyContent;
    }

    /**
     * 注释：设置 评论文本内容
     */
    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    /**
     * 注释：获取 查看人数
     */
    public Integer getVisitorCount() {
        return visitorCount;
    }

    /**
     * 注释：设置 查看人数
     */
    public void setVisitorCount(Integer visitorCount) {
        this.visitorCount = visitorCount;
    }

    /**
     * 注释：获取 点赞人数
     */
    public Integer getLikeCount() {
        return likeCount;
    }

    /**
     * 注释：设置 点赞人数
     */
    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    /**
     * 注释：获取 回复时间
     */
    public String getReplyTime() {
        return replyTime;
    }

    /**
     * 注释：设置 回复时间
     */
    public void setReplyTime(String replyTime) {
        this.replyTime = replyTime;
    }

    /**
     * 注释：获取 评论状态
     */
    public Short getCommentState() {
        return commentState;
    }

    /**
     * 注释：设置 评论状态
     */
    public void setCommentState(Short commentState) {
        this.commentState = commentState;
    }
}