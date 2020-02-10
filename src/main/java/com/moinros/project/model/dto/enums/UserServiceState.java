package com.moinros.project.model.dto.enums;

/**
 * 注释:
 *
 * @Author moinros
 * @WebSite www.moinros.com
 * @Date 2020/2/7 14:36
 * @Verison 1.0
 */
public enum UserServiceState {

    /**
     * SAVE_SUCCESS : 保存成功
     */
    SAVE_SUCCESS("保存成功！"),

    /**
     * SAVE_ERROR : 保存失败
     */
    SAVE_ERROR("保存失败！"),

    /**
     * NO_FOUND : 用户不存在
     */
    NO_FOUND("用户不存在！"),

    /**
     * IS_EXIST : 用户已存在
     */
    IS_EXIST("用户已存在！"),

    /**
     * DATA_INCOMPLETE : 数据填写不完整
     */
    DATA_INCOMPLETE("信息填写不完整！"),

    /**
     * VALUE_SAME : 值相同
     */
    VALUE_SAME("你没有做出任何更改哦~"),

    /**
     * LOGIN_SUCCESS : 登录成功
     */
    LOGIN_SUCCESS("登录成功！"),

    /**
     * LOGIN_ERROR : 登录失败
     */
    LOGIN_ERROR("登录失败！"),

    /**
     * LOGIN_PASSWORD : 账号或者密码错误
     */
    LOGIN_PASSWORD("账号或者密码错误！"),

    /**
     * REGISTER_SUCCESS ： 注册成功
     */
    REGISTER_SUCCESS("注册成功！"),

    /**
     * REGISTER_ERROR ： 注册成功
     */
    REGISTER_ERROR("注册失败！"),

    ;

    // 消息
    private String tips;

    private UserServiceState(String tips) {
        this.tips = tips;
    }

    public String getTips() {
        return tips;
    }

}
