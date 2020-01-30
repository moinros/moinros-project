package com.moinros.project.model.pojo.system;

import java.io.Serializable;

/**
 * 类注释: 
 * 
 * @Title: SysAdmin
 * @Author Administrator
 * @Blog https://www.moinros.com
 * @Date 2020-01-16 01:46:41
 * @Version 1.0 
 */
public class Admin implements Serializable {

	// 实现序列化接口
	private static final long serialVersionUID = 1L;

	/**
	 * 注释：管理员ID
	 */
	private Integer aid;

	/**
	 * 注释：管理员账号
	 */
	private String adminName;

	/**
	 * 注释：管理员密码
	 */
	private String password;

	/**
	 * 注释：管理员权限
	 */
	private String privilege;

	/**
	 * 注释：昵称
	 */
	private String nickName;

	/**
	 * 注释：头像路径
	 */
	private String imagePath;

	// 构造方法
	public Admin() {
	}

	/**
	 * 注释：获取 管理员ID
	 */
	public Integer getAid() {
	    return aid;
	}

	/**
	 * 注释：设置 管理员ID
	 */
	public void setAid(Integer aid) {
	    this.aid = aid;
	}

	/**
	 * 注释：获取 管理员账号
	 */
	public String getAdminName() {
	    return adminName;
	}

	/**
	 * 注释：设置 管理员账号
	 */
	public void setAdminName(String adminName) {
	    this.adminName = adminName;
	}

	/**
	 * 注释：获取 管理员密码
	 */
	public String getPassword() {
	    return password;
	}

	/**
	 * 注释：设置 管理员密码
	 */
	public void setPassword(String password) {
	    this.password = password;
	}

	/**
	 * 注释：获取 管理员权限
	 */
	public String getPrivilege() {
	    return privilege;
	}

	/**
	 * 注释：设置 管理员权限
	 */
	public void setPrivilege(String privilege) {
	    this.privilege = privilege;
	}

	/**
	 * 注释：获取 昵称
	 */
	public String getNickName() {
	    return nickName;
	}

	/**
	 * 注释：设置 昵称
	 */
	public void setNickName(String nickName) {
	    this.nickName = nickName;
	}

	/**
	 * 注释：获取 头像路径
	 */
	public String getImagePath() {
	    return imagePath;
	}

	/**
	 * 注释：设置 头像路径
	 */
	public void setImagePath(String imagePath) {
	    this.imagePath = imagePath;
	}
}