package com.moinros.project.model.dto;

import com.moinros.project.common.annotation.base64.Base64Mark;
import com.moinros.project.model.dto.enums.Gender;

/**
 * 类注释: 定义了User的公共属性的抽象类
 *
 * @Title: User
 * @Author moinros
 * @Blog https://www.moinros.com
 * @Date 2019年11月20日 下午10:41:09
 * @Version 1.0
 */
@Base64Mark
abstract public class User {

	/**
	 * uid 注释: 用户ID
	 */
	private Integer userId;

	/**
	 * userName : 用户名
	 */
	private String userName;

	/**
	 * nickName 注释: 昵称
	 */
	@Base64Mark
	private String nickName;

	/**
	 * imagePath 注释: 头像路径
	 */
	private String imagePath;

	/**
	 * gender 注释: 性别
	 */
	private Gender gender = Gender.SECRECY;

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
	 * 注释：获取 性别[枚举],值包括:'MALE','FEMALE','SECRECY';默认为'SECRECY'
	 */
	public Gender getGender() {
		return gender;
	}

	/**
	 * 注释：设置 性别[枚举],值包括:'MALE','FEMALE','SECRECY';默认为'SECRECY'
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (gender != other.gender)
			return false;
		if (imagePath == null) {
			if (other.imagePath != null)
				return false;
		} else if (!imagePath.equals(other.imagePath))
			return false;
		if (nickName == null) {
			if (other.nickName != null)
				return false;
		} else if (!nickName.equals(other.nickName))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	public boolean paramIsNull() {

		if (userId != null) {
			return false;
		}
		if (userName != null) {
			return false;
		}
		if (nickName != null) {
			return false;
		}
		if (imagePath != null) {
			return false;
		}
		if (gender != null) {
			return false;
		}
		return true;
	}
}
