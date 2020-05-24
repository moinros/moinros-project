package com.moinros.project.controller;

import com.moinros.project.service.blog.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * 注释:
 *
 * @Author moinros
 * @WebSite www.moinros.com
 * @Date 2020/1/31 22:45
 * @Verison 1.0
 */
@Controller
public class SearchController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/search")
    public String search(String value, Model model) {
        //model.addAttribute("value", value);
        if (value != null) {
            List list = blogService.searchBlog(value);
            model.addAttribute("value", value);
            model.addAttribute("value_list", list);
        }
        return "plugin/search";
    }
}
