package com.moinros.project.service;

import com.moinros.project.model.dto.ResultValue;
import com.moinros.project.model.dto.enums.UserServiceState;
import com.moinros.project.model.dto.vo.ClientSide;
import com.moinros.project.model.pojo.UserData;

/**
 * 注释:
 */
public interface UserService {

    /**
     * 注释:登录
     *
     * @param username 登录账号
     * @param password 登录密码
     * @param client   客户端信息
     * @return ResultValue 返回处理结果
     */
    ResultValue<UserServiceState, UserData> login(String username, String password, ClientSide client);

    /**
     * 注释: 用户账号注册
     *
     * @param nickName  用户昵称
     * @param userEmail 用户邮箱
     * @param password  登录密码
     * @return ResultValue 返回处理结果
     */
    ResultValue<UserServiceState, UserData> register(String nickName, String userEmail, String password);

    /**
     * 注释: 用户账号注册
     *
     * @param user 用户数据
     * @return [ResultValue] 返回处理结果
     */
    ResultValue<UserServiceState, UserData> register(UserData user);

    /**
     * 注释: 查询用户邮箱是否存在
     *
     * @param userEmail 用户邮箱
     * @return boolean 存在返回true,不存在返回false
     */
    boolean mailboxExist(String userEmail);

    /**
     * 注释:查询用户昵称是否存在
     *
     * @param nickname 用户昵称
     * @return boolean 存在返回true,不存在返回false
     */
    boolean nicknameExist(String nickname);

    /**
     * 注释:根据UserID查询User数据
     *
     * @param userId UserID
     * @return UserData 成功返回查询到的User数据,失败返回null
     */
    UserData findUserById(Integer userId);

    /**
     * 注释: 修改User数据
     *
     * @param userdata USER数据
     * @return ResultValue 返回处理结果
     */
    ResultValue<UserServiceState, UserData> modifyUserData(UserData userdata);

    /**
     * 注释: 根据用户绑定的邮箱重置密码
     *
     * @param userEmail 用户邮箱
     * @param password  密码
     * @return boolean ResultValue 返回处理结果
     */
    ResultValue<UserServiceState, String> resetPassword(String userEmail, String password);

}
