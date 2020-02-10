package com.moinros.project.controller.user;

import com.moinros.project.result.Reply;
import com.moinros.project.result.enums.Status;
import com.moinros.project.result.vo.WebReply;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping("/register")
    public String register() {
        return "plugin/user/user-register";
    }

    @ResponseBody
    @PostMapping("/is/exist/user/email")
    public Reply isMailboxExist() {
        Reply r = new WebReply();
        r.setState(Status.OK);
        r.setContent("可以使用的邮箱！");
        return r;
    }

    @ResponseBody
    @PostMapping("/is/exist/user/nickname")
    public Reply isNicknameExist() {
        Reply r = new WebReply();
        r.setState(Status.OK);
        r.setContent("可以使用的昵称！");
        return r;
    }


}
