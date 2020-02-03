package com.moinros;

import com.moinros.project.tool.util.date.DateFormatUtil;

import java.util.Date;

/**
 * 注释:
 *
 * @Author moinros
 * @WebSite www.moinros.com
 * @Date 2020/2/3 18:20
 * @Verison 1.0
 */
public class DemoTest {

    public static void main(String[] args) {
        test2();
    }

    public static void test2() {
        String time = DateFormatUtil.getDateTime();

        Date date = DateFormatUtil.stringToDate(time, DateFormatUtil.TIME_Y_M_D_H_M_S);
        System.out.println(">> " + date.getTime());
        System.out.println(">> " + date.getYear());
    }

    public static void test1() {

        String time = DateFormatUtil.getDateTime();
        System.out.println(time);
        char[] arr = time.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 7; i++) {
            if (arr[i] == '-') {
                arr[i] = '年';
            }
        }
        sb.append('月');
        System.out.println(">> " + sb.toString());
    }

}
