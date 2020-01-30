package com.moinros.project.tool.util.string;

import java.util.regex.Pattern;

/**
 * 类注释: 匹配正则表达式的工具类
 *
 * @Title: RegexUtil
 * @author moinros
 * @website https://www.moinros.com
 * @date 2019年10月12日 上午12:53:59
 * @version 1.0
 */
public class RegexUtil {

	/**
	 * 注释: 传入指定的正则表达式与字符串；返回匹配结果
	 *
	 * @param regex 正则表达式
	 * @param param 匹配的字符串
	 * @return boolean 匹配返回true；不匹配||param == null返回false
	 */
	public static synchronized boolean equalsRegex(String regex, String param) {
		if (param != null) {
			Pattern pattern = Pattern.compile(regex);
			return pattern.matcher(param).matches();
		} else {
			return false;
		}
	}

}
