package com.moinros.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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

    @GetMapping
    public String index() {
        return "index";
    }
}
