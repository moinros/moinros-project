package com.moinros.project.model.dao.system;

import com.moinros.project.model.dao.system.provider.TagSqlProvider;
import com.moinros.project.model.pojo.Tag;
import com.moinros.project.model.pojo.system.Access;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 注释:
 */
@Mapper
public interface SystemMapper {

    /**
     * 查询所有的设备数据
     *
     * @return List<Access>
     */
    @Select("SELECT `client_ip` AS `clientIp`, `access_note` AS `accessNote` FROM `sys_access`")
    List<Access> selectAllAccess();

    /**
     * 根据IP查询指定设备数据
     *
     * @param clientIp ip
     * @return Access
     */
    @Select("SELECT `client_ip` AS `clientIp`, `access_note` AS `accessNote` FROM `sys_access` WHERE `client_ip` = #{clientIp}")
    Access selectAccessByIP(String clientIp);

    /**
     * 添加新的设备
     *
     * @param access Access
     * @return Integer
     */
    @Insert("INSERT INTO `sys_access` (`client_ip`, `access_note`) VALUES (#{clientIp}, #{accessNote})")
    Integer insertAccess(Access access);

    /**
     * 根据IP删除设备信息
     *
     * @param clientIp ip
     * @return Integer
     */
    @Delete("DELETE FROM sys_access WHERE client_ip = #{clientIp}")
    Integer deleteAccessByIP(String clientIp);

    //|----------|----------|----------|----------|----------|----------|----------|----------|----------|----------|

    /**
     * 查询所有标签数据
     *
     * @return List<Tag>
     */
    @SelectProvider(method = "selectAllTagSQL", type = TagSqlProvider.class)
    List<Tag> selectAllTag();

    /**
     * 根据标签查询标签数据
     *
     * @param tag 条件
     * @return 标签
     */
    @SelectProvider(method = "selectTagSQL", type = TagSqlProvider.class)
    Tag selectTag(Tag tag);

    /**
     * 添加标签数据
     *
     * @param tag 标签
     * @return Integer
     */
    @InsertProvider(method = "insertTagSQL", type = TagSqlProvider.class)
    Integer insertTag(Tag tag);

    /**
     * 修改标签数据
     *
     * @param tag 标签
     * @return Integer
     */
    @UpdateProvider(method = "updateTagSQL", type = TagSqlProvider.class)
    Integer updateTag(Tag tag);

    //|----------|----------|----------|----------|----------|----------|----------|----------|----------|----------|

    @Select("SELECT `sn`.`system_notice` AS `notice` FROM `system_notice` AS `sn` WHERE `sn`.`id` = 1")
    String selectNotice();

    @Update("UPDATE `system_notice` AS `sn` SET `sn`.`system_notice` = #{notice} WHERE `sn`.`id` = 1")
    Integer updateNotice(String notice);
}

