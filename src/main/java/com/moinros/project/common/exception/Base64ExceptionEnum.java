package com.moinros.project.common.exception;
/**
 * 注释: 
 *
 * @Auther: moinros
 * @WebSite www.moinros.com
 * @Date: 2020/1/15 19:13
 */
public enum Base64ExceptionEnum {

	/**
	 * FIELD_IS_NULL : 字段为 null
	 */
	FIELD_IS_NULL,

	/**
	 * NO_FIND_PARAM : 找不到参数
	 */
	NO_FIND_PARAM,

	/**
	 * PARAM_TYPE_MATCH : 参数类型不匹配
	 */
	PARAM_TYPE_MATCH,

	/**
	 * METHOD_NO_PARAM : 方法没有参数
	 */
	METHOD_NO_PARAM,

	/**
	 * METHOD_IS_VOID : 方法没有返回值
	 */
	METHOD_IS_VOID,

	/**
	 * REPEATED : 重复标记
	 */
	REPEATED,
}
