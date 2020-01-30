package com.moinros.project.tool.fns;

/**
 * 注释: 函数式接口 - 声明带参数的回调函数
 *
 * @Auther: moinros
 * @WebSite www.moinros.com
 * @Date: 2020/1/16 3:50
 */
@FunctionalInterface
public interface CallbackParam {

    /**
     * 回调函数
     *
     * @param param 参数
     * @param <T>   泛型
     * @return 犯回T类型的值
     */
    public <T> T fn(T param);

}

