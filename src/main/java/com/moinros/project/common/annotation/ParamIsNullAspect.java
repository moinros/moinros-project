package com.moinros.project.common.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class ParamIsNullAspect {

    @Pointcut(value = "@annotation(com.moinros.project.common.annotation.ParamIsNull)")
    public void paramPointCut() {
    }

    @Around("paramPointCut()")
    public Object setValue(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = ((MethodSignature) pjp.getSignature());
        // 得到拦截的方法
        Method method = signature.getMethod();
        ParamIsNull pin = method.getAnnotation(ParamIsNull.class);
        String[] names = signature.getParameterNames();
        Object[] ages = pjp.getArgs();
        Class<?>[] types = signature.getParameterTypes();
        if (pin != null) {
            String[] paramNames = pin.paramName();
            boolean f;
            for (int i = 0; i < paramNames.length; i++) {
                f = true;
                for (int j = 0; j < names.length; j++) {
                    if (paramNames[i].equals(names[j])) {
                        if (String.class == types[j]) {
                            String age = (String) ages[j];
                            if (age != null) {
                                if ("".equals(age)) {
                                    ages[j] = null;
                                } else {
                                    age = age.replace(" ", "");
                                    if ("".equals(age)) {
                                        ages[j] = null;
                                    }
                                }
                            }
                        }
                        f = false;
                        break;
                    }
                }
                if (f) {
                    throw new RuntimeException(">> @ParamIsNull 指定了参数名 [ " + paramNames[i] + " ], 但是在 [ " + pjp.getSignature().getDeclaringType() + '.' + method.getName() + "() ]方上并没有找到这个参数！！");
                }
            }
        }
        return pjp.proceed(ages);
    }

}
