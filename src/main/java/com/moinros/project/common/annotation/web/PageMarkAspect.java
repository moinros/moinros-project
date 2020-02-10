package com.moinros.project.common.annotation.web;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 注释: 处理 @PageMark 的AOP切面类
 *
 * @Author moinros
 * @Date 2020/2/9 21:40
 * @Verison 1.0
 */
@Aspect
@Component
public class PageMarkAspect {

    @Autowired
    private HttpServletRequest request;

    @Pointcut(value = "@annotation(com.moinros.project.common.annotation.web.PageMark)")
    public void pageMarkPointCut() {
    }

    @Before(value = "pageMarkPointCut()")
    public void onStart(JoinPoint joinPoint) {
        // 获取切面方法
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        // 获取方法上定义的注解
        PageMark pm = method.getAnnotation(PageMark.class);
        if (pm != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("{'mark':'");
            sb.append(pm.mark());
            sb.append("','name':'");
            sb.append(pm.name());
            sb.append("'}");
            request.setAttribute("PAGE_MARK", sb.toString());
        }
    }

}
