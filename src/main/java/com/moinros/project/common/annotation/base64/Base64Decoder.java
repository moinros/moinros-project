package com.moinros.project.common.annotation.base64;

import java.lang.annotation.*;

/**
 * 类注释: 标记需要使用Base64解码的类或者方法<br>
 * 注意: 此注解必须配合 {@link Base64Mark}注解使用<br>
 * 使用此注解时,将此注解标记在指定的类或者方法实例上,标记在接口中不会生效!<br>
 * 同时还要在需要使用 {@link Base64Decoder} 功能的类和字段上标注 {@link Base64Mark} ,否则此注解不生效!<br>
 * 如果方法返回值是基本数据类型,此注解不生效!<br>
 * 当 {@link Base64Decoder} 标注在类上的时候,可以在特定方法上使用 {@link Base64Decoder}(exclude =
 * false) 来标记该方法忽略此注解
 *
 * @Author moinros
 * @Date 2019年12月13日 下午9:04:35
 * @Version 1.0
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME) // 运行时注解
@Documented
public @interface Base64Decoder {

    /**
     * 注释: 标注是否执行解码操作,默认为true<br>
     * 设置为false时表示忽略该切点方法
     */
    boolean exclude() default true;
}
