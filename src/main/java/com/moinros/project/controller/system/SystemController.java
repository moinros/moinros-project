package com.moinros.project.controller.system;

import com.moinros.project.common.annotation.ParamIsNull;
import com.moinros.project.common.annotation.system.MenuMark;
import com.moinros.project.common.annotation.system.MenuTable;
import com.moinros.project.model.dto.ResultValue;
import com.moinros.project.model.pojo.Blog;
import com.moinros.project.result.Reply;
import com.moinros.project.result.enums.Status;
import com.moinros.project.result.sub.StrConst;
import com.moinros.project.result.vo.WebReply;
import com.moinros.project.service.blog.BlogService;
import com.moinros.project.service.system.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 注释:
 *
 * @Author moinros
 * @WebSite www.moinros.com
 * @Date 2020/1/17 0:16
 * @Verison 1.0
 */
@Controller
@RequestMapping("/system")
public class SystemController {

    @Autowired
    private SystemService systemService;
    @Autowired
    private BlogService blogService;

    @GetMapping("/index")
    @MenuTable(value = {@MenuMark(name = "side_bar_meun", mark = "system")})
    public String index() {
        return "plugin/system/system-index";
    }

    @GetMapping("/blog/write")
    @MenuTable(value = {@MenuMark(name = "side_bar_meun", mark = "system")})
    public String blogWrite(Model model) {
        List taglist = systemService.findTagList();
        model.addAttribute("tagList", taglist);
        return "plugin/system/sub/blog-write";
    }

    @GetMapping("/blog/update/{id}")
    @MenuTable(value = {@MenuMark(name = "side_bar_meun", mark = "system")})
    public String blogUpdate(@PathVariable(name = "id") Integer id, Model model, HttpSession session) {
        if (id != null) {
            Blog blog = blogService.findBlogById(id);
            List li = systemService.findTagList();
            model.addAttribute("tagList", li);
            model.addAttribute("blogData", blog);
            return "plugin/system/sub/blog-update";
        } else {
            session.setAttribute(StrConst.ERROR_MESSAGE, "不存在的博客ID！！");
            return "redirect:/error.html";
        }
    }

    @ResponseBody
    @PostMapping("/blog/update/save/{bid}")
    @ParamIsNull(paramName = {"cover", "subject", "intro", "tags", "content"})
    public Reply blogUpdateSave(String cover, String subject, String intro, String tags, String content, @PathVariable(name = "bid") Integer bid, HttpSession session) {
        return savaBlog(cover, tags, subject, content, intro, bid, session);
    }

    @ResponseBody
    @PostMapping("/blog/save")
    @ParamIsNull(paramName = {"cover", "subject", "intro", "tags", "content"})
    public Reply blogSave(String cover, String subject, String intro, String tags, String content, HttpSession session) {
        return savaBlog(cover, tags, subject, content, intro, null, session);
    }

    private Reply savaBlog(String cover, String tags, String subject, String content, String intro, Integer bid, HttpSession session) {
        WebReply r = new WebReply();
        if (subject != null && intro != null && tags != null && content != null) {
            ResultValue<Boolean, Blog> rv = blogService.saveBlog(cover, tags, subject, content, intro, bid);
            if (rv.getState()) {
                r.setState(Status.success);
                r.setContent("保存成功！");
                session.setAttribute(StrConst.MESSAGE, StrConst.setMessage("保存成功！", "跳转到该博客页面！", "/system/blog/find/" + rv.getValue().getBlogId()));
            } else {
                r.setState(Status.error);
                r.setContent("保存失败！");
            }
        } else {
            r.setState(Status.exception);
            r.setContent("博客数据填写不完整，保存失败！");
        }
        return r;
    }

    @GetMapping("/blog/find/{id}")
    @MenuTable(value = {@MenuMark(name = "side_bar_meun", mark = "system")})
    public String blogData(@PathVariable(name = "id") Integer id, Model model, HttpSession session) {
        Blog blog = blogService.findBlogById(id);
        if (blog != null) {
            model.addAttribute("blogData", blog);
        } else {
            session.setAttribute(StrConst.ERROR_MESSAGE, "没有查询到数据！这篇博客可能已经被删除了哦~");
            return "redirect:/error.html";
        }
        return "plugin/system/sub/blog-data";
    }

    @GetMapping("/blog/find/list")
    @MenuTable(value = {@MenuMark(name = "side_bar_meun", mark = "system")})
    public String blogList(Model model) {
        List li = blogService.findBlogLi();
        if (li != null) {
            model.addAttribute("blogList", li);
        }
        return "plugin/system/sub/blog-list";
    }

    @GetMapping("/blog/find/type")
    @ParamIsNull(paramName = "value")
    @MenuTable(value = {@MenuMark(name = "side_bar_meun", mark = "system")})
    public String blogType(String value, Model model) {
        if (value != null) {
            List li = blogService.findBlogByType(value);
            if (li != null) {
                model.addAttribute("blogList", li);
                return "plugin/system/sub/blog-list";
            }
        }
        return "plugin/system/sub/blog-type";
    }

}
