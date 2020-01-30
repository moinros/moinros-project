package com.moinros.project.model.dao.system.provider;

import com.moinros.project.model.pojo.system.Checkcode;
import org.apache.ibatis.jdbc.SQL;

/**
 *
 * @说明: 自动生成的与Dao层CheckcodeMapper接口对应的Provider类。
 * @作者: moinros  https://www.moinros.com
 *
 * 注释: 根据Checkcode生成与Mapper接口对应的动态SQL的Provider.Java类
 *
 * @Title: CheckcodeSqlProvider
 * @author: Administrator
 * @date 2020-01-16 01:46:41
 */
public class CheckcodeSqlProvider {

	private static final String SELECT_SQL = 
			  "`sc`.`checkcode_key` AS `checkcodeKey`, "
			+ "`sc`.`checkcode_value` AS `checkcodeValue`, "
			+ "`sc`.`checkcode_time_limit` AS `checkcodeTimeLimit`, "
			+ "`sc`.`checkcode_status` AS `checkcodeStatus`";

	public String selectAllCheckcodeSQL() {
		return new SQL(){
			{
				SELECT(SELECT_SQL);
				FROM("`sys_checkcode` AS `sc`");
			}
		}.toString();
	}

	public String selectCheckcodeSQL(Checkcode checkcode) {
	    return new SQL(){
			{
				SELECT(SELECT_SQL);
				FROM("`sys_checkcode` AS `sc`");
				if (checkcode.getCheckcodeKey() != null) {
					WHERE("`sc`.`checkcode_key` = #{checkcodeKey}");
				}
				if (checkcode.getCheckcodeValue() != null) {
					WHERE("`sc`.`checkcode_value` = #{checkcodeValue}");
				}
				if (checkcode.getCheckcodeTimeLimit() != null) {
					WHERE("`sc`.`checkcode_time_limit` = #{checkcodeTimeLimit}");
				}
				if (checkcode.getCheckcodeStatus() != null) {
					WHERE("`sc`.`checkcode_status` = #{checkcodeStatus}");
				}
			}
		}.toString();
	}

	public String insertCheckcodeSQL(Checkcode checkcode) {
	    return new SQL(){
			{
				INSERT_INTO("`sys_checkcode`");
				if (checkcode.getCheckcodeKey() != null) {
					VALUES("`checkcode_key`", "#{checkcodeKey}");
				}
				if (checkcode.getCheckcodeValue() != null) {
					VALUES("`checkcode_value`", "#{checkcodeValue}");
				}
				if (checkcode.getCheckcodeTimeLimit() != null) {
					VALUES("`checkcode_time_limit`", "#{checkcodeTimeLimit}");
				}
				if (checkcode.getCheckcodeStatus() != null) {
					VALUES("`checkcode_status`", "#{checkcodeStatus}");
				}
			}
		}.toString();
	}

	public String updateCheckcodeSQL(Checkcode checkcode) {
	    return new SQL(){
			{
				UPDATE("sys_checkcode");
				if (checkcode.getCheckcodeKey() != null) {
					SET("`checkcode_key` = #{checkcodeKey}");
				}
				if (checkcode.getCheckcodeValue() != null) {
					SET("`checkcode_value` = #{checkcodeValue}");
				}
				if (checkcode.getCheckcodeTimeLimit() != null) {
					SET("`checkcode_time_limit` = #{checkcodeTimeLimit}");
				}
				if (checkcode.getCheckcodeStatus() != null) {
					SET("`checkcode_status` = #{checkcodeStatus}");
				}
			}
		}.toString();
	}

}