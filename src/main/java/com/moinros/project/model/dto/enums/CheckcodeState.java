package com.moinros.project.model.dto.enums;

/**
 * 类注释: 匹配邮箱验证码时可能出现的异常状态枚举
 *
 * @Author moinros
 * @Date 2019年12月20日 下午11:35:51
 * @Version 1.0
 */
public enum CheckcodeState {

    /**
     * success : 验证通过
     */
    success("验证通过！"),

    /**
     * error : 没有查询到验证码
     */
    error("无效的验证码！"),

    /**
     * overtime : 验证码超时
     */
    overtime("验证码超时！"),

    /**
     * invalid : 验证码失效
     */
    invalid("验证码已失效！"),

    /**
     * isnull : 传入了空值
     */
    isnull("Checkcode is null!!"),

    /**
     * mismatch : 验证码不匹配
     */
    mismatch("验证码不匹配！"),
    ;

    private String code;

    private CheckcodeState(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
