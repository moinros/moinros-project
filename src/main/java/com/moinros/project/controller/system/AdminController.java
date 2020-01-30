package com.moinros.project.controller.system;

import com.moinros.project.model.pojo.system.AdminLog;
import com.moinros.project.result.Reply;
import com.moinros.project.result.enums.Status;
import com.moinros.project.result.sub.StrConst;
import com.moinros.project.result.vo.WebReply;
import com.moinros.project.service.system.AdminService;
import com.moinros.project.tool.cipher.WebRSAEncrypt;
import com.moinros.project.tool.web.NetworkUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 注释:
 *
 * @Author moinros
 * @WebSite www.moinros.com
 * @Date 2020/1/30 22:13
 * @Verison 1.0
 */
@Controller
public class AdminController {



    @Autowired
    private AdminService adminService;

    //---------- ---------- ---------- ---------- | login | ---------- ---------- ---------- ----------

    /**
     * 进入管理员登录页面
     */
    @GetMapping("/system/admin/login")
    public String loginPage() {
        return "plugin/system/admin-login";
    }

    /**
     * 登出
     */
    @GetMapping("/system/admin/logout")
    public String logout(HttpSession session) {
        var admin = session.getAttribute(StrConst.ADMIN_DATA);
        session.setAttribute(StrConst.ADMIN_DATA, null);
        if (admin != null) {
            session.setAttribute(StrConst.MESSAGE, StrConst.setMessage("退出后台系统！", "返回网站首页", "/"));
        } else {
            session.setAttribute(StrConst.MESSAGE, StrConst.setMessage("你还没有登录哦！", "返回网站首页", "/"));
        }
        return "redirect:/message.html";
    }

    /**
     * 管理员登录
     *
     * @param username  管理员账号
     * @param password  登录密码
     * @param checkcode 验证码
     * @param request   HttpServletRequest
     * @return Reply 响应结果
     */
    @ResponseBody
    @PostMapping("/system/admin/login")
    public Reply login(String username, String password, String checkcode, HttpServletRequest request) {
        HttpSession session = request.getSession();
        // 获取客户端消息头数据
        NetworkUtil nu = (NetworkUtil) request.getAttribute(StrConst.USER_AGENT);
        String ip = null;
        String os = null;
        try {
            ip = nu.getIpAddress();
            os = nu.getClientOS();
        } catch (IOException e) {
            e.printStackTrace();
        }
        WebReply r = new WebReply();
        if (ip != null && os != null) {
            // 匹配验证码是否正确
            String code = (String) session.getAttribute(StrConst.CHECKCODE);
            session.setAttribute(StrConst.CHECKCODE, null);
            if (checkcode != null && code != null) {
                if (code.equalsIgnoreCase(checkcode)) {

                    // 使用私钥解密密码
                    WebRSAEncrypt rsa = new WebRSAEncrypt();
                    String cipher = rsa.decrypt(password, session);
                    AdminLog log = new AdminLog();
                    log.setClientOs(nu.getHeader());
                    log.setClientType(os);
                    log.setClientIp(ip);
                    var admin = adminService.login(username, cipher, log);
                    if (admin != null) {
                        session.setAttribute(StrConst.ADMIN_DATA, admin);
                        session.setAttribute(StrConst.MESSAGE, StrConst.setMessage("登录成功！", "跳转到后台管理首页", "/system/index"));
                        r.setState(Status.success);
                        r.setContent("登录成功！");
                    } else {
                        r.setState(Status.error);
                        r.setContent("账号或者密码错误！");
                    }
                    return r;
                }
            }
            r.setState(Status.error);
            r.setContent("验证码错误！");
        } else {
            r.setState(Status.exception);
            r.setContent("禁止访问！！");
        }
        return r;
    }

    //---------- ---------- ---------- ---------- | login | ---------- ---------- ---------- ----------
}
