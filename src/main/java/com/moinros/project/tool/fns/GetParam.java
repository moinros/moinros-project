package com.moinros.project.tool.fns;

/**
 * 类注释: 函数式接口 - 获取Param值
 *
 * @Author moinros
 * @Date 2019年12月23日 下午10:05:05
 * @Version 1.0
 */
@FunctionalInterface
public interface GetParam {
    /**
     *  获取Param值
     * @param <T 泛型Param
     * @return Param值
     */
    <T> T getParam();

}
