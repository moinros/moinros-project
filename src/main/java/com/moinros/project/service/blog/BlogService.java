package com.moinros.project.service.blog;

import com.moinros.project.model.dto.ResultValue;
import com.moinros.project.model.pojo.Blog;
import com.moinros.project.tool.web.PageBean;

import java.util.List;

public interface BlogService {

    /**
     * 注释: 查询所有的博客部分数据
     *
     * @return List<Blog>
     */
    List<Blog> findBlogLi();

    List<Blog> findTitle();

    /**
     * 注释: 查询指定数量的最新的博客部分数据
     *
     * @param number 指定条数
     * @return List<Blog>
     */
    List<Blog> findBlogUpLimit(int number);

    /**
     * 分类查询文章数据
     *
     * @param type 类型(标签)
     * @return List<Blog>
     */
    List<Blog> findBlogByType(String type);

    /**
     * 分页显示文章数据
     *
     * @param pageNo   当前页码数
     * @param dataSize 每页显示的记录数
     * @return PageBean<Blog>
     */
    PageBean<Blog> pagingBlog(Integer pageNo, int dataSize);

    /**
     * 注释: 根据博客ID查询数据
     *
     * @param blogId 博客ID
     * @return Blog
     */
    Blog findBlogById(Integer blogId);

    /**
     * 注释: 指定条件搜索相关博客数据
     *
     * @param value 条件
     * @return List<Blog>
     */
    List<Blog> searchBlog(String value);

    /**
     * 注释: 保存博客数据
     *
     * @param cover   封面
     * @param tags    博客标签
     * @param subject 标题
     * @param content 内容
     * @param intro   简介
     * @param blogId  博客ID,不为空则修改对应博客数据,为空则新增
     * @return ResultValue
     */
    ResultValue<Boolean, Blog> saveBlog(String cover, String tags, String subject, String content, String intro, Integer blogId);
}
