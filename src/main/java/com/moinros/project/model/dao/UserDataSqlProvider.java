package com.moinros.project.model.dao;

import com.moinros.project.model.pojo.UserData;
import org.apache.ibatis.jdbc.SQL;

/**
 *
 * @说明: 自动生成的与Dao层UserDataMapper接口对应的Provider类。
 * @作者: moinros  https://www.moinros.com
 *
 * 注释: 根据UserData生成与Mapper接口对应的动态SQL的Provider.Java类
 *
 * @Title: UserDataSqlProvider
 * @author: Administrator
 * @date 2020-01-16 01:50:20
 */
public class UserDataSqlProvider {

	private static final String SELECT_SQL =
			  "`ud`.`user_id` AS `userId`, "
			+ "`ud`.`user_name` AS `userName`, "
			+ "`ud`.`password` AS `password`, "
			+ "`ud`.`nick_name` AS `nickName`, "
			+ "`ud`.`gender` AS `gender`, "
			+ "`ud`.`image_path` AS `imagePath`, "
			+ "`ud`.`birthday` AS `birthday`, "
			+ "`ud`.`user_phone` AS `userPhone`, "
			+ "`ud`.`user_email` AS `userEmail`, "
			+ "`ud`.`user_intro` AS `userIntro`, "
			+ "`ud`.`client_ip` AS `clientIp`, "
			+ "`ud`.`login_time` AS `loginTime`, "
			+ "`ud`.`register_time` AS `registerTime`";

	public String selectAllUserDataSQL() {
		return new SQL(){
			{
				SELECT(SELECT_SQL);
				FROM("`user_data` AS `ud`");
			}
		}.toString();
	}

	public String selectUserByIdSQL() {
		return new SQL(){
			{
				SELECT(SELECT_SQL);
				FROM("`user_data` AS `ud`");
				WHERE("`ud`.`user_id` = #{userId}");
			}
		}.toString();
	}


	public String selectUserDataSQL(UserData userData) {
	    return new SQL(){
			{
				SELECT(SELECT_SQL);
				FROM("`user_data` AS `ud`");
				if (userData.getUserId() != null) {
					WHERE("`ud`.`user_id` = #{userId}");
				}
				if (userData.getUserName() != null) {
					WHERE("`ud`.`user_name` = #{userName}");
				}
				if (userData.getPassword() != null) {
					WHERE("`ud`.`password` = #{password}");
				}
				if (userData.getNickName() != null) {
					WHERE("`ud`.`nick_name` = #{nickName}");
				}
				if (userData.getUserPhone() != null) {
					WHERE("`ud`.`user_phone` = #{userPhone}");
				}
				if (userData.getUserEmail() != null) {
					WHERE("`ud`.`user_email` = #{userEmail}");
				}
			}
		}.toString();
	}

	public String insertUserDataSQL(UserData userData) {
	    return new SQL(){
			{
				INSERT_INTO("`user_data`");
				if (userData.getUserId() != null) {
					VALUES("`user_id`", "#{userId}");
				}
				if (userData.getUserName() != null) {
					VALUES("`user_name`", "#{userName}");
				}
				if (userData.getPassword() != null) {
					VALUES("`password`", "#{password}");
				}
				if (userData.getNickName() != null) {
					VALUES("`nick_name`", "#{nickName}");
				}
				if (userData.getGender() != null) {
					VALUES("`gender`", "#{gender}");
				}
				if (userData.getImagePath() != null) {
					VALUES("`image_path`", "#{imagePath}");
				}
				if (userData.getBirthday() != null) {
					VALUES("`birthday`", "#{birthday}");
				}
				if (userData.getUserPhone() != null) {
					VALUES("`user_phone`", "#{userPhone}");
				}
				if (userData.getUserEmail() != null) {
					VALUES("`user_email`", "#{userEmail}");
				}
				if (userData.getUserIntro() != null) {
					VALUES("`user_intro`", "#{userIntro}");
				}
				if (userData.getClientIp() != null) {
					VALUES("`client_ip`", "#{clientIp}");
				}
				if (userData.getLoginTime() != null) {
					VALUES("`login_time`", "#{loginTime}");
				}
				if (userData.getRegisterTime() != null) {
					VALUES("`register_time`", "#{registerTime}");
				}
			}
		}.toString();
	}

	public String updateUserDataSQL(UserData userData) {
	    return new SQL(){
			{
				UPDATE("user_data");
				if (userData.getNickName() != null) {
					SET("`nick_name` = #{nickName}");
				}
				if (userData.getGender() != null) {
					SET("`gender` = #{gender}");
				}
				if (userData.getImagePath() != null) {
					SET("`image_path` = #{imagePath}");
				}
				if (userData.getBirthday() != null) {
					SET("`birthday` = #{birthday}");
				}
				if (userData.getUserPhone() != null) {
					SET("`user_phone` = #{userPhone}");
				}
				if (userData.getUserEmail() != null) {
					SET("`user_email` = #{userEmail}");
				}
				if (userData.getUserIntro() != null) {
					SET("`user_intro` = #{userIntro}");
				}
				if (userData.getClientIp() != null) {
					SET("`client_ip` = #{clientIp}");
				}
				if (userData.getLoginTime() != null) {
					SET("`login_time` = #{loginTime}");
				}
				if (userData.getRegisterTime() != null) {
					SET("`register_time` = #{registerTime}");
				}
				WHERE("`ud`.`user_id` = #{userId}");
			}
		}.toString();
	}

}