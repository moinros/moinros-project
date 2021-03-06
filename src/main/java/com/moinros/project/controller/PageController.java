package com.moinros.project.controller;

import com.moinros.project.common.annotation.web.PageMark;
import com.moinros.project.model.pojo.Blog;
import com.moinros.project.model.pojo.Friend;
import com.moinros.project.result.sub.StrConst;
import com.moinros.project.result.vo.Archive;
import com.moinros.project.service.blog.BlogService;
import com.moinros.project.service.other.FriendService;
import com.moinros.project.service.system.SystemService;
import com.moinros.project.tool.util.date.DateInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private BlogService blogService;
    @Autowired
    private FriendService friendService;
    @Autowired
    private SystemService systemService;

    /**
     * 进入友情链接页面
     */
    @GetMapping("/friends.html")
    @PageMark(mark = "friend", name = "友链")
    public String friend(Model model) {
        List<Friend> li = friendService.findFriend();
        model.addAttribute("friendList", li);
        List tagList = systemService.findTagList();
        List blogLatest = blogService.findBlogUpLimit(5);
        model.addAttribute("tagList", tagList);
        model.addAttribute("blogLatest", blogLatest);
        return "other/friend";
    }

    /**
     * 进入文章档案页面
     */
    @GetMapping("/archive.html")
    @PageMark(mark = "archive", name = "归档")
    public String archive(Model model) {
        List<Blog> li = blogService.findTitle();
        if (li != null && li.size() > 0) {
            List<Archive> archives = sort(li);
            model.addAttribute("archives", archives);
        }
        List tagList = systemService.findTagList();
        List blogLatest = blogService.findBlogUpLimit(5);
        model.addAttribute("tagList", tagList);
        model.addAttribute("blogLatest", blogLatest);
        return "other/archive";
    }

    /**
     * 文章归档
     *
     * @param li
     * @return
     */
    private List<Archive> sort(List<Blog> li) {
        List<Archive> list = new ArrayList();
        boolean flag;
        for (int i = 0; i < li.size(); i++) {
            DateInfo date = new DateInfo(li.get(i).getEditTime());
            Archive archive = new Archive();
            archive.setDate(date);
            li.get(i).setEditTime(date.getDay());
            archive.add(li.get(i));
            flag = true;
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).getDate().getYear().equals(date.getYear())) {
                    if (list.get(j).getDate().getMonth().equals(date.getMonth())) {
                        list.get(j).add(li.get(i));
                        flag = false;
                    }
                }
            }
            if (flag) {

                list.add(archive);
            }
        }
        return list;
    }

    /**
     * 进入网站日志页面
     */
    @GetMapping("/log.html")
    @PageMark(mark = "log", name = "日志")
    public String log() {
        return "other/log";
    }

    /**
     * 进入网站说明页面
     */
    @GetMapping("/about.html")
    @PageMark(mark = "about", name = "关于")
    public String about() {
        return "other/about";
    }

    /**
     * 进入留言板
     */
    @GetMapping("/msg.html")
    @PageMark(mark = "msg", name = "留言板")
    public String msg(Model model) {
        List tagList = systemService.findTagList();
        List blogLatest = blogService.findBlogUpLimit(5);
        model.addAttribute("tagList", tagList);
        model.addAttribute("blogLatest", blogLatest);
        return "other/msg";
    }

    @GetMapping("/test")
    public String test() {
        return "test";
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
