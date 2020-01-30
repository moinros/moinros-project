package com.moinros.project.model.pojo.system;

import java.io.Serializable;

/**
 * 类注释: 
 * 
 * @Title: SysCheckcode
 * @Author Administrator
 * @Blog https://www.moinros.com
 * @Date 2020-01-16 01:46:41
 * @Version 1.0 
 */
public class Checkcode implements Serializable {

	// 实现序列化接口
	private static final long serialVersionUID = 1L;

	/**
	 * 注释：验证码的KEY，通常为邮箱
	 */
	private String checkcodeKey;

	/**
	 * 注释：验证码
	 */
	private String checkcodeValue;

	/**
	 * 注释：验证码的生成时间，用于判断验证码是否失效
	 */
	private Long checkcodeTimeLimit;

	/**
	 * 注释：验证码使用状态，0表示不可使用，1表示可以使用
	 */
	private Boolean checkcodeStatus;

	// 构造方法
	public Checkcode() {
	}

	/**
	 * 注释：获取 验证码的KEY，通常为邮箱
	 */
	public String getCheckcodeKey() {
	    return checkcodeKey;
	}

	/**
	 * 注释：设置 验证码的KEY，通常为邮箱
	 */
	public void setCheckcodeKey(String checkcodeKey) {
	    this.checkcodeKey = checkcodeKey;
	}

	/**
	 * 注释：获取 验证码
	 */
	public String getCheckcodeValue() {
	    return checkcodeValue;
	}

	/**
	 * 注释：设置 验证码
	 */
	public void setCheckcodeValue(String checkcodeValue) {
	    this.checkcodeValue = checkcodeValue;
	}

	/**
	 * 注释：获取 验证码的生成时间，用于判断验证码是否失效
	 */
	public Long getCheckcodeTimeLimit() {
	    return checkcodeTimeLimit;
	}

	/**
	 * 注释：设置 验证码的生成时间，用于判断验证码是否失效
	 */
	public void setCheckcodeTimeLimit(Long checkcodeTimeLimit) {
	    this.checkcodeTimeLimit = checkcodeTimeLimit;
	}

	/**
	 * 注释：获取 验证码使用状态，0表示不可使用，1表示可以使用
	 */
	public Boolean getCheckcodeStatus() {
	    return checkcodeStatus;
	}

	/**
	 * 注释：设置 验证码使用状态，0表示不可使用，1表示可以使用
	 */
	public void setCheckcodeStatus(Boolean checkcodeStatus) {
	    this.checkcodeStatus = checkcodeStatus;
	}
}