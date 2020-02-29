package com.moinros.project.common.annotation.tool;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 注释:
 *
 * @Author moinros
 * @Date 2020/2/15 22:50
 * @Verison 1.0
 */
@Aspect
@Component
public class LogInfoAspect {

    @Pointcut(value = "@annotation(com.moinros.project.common.annotation.tool.LogInfo)")
    public void logPointCut() {
    }

    // 得到拦截的方法
    Method getMethod(JoinPoint joinPoint) {
        MethodSignature signature = ((MethodSignature) joinPoint.getSignature());
        return signature.getMethod();
    }

    // 前置通知,方法开始前执行
    @Before(value = "logPointCut()")
    public void aVoid(JoinPoint joinPoint) {
        Method method = getMethod(joinPoint);
        LogInfo li = method.getAnnotation(LogInfo.class);
        Logger log = LoggerFactory.getLogger(method.getDeclaringClass());
        log.info("========== ========== " + li.value() + " LOG THE START ========== ==========");
    }

    // 方法返回时触发
    @AfterReturning(value = "logPointCut()", returning = "result")
    public void afterInMethod(JoinPoint joinPoint, Object result) {
        Method method = getMethod(joinPoint);
        LogInfo li = method.getAnnotation(LogInfo.class);
        Logger log = LoggerFactory.getLogger(method.getDeclaringClass());
        log.info("========== ========== " + li.value() + " LOG THE END ========== ==========");
    }

    // 方法异常时触发
    @AfterThrowing(value = "logPointCut()", throwing = "e")
    public void logException(JoinPoint joinPoint, Exception e) {
        Method method = getMethod(joinPoint);
        LogInfo li = method.getAnnotation(LogInfo.class);
        Logger log = LoggerFactory.getLogger(method.getDeclaringClass());
        log.info("========== ========== " + li.value() + " LOG THE ERROR ========== ==========");
        log.error("运行时异常！ MSG: " + e.toString());
    }

}
