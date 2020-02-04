package com.moinros.config.web.interceptor;

import com.moinros.config.Config;
import com.moinros.project.result.sub.StrConst;
import com.moinros.project.tool.web.NetworkUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 注释:
 *
 * @Author moinros
 * @WebSite www.moinros.com
 * @Date 2020/1/15 16:54
 * @Verison 1.0
 */
public class SystemInterceptor implements HandlerInterceptor {

    private static final String URL_LOGIN = "/system/admin/login"; // 管理员登录url
    private static final String URL_LOGOUT = "/system/admin/logout"; // 管理员退出登录url

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取客户端IP地址
        NetworkUtil nu = new NetworkUtil(request);
        String ip = nu.getIpAddress();
        boolean flag = false;
        if (ip != null) {
            for (int i = 0; i < Config.ACCESS.length; i++) {
                // 判断IP是否为服务器允许访问的IP
                if (Config.ACCESS[i].equals(ip)) {
                    request.setAttribute(StrConst.USER_AGENT, nu);
                    flag = true;
                    break;
                }
            }
        }
        if (flag) {
            String uri = request.getRequestURI();
            // 通过设备检查,匹配管理员登录页面URL,
            if (!URL_LOGIN.equals(uri) && !URL_LOGOUT.equals(uri)) {
                // 判断管理员是否登录
                var session = request.getSession();
                var admin = session.getAttribute(StrConst.ADMIN_DATA);
                if (admin == null) {
                    flag = false;
                    // 管理员未登录禁止访问,重定向到登录页面
                    StrConst.setMessage("管理员未登录！禁止访问！", "跳转到管理员登录页面", URL_LOGIN, session);
                    response.sendRedirect("/message.html");
                }
            }
        } else {
            // 设备没有在允许列表中,禁止访问
            StrConst.setError("权限不足！禁止访问！", request, response);
        }
        return flag;
    }
}
