package com.moinros.project.controller;

import com.moinros.project.result.sub.StrConst;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * 注释: 负责页面跳转的控制器
 *
 * @Author moinros
 * @WebSite www.moinros.com
 * @Date 2020/1/16 14:49
 * @Verison 1.0
 */
@Controller
public class PageController {

    /**
     * 进入友情链接页面
     */
    @GetMapping("/friends.html")
    public String friend() {
        return "other/friend";
    }

    /**
     * 进入档案页面
     */
    @GetMapping("/archive.html")
    public String archive() {
        return "other/archive";
    }

    /**
     * 进入网站日志页面
     */
    @GetMapping("/log.html")
    public String log() {
        return "other/log";
    }

    /**
     * 进入网站说明页面
     */
    @GetMapping("/about.html")
    public String about() {
        return "other/about";
    }

    /**
     * 进入留言板
     */
    @GetMapping("/msg.html")
    public String msg() {
        return "other/msg";
    }

    /**
     * 进入错误页面
     */
    @RequestMapping("/error.html")
    public String error(HttpSession session, Model model) {
        Object message = session.getAttribute(StrConst.ERROR_MESSAGE);
        session.setAttribute(StrConst.ERROR_MESSAGE, null);
        model.addAttribute(StrConst.ERROR_MESSAGE, message);
        return "error";
    }

    /**
     * 进入消息页面
     */
    @RequestMapping("/message.html")
    public String message(HttpSession session, Model model) {
        Object message = session.getAttribute(StrConst.MESSAGE);
        session.setAttribute(StrConst.MESSAGE, null);
        model.addAttribute(StrConst.MESSAGE, message);
        return "message";
    }
}
