package com.moinros;

import org.apache.commons.lang3.StringUtils;

/**
 * 注释:
 *
 * @Author moinros
 * @Date 2020/2/10 0:18
 * @Verison 1.0
 */
public class StringUtilTest {

    public static void main(String[] args) {
        String str1 = "";
        String str2 = "      ";
        System.out.println(str1.length());
        System.out.println(str2.length());

        StringUtils.isEmpty(str1);
    }
}
