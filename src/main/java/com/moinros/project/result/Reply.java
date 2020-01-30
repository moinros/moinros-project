package com.moinros.project.result;

/**
 * 类注释: 定义了响应结果集的接口
 * 
 * @param <C> [content]响应内容
 * @Type View Object
 */
public interface Reply<C> {

	/**
	 * 注释: 设置响应内容
	 */
	public void setContent(C content);

	/**
	 * 注释: 获取响应内容
	 */
	public C getContent();
}
