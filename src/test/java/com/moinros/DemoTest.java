package com.moinros;

import com.moinros.project.tool.email.RosSenderEmail;
import com.moinros.project.tool.email.sub.EmailConfig;
import com.moinros.project.tool.util.date.DateFormatUtil;

import java.util.Date;

public class DemoTest {

    public static void main(String[] args) {
        // test2();
        EmailConfig conf = EmailConfig.getConfig();
        conf.setConfigPath("D:/java/config/moinros-config.properties");
        conf.init();

        RosSenderEmail rse = RosSenderEmail.getRosSenderEmail();

        rse.setRecipient("邮件标题", "moinros@qq.com", "邮件的文本内容");
        rse.threadSend(() -> {
            System.out.println("邮件发送成功时调用");
        }, () -> {
            System.out.println("邮件发送失败时调用");
        });

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
