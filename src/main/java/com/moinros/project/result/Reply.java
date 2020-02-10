package com.moinros.project.result;

/**
 * 类注释: 定义了响应结果集的接口
 *
 * @param <C> [content]响应内容
 * @Type View Object
 */
public interface Reply<S, C> {

	/**
	 * 获取状态码
	 * @return
	 */
    S getState();

	/**
	 * 设置状态码
	 * @param state 状态码
	 */
	void setState(S state);

    /**
     * 注释: 设置响应内容
     */
    void setContent(C content);

    /**
     * 注释: 获取响应内容
     */
    C getContent();
}
