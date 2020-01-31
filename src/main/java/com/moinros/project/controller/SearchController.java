package com.moinros.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

    @GetMapping("/search")
    public String search(String value, Model model) {
        model.addAttribute("value", value);
        return "plugin/search";
    }
}
