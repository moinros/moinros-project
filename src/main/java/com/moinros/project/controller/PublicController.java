package com.moinros.project.controller;

import com.moinros.project.model.pojo.system.Checkcode;
import com.moinros.project.result.Reply;
import com.moinros.project.result.enums.Status;
import com.moinros.project.result.sub.StrConst;
import com.moinros.project.result.vo.WebReply;
import com.moinros.project.service.system.CheckcodeService;
import com.moinros.project.tool.cipher.WebRSAEncrypt;
import com.moinros.project.tool.email.RosSenderEmail;
import com.moinros.project.tool.email.bean.Recipient;
import com.moinros.project.tool.email.sub.ReadHtmlFile;
import com.moinros.project.tool.util.string.RegexUtil;
import com.moinros.project.tool.util.string.StringUtil;
import com.moinros.project.tool.web.WebCheckCode;
import com.moinros.project.tool.web.socket.Heartbeat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

    @Autowired
    private CheckcodeService checkcodeService;

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

    @ResponseBody
    @PostMapping("/sys/get/mail/checkcode")
    public Reply senderEmail(@RequestBody String email, HttpSession session) {
     //   System.out.println(email);
        String checkcode = StringUtil.getRandomChar(StrConst.SEQUENCE, 10);
        Map map = new HashMap();
        map.put("${checkcode}", checkcode);
        String html = ReadHtmlFile.readHtmlFile("static/template/mail-checkcode.html", map);

        Checkcode check = new Checkcode();
        check.setCheckcodeKey(email);
        check.setCheckcodeValue(checkcode);
        boolean f = checkcodeService.addCheckcode(check);
        Reply reply;
        if (f) {
            reply = sendMail(email, "邮箱验证码", html, session);
            if (reply.getState() == Status.success) {
                reply.setContent("验证通过！稍后验证码将发送到您的邮箱中，请注意查看！");
            }
        } else {
            reply = new WebReply();
            reply.setState(Status.exception);
            reply.setContent("验证码生成失败！请重试！");
        }
        return reply;
    }

    /**
     * 发送邮件
     *
     * @param email   收件邮箱地址
     * @param subject 邮件标题
     * @param content 邮件内容
     * @param session [HttpSession]
     * @return [Reply]响应结果
     */
    public Reply sendMail(String email, String subject, String content, HttpSession session) {
        Reply reply = new WebReply();
        if (RegexUtil.isEmail(email)) {
            RosSenderEmail rse = RosSenderEmail.getRosSenderEmail();
            Recipient recipient = new Recipient();
            recipient.setSubject(subject);
            recipient.setContent(content);
            recipient.setRecipient(email);
            rse.setRecipient(recipient);
            rse.threadSend(() -> {
                Heartbeat heartbeat = SocketController.CLIENT.get(session.getId());
                if (heartbeat == null) {
                    heartbeat = new Heartbeat();
                }
                heartbeat.setSate(1);
                heartbeat.setTimer(System.currentTimeMillis());
                heartbeat.setSid(session.getId());
                heartbeat.setTask("邮件发送成功！请注意查看！");
            }, () -> {
                Heartbeat heartbeat = SocketController.CLIENT.get(session.getId());
                if (heartbeat == null) {
                    heartbeat = new Heartbeat();
                }
                heartbeat.setSate(1);
                heartbeat.setTimer(System.currentTimeMillis());
                heartbeat.setSid(session.getId());
                heartbeat.setTask("邮件发送失败！请注意查看！");
            });
            reply.setState(Status.success);
        } else {
            reply.setState(Status.error);
            reply.setContent("邮箱格式不正确！");
        }
        return reply;
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
