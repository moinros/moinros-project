package com.moinros.project.model.dao.other;

import org.apache.ibatis.jdbc.SQL;
import com.moinros.project.model.pojo.Friend;

/**
 *
 * @说明: 自动生成的与Dao层FriendMapper接口对应的Provider类。
 * @作者: moinros  https://www.moinros.com
 *
 * 注释: 根据Friend生成与Mapper接口对应的动态SQL的Provider.Java类
 *
 * @Title: FriendSqlProvider
 * @author: Administrator
 * @date 2020-01-16 01:46:41
 */
public class FriendSqlProvider {

	private static final String SELECT_SQL = 
			  "`f`.`fid` AS `fid`, "
			+ "`f`.`friend_name` AS `friendName`, "
			+ "`f`.`friend_link` AS `friendLink`, "
			+ "`f`.`friend_image` AS `friendImage`, "
			+ "`f`.`friend_intro` AS `friendIntro`";

	public String selectAllFriendSQL() {
		return new SQL(){
			{
				SELECT(SELECT_SQL);
				FROM("`friend` AS `f`");
			}
		}.toString();
	}

	public String selectFriendSQL(Friend friend) {
	    return new SQL(){
			{
				SELECT(SELECT_SQL);
				FROM("`friend` AS `f`");
				if (friend.getFid() != null) {
					WHERE("`f`.`fid` = #{fid}");
				}
				if (friend.getFriendName() != null) {
					WHERE("`f`.`friend_name` = #{friendName}");
				}
				if (friend.getFriendLink() != null) {
					WHERE("`f`.`friend_link` = #{friendLink}");
				}
				if (friend.getFriendImage() != null) {
					WHERE("`f`.`friend_image` = #{friendImage}");
				}
				if (friend.getFriendIntro() != null) {
					WHERE("`f`.`friend_intro` = #{friendIntro}");
				}
			}
		}.toString();
	}

	public String insertFriendSQL(Friend friend) {
	    return new SQL(){
			{
				INSERT_INTO("`friend`");
				if (friend.getFid() != null) {
					VALUES("`fid`", "#{fid}");
				}
				if (friend.getFriendName() != null) {
					VALUES("`friend_name`", "#{friendName}");
				}
				if (friend.getFriendLink() != null) {
					VALUES("`friend_link`", "#{friendLink}");
				}
				if (friend.getFriendImage() != null) {
					VALUES("`friend_image`", "#{friendImage}");
				}
				if (friend.getFriendIntro() != null) {
					VALUES("`friend_intro`", "#{friendIntro}");
				}
			}
		}.toString();
	}

	public String updateFriendSQL(Friend friend) {
	    return new SQL(){
			{
				UPDATE("friend");
				if (friend.getFid() != null) {
					SET("`fid` = #{fid}");
				}
				if (friend.getFriendName() != null) {
					SET("`friend_name` = #{friendName}");
				}
				if (friend.getFriendLink() != null) {
					SET("`friend_link` = #{friendLink}");
				}
				if (friend.getFriendImage() != null) {
					SET("`friend_image` = #{friendImage}");
				}
				if (friend.getFriendIntro() != null) {
					SET("`friend_intro` = #{friendIntro}");
				}
			}
		}.toString();
	}

}