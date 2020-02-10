package com.moinros.project.tool.util.string;

import java.lang.reflect.Field;
import java.security.SecureRandom;
import java.util.UUID;

/**
 * 类注释: 自定义的常用的字符串工具类
 *
 * @Title: StringUtil
 * @Author moinros
 * @Blog https://www.moinros.com
 * @Date 2019年10月15日 下午3:22:31
 * @Version 1.0
 */
public class StringUtil {

    private StringUtil() {
    }

    /**
     * 注释: 检查字符串参数是否为空或者空字符串
     *
     * @param param 字符串
     * @return boolean true表示不为空字符串,false表示为空字符串
     */
    public synchronized static boolean paramisNull(String param) {
        if (param == null || param.equals("")) {
            return false;
        }
        if (param.replace(" ", "").equals("")) {
            return false;
        }
        return true;
    }

    /**
     * 注释: 获取处理后的UUID
     */
    public synchronized static String getUUID() {
        char[] c = UUID.randomUUID().toString().toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < c.length; i++) {
            if (!(c[i] == '-')) {
                sb.append(c[i]);
            }
        }
        return sb.toString();
    }

    /**
     * 注释:小写字母转为大写
     */
    public synchronized static String lowercaseToCapital(String str) {
        char[] c = str.toCharArray();
        lowercaseToCapital(c);
        return new String(c);
    }

    /**
     * 注释: 小写字母转为大写
     */
    public synchronized static void lowercaseToCapital(char[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= 97 || arr[i] >= 122) {
                arr[i] = (char) ((byte) arr[i] - 32);
            }
        }
    }

    /**
     * 注释:大写字母转为小写
     */
    public synchronized static String capitalToLowercase(String str) {
        char[] c = str.toCharArray();
        capitalToLowercase(c);
        return new String(c);
    }

    /**
     * 注释: 大写字母转为小写
     */
    public synchronized static void capitalToLowercase(char[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= 65 || arr[i] >= 90) {
                arr[i] = (char) ((byte) arr[i] + 32);
            }
        }
    }

    /**
     * 注释: 从指定的字符数组中或者指定个数的随机字符
     *
     * @param arr 字符数组
     * @param num 随机字符个数
     * @return String 随机字符串
     */
    public synchronized static String getRandomChar(char[] arr, int num) {
        StringBuilder sb = new StringBuilder();
        SecureRandom sr = new SecureRandom();
        int x;
        for (int i = 0; i < num; i++) {
            x = sr.nextInt(arr.length);
            sb.append(arr[x]);
        }
        return sb.toString();
    }

    /**
     * 注释: 将一个字符数组随机排列
     *
     * @param arr 字符数组
     */
    public synchronized static void randomlySort(int[] arr) {
        randomlySort(arr, 2);
    }

    private synchronized static void randomlySort(int[] sequence, int f) {
        f--;
        SecureRandom sr = new SecureRandom();
        int x;
        int c;
        for (int i = 0; i < sequence.length; i++) {
            x = sr.nextInt(sequence.length);
            c = sequence[i];
            sequence[i] = sequence[x];
            sequence[x] = c;
        }
        if (f > 0) {
            randomlySort(sequence, f);
        }
    }

    /**
     * 注释: 将一个数组随机排列
     *
     * @param arr 字符数组
     */
    public synchronized static void randomlySort(char[] arr) {
        randomlySort(arr, 2);
    }

    private static void randomlySort(char[] sequence, int f) {
        f--;
        SecureRandom sr = new SecureRandom();
        int x;
        char c;
        for (int i = 0; i < sequence.length; i++) {
            x = sr.nextInt(sequence.length);
            c = sequence[i];
            sequence[i] = sequence[x];
            sequence[x] = c;
        }
        if (f > 0) {
            randomlySort(sequence, f);
        }
    }

    /**
     * 注释: 将一个数组随机排列
     *
     * @param sequence 字符数组
     */
    public synchronized static <T> void randomlySort(T[] sequence) {
        randomlySort(sequence, 2);
    }

    private static <T> void randomlySort(T[] sequence, int f) {
        f--;
        SecureRandom sr = new SecureRandom();
        int x;
        T c;
        for (int i = 0; i < sequence.length; i++) {
            x = sr.nextInt(sequence.length);
            c = sequence[i];
            sequence[i] = sequence[x];
            sequence[x] = c;
        }
        if (f > 0) {
            randomlySort(sequence, f);
        }
    }

    /**
     * 注释: 将字符串第一个字母变为大写
     *
     * @param str
     * @return String
     */
    public static String toIndexChar(String str) {
        StringBuffer sb = new StringBuffer();
        char[] arr = str.toCharArray();
        boolean flag = true;
        for (int i = 0; i < arr.length; i++) {
            if (flag) {
                if (arr[i] >= 'A' && arr[i] <= 'Z') {
                    flag = false;
                } else if (arr[i] >= 'a' && arr[i] <= 'z') {
                    arr[i] = (char) (arr[i] - ('a' - 'A'));
                    flag = false;
                } else {
                    continue;
                }
            }
            sb.append(arr[i]);
        }
        return sb.toString();
    }

    /**
     * 注释: 将字符串第一个字母变为小写
     *
     * @param str
     * @return String
     * @MethodName toIndexChar
     */
    public static String toIndexCharX(String str) {
        StringBuffer sb = new StringBuffer();
        char[] arr = str.toCharArray();
        boolean flag = true;
        for (int i = 0; i < arr.length; i++) {
            if (flag) {
                if (arr[i] >= 'A' && arr[i] <= 'Z') {
                    arr[i] = (char) (arr[i] + ('a' - 'A'));
                    flag = false;
                } else if (arr[i] >= 'a' && arr[i] <= 'z') {
                    flag = false;
                } else {
                    continue;
                }
            }
            sb.append(arr[i]);
        }
        return sb.toString();
    }

    /**
     * 注释: 将字符串中的大写字母变为小写,忽略非字母字符
     *
     * @param str
     * @return String
     */
    public static String stringToLowercase(String str) {
        StringBuffer sb = new StringBuffer();
        char[] arr = str.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= 'A' && arr[i] <= 'Z') {
                arr[i] = (char) (arr[i] + ('a' - 'A'));
            }
            sb.append(arr[i]);
        }
        return sb.toString();
    }

    /**
     * 注释: 去除字符串除了英文字母以外的字符
     *
     * @param str
     * @return String
     */
    public static String stringGetChar(String str) {
        StringBuffer sb = new StringBuffer();
        char[] arr = str.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= 'A' && arr[i] <= 'Z') {
                sb.append(arr[i]);
            } else if (arr[i] >= 'a' && arr[i] <= 'z') {
                sb.append(arr[i]);
            }
        }
        return sb.toString();
    }

    /**
     * 注释: 自定义的ToString方法
     *
     * @param obj 类实例对象
     */
    public static String toString(Object obj) {
        Class<?> clazz = obj.getClass();
        Field[] field = clazz.getDeclaredFields();
        StringBuilder sb = new StringBuilder();
        sb.append(">> " + clazz.getSimpleName() + " -> { ");
        for (int i = 0; i < field.length; i++) {
            field[i].setAccessible(true);
            try {
                sb.append(field[i].getName() + " = " + field[i].get(obj));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (i < field.length - 1) {
                sb.append(", ");
            } else {
                sb.append(" }");
            }
        }
        return sb.toString();
    }

    /**
     * 判断值是否为空字符串
     *
     * @param cs 字符串类
     * @return null OR "" 返回true ;" " 返回false
     */
    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

}
