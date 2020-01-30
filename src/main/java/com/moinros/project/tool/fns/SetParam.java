package com.moinros.project.tool.fns;

/**
 * 类注释: 函数式接口 - 设置Param值
 *
 * @Author moinros
 * @Date 2019年12月23日  下午10:05:39
 * @Version 1.0
 */
@FunctionalInterface
public interface SetParam {
    /**
     * 设置Param的值
     *
     * @param param 值
     * @param <T> 泛型param
     */
    <T> void setParam(T param);
}
