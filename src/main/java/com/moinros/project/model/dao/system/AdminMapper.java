package com.moinros.project.model.dao.system;

import com.moinros.project.model.dao.system.provider.AdminSqlProvider;
import com.moinros.project.model.pojo.system.Admin;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

/**
 *
 * @说明: 自动升成Dao层Admin的Mapper接口。
 * @作者: moinros  https://www.moinros.com
 *
 * 注释: 根据JavaBean生成的mMapper接口
 *
 * @Title: AdminMapper
 * @author: Administrator
 * @date 2020-01-16 01:46:41
 */
@Mapper
public interface AdminMapper {

	@SelectProvider(method = "selectAllAdminSQL", type = AdminSqlProvider.class)
	public List<Admin> selectAllAdmin();

	@SelectProvider(method = "selectAdminSQL", type = AdminSqlProvider.class)
	public Admin selectAdmin(Admin admin);

	@InsertProvider(method = "insertAdminSQL", type = AdminSqlProvider.class)
	public Integer insertAdmin(Admin admin);

	@UpdateProvider(method = "updateAdminSQL", type = AdminSqlProvider.class)
	public Integer updateAdmin(Admin admin);

}