package com.moinros.project.common.annotation.base64;

import java.lang.annotation.*;

/**
 * 类注释: 标记需要使用Base64编码的方法<br>
 * 注意: 此注解必须配合 {@link Base64Mark} 注解使用<br>
 * 使用此注解时,将此注解标记在指定的方法实例上(标记在接口中不会生效),同时在需要使用 {@link Base64Encoder} 功能的类和字段上标注
 * {@link Base64Mark},否则此注解不生效<br>
 * 如果方法参数是基本数据类型, 或者方法有多个参数时,可以配合 {@link Base64EncoderParam} 一起使用<br>
 * 如果要指定多个参数可以使用 {@link Base64EncoderParam} 指定参数名和类型<br>
 *
 * @Author moinros
 * @Date 2019年12月13日 下午9:04:59
 * @Version 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME) // 运行时注解
@Documented
public @interface Base64Encoder {

    public Base64EncoderParam[] param() default {};

}
