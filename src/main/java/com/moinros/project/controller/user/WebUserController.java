package com.moinros.project.controller.user;

import com.moinros.project.result.Reply;
import com.moinros.project.result.enums.Status;
import com.moinros.project.result.sub.StrConst;
import com.moinros.project.result.vo.WebReply;
import com.moinros.project.result.vo.WebUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 注释:
 *
 * @Author moinros
 * @WebSite www.moinros.com
 * @Date 2020/2/7 15:17
 * @Verison 1.0
 */
abstract class WebUserController {

    public Reply login(WebUser userdata, HttpServletRequest request, HttpServletResponse response) {
        Reply r = new WebReply();
        if (userdata != null) {
            HttpSession session = request.getSession();
            if (userdata.getCheckcode() != null) {
                String code = (String) session.getAttribute(StrConst.CHECKCODE);
            }
        } else {
            r.setState(Status.exception);
            r.setContent("值为空！");
        }
        return r;
    }
}
