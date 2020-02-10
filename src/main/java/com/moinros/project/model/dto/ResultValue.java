package com.moinros.project.model.dto;

/**
 * 类注释: 定义了Service接口在返回结果时,可能会出现结果状态和结果一起返回的情况
 *
 * @param <S> 状态码
 * @param <V> 返回结果
 * @Author moinros
 * @Date 2019年12月20日 下午4:39:12
 * @Version 1.0
 */
public interface ResultValue<S, V> {

    S getState();

    void setState(S state);

    V getValue();

    void setValue(V value);
}
