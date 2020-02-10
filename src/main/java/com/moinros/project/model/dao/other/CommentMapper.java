package com.moinros.project.model.dao.other;

import com.moinros.project.model.pojo.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @说明: 自动升成Dao层Comment的Mapper接口。
 * @作者: moinros  https://www.moinros.com
 * <p>
 * 注释: 根据JavaBean生成的mMapper接口
 * @Title: CommentMapper
 * @author: Administrator
 * @date 2020-02-07 19:59:57
 */
@Mapper
public interface CommentMapper {

    @SelectProvider(method = "selectCommentByMarkSQL", type = CommentSqlProvider.class)
    List<Comment> selectCommentByMark(String mark);

    @SelectProvider(method = "selectCommentByReplySQL", type = CommentSqlProvider.class)
    List<Comment> selectCommentByReply(Integer replyId);

    @SelectProvider(method = "selectCommentByIdSQL", type = CommentSqlProvider.class)
    Comment selectCommentById(Integer id);

    @SelectProvider(method = "selectCommentSQL", type = CommentSqlProvider.class)
    List<Comment> selectComment(Comment comment);

    @InsertProvider(method = "insertCommentSQL", type = CommentSqlProvider.class)
    Integer insertComment(Comment comment);

    @UpdateProvider(method = "updateCommentSQL", type = CommentSqlProvider.class)
    Integer updateComment(Comment comment);

    @Update("UPDATE `comment` SET `visitor_count` = (`visitor_count` + 1) WHERE `id` = #{id}")
    Integer visitorCountIncrease(int id);

}