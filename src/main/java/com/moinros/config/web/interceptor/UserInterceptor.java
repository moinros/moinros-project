package com.moinros.config.web.interceptor;

import com.moinros.project.result.sub.StrConst;
import com.moinros.project.tool.util.string.RegexUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 注释:
 *
 * @Auther: moinros
 * @WebSite www.moinros.com
 * @Date: 2020/1/15 17:10
 */
public class UserInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // System.out.println(">> 进入拦截器 - UserInterceptor");
        HttpSession session = request.getSession();
        String uri = request.getRequestURI();
        boolean f = RegexUtil.equalsRegex(RegexUtil.REGEX_URI_USERDATA, uri);
        if (f) {
            return true;
        }
        Object obj = session.getAttribute(StrConst.USER_DATA);
        if (obj == null) {
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }
}
