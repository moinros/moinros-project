package com.moinros.project.model.dao.other;

import com.moinros.project.model.pojo.Comment;
import org.apache.ibatis.jdbc.SQL;

/**
 *
 * @说明: 自动生成的与Dao层CommentMapper接口对应的Provider类。
 * @作者: moinros  https://www.moinros.com
 *
 * 注释: 根据Comment生成与Mapper接口对应的动态SQL的Provider.Java类
 *
 * @Title: CommentSqlProvider
 * @author: Administrator
 * @date 2020-02-07 19:59:57
 */
public class CommentSqlProvider {

	private static final String SELECT_SQL =
			  "`c`.`id` AS `id`, "
			+ "`c`.`reply_id` AS `replyId`, "
			+ "`c`.`page_mark` AS `pageMark`, "
			+ "`c`.`reply_content` AS `replyContent`, "
			+ "`c`.`visitor_count` AS `visitorCount`, "
			+ "`c`.`like_count` AS `likeCount`, "
			+ "`c`.`reply_time` AS `replyTime`, "
			+ "`c`.`comment_state` AS `commentState`, "
			+ "`ud`.`user_id` AS `userId`, "
			+ "`ud`.`nick_name` AS `nickName`, "
			+ "`ud`.`image_path` AS `imagePath` ";

	public String selectCommentByMarkSQL() {
		return new SQL(){
			{
				SELECT(SELECT_SQL);
				FROM("`comment` AS `c`");
				FROM("`user_data` AS `ud`");
				WHERE("`c`.`user_id` = `ud`.`user_id`");
				WHERE("`c`.`page_mark` = #{pageMark}");
				WHERE("`c`.`comment_state` = 1");
				ORDER_BY("`c`.`reply_time` DESC");
			}
		}.toString();
	}

	public String selectCommentByReplySQL() {
		return new SQL(){
			{
				SELECT(SELECT_SQL);
				FROM("`comment` AS `c`");
				FROM("`user_data` AS `ud`");
				WHERE("`c`.`user_id` = `ud`.`user_id`");
				WHERE("`c`.`reply_id` = #{replyId}");
				WHERE("`c`.`comment_state` = 1");
				ORDER_BY("`c`.`reply_time` DESC");
			}
		}.toString();
	}

	public String selectCommentByIdSQL() {
		return new SQL(){
			{
				SELECT(SELECT_SQL);
				FROM("`comment` AS `c`");
				FROM("`user_data` AS `ud`");
				WHERE("`c`.`user_id` = `ud`.`user_id`");
				WHERE("`c`.`id` = #{id}");
				WHERE("`c`.`comment_state` = 1");
				ORDER_BY("`c`.`reply_time` DESC");
			}
		}.toString();
	}


	public String selectCommentSQL(Comment comment) {
	    return new SQL(){
			{
				SELECT(SELECT_SQL);
				FROM("`comment` AS `c`");
				FROM("`user_data` AS `ud`");
				WHERE("`c`.`user_id` = `ud`.`user_id`");
				if (comment.getId() != null) {
					WHERE("`c`.`id` = #{id}");
				}
				if (comment.getReplyId() != null) {
					WHERE("`c`.`reply_id` = #{replyId}");
				}
				if (comment.getUserId() != null) {
					WHERE("`c`.`user_id` = #{userId}");
				}
				if (comment.getPageMark() != null) {
					WHERE("`c`.`page_mark` = #{pageMark}");
				}
				WHERE("`c`.`comment_state` = 1");
				ORDER_BY("`c`.`reply_time` DESC");
			}
		}.toString();
	}

	public String insertCommentSQL(Comment comment) {
	    return new SQL(){
			{
				INSERT_INTO("`comment`");
				if (comment.getReplyId() != null) {
					VALUES("`reply_id`", "#{replyId}");
				}
				if (comment.getUserId() != null) {
					VALUES("`user_id`", "#{userId}");
				}
				if (comment.getPageMark() != null) {
					VALUES("`page_mark`", "#{pageMark}");
				}
				if (comment.getReplyContent() != null) {
					VALUES("`reply_content`", "#{replyContent}");
				}
				if (comment.getVisitorCount() != null) {
					VALUES("`visitor_count`", "#{visitorCount}");
				}
				if (comment.getLikeCount() != null) {
					VALUES("`like_count`", "#{likeCount}");
				}
				if (comment.getReplyTime() != null) {
					VALUES("`reply_time`", "#{replyTime}");
				}
			}
		}.toString();
	}

	public String updateCommentSQL(Comment comment) {
	    return new SQL(){
			{
				UPDATE("comment");
				if (comment.getReplyId() != null) {
					SET("`reply_id` = #{replyId}");
				}
				if (comment.getUserId() != null) {
					SET("`user_id` = #{userId}");
				}
				if (comment.getPageMark() != null) {
					SET("`page_mark` = #{pageMark}");
				}
				if (comment.getReplyContent() != null) {
					SET("`reply_content` = #{replyContent}");
				}
				if (comment.getVisitorCount() != null) {
					SET("`visitor_count` = #{visitorCount}");
				}
				if (comment.getLikeCount() != null) {
					SET("`like_count` = #{likeCount}");
				}
				WHERE("`c`.`id` = #{id}");
			}
		}.toString();
	}

}