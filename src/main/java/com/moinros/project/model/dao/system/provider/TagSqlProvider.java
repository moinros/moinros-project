package com.moinros.project.model.dao.system.provider;

import com.moinros.project.model.pojo.Tag;
import org.apache.ibatis.jdbc.SQL;

/**
 *
 * @说明: 自动生成的与Dao层TagMapper接口对应的Provider类。
 * @作者: moinros  https://www.moinros.com
 *
 * 注释: 根据Tag生成与Mapper接口对应的动态SQL的Provider.Java类
 *
 * @Title: TagSqlProvider
 * @author: Administrator
 * @date 2020-01-16 01:22:51
 */
public class TagSqlProvider {

	private static final String SELECT_SQL = 
			  "`st`.`tid` AS `tid`, "
			+ "`st`.`tag_mark` AS `tagMark`, "
			+ "`st`.`tag_name` AS `tagName`, "
			+ "`st`.`tag_note` AS `tagNote`, "
			+ "`st`.`is_show` AS `isShow`";

	public String selectAllTagSQL() {
		return new SQL(){
			{
				SELECT(SELECT_SQL);
				FROM("`sys_tag` AS `st`");
				WHERE("`st`.`is_show` = 1");
			}
		}.toString();
	}

	public String selectTagSQL(Tag tag) {
	    return new SQL(){
			{
				SELECT(SELECT_SQL);
				FROM("`sys_tag` AS `st`");
				if (tag.getTid() != null) {
					WHERE("`st`.`tid` = #{tid}");
				}
				if (tag.getTagMark() != null) {
					WHERE("`st`.`tag_mark` = #{tagMark}");
				}
				if (tag.getTagName() != null) {
					WHERE("`st`.`tag_name` = #{tagName}");
				}
			}
		}.toString();
	}

	public String insertTagSQL(Tag tag) {
	    return new SQL(){
			{
				INSERT_INTO("`sys_tag`");
				if (tag.getTagMark() != null) {
					VALUES("`tag_mark`", "#{tagMark}");
				}
				if (tag.getTagName() != null) {
					VALUES("`tag_name`", "#{tagName}");
				}
				if (tag.getTagNote() != null) {
					VALUES("`tag_note`", "#{tagNote}");
				}
			}
		}.toString();
	}

	public String updateTagSQL(Tag tag) {
	    return new SQL(){
			{
				UPDATE("sys_tag");
				if (tag.getTagMark() != null) {
					SET("`tag_mark` = #{tagMark}");
				}
				if (tag.getTagName() != null) {
					SET("`tag_name` = #{tagName}");
				}
				if (tag.getTagNote() != null) {
					SET("`tag_note` = #{tagNote}");
				}
				if (tag.getIsShow() != null) {
					SET("`is_show` = #{isShow}");
				}
				VALUES("`tid`", "#{tid}");
			}
		}.toString();
	}

}