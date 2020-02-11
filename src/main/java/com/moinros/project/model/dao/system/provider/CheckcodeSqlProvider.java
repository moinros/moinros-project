package com.moinros.project.model.dao.system.provider;

import com.moinros.project.model.pojo.system.Checkcode;
import org.apache.ibatis.jdbc.SQL;

/**
 * @说明: 自动生成的与Dao层CheckcodeMapper接口对应的Provider类。
 * @作者: moinros  https://www.moinros.com
 * <p>
 * 注释: 根据Checkcode生成与Mapper接口对应的动态SQL的Provider.Java类
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

    public String selectCheckcodeSQL() {
        return new SQL() {
            {
                SELECT(SELECT_SQL);
                FROM("`sys_checkcode` AS `sc`");
                WHERE("`sc`.`checkcode_key` = #{key}");
            }
        }.toString();
    }

    public String insertCheckcodeSQL(Checkcode checkcode) {
        return new SQL() {
            {
                INSERT_INTO("`sys_checkcode`");
                VALUES("`checkcode_key`", "#{checkcodeKey}");
                VALUES("`checkcode_value`", "#{checkcodeValue}");
                VALUES("`checkcode_time_limit`", "#{checkcodeTimeLimit}");
            }
        }.toString();
    }

    public String updateCheckcodeSQL(Checkcode checkcode) {
        return new SQL() {
            {
                UPDATE("sys_checkcode");
                if (checkcode.getCheckcodeValue() != null) {
                    SET("`checkcode_value` = #{checkcodeValue}");
                }
                if (checkcode.getCheckcodeTimeLimit() != null) {
                    SET("`checkcode_time_limit` = #{checkcodeTimeLimit}");
                }
                if (checkcode.getCheckcodeStatus() != null) {
                    SET("`checkcode_status` = #{checkcodeStatus}");
                }
                WHERE("`checkcode_key` = #{checkcodeKey}");
            }
        }.toString();
    }

}