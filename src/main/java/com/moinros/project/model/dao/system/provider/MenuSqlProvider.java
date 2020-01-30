package com.moinros.project.model.dao.system.provider;

import com.moinros.project.model.pojo.system.Menu;
import org.apache.ibatis.jdbc.SQL;

/**
 *
 * @说明: 自动生成的与Dao层menuMapper接口对应的Provider类。
 * @作者: moinros  https://www.moinros.com
 *
 * 注释: 根据menu生成与Mapper接口对应的动态SQL的Provider.Java类
 *
 * @Title: MenuSQLProvider
 * @author: Administrator
 * @date 2020-01-16 01:46:41
 */
public class MenuSqlProvider {

	private static final String SELECT_SQL = 
			  "`sm`.`mid` AS `mid`, "
			+ "`sm`.`menu_name` AS `menuName`, "
			+ "`sm`.`menu_sort` AS `menuSort`, "
			+ "`sm`.`is_link` AS `isLink`, "
			+ "`sm`.`link_path` AS `linkPath`, "
			+ "`sm`.`html_mark` AS `htmlMark`, "
			+ "`sm`.`is_show` AS `isShow`, "
			+ "`sm`.`level` AS `level`, "
			+ "`sm`.`mfid` AS `mfid`, "
			+ "`sm`.`is_son` AS `isSon`";

	public String selectAllMenuSQL() {
		return new SQL(){
			{
				SELECT(SELECT_SQL);
				FROM("`sys_menu` AS `sm`");
			}
		}.toString();
	}

	public String selectMenuListSQL() {
		return new SQL(){
			{
				SELECT(SELECT_SQL);
				FROM("`sys_menu` AS `sm`");
				WHERE("`sm`.`html_mark` = #{htmlMark}");
			}
		}.toString();
	}

	public String selectMenuSQL(Menu menu) {
	    return new SQL(){
			{
				SELECT(SELECT_SQL);
				FROM("`sys_menu` AS `sm`");
				if (menu.getMid() != null) {
					WHERE("`sm`.`mid` = #{mid}");
				}
				if (menu.getMenuName() != null) {
					WHERE("`sm`.`menu_name` = #{menuName}");
				}
				if (menu.getMenuSort() != null) {
					WHERE("`sm`.`menu_sort` = #{menuSort}");
				}
				if (menu.getIsLink() != null) {
					WHERE("`sm`.`is_link` = #{isLink}");
				}
				if (menu.getLinkPath() != null) {
					WHERE("`sm`.`link_path` = #{linkPath}");
				}
				if (menu.getHtmlMark() != null) {
					WHERE("`sm`.`html_mark` = #{htmlMark}");
				}
				if (menu.getIsShow() != null) {
					WHERE("`sm`.`is_show` = #{isShow}");
				}
				if (menu.getLevel() != null) {
					WHERE("`sm`.`level` = #{level}");
				}
				if (menu.getMfid() != null) {
					WHERE("`sm`.`mfid` = #{mfid}");
				}
				if (menu.getIsSon() != null) {
					WHERE("`sm`.`is_son` = #{isSon}");
				}
			}
		}.toString();
	}

	public String insertMenuSQL(Menu menu) {
	    return new SQL(){
			{
				INSERT_INTO("`sys_menu`");
				if (menu.getMid() != null) {
					VALUES("`mid`", "#{mid}");
				}
				if (menu.getMenuName() != null) {
					VALUES("`menu_name`", "#{menuName}");
				}
				if (menu.getMenuSort() != null) {
					VALUES("`menu_sort`", "#{menuSort}");
				}
				if (menu.getIsLink() != null) {
					VALUES("`is_link`", "#{isLink}");
				}
				if (menu.getLinkPath() != null) {
					VALUES("`link_path`", "#{linkPath}");
				}
				if (menu.getHtmlMark() != null) {
					VALUES("`html_mark`", "#{htmlMark}");
				}
				if (menu.getIsShow() != null) {
					VALUES("`is_show`", "#{isShow}");
				}
				if (menu.getLevel() != null) {
					VALUES("`level`", "#{level}");
				}
				if (menu.getMfid() != null) {
					VALUES("`mfid`", "#{mfid}");
				}
				if (menu.getIsSon() != null) {
					VALUES("`is_son`", "#{isSon}");
				}
			}
		}.toString();
	}

	public String updateMenuSQL(Menu menu) {
	    return new SQL(){
			{
				UPDATE("sys_menu");
				if (menu.getMid() != null) {
					SET("`mid` = #{mid}");
				}
				if (menu.getMenuName() != null) {
					SET("`menu_name` = #{menuName}");
				}
				if (menu.getMenuSort() != null) {
					SET("`menu_sort` = #{menuSort}");
				}
				if (menu.getIsLink() != null) {
					SET("`is_link` = #{isLink}");
				}
				if (menu.getLinkPath() != null) {
					SET("`link_path` = #{linkPath}");
				}
				if (menu.getHtmlMark() != null) {
					SET("`html_mark` = #{htmlMark}");
				}
				if (menu.getIsShow() != null) {
					SET("`is_show` = #{isShow}");
				}
				if (menu.getLevel() != null) {
					SET("`level` = #{level}");
				}
				if (menu.getMfid() != null) {
					SET("`mfid` = #{mfid}");
				}
				if (menu.getIsSon() != null) {
					SET("`is_son` = #{isSon}");
				}
			}
		}.toString();
	}

}