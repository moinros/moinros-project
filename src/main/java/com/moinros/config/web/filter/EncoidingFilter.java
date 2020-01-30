package com.moinros.config.web.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 注释: 自定义字符编码过滤器
 *
 * @Author [ moinros ]
 * @Website [ https://www.moinros.com ]
 * @Title EncoidingFilter
 * @Version [ V - 1.0.0 β ]
 * @Data [ 2019年6月18日 下午2:36:12 ]
 */
public class EncoidingFilter implements javax.servlet.Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        request.setCharacterEncoding("utf-8");
        request.setAttribute("focustext", "向着梦的方向艰难前行！");
        response.setCharacterEncoding("utf-8");
        response.setContentType("txt/html;charset=utf-8");
        chain.doFilter(request, response);
    }


}
