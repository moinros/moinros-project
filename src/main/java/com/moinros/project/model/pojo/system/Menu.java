package com.moinros.project.model.pojo.system;

import java.io.Serializable;

/**
 * 类注释: 
 * 
 * @Title: SysMenu
 * @Author Administrator
 * @Blog https://www.moinros.com
 * @Date 2020-01-16 01:46:41
 * @Version 1.0 
 */
public class Menu implements Serializable {

	// 实现序列化接口
	private static final long serialVersionUID = 1L;

	/**
	 * 注释：菜单ID
	 */
	private Integer mid;

	/**
	 * 注释：菜单名字
	 */
	private String menuName;

	/**
	 * 注释：排序规则;用于确定菜单显示的先后顺序
	 */
	private Integer menuSort;

	/**
	 * 注释：是否为超链接;0表示不是超链接
	 */
	private Boolean isLink;

	/**
	 * 注释：超链接路径
	 */
	private String linkPath;

	/**
	 * 注释：HTML标记,用于注明菜单所属HTML位置
	 */
	private String htmlMark;

	/**
	 * 注释：是否显示
	 */
	private Boolean isShow;

	/**
	 * 注释：菜单栏等级;0表示无父级菜单
	 */
	private Integer level;

	/**
	 * 注释：父级菜单ID
	 */
	private Integer mfid;

	/**
	 * 注释：是否有子菜单;0表示没有子菜单
	 */
	private Boolean isSon;

	// 构造方法
	public Menu() {
	}

	/**
	 * 注释：获取 菜单ID
	 */
	public Integer getMid() {
	    return mid;
	}

	/**
	 * 注释：设置 菜单ID
	 */
	public void setMid(Integer mid) {
	    this.mid = mid;
	}

	/**
	 * 注释：获取 菜单名字
	 */
	public String getMenuName() {
	    return menuName;
	}

	/**
	 * 注释：设置 菜单名字
	 */
	public void setMenuName(String menuName) {
	    this.menuName = menuName;
	}

	/**
	 * 注释：获取 排序规则;用于确定菜单显示的先后顺序
	 */
	public Integer getMenuSort() {
	    return menuSort;
	}

	/**
	 * 注释：设置 排序规则;用于确定菜单显示的先后顺序
	 */
	public void setMenuSort(Integer menuSort) {
	    this.menuSort = menuSort;
	}

	/**
	 * 注释：获取 是否为超链接;0表示不是超链接
	 */
	public Boolean getIsLink() {
	    return isLink;
	}

	/**
	 * 注释：设置 是否为超链接;0表示不是超链接
	 */
	public void setIsLink(Boolean isLink) {
	    this.isLink = isLink;
	}

	/**
	 * 注释：获取 超链接路径
	 */
	public String getLinkPath() {
	    return linkPath;
	}

	/**
	 * 注释：设置 超链接路径
	 */
	public void setLinkPath(String linkPath) {
	    this.linkPath = linkPath;
	}

	/**
	 * 注释：获取 HTML标记,用于注明菜单所属HTML位置
	 */
	public String getHtmlMark() {
	    return htmlMark;
	}

	/**
	 * 注释：设置 HTML标记,用于注明菜单所属HTML位置
	 */
	public void setHtmlMark(String htmlMark) {
	    this.htmlMark = htmlMark;
	}

	/**
	 * 注释：获取 是否显示
	 */
	public Boolean getIsShow() {
	    return isShow;
	}

	/**
	 * 注释：设置 是否显示
	 */
	public void setIsShow(Boolean isShow) {
	    this.isShow = isShow;
	}

	/**
	 * 注释：获取 菜单栏等级;0表示无父级菜单
	 */
	public Integer getLevel() {
	    return level;
	}

	/**
	 * 注释：设置 菜单栏等级;0表示无父级菜单
	 */
	public void setLevel(Integer level) {
	    this.level = level;
	}

	/**
	 * 注释：获取 父级菜单ID
	 */
	public Integer getMfid() {
	    return mfid;
	}

	/**
	 * 注释：设置 父级菜单ID
	 */
	public void setMfid(Integer mfid) {
	    this.mfid = mfid;
	}

	/**
	 * 注释：获取 是否有子菜单;0表示没有子菜单
	 */
	public Boolean getIsSon() {
	    return isSon;
	}

	/**
	 * 注释：设置 是否有子菜单;0表示没有子菜单
	 */
	public void setIsSon(Boolean isSon) {
	    this.isSon = isSon;
	}

	@Override
	public String toString() {
		return "Menu{" +
				"mid=" + mid +
				", menuName='" + menuName + '\'' +
				", menuSort=" + menuSort +
				", isLink=" + isLink +
				", linkPath='" + linkPath + '\'' +
				", htmlMark='" + htmlMark + '\'' +
				", isShow=" + isShow +
				", level=" + level +
				", mfid=" + mfid +
				", isSon=" + isSon +
				'}';
	}
}