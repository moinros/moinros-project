package com.moinros.project.controller.user;

import com.moinros.project.common.annotation.tool.ParamIsNull;
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
import com.moinros.project.tool.util.string.RegexUtil;
import com.moinros.project.tool.web.NetworkUtil;
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

/**
 * 注释:
 *
 * @Author moinros
 * @WebSite www.moinros.com
 * @Date 2020/2/7 14:16
 * @Verison 1.0
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String register() {
        return "plugin/user/user-register";
    }

    @ResponseBody
    @PostMapping("/register")
    @ParamIsNull(paramName = {"nickname", "password", "username", "checkcode"})
    public Reply register(String nickname, String password, String username, String checkcode, HttpSession session) {
        Reply r = new WebReply();
        System.out.println(nickname);
        System.out.println(password);
        System.out.println(username);
        System.out.println(checkcode);
        if (nickname != null && password != null && username != null && checkcode != null) {
            StrConst.setMessage("注册成功！", "网站首页", "/", session);
            r.setState(Status.success);
            r.setContent("注册成功！");
        }
        return r;
    }

    @ResponseBody
    @PostMapping("/is/exist/user/email")
    @ParamIsNull(paramName = {"email"})
    public Reply isMailboxExist(@RequestBody String email) {
        Reply r = new WebReply();
        if (email != null) {
            if (RegexUtil.isEmail(email)) {
                boolean flag = userService.mailboxExist(email);
                if (flag) {
                    r.setState(Status.NO);
                    r.setContent("邮箱已注册！");
                } else {
                    r.setState(Status.OK);
                    r.setContent("可以使用的邮箱！");
                }
            } else {
                r.setState(Status.ERROR);
                r.setContent("邮箱格式不正确！");
            }
        } else {
            r.setState(Status.EXCEPTION);
            r.setContent("请输入邮箱！");
        }
        return r;
    }

    @ResponseBody
    @PostMapping("/is/exist/user/nickname")
    @ParamIsNull(paramName = {"nickName"})
    public Reply isNicknameExist(@RequestBody String nickName) {
        Reply r = new WebReply();
        if (nickName != null) {
            boolean flag = userService.nicknameExist(nickName);
            if (flag) {
                r.setState(Status.NO);
                r.setContent("这个昵称已经有人使用了哦~");
            } else {
                r.setState(Status.OK);
                r.setContent("可以使用的昵称！");
            }
        } else {
            r.setState(Status.EXCEPTION);
            r.setContent("请输入昵称！");
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
