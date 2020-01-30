package com.moinros.project.model.dao.system;

import com.moinros.project.model.dao.system.provider.CheckcodeSqlProvider;
import com.moinros.project.model.pojo.system.Checkcode;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

/**
 *
 * @说明: 自动升成Dao层Checkcode的Mapper接口。
 * @作者: moinros  https://www.moinros.com
 *
 * 注释: 根据JavaBean生成的mMapper接口
 *
 * @Title: CheckcodeMapper
 * @author: Administrator
 * @date 2020-01-16 01:46:41
 */
@Mapper
public interface CheckcodeMapper {

	@SelectProvider(method = "selectAllCheckcodeSQL", type = CheckcodeSqlProvider.class)
	public List<Checkcode> selectAllCheckcode();

	@SelectProvider(method = "selectCheckcodeSQL", type = CheckcodeSqlProvider.class)
	public List<Checkcode> selectCheckcode(Checkcode checkcode);

	@InsertProvider(method = "insertCheckcodeSQL", type = CheckcodeSqlProvider.class)
	public Integer insertCheckcode(Checkcode checkcode);

	@UpdateProvider(method = "updateCheckcodeSQL", type = CheckcodeSqlProvider.class)
	public Integer updateCheckcode(Checkcode checkcode);

}