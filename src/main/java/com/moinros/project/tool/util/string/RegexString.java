package com.moinros.project.tool.util.string;

public class RegexString {

	private RegexString() {
	}

	/**
	 * URI_OF_UID : 正则表达式,匹配URI中的uid
	 */
	public static final String URI_OF_UID = "(?<=/)[0-9]+?(?=/)";

	/**
	 * REGEX_EMAIL : 验证邮箱的正则表达式
	 */
	public static final String REGEX_EMAIL = "^([a-zA-Z]|[0-9])(\\w|\\-)+@[a-zA-Z0-9]+\\.([a-zA-Z]{2,4})$";

	/**
	 * REGEX_PASSWORD : 一个正则表达式:可以包含数字、字母、下划线、@、.、,、=、+、-、并且要同时含有数字和字母，且长度要在8-16位之间。
	 */
	public static final String REGEX_PASSWORD = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z_|@|.|,|=|+|-]{8,16}$";

	/**
	 * REGEX_URI_USERDATA : 匹配/user/data/000 的正则表达式
	 */
	public static final String REGEX_URI_USERDATA = "^.*/user/[0-9]+/data$";

}
