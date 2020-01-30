package com.moinros.project.result.enums;

import com.moinros.project.result.State;

/**
 * 类注释: 表示响应状态枚举
 */
public enum Status implements State {

	/**
	 * success 注释: 成功
	 */
	success,

	/**
	 * error 注释: 失败
	 */
	error,

	/**
	 * exception 注释: 出现异常
	 */
	exception,;

}