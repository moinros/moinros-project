package com.moinros.project.result.enums;

import com.moinros.project.result.State;

/**
 * 类注释: 表示响应状态枚举
 */
public enum Status implements State {

    /**
     * success : 成功
     */
    success,

    /**
     * SUCCESS : 成功
     */
    SUCCESS,

    /**
     * error : 失败
     */
    error,

    /**
     * ERROR : 失败
     */
    ERROR,

    /**
     * exception : 服务器出现异常
     */
    exception,

    /**
     * EXCEPTION : 服务器出现异常
     */
    EXCEPTION,

    /**
     * OK : 正常
     */
    OK,

    /**
     * NO : 异常
     */
    NO,

    /**
     * MSG : 新的消息
     */
    MSG,
    ;

}