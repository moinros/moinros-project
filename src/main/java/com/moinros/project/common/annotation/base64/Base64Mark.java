package com.moinros.project.common.annotation.base64;

import java.lang.annotation.*;

/**
 * 类注释: 与 {@link Base64Encoder} 和 {@link Base64Decoder} 配对的注解<br>
 * 用于标记需要使用 {@link Base64Encoder} 或者 {@link Base64Decoder} 的类、字段、参数<br>
 * 单独使用不生效!
 *
 * @Author moinros
 * @Date 2019年12月13日 下午9:05:21
 * @Version 1.0
 */
@Target({ElementType.FIELD, ElementType.TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME) // 运行时注解
@Inherited
@Documented
public @interface Base64Mark {

    /**
     * 注释: 修饰字段时字段是否能为空值,默认可以为空<br>
     * 默认可以为空,设置为false时如果注解字段如果为null则抛出异常
     */
    public boolean fieldIsNull() default true;
}
