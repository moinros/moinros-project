package com.moinros.project.model.dao;

import com.moinros.project.model.pojo.UserData;
import org.apache.ibatis.annotations.*;

/**
 * @说明: 自动升成Dao层UserData的Mapper接口。
 * @作者: moinros  https://www.moinros.com
 * <p>
 * 注释: 根据JavaBean生成的mMapper接口
 * @Title: UserDataMapper
 * @author: Administrator
 * @date 2020-01-16 01:50:20
 */
@Mapper
public interface UserDataMapper {

    @SelectProvider(method = "selectUserByIdSQL", type = UserDataSqlProvider.class)
    UserData selectUserDataById(Integer userId);

    @Select(value = {"SELECT `user_id` AS `userId`, `user_name` AS `userName`, `password` AS `password` FROM `user_data` WHERE `user_name` = #{userName} OR `user_email` = #{userName} OR `user_phone` = #{userName}"})
    UserData selectPasswordByName(String userName);

    @SelectProvider(method = "selectUserDataSQL", type = UserDataSqlProvider.class)
    UserData selectUser(UserData userdata);

    @Select("SELECT `user_id` FROM `user_data` WHERE `user_name` = #{email} OR `user_email` = #{email}")
    Integer selectEmailEx(String email);

    @Select("SELECT `user_id` FROM `user_data` WHERE `nick_name` = #{nickName}")
    Integer selectNickNameEx(String nickName);

    /**
     * 注释: 根据邮箱查询user数据
     *
     * @param userEmail 邮箱
     * @return UserData
     */
    @Select(value = {"SELECT ud.`user_id` AS userId,ud.`nick_name` AS nickName,ud.`user_phone` userPhone,ud.`user_email` AS userEmail FROM user_data AS ud WHERE ud.`user_email` = #{userEmail}"})
    UserData getUserByEmail(String userEmail);

    /**
     * 注释: 查询数据库中ID最大的值
     *
     * @return Integer ID MAX
     */
    @Select(value = {"SELECT MAX(`user_data`.`user_id`) FROM `user_data`"})
    Integer getUserIdValueIsMax();

    /**
     * 注释:向user_data表中添加数据
     *
     * @param userData userdata
     * @return Integer
     */
    @InsertProvider(method = "insertUserDataSQL", type = UserDataSqlProvider.class)
    Integer insertUserData(UserData userdata);

    /**
     * 注释: 修改userdata
     *
     * @param userdata userdata
     * @return Integer
     */
    @UpdateProvider(method = "updateUserDataSQL", type = UserDataSqlProvider.class)
    Integer updateUserData(UserData userdata);

}