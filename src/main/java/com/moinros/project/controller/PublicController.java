package com.moinros.project.controller;

import com.moinros.project.model.dto.ResultValue;
import com.moinros.project.model.dto.enums.UserServiceState;
import com.moinros.project.model.dto.vo.ClientSide;
import com.moinros.project.model.pojo.UserData;
import com.moinros.project.result.Reply;
import com.moinros.project.result.enums.Status;
import com.moinros.project.result.sub.StrConst;
import com.moinros.project.result.vo.WebReply;
import com.moinros.project.service.UserService;
import com.moinros.project.tool.cipher.WebRSAEncrypt;
import com.moinros.project.tool.email.RosSenderEmail;
import com.moinros.project.tool.email.sub.ReadHtmlFile;
import com.moinros.project.tool.util.string.RegexString;
import com.moinros.project.tool.util.string.RegexUtil;
import com.moinros.project.tool.util.string.StringUtil;
import com.moinros.project.tool.web.NetworkUtil;
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
    private UserService userService;

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
        Reply r = new WebReply();
        System.out.println(email);
        if (RegexUtil.equalsRegex(RegexString.REGEX_EMAIL, email)) {
            RosSenderEmail rse = RosSenderEmail.getRosSenderEmail();
            String code = StringUtil.getRandomChar(StrConst.SEQUENCE, 10);
            Map map = new HashMap();
            map.put("${checkcode}", code);
            String html = ReadHtmlFile.readHtmlFile("static/template/mail-checkcode.html", map);
            rse.setRecipient("邮件验证码", email, html);
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
            r.setState(Status.success);
            r.setContent("邮箱验证通过！稍后验证码将通过邮件发送到您的邮箱中！");
        } else {
            r.setState(Status.error);
            r.setContent("邮箱格式不正确！");
        }
        return r;
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

    @ResponseBody
    @PostMapping("/login")
    public Reply login(String username, String password, String url, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        System.out.println(">> " + username);
        System.out.println(">> " + password);
        WebReply r = new WebReply();
        if (username != null && password != null) {
            // 获取客户端消息头数据

            ClientSide client = new ClientSide();
            try {
                NetworkUtil nu = (NetworkUtil) request.getAttribute(StrConst.USER_AGENT);
                if (nu == null) {
                    nu = new NetworkUtil(request);
                }
                client.setClientIp(nu.getIpAddress());
                client.setClientIp(nu.getClientOS());
                client.setBrowserInfo(nu.getBrowserInfo());
            } catch (IOException e) {
                e.printStackTrace();
            }

            // 使用私钥解密密码
            WebRSAEncrypt rsa = new WebRSAEncrypt();
            String cipher = rsa.decrypt(password, session);

            ResultValue<UserServiceState, UserData> rv = userService.login(username, cipher, client);
            if (rv != null) {
                if (rv.getState() == UserServiceState.LOGIN_SUCCESS) {
                    r.setState(Status.success);
                    session.setAttribute(StrConst.USER_DATA, rv.getValue());
                    StrConst.setMessage("登录成功！", "返回：" + url, url, session);
                } else {
                    r.setState(Status.error);
                }
                r.setContent(rv.getState().getTips());
            } else {
                throw new RuntimeException("错误！服务器拒绝了您的请求！");
            }
        } else {
            r.setState(Status.exception);
            r.setContent("登录信息填写不完整！");
        }
        return r;
    }

    @GetMapping("/logout")
    public String logout(String url, HttpSession session) {
        var user = session.getAttribute(StrConst.USER_DATA);
        if (user != null) {
            session.setAttribute(StrConst.USER_DATA, null);
            StrConst.setMessage("你已经退出登录！", url != null ? url : "首页", url != null ? url : "/", session);
        } else {
            StrConst.setMessage("你还没有登录！", url != null ? url : "首页", url != null ? url : "/", session);
        }
        return "redirect:message.html";
    }

}
