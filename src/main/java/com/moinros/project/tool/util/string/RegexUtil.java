package com.moinros.project.tool.util.string;

import java.util.regex.Pattern;

/**
 * 类注释: 匹配正则表达式的工具类
 *
 * @author moinros
 * @version 1.0
 * @Title: RegexUtil
 * @website https://www.moinros.com
 * @date 2019年10月12日 上午12:53:59
 */
public class RegexUtil {

    /**
     * URI_OF_UID : 正则表达式,匹配URI中的uid
     */
    public static final String URI_OF_UID = "(?<=/)[0-9]+?(?=/)";

    /**
     * REGEX_EMAIL : 验证邮箱的正则表达式
     */
    public static final String REGEX_EMAIL = "^([a-zA-Z]|[0-9])(\\w|\\-)+@[a-zA-Z0-9]+\\.([a-zA-Z]{2,4})$";

    /**
     * REGEX_PHONE : 手机号正则表达式
     */
    public static final String REGEX_PHONE = "^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$";

    /**
     * REGEX_PASSWORD : 一个正则表达式:可以包含数字、字母、下划线、@、.、,、=、+、-、并且要同时含有数字和字母，且长度要在8-16位之间。
     */
    public static final String REGEX_PASSWORD = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z_|@|.|,|=|+|-]{8,16}$";

    /**
     * REGEX_URI_USERDATA : 匹配/user/data/000 的正则表达式
     */
    public static final String REGEX_URI_USERDATA = "^.*/user/[0-9]+/data$";

    /**
     * 注释: 传入指定的正则表达式与字符串；返回匹配结果
     *
     * @param regex 正则表达式
     * @param param 匹配的字符串
     * @return boolean 匹配返回true；不匹配||param == null返回false
     */
    public static boolean equalsRegex(String regex, String param) {
        if (param != null) {
            Pattern pattern = Pattern.compile(regex);
            return pattern.matcher(param).matches();
        } else {
            return false;
        }
    }

    /**
     * 正则表达式匹配邮箱
     *
     * @param email 邮箱
     * @return [boolean] true OR false
     */
    public static boolean isEmail(String email) {
        return equalsRegex(REGEX_EMAIL, email);
    }

    /**
     * 正则表达式匹配手机号
     *
     * @param phone 手机号
     * @return [boolean] true OR false
     */
    public static boolean isPhone(String phone) {
        return equalsRegex(REGEX_PHONE, phone);
    }
}
