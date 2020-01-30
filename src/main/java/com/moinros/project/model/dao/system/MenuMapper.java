package com.moinros.project.model.dao.system;

import com.moinros.project.model.dao.system.provider.MenuSqlProvider;
import com.moinros.project.model.pojo.system.Menu;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

/**
 * @说明: 自动升成Dao层Menu的Mapper接口。
 * @作者: moinros  https://www.moinros.com
 * <p>
 * 注释: 根据JavaBean生成的mMapper接口
 * @Title: MenuMapper
 * @author: Administrator
 * @date 2020-01-16 01:46:41
 */
@Mapper
public interface MenuMapper {

    @SelectProvider(method = "selectAllMenuSQL", type = MenuSqlProvider.class)
    public List<Menu> selectAllMenu();

    @SelectProvider(method = "selectMenuListSQL", type = MenuSqlProvider.class)
    public List<Menu> selectMenuList(String htmlMark);

    @InsertProvider(method = "insertMenuSQL", type = MenuSqlProvider.class)
    public Integer insertMenu(Menu menu);

    @UpdateProvider(method = "updateMenuSQL", type = MenuSqlProvider.class)
    public Integer updateMenu(Menu menu);

}