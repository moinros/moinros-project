package com.moinros.project.controller;

import com.moinros.project.result.Reply;
import com.moinros.project.result.enums.Status;
import com.moinros.project.result.vo.WebReply;
import com.moinros.project.tool.cipher.WebRSAEncrypt;
import com.moinros.project.tool.web.WebCheckCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 注释: 通用资源控制器
 *
 * @Author moinros
 * @WebSite www.moinros.com
 * @Date 2020/1/17 17:50
 * @Verison 1.0
 */
@Controller
public class PublicController {

    /**
     * 获取图形验证码
     */
    @GetMapping("/sys/get/img/checkcode.jpeg")
    public void getCheckCodeImg(HttpServletRequest request, HttpServletResponse response) {
        try {
            new WebCheckCode(request, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取公钥
     *
     * @param session HttpSession
     * @return 公钥
     */
    @ResponseBody
    @PostMapping("/token/rsa/get/public/key")
    public Reply getPublicKey(HttpSession session) {
        WebRSAEncrypt rsa = new WebRSAEncrypt();
        WebReply r = new WebReply();
        try {
            String key = rsa.getPublicKey(session);
            r.setState(Status.success);
            r.setContent(key);
        } catch (Exception e) {
            r.setState(Status.exception);
            r.setContent("获取公钥失败，请重试！");
        }
        return r;
    }
}
