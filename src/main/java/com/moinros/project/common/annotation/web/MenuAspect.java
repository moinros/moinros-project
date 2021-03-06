package com.moinros.project.common.annotation.web;

import com.moinros.project.service.system.SystemService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 注释: 通过注解的方式设置动态菜单的值
 *
 * @Author moinros
 * @WebSite www.moinros.com
 * @Date 2020/1/30 23:44
 * @Verison 1.0
 */
@Aspect
@Component
public class MenuAspect {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private SystemService service;

    @Pointcut(value = "@annotation(com.moinros.project.common.annotation.web.MenuTable)")
    public void menuMarkPointCut() {
    }

    @Before(value = "menuMarkPointCut()")
    public void onStart(JoinPoint joinPoint) {
        // 获取切面方法
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        // 获取方法上定义的注解
        MenuTable mt = method.getAnnotation(MenuTable.class);
        if (mt != null) {
            for (int i = 0; i < mt.value().length; i++) {
                List list = service.findMenuSon(mt.value()[i].mark());
                if (list != null) {
                    request.setAttribute(mt.value()[i].name(), list);
                }
            }
        }
    }

}
