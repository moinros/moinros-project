package com.moinros.project.model.pojo;

import com.moinros.project.common.annotation.base64.Base64Mark;
import com.moinros.project.model.dto.enums.Gender;
import com.moinros.project.tool.util.string.StringUtil;

import java.io.Serializable;

/**
 * 类注释:
 *
 * @Title: UserData
 * @Author Administrator
 * @Blog https://www.moinros.com
 * @Date 2020-01-16 01:50:20
 * @Version 1.0
 */
@Base64Mark
public class UserData implements Serializable {

    // 实现序列化接口
    private static final long serialVersionUID = 1L;

    /**
     * 注释：用户ID [唯一,且不能更改]
     */
    private Integer userId;

    /**
     * 注释：用户名(登录账号)
     */
    private String userName;

    /**
     * 注释：密码
     */
    private String password;

    /**
     * 注释：user 昵称
     */
    @Base64Mark
    private String nickName;

    /**
     * 注释：性别枚举,值包括:'MALE','FEMALE','SECRECY'。默认为'SECRECY'
     */
    private Gender gender;

    /**
     * 注释：头像图片路径
     */
    private String imagePath;

    /**
     * 注释：生日
     */
    private String birthday;

    /**
     * 注释：手机号
     */
    private String userPhone;

    /**
     * 注释：邮箱
     */
    private String userEmail;

    /**
     * 注释：用户签名
     */
    @Base64Mark
    private String userIntro;

    /**
     * 注释：上次登录客户端IP地址
     */
    private String clientIp;

    /**
     * 注释：最近登录时间
     */
    private String loginTime;

    /**
     * 注释：账号注册时间
     */
    private String registerTime;

    // 构造方法
    public UserData() {
    }

    /**
     * 注释：获取 用户ID [唯一,且不能更改]
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 注释：设置 用户ID [唯一,且不能更改]
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 注释：获取 用户名(登录账号)
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 注释：设置 用户名(登录账号)
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 注释：获取 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 注释：设置 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 注释：获取 user 昵称
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 注释：设置 user 昵称
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * 注释：获取 性别枚举,值包括:'MALE','FEMALE','SECRECY'。默认为'SECRECY'
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * 注释：设置 性别枚举,值包括:'MALE','FEMALE','SECRECY'。默认为'SECRECY'
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * 注释：获取 头像图片路径
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * 注释：设置 头像图片路径
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     * 注释：获取 生日
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * 注释：设置 生日
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * 注释：获取 手机号
     */
    public String getUserPhone() {
        return userPhone;
    }

    /**
     * 注释：设置 手机号
     */
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    /**
     * 注释：获取 邮箱
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * 注释：设置 邮箱
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     * 注释：获取 用户签名
     */
    public String getUserIntro() {
        return userIntro;
    }

    /**
     * 注释：设置 用户签名
     */
    public void setUserIntro(String userIntro) {
        this.userIntro = userIntro;
    }

    /**
     * 注释：获取 上次登录客户端IP地址
     */
    public String getClientIp() {
        return clientIp;
    }

    /**
     * 注释：设置 上次登录客户端IP地址
     */
    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    /**
     * 注释：获取 最近登录时间
     */
    public String getLoginTime() {
        return loginTime;
    }

    /**
     * 注释：设置 最近登录时间
     */
    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    /**
     * 注释：获取 账号注册时间
     */
    public String getRegisterTime() {
        return registerTime;
    }

    /**
     * 注释：设置 账号注册时间
     */
    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String toString() {
        return StringUtil.toString(this);
    }
}