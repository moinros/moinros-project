package com.moinros.project.controller;

import com.moinros.project.model.pojo.Blog;
import com.moinros.project.service.blog.BlogService;
import com.moinros.project.service.system.SystemService;
import com.moinros.project.tool.web.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 注释:
 *
 * @Author moinros
 * @WebSite www.moinros.com
 * @Date 2020/1/30 16:59
 * @Verison 1.0
 */
@Controller
public class IndexController {

    @Autowired
    private SystemService systemService;

    @Autowired
    private BlogService blogService;

    @GetMapping
    public String index(Integer page, Model model) {
        List tagList = systemService.findTagList();
        List blogLatest = blogService.findBlogUpLimit(5);
        PageBean pageBean = blogService.pagingBlog(page, 10);
        model.addAttribute("tagList", tagList);
        model.addAttribute("blogLatest", blogLatest);
        model.addAttribute("pageBean", pageBean);
        return "index";
    }

    /**
     * 显示指定的文章
     *
     * @param mark 文章标签
     * @param bid  文章ID
     */
    @GetMapping("/blog/{mark}/{bid}")
    public String findBlog(@PathVariable(name = "mark") String mark, @PathVariable(name = "bid") Integer bid, Model model) {
        Blog blog = blogService.findBlogById(bid);
        List tagList = systemService.findTagList();
        List blogLatest = blogService.findBlogUpLimit(5);
        model.addAttribute("blogLatest", blogLatest);
        model.addAttribute("tagList", tagList);
        model.addAttribute("blogData", blog);
        return "plugin/blog/blog-data";
    }
}
