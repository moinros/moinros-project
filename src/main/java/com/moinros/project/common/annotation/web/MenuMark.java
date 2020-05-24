package com.moinros.project.common.annotation.web;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注释: 菜单标记
 *
 * @Auther: moinros
 * @WebSite www.moinros.com
 * @Date: 2020/2/12 1:51
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface MenuMark {

    /**
     * 菜单名
     *
     * @return [String]
     */
    String name();

    /**
     * 菜单标记
     *
     * @return [String]
     */
    String mark();
}
