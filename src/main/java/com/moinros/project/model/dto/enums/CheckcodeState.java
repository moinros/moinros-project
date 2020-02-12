package com.moinros.project.model.dto.enums;

/**
 * 注释: 匹配邮箱验证码时可能出现的异常状态枚举
 *
 * @Auther: moinros
 * @WebSite www.moinros.com
 * @Date: 2020/2/11 18:21
 */
public enum CheckcodeState {

    /**
     * SUCCESS : 验证通过
     */
    SUCCESS("验证通过！"),

    /**
     * ERROR : 没有查询到验证码
     */
    ERROR("无效的验证码！"),

    /**
     * OVERTIME : 验证码超时
     */
    OVERTIME("验证码超时！"),

    /**
     * INVALID : 验证码失效
     */
    INVALID("验证码已失效！"),

    /**
     * IS_NULL : 传入了空值
     */
    IS_NULL("Checkcode is null!!"),

    /**
     * MISMATCH : 验证码不匹配
     */
    MISMATCH("验证码不匹配！"),
    ;

    private String code;

    private CheckcodeState(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
