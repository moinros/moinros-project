package com.moinros.project.model.dao.blog;

import com.moinros.project.model.dao.blog.provider.BlogSqlProvider;
import com.moinros.project.model.pojo.Blog;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @说明: 自动升成Dao层Blog的Mapper接口。
 * @作者: moinros  https://www.moinros.com
 * <p>
 * 注释: 根据JavaBean生成的mMapper接口
 * @Title: BlogMapper
 * @author: Administrator
 * @date 2020-01-16 01:46:41
 */
@Mapper
public interface BlogMapper {

    @SelectProvider(method = "selectAllBlogSQL", type = BlogSqlProvider.class)
    List<Blog> selectAllBlog();

    @SelectProvider(method = "selectBlogLiSQL", type = BlogSqlProvider.class)
    List<Blog> selectBlogLi();

    @Select("SELECT `b`.`blog_id` AS `blogId`,`b`.`blog_subject` AS `blogSubject`,`b`.`edit_time` AS `editTime`,`b`.`blog_tags` AS `blogTags` FROM `blog` AS `b` ORDER BY `b`.`edit_time` DESC")
    List<Blog> selectBlogTitle();

    @SelectProvider(method = "selectBlogSQL", type = BlogSqlProvider.class)
    List<Blog> selectBlog(Blog blog);

    @SelectProvider(method = "selectBlogSQL", type = BlogSqlProvider.class)
    Blog selectBlogByBlog(Blog blog);

    @SelectProvider(method = "selectBlogByTypeSQL", type = BlogSqlProvider.class)
    List<Blog> selectBlogByType(String blogType);

    @SelectProvider(method = "selectBlogBySubjectSQL", type = BlogSqlProvider.class)
    List<Blog> selectBlogBySubject(String subject);

    @Select("SELECT count(*) FROM `blog`")
    Integer selectBlogTotalCount();

    @Update("UPDATE blog SET `visitor_count` = (`visitor_count` + 1) WHERE `blog_id` = #{blogId}")
    Integer updateVisitorCount(int blogId);

    @InsertProvider(method = "insertBlogSQL", type = BlogSqlProvider.class)
    Integer insertBlog(Blog blog);

    @UpdateProvider(method = "updateBlogSQL", type = BlogSqlProvider.class)
    Integer updateBlog(Blog blog);

    @SelectProvider(method = "selectBlogByLimitSQL", type = BlogSqlProvider.class)
    List<Blog> selectBlogByLimit(int start, int end);

}