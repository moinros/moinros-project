package com.moinros.project.service.system;

import com.moinros.project.model.dto.ResultValue;
import com.moinros.project.model.pojo.Tag;
import com.moinros.project.model.pojo.system.Access;
import com.moinros.project.model.pojo.system.Menu;
import com.moinros.project.model.pojo.system.MenuSon;

import java.util.List;

/**
 * 注释: 系统通用业务模块
 */
public interface SystemService {

    //|----------|----------|----------|----------|----------|----------|----------|----------|----------|----------|

    /**
     * 查询所有拥有访问后台管理权限设备IP
     */
    Access[] findAllAccess();

    /**
     * 添加设备IP
     *
     * @param access 设备信息
     * @return 结果集
     */
    ResultValue addAccess(Access access);

    /**
     * 修改设备信息
     *
     * @param access 设备信息
     * @return true or false
     */
    boolean modifyAccess(Access access);

    /**
     * 移除指定ip的设备信息
     *
     * @param ip IP地址
     * @return true or false
     */
    boolean removeAccess(String ip);

    //|----------|----------|----------|----------|----------|----------|----------|----------|----------|----------|

    /**
     * 查询所有标签
     *
     * @return List<Tag>
     */
    List<Tag> findTagList();

    /**
     * 根据id查询标签数据
     *
     * @param id id
     * @return 标签
     */
    Tag findTagById(Integer id);

    /**
     * 根据标记查询标签数据
     *
     * @param mark 标记
     * @return 标签
     */
    Tag findTagByMark(String mark);

    /**
     * 添加新的标签
     *
     * @param tag 标签
     * @return true or false
     */
    ResultValue<Boolean, Object> addTag(Tag tag);

    /**
     * 修改标签数据
     *
     * @param tag 标签
     * @return 结果集
     */
    ResultValue modifyTag(Tag tag);

    //|----------|----------|----------|----------|----------|----------|----------|----------|----------|----------|

    List<MenuSon> findMenuSon(String mark);

    List<Menu> findMenuList(String mark);

    Menu findMenuById(Integer mid);

    boolean addMenu(Menu menu);

    boolean modifyMenu(Menu menu);

    //|----------|----------|----------|----------|----------|----------|----------|----------|----------|----------|

    String findNotice();

    boolean modifyNotice(String notice);

}


