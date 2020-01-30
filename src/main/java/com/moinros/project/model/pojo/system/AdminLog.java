package com.moinros.project.model.pojo.system;

import java.io.Serializable;


/**
 * 类注释: 
 * 
 * @Title: SysAdminLog
 * @Author Administrator
 * @Blog https://www.moinros.com
 * @Date 2020-01-30 23:04:39
 * @Version 1.0 
 */
public class AdminLog implements Serializable {

	// 实现序列化接口
	private static final long serialVersionUID = 1L;

	/**
	 * 注释：日志id
	 */
	private Integer logId;

	/**
	 * 注释：管理员Id
	 */
	private Integer adminId;

	/**
	 * 注释：登录时间
	 */
	private String loginTime;

	/**
	 * 注释：登录客户端ip
	 */
	private String clientIp;

	/**
	 * 注释：客户端操作系统
	 */
	private String clientOs;

	/**
	 * 注释：登录客户端类型(平台)
	 */
	private String clientType;

	// 构造方法
	public AdminLog() {
	}

	/**
	 * 注释：获取 日志id
	 */
	public Integer getLogId() {
	    return logId;
	}

	/**
	 * 注释：设置 日志id
	 */
	public void setLogId(Integer logId) {
	    this.logId = logId;
	}

	/**
	 * 注释：获取 管理员Id
	 */
	public Integer getAdminId() {
	    return adminId;
	}

	/**
	 * 注释：设置 管理员Id
	 */
	public void setAdminId(Integer adminId) {
	    this.adminId = adminId;
	}

	/**
	 * 注释：获取 登录时间
	 */
	public String getLoginTime() {
	    return loginTime;
	}

	/**
	 * 注释：设置 登录时间
	 */
	public void setLoginTime(String loginTime) {
	    this.loginTime = loginTime;
	}

	/**
	 * 注释：获取 登录客户端ip
	 */
	public String getClientIp() {
	    return clientIp;
	}

	/**
	 * 注释：设置 登录客户端ip
	 */
	public void setClientIp(String clientIp) {
	    this.clientIp = clientIp;
	}

	/**
	 * 注释：获取 客户端操作系统
	 */
	public String getClientOs() {
	    return clientOs;
	}

	/**
	 * 注释：设置 客户端操作系统
	 */
	public void setClientOs(String clientOs) {
	    this.clientOs = clientOs;
	}

	/**
	 * 注释：获取 登录客户端类型(平台)
	 */
	public String getClientType() {
	    return clientType;
	}

	/**
	 * 注释：设置 登录客户端类型(平台)
	 */
	public void setClientType(String clientType) {
	    this.clientType = clientType;
	}
}