package com.moinros.project.model.dto.vo;

import com.moinros.project.model.dto.ResultValue;

/**
 * 类注释: 实现了 {@link ResultValue} 接口的的dto
 *
 * @Author moinros
 * @Date 2019年12月20日 下午5:20:05
 * @Version 1.0
 * @param <S> 返回结果状态码
 * @param <V> 返回结果value
 */
public class ResultStateValue<S, V> implements ResultValue<S, V> {

	private S state;
	private V value;

	@Override
	public S getState() {
		return state;
	}

	@Override
	public void setState(S state) {
		this.state = state;
	}

	@Override
	public V getValue() {
		return value;
	}

	@Override
	public void setValue(V value) {
		this.value = value;
	}

}
