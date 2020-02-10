package com.moinros.project.common.annotation.web;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注释: 标记HTML页面 MARK
 *
 * @Auther: moinros
 * @WebSite www.moinros.com
 * @Date: 2020/2/9 21:37
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PageMark {

    /**
     * HTML 标记
     */
    String mark();

    /**
     * HTML 名字
     */
    String name();

}
