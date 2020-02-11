package com.moinros.project.common.annotation.tool;

import java.lang.annotation.*;

/**
 * 类注释: 判断传入的参数是否为空字符串,是的话将其替换为null
 *
 * @Author moinros
 * @Date 2019年12月23日 下午6:15:52
 * @Version 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ParamIsNull {

    String[] paramName();

    //Class paramType() default String.class;

}
