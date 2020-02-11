package com.moinros.project.common.annotation.web;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注释: 标记需要显示要页面的标记和菜单列表
 *
 * @Auther: moinros
 * @WebSite www.moinros.com
 * @Date: 2020/2/12 1:52
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MenuTable {

    /**
     * 菜单标记
     *
     * @return [MenuMark]
     */
    MenuMark[] value() default {};

}
