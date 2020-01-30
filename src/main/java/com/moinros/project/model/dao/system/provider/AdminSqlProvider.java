package com.moinros.project.model.dao.system.provider;

import com.moinros.project.model.pojo.system.Admin;
import org.apache.ibatis.jdbc.SQL;

/**
 * @说明: 自动生成的与Dao层AdminMapper接口对应的Provider类。
 * @作者: moinros  https://www.moinros.com
 * <p>
 * 注释: 根据Admin生成与Mapper接口对应的动态SQL的Provider.Java类
 * @Title: AdminSqlProvider
 * @author: Administrator
 * @date 2020-01-16 01:46:41
 */
public class AdminSqlProvider {

    private static final String SELECT_SQL =
            "`sa`.`aid` AS `aid`, "
                    + "`sa`.`admin_name` AS `adminName`, "
                    + "`sa`.`password` AS `password`, "
                    + "`sa`.`privilege` AS `privilege`, "
                    + "`sa`.`nick_name` AS `nickName`, "
                    + "`sa`.`image_path` AS `imagePath`";

    public String selectAllAdminSQL() {
        return new SQL() {
            {
                SELECT(SELECT_SQL);
                FROM("`sys_admin` AS `sa`");
            }
        }.toString();
    }

    public String selectAdminSQL(Admin admin) {
        return new SQL() {
            {
                SELECT(SELECT_SQL);
                FROM("`sys_admin` AS `sa`");
                if (admin.getAid() != null) {
                    WHERE("`sa`.`aid` = #{aid}");
                }
                if (admin.getAdminName() != null) {
                    WHERE("`sa`.`admin_name` = #{adminName}");
                }
                if (admin.getPassword() != null) {
                    WHERE("`sa`.`admin_password` = #{password}");
                }
                if (admin.getPrivilege() != null) {
                    WHERE("`sa`.`privilege` = #{privilege}");
                }
            }
        }.toString();
    }

    public String insertAdminSQL(Admin admin) {
        return new SQL() {
            {
                INSERT_INTO("`sys_admin`");
                if (admin.getAid() != null) {
                    VALUES("`aid`", "#{aid}");
                }
                if (admin.getAdminName() != null) {
                    VALUES("`admin_name`", "#{adminName}");
                }
                if (admin.getPassword() != null) {
                    VALUES("`admin_password`", "#{password}");
                }
                if (admin.getPrivilege() != null) {
                    VALUES("`privilege`", "#{privilege}");
                }
                if (admin.getNickName() != null) {
                    VALUES("`nick_name`", "#{nickName}");
                }
                if (admin.getImagePath() != null) {
                    VALUES("`image_path`", "#{imagePath}");
                }
            }
        }.toString();
    }

    public String updateAdminSQL(Admin admin) {
        return new SQL() {
            {
                UPDATE("sys_admin");
                if (admin.getAid() != null) {
                    SET("`aid` = #{aid}");
                }
                if (admin.getAdminName() != null) {
                    SET("`admin_name` = #{adminName}");
                }
                if (admin.getPassword() != null) {
                    SET("`admin_password` = #{password}");
                }
                if (admin.getPrivilege() != null) {
                    SET("`privilege` = #{privilege}");
                }
                if (admin.getNickName() != null) {
                    SET("`nick_name` = #{nickName}");
                }
                if (admin.getImagePath() != null) {
                    SET("`image_path` = #{imagePath}");
                }
            }
        }.toString();
    }

}