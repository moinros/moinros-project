package com.moinros.project.common.annotation.base64;

import java.lang.annotation.*;

/**
 * 类注释: 标记需要使用Base64编码的方法参数<br>
 * 注意:此注解必须配合 {@link Base64Encoder} 使用,单独使用不生效!
 *
 * @Author moinros
 * @Date 2019年12月13日 下午9:05:09
 * @Version 1.0
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Base64EncoderParam {

    /**
     * 注释: 指定参数名字
     */
    String name();

    /**
     * 注释: 指定参数类型
     */
    Class<?> type();
}
