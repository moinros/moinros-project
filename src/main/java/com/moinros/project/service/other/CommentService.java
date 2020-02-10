package com.moinros.project.service.other;

import com.moinros.project.model.pojo.Comment;

import java.util.List;

/**
 * 注释:
 */
public interface CommentService {

    /**
     * 查询指定页面的全部评论
     *
     * @param mark 页面标记
     * @return List<Comment>
     */
    List<Comment> findListByMark(String mark);

    /**
     * 查询指定评论下的全部评论
     *
     * @param replyId 评论ID
     * @return List<Comment>
     */
    List<Comment> findCommentByReply(Integer replyId);

    /**
     * 添加新的评论
     *
     * @param comment 评论数据
     * @return boolean
     */
    boolean saveComment(Comment comment);

    /**
     * 修改评论
     *
     * @param comment
     * @return boolean
     */
    boolean modifyComment(Comment comment);

}
