package com.moinros.project.model.dao.system;

import com.moinros.project.model.dao.system.provider.AdminLogSqlProvider;
import com.moinros.project.model.pojo.system.AdminLog;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

/**
 * @说明: 自动升成Dao层SysAdminLog的Mapper接口。
 * @作者: moinros  https://www.moinros.com
 * <p>
 * 注释: 根据JavaBean生成的mMapper接口
 * @Title: SysAdminLogMapper
 * @author: Administrator
 * @date 2020-01-30 23:04:39
 */
@Mapper
public interface AdminLogMapper {

    @SelectProvider(method = "selectAllAdminLogSQL", type = AdminLogSqlProvider.class)
    public List<AdminLog> selectAllAdminLog();

    @SelectProvider(method = "selectAdminLogSQL", type = AdminLogSqlProvider.class)
    public List<AdminLog> selectAdminLog(AdminLog log);

    @InsertProvider(method = "insertAdminLogSQL", type = AdminLogSqlProvider.class)
    public Integer insertAdminLog(AdminLog log);

    @UpdateProvider(method = "updateAdminLogSQL", type = AdminLogSqlProvider.class)
    public Integer updateAdminLog(AdminLog log);

}