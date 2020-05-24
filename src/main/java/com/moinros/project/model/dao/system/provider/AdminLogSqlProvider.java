package com.moinros.project.model.dao.system.provider;

import com.moinros.project.model.pojo.system.AdminLog;
import org.apache.ibatis.jdbc.SQL;

/**
 * @说明: 自动生成的与Dao层SysAdminLogMapper接口对应的Provider类。
 * @作者: moinros  https://www.moinros.com
 * <p>
 * 注释: 根据SysAdminLog生成与Mapper接口对应的动态SQL的Provider.Java类
 * @Title: SysAdminLogSqlProvider
 * @author: Administrator
 * @date 2020-01-30 23:04:39
 */
public class AdminLogSqlProvider {

    private static final String SELECT_SQL =
            "`sa`.`log_id` AS `logId`, "
                    + "`sa`.`admin_id` AS `adminId`, "
                    + "`sa`.`login_time` AS `loginTime`, "
                    + "`sa`.`client_ip` AS `clientIp`, "
                    + "`sa`.`client_os` AS `clientOs`, "
                    + "`sa`.`client_type` AS `clientType`";

    public String selectAllAdminLogSQL() {
        return new SQL() {
            {
                SELECT(SELECT_SQL);
                FROM("`sys_admin_log` AS `sa`");
            }
        }.toString();
    }

    public String selectAdminLogSQL(AdminLog log) {
        return new SQL() {
            {
                SELECT(SELECT_SQL);
                FROM("`sys_admin_log` AS `sa`");
                if (log.getLogId() != null) {
                    WHERE("`sa`.`log_id` = #{logId}");
                }
                if (log.getAdminId() != null) {
                    WHERE("`sa`.`admin_id` = #{adminId}");
                }
                if (log.getClientIp() != null) {
                    WHERE("`sa`.`client_ip` = #{clientIp}");
                }
                if (log.getClientOs() != null) {
                    WHERE("`sa`.`client_os` = #{clientOs}");
                }
                if (log.getClientType() != null) {
                    WHERE("`sa`.`client_type` = #{clientType}");
                }
            }
        }.toString();
    }

    public String insertAdminLogSQL(AdminLog log) {
        return new SQL() {
            {
                INSERT_INTO("`sys_admin_log`");
                VALUES("`admin_id`", "#{adminId}");
                VALUES("`login_time`", "#{loginTime}");
                VALUES("`client_ip`", "#{clientIp}");
                VALUES("`client_os`", "#{clientOs}");
                VALUES("`client_type`", "#{clientType}");
            }
        }.toString();
    }

    public String updateAdminLogSQL(AdminLog log) {
        return new SQL() {
            {
                UPDATE("sys_admin_log");
                if (log.getAdminId() != null) {
                    SET("`admin_id` = #{adminId}");
                }
                if (log.getLoginTime() != null) {
                    SET("`login_time` = #{loginTime}");
                }
                if (log.getClientIp() != null) {
                    SET("`client_ip` = #{clientIp}");
                }
                if (log.getClientOs() != null) {
                    SET("`client_os` = #{clientOs}");
                }
                if (log.getClientType() != null) {
                    SET("`client_type` = #{clientType}");
                }
                WHERE("`sa`.`log_id` = #{logId}");
            }
        }.toString();
    }

}