package com.moinros.project.model.dao.blog.provider;

import com.moinros.project.model.pojo.Blog;
import org.apache.ibatis.jdbc.SQL;

/**
 * @说明: 自动生成的与Dao层BlogMapper接口对应的Provider类。
 * @作者: moinros  https://www.moinros.com
 * <p>
 * 注释: 根据Blog生成与Mapper接口对应的动态SQL的Provider.Java类
 * @Title: BlogSqlProvider
 * @author: Administrator
 * @date 2020-01-16 01:46:41
 */
public class BlogSqlProvider {

    private static final String SELECT_SQL =
            "`b`.`blog_id` AS `blogId`, "
                    + "`b`.`blog_subject` AS `blogSubject`, "
                    + "`b`.`blog_content` AS `blogContent`, "
                    + "`b`.`blog_tags` AS `blogTags`, "
                    + "`b`.`blog_cover` AS `blogCover`, "
                    + "`b`.`blog_intro` AS `blogIntro`, "
                    + "`b`.`edit_time` AS `editTime`, "
                    + "`b`.`update_time` AS `updateTime`, "
                    + "`b`.`blog_state` AS `blogState`, "
                    + "`b`.`is_top` AS `isTop`, "
                    + "`b`.`visitor_count` AS `visitorCount`, "
                    + "`b`.`like_count` AS `likeCount`, "
                    + "`b`.`blog_sub` AS `blogSub`";

    private static final String SELECT_LI_SQL =
            "`b`.`blog_id` AS `blogId`, "
                    + "`b`.`blog_subject` AS `blogSubject`, "
                    + "`b`.`blog_tags` AS `blogTags`, "
                    + "`b`.`blog_cover` AS `blogCover`, "
                    + "`b`.`blog_intro` AS `blogIntro`, "
                    + "`b`.`edit_time` AS `editTime`, "
                    + "`b`.`blog_state` AS `blogState`, "
                    + "`b`.`visitor_count` AS `visitorCount`, "
                    + "`b`.`is_top` AS `isTop`";

    public String selectBlogLiSQL() {
        return new SQL() {
            {
                SELECT(SELECT_LI_SQL);
                FROM("`blog` AS `b`");
                ORDER_BY("`b`.`edit_time` DESC");
            }
        }.toString();
    }

    public String selectAllBlogSQL() {
        return new SQL() {
            {
                SELECT(SELECT_SQL);
                FROM("`blog` AS `b`");
                ORDER_BY("`b`.`edit_time` DESC");
            }
        }.toString();
    }

    public String selectBlogByTypeSQL() {
        return new SQL() {
            {
                SELECT(SELECT_LI_SQL);
                FROM("`blog` AS `b`");
                WHERE("`b`.`blog_tags` LIKE CONCAT('%', #{blogTags}, '%')");
                ORDER_BY("`b`.`edit_time` DESC");
            }
        }.toString();
    }

    public String selectBlogByLimitSQL() {
        return new SQL() {
            {
                SELECT(SELECT_LI_SQL);
                FROM("`blog` AS `b`");
                ORDER_BY("`b`.`edit_time` DESC");
                LIMIT("#{start}, #{end}");
            }
        }.toString();
    }

    public String selectBlogSQL(Blog blog) {
        return new SQL() {
            {
                SELECT(SELECT_SQL);
                FROM("`blog` AS `b`");
                ORDER_BY("`b`.`edit_time` DESC");
                if (blog.getBlogId() != null) {
                    WHERE("`b`.`blog_id` = #{blogId}");
                }
                if (blog.getBlogSubject() != null) {
                    WHERE("`b`.`blog_subject` = #{blogSubject}");
                }
                if (blog.getBlogTags() != null) {
                    WHERE("`b`.`blog_tags` = #{blogTags}");
                }
                if (blog.getBlogState() != null) {
                    WHERE("`b`.`blog_state` = #{blogState}");
                }
            }
        }.toString();
    }

    public String insertBlogSQL(Blog blog) {
        return new SQL() {
            {
                INSERT_INTO("`blog`");
                if (blog.getBlogSubject() != null) {
                    VALUES("`blog_subject`", "#{blogSubject}");
                }
                if (blog.getBlogContent() != null) {
                    VALUES("`blog_content`", "#{blogContent}");
                }
                if (blog.getBlogTags() != null) {
                    VALUES("`blog_tags`", "#{blogTags}");
                }
                if (blog.getBlogCover() != null) {
                    VALUES("`blog_cover`", "#{blogCover}");
                }
                if (blog.getBlogIntro() != null) {
                    VALUES("`blog_intro`", "#{blogIntro}");
                }
                if (blog.getEditTime() != null) {
                    VALUES("`edit_time`", "#{editTime}");
                }
                if (blog.getUpdateTime() != null) {
                    VALUES("`update_time`", "#{updateTime}");
                }
                if (blog.getBlogState() != null) {
                    VALUES("`blog_state`", "#{blogState}");
                }
                if (blog.getIsTop() != null) {
                    VALUES("`is_top`", "#{isTop}");
                }
                if (blog.getVisitorCount() != null) {
                    VALUES("`visitor_count`", "#{visitorCount}");
                }
                if (blog.getLikeCount() != null) {
                    VALUES("`like_count`", "#{likeCount}");
                }
                if (blog.getBlogSub() != null) {
                    VALUES("`blog_sub`", "#{blogSub}");
                }
            }
        }.toString();
    }

    public String updateBlogSQL(Blog blog) {
        return new SQL() {
            {
                UPDATE("blog");
                if (blog.getBlogSubject() != null) {
                    SET("`blog_subject` = #{blogSubject}");
                }
                if (blog.getBlogContent() != null) {
                    SET("`blog_content` = #{blogContent}");
                }
                if (blog.getBlogTags() != null) {
                    SET("`blog_tags` = #{blogTags}");
                }
                if (blog.getBlogCover() != null) {
                    SET("`blog_cover` = #{blogCover}");
                }
                if (blog.getBlogIntro() != null) {
                    SET("`blog_intro` = #{blogIntro}");
                }
                if (blog.getUpdateTime() != null) {
                    SET("`update_time` = #{updateTime}");
                }
                if (blog.getBlogState() != null) {
                    SET("`blog_state` = #{blogState}");
                }
                if (blog.getIsTop() != null) {
                    SET("`is_top` = #{isTop}");
                }
                if (blog.getVisitorCount() != null) {
                    SET("`visitor_count` = #{visitorCount}");
                }
                if (blog.getLikeCount() != null) {
                    SET("`like_count` = #{likeCount}");
                }
                if (blog.getBlogSub() != null) {
                    SET("`blog_sub` = #{blogSub}");
                }
            }
        }.toString();
    }

}