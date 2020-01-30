package com.moinros.project.common.annotation.base64;

import com.moinros.config.Config;
import com.moinros.project.common.exception.Base64CodeException;
import com.moinros.project.common.exception.Base64ExceptionEnum;
import com.moinros.project.tool.web.PageBean;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.List;

/**
 * 类注释: 自定义的Base64编码注解AOP切面类,对应了{@link Base64Decoder},{@link Base64Encoder}
 *
 * @Author moinros
 * @Date 2019年12月12日 下午11:39:10
 * @Version 1.0
 */
@Aspect
@Component
public class Base64Aspect {

    // 注解标记在切点方法上时触发
    @Pointcut(value = "@annotation(com.moinros.project.common.annotation.base64.Base64Decoder)")
    public void decoderPointCut() {
    }

    // 注解标记在切点方法上时触发
    @Pointcut(value = "@annotation(com.moinros.project.common.annotation.base64.Base64Encoder)")
    public void encoderPointCut() {
    }

    // 注解标记在类上时触发
    @Pointcut(value = "@within(com.moinros.project.common.annotation.base64.Base64Decoder)")
    public void pointCut() {
    }

    // 方法返回时触发
    @AfterReturning(value = "pointCut()", returning = "result")
    public void afterInClass(JoinPoint joinPoint, Object result) {
        decoder(joinPoint, result, true);
    }

    // 方法返回时触发
    @AfterReturning(value = "decoderPointCut()", returning = "result")
    public void afterInMethod(JoinPoint joinPoint, Object result) {
        decoder(joinPoint, result, false);
    }

    /**
     * 后置增强<br>
     * 注释: Base64解码<br>
     * 当方法进行返回的时候，returning属性是指定方法参数中的result来接收返回参数，这样就可以修改返回参数
     */
    private void decoder(JoinPoint joinPoint, Object result, boolean f) {
        // 得到切面方法
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        if (result == null) {
            Class<?> c = method.getReturnType();
            // 当方法没有返回值时,返回值类型为void。(void.class等价于 Void.TYPE)
            if (c == Void.TYPE) {
                throw new Base64CodeException(Base64ExceptionEnum.METHOD_IS_VOID, joinPoint.getSignature().getDeclaringType(), method.getName());
            }
            return;
        }
        Base64Decoder bd = method.getAnnotation(Base64Decoder.class);
        if (f) {
            if (bd != null && bd.exclude()) {
                Class<?> cl = joinPoint.getSignature().getDeclaringType();
                if (cl.getAnnotation(Base64Decoder.class) != null) {
                    throw new Base64CodeException(Base64ExceptionEnum.REPEATED, joinPoint.getSignature().getDeclaringType(), method.getName());
                }
            }
        }
        Decoder decoder = Base64.getDecoder();
        if (bd == null || bd.exclude()) {
            Class<?> clazz = result.getClass();
            if (List.class.isAssignableFrom(clazz)) {
                List<?> list = (List<?>) result;
                if (list.size() > 0 && list.get(0) != null) {
                    for (int i = 0; i < list.size(); i++) {
                        setParam(list.get(i), key -> decoder.decode(key));
                    }
                }
            } else if (PageBean.class.isAssignableFrom(clazz)) {
                PageBean bean = (PageBean) result;
                if (bean.isNull()) {
                    for (int i = 0; i < bean.getList().size(); i++) {
                        setParam(bean.get(i), key -> decoder.decode(key));
                    }
                }
            } else {
                setParam(result, key -> decoder.decode(key));
            }
        }
    }

    /**
     * 环绕增强<br>
     * 注释: 对注有@Base64Encoder注解的参数进行Base64编码
     */
    @Around("encoderPointCut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = ((MethodSignature) pjp.getSignature());
        // 得到拦截的方法
        Method method = signature.getMethod();
        Base64Encoder be = method.getAnnotation(Base64Encoder.class);
        // 获取参数值
        Object[] paranValues = pjp.getArgs();
        if (paranValues == null) {
            return pjp.proceed(null);
        } else {
            if (paranValues.length == 0) {
                throw new Base64CodeException(Base64ExceptionEnum.METHOD_NO_PARAM, pjp.getSignature().getDeclaringType(), method.getName());
            }
        }
        // 获取指定参数的注解
        Base64EncoderParam[] bep = be.param();
        Encoder encoder = Base64.getEncoder();
        // 指定了多个参数
        if (bep.length != 0) {
            // 获取方法参数名
            String[] paramNames = signature.getParameterNames();

            // 获取方法参数类型
            Class<?>[] parameterTypes = method.getParameterTypes();

            boolean f;
            for (int x = 0; x < bep.length; x++) {
                f = true;
                for (int i = 0; i < paramNames.length; i++) {
                    // 匹配方法参数名
                    if (bep[x].name().equals(paramNames[i])) {
                        // 匹配方法参数类型
                        if (bep[x].type() == parameterTypes[i]) {
                            // 判断参数是否为String类型
                            if (parameterTypes[i] == String.class) {
                                String str = (String) paranValues[i];
                                byte[] key = encoder.encode(str.getBytes(Config.UTF8));
                                paranValues[i] = new String(key);
                            } else {
                                setParam(parameterTypes[i], paranValues[i], key -> encoder.encode(key));
                            }
                            f = false;
                            break;
                        } else {
                            throw new Base64CodeException(Base64ExceptionEnum.PARAM_TYPE_MATCH, pjp.getSignature().getDeclaringType(), bep[x].name(), bep[x].type().getName(), parameterTypes[i].getName(), method.getName());
                        }
                    }
                }
                if (f) {
                    throw new Base64CodeException(Base64ExceptionEnum.NO_FIND_PARAM, pjp.getSignature().getDeclaringType(), bep[x].name(), method.getName());
                }
            }
        } else {
            // 只有一个参数
            for (int i = 0; i < paranValues.length; i++) {
                if (paranValues[i] != null) {
                    Class<?> clazz = paranValues[i].getClass();
                    if (List.class.isAssignableFrom(clazz)) {
                        List<?> list = (List<?>) paranValues[i];
                        for (int j = 0; j < list.size(); j++) {
                            setParam(list.get(i), key -> encoder.encode(key));
                        }
                    } else {
                        setParam(paranValues[i], key -> encoder.encode(key));
                    }
                }
            }
        }
        return pjp.proceed(paranValues);
    }

    /**
     * 注释: 对传入对象中指定属性字段进行编码
     *
     * @param obj  修改对象实例
     * @param base 编码方法的接口
     */
    private void setParam(Object obj, Base64Code base) {
        Class<?> clazz = obj.getClass();
        setParam(clazz, obj, base);
    }

    public synchronized void setParam(Class<?> clazz, Object obj, Base64Code base) {
        // 判断是否为Object、枚举类型,不是则继续向下查找(必须判断;否则会进入死循环)
        if (clazz == null || clazz == Object.class || Enum.class.isAssignableFrom(clazz)) {
            return;
        }
        Base64Mark bm = clazz.getAnnotation(Base64Mark.class);
        // 在obj中标记了@Base64Mark
        if (bm != null) {
            Field[] field = clazz.getDeclaredFields();
            for (int j = 0; j < field.length; j++) {
                // 访问私有字段
                field[j].setAccessible(true);
                if (field[j].getAnnotation(Base64Mark.class) != null) {
                    try {
                        Object o = field[j].get(obj);
                        // 匹配成功,对字段值进行编码
                        if (o != null) {
                            String str = (String) o;
                            byte[] key = null;
                            try {
                                key = str.getBytes(Config.UTF8);
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                            byte[] b = base.encrypt(key);
                            field[j].set(obj, new String(b));
                        } else {
                            Base64Mark fieldMark = field[j].getAnnotation(Base64Mark.class);
                            if (!fieldMark.fieldIsNull()) {
                                throw new Base64CodeException(Base64ExceptionEnum.FIELD_IS_NULL, clazz, field[j].getName());
                            }
                        }
                    } catch (IllegalArgumentException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            // obj没有标记了@Base64Mark
            // 判断是否为结果集形式的返回值,使用递归继续差找
            Field[] field = clazz.getDeclaredFields();
            for (int i = 0; i < field.length; i++) {
                // 访问私有字段
                field[i].setAccessible(true);
                Object o = null;
                try {
                    // 获取字段值
                    o = field[i].get(obj);
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                if (o != null) {
                    // 继续匹配字段值
                    if (List.class.isAssignableFrom(o.getClass())) {
                        List<?> list = (List<?>) o;
                        for (int j = 0; j < list.size(); j++) {
                            setParam(list.get(i), base);
                        }
                    } else {
                        setParam(o, base);
                    }
                }
            }
        }
        // 使用递归查找父类字段
        setParam(clazz.getSuperclass(), obj, base);
    }

}
