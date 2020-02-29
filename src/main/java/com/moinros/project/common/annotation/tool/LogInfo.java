package com.moinros.project.common.annotation.tool;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注释: 设置日志头信息
 *
 * @Author moinros
 * @Date 2020/2/15 22:48
 * @Verison 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogInfo {

    /**
     * 日志标记
     *
     * @return
     */
    String value() default "";

    Class clazz() default Object.class;


}
