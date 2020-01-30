package com.moinros.project.common.annotation.base64;

/**
 * 类注释: 指定编码的函数接口;编码或者解码需单独实现此接口
 */
@FunctionalInterface
public interface Base64Code {

    /**
     * 注释: 对参数进行编码
     *
     * @param key 需要编码的参数
     * @return byte[]    编码后的参数
     */
    byte[] encrypt(byte[] key);
}
