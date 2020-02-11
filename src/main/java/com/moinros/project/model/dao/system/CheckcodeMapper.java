package com.moinros.project.model.dao.system;

import com.moinros.project.model.dao.system.provider.CheckcodeSqlProvider;
import com.moinros.project.model.pojo.system.Checkcode;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

/**
 * @说明: 自动升成Dao层Checkcode的Mapper接口。
 * @作者: moinros  https://www.moinros.com
 * <p>
 * 注释: 根据JavaBean生成的mMapper接口
 * @Title: CheckcodeMapper
 * @author: Administrator
 * @date 2020-01-16 01:46:41
 */
@Mapper
public interface CheckcodeMapper {

    @SelectProvider(method = "selectCheckcodeSQL", type = CheckcodeSqlProvider.class)
    Checkcode selectCheckcode(String key);

    @InsertProvider(method = "insertCheckcodeSQL", type = CheckcodeSqlProvider.class)
    Integer insertCheckcode(Checkcode checkcode);

    @UpdateProvider(method = "updateCheckcodeSQL", type = CheckcodeSqlProvider.class)
    Integer updateCheckcode(Checkcode checkcode);

}