package com.moinros.project.common.annotation;

import java.lang.annotation.*;

/**
 * 注释: 标记需要对Tag标记进行转换的方法,用于Service层取出数据时。将字符串形式的Tag转换为数组
 *
 * @Auther: moinros
 * @WebSite www.moinros.com
 * @Date: 2020/2/12 1:53
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TagMapping {

    /**
     * @return
     */
    String[] types() default {};
}
