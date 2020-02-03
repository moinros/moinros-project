package com.moinros.project.tool.util.date;

import java.util.Date;

/**
 * 注释:
 *
 * @Author moinros
 * @WebSite www.moinros.com
 * @Date 2020/2/3 19:27
 * @Verison 1.0
 */
public class DateInfo {

    private String time;
    private Date date;
    private char[] stamp;

    public DateInfo(String time) {
        date = DateFormatUtil.stringToDate(time, DateFormatUtil.TIME_Y_M_D_H_M_S);
        this.time = time;
        stamp = time.toCharArray();
    }

    /**
     * 年
     */
    public String getYear() {
        StringBuilder sb = new StringBuilder();
        sb.append(stamp[0]);
        sb.append(stamp[1]);
        sb.append(stamp[2]);
        sb.append(stamp[3]);
        return sb.toString();
    }

    /**
     * 月
     */
    public String getMonth() {

        StringBuilder sb = new StringBuilder();
        sb.append(stamp[5]);
        sb.append(stamp[6]);
        return sb.toString();
    }

    /**
     * 日
     */
    public String getDay() {
        StringBuilder sb = new StringBuilder();
        sb.append(stamp[8]);
        sb.append(stamp[9]);
        return sb.toString();
    }

    /**
     * 时
     */
    public String getHours() {
        StringBuilder sb = new StringBuilder();
        sb.append(stamp[11]);
        sb.append(stamp[12]);
        return sb.toString();
    }

    /**
     * 分
     */
    public String getMinutes() {
        StringBuilder sb = new StringBuilder();
        sb.append(stamp[14]);
        sb.append(stamp[15]);
        return sb.toString();
    }

    /**
     * 秒
     */
    public String getSeconds() {
        StringBuilder sb = new StringBuilder();
        sb.append(stamp[17]);
        sb.append(stamp[18]);
        return sb.toString();
    }

    /**
     * 毫秒
     */
    public long getTime() {
        return date.getTime();
    }

    public static void main(String[] args) {
        DateInfo date = new DateInfo(DateFormatUtil.getDateTime());
        System.out.println(">> " + date.getYear());
        System.out.println(">> " + date.getMonth());
        System.out.println(">> " + date.getDay());
        System.out.println(">> " + date.getHours());
        System.out.println(">> " + date.getMinutes());
        System.out.println(">> " + date.getSeconds());
    }
}
