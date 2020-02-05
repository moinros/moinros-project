package com.moinros.project.model.pojo;

import java.io.Serializable;


/**
 * 类注释: 
 * 
 * @Title: Friend
 * @Author Administrator
 * @Blog https://www.moinros.com
 * @Date 2020-01-16 01:46:41
 * @Version 1.0 
 */
public class Friend implements Serializable {

	// 实现序列化接口
	private static final long serialVersionUID = 1L;

	/**
	 * 注释：朋友ID
	 */
	private Integer fid;

	/**
	 * 注释：朋友昵称
	 */
	private String friendName;

	/**
	 * 注释：朋友博客链接
	 */
	private String friendLink;

	/**
	 * 注释：朋友头像
	 */
	private String friendImage;

	/**
	 * 注释：朋友简介
	 */
	private String friendIntro;

	// 构造方法
	public Friend() {
	}

	/**
	 * 注释：获取 朋友ID
	 */
	public Integer getFid() {
	    return fid;
	}

	/**
	 * 注释：设置 朋友ID
	 */
	public void setFid(Integer fid) {
	    this.fid = fid;
	}

	/**
	 * 注释：获取 朋友昵称
	 */
	public String getFriendName() {
	    return friendName;
	}

	/**
	 * 注释：设置 朋友昵称
	 */
	public void setFriendName(String friendName) {
	    this.friendName = friendName;
	}

	/**
	 * 注释：获取 朋友博客链接
	 */
	public String getFriendLink() {
	    return friendLink;
	}

	/**
	 * 注释：设置 朋友博客链接
	 */
	public void setFriendLink(String friendLink) {
	    this.friendLink = friendLink;
	}

	/**
	 * 注释：获取 朋友头像
	 */
	public String getFriendImage() {
	    return friendImage;
	}

	/**
	 * 注释：设置 朋友头像
	 */
	public void setFriendImage(String friendImage) {
	    this.friendImage = friendImage;
	}

	/**
	 * 注释：获取 朋友简介
	 */
	public String getFriendIntro() {
	    return friendIntro;
	}

	/**
	 * 注释：设置 朋友简介
	 */
	public void setFriendIntro(String friendIntro) {
	    this.friendIntro = friendIntro;
	}
}