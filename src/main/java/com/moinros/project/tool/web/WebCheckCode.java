package com.moinros.project.tool.web;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 类注释: 生成验证码的工具类
 *
 * @Title: CheckCode
 * @Author moinros
 * @Blog https://www.moinros.com
 * @Date 2019年11月20日  下午10:46:13
 * @Version 1.0
 */
public class WebCheckCode {

    public WebCheckCode() {
    }

    /**
     * 使用默认设置生成图形验证码
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws IOException 生成图形验证码时可能出现异常
     */
    public WebCheckCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        createVerifyCodeImg(request, response);
    }

    /**
     * width : 图片的宽度
     */
    private static int WIDTH = 160;
    /**
     * height : 图片的高度
     */
    private static int HEIGHT = 32;
    /**
     * sessionName : 存放在session中的随机字符名字
     */
    private static String SESSION_NAME = "CHECKCODE";
    /**
     * charSequence : 随机字符数组序列
     */
    private static char[] CHAR_SEQUENCE =
            {
                    'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                    'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                    '2', '3', '4', '5', '6', '7', '8', '9'
            };
    /**
     * colorArray : 字符颜色数组
     */
    private static final Color[] COLOR_ARRAY =
            {
                    new Color(0, 0, 0),
                    new Color(0, 0, 255),
                    new Color(0, 64, 0),
                    new Color(0, 128, 128),
                    new Color(64, 0, 128),
                    new Color(64, 64, 64),
                    new Color(128, 0, 128),
                    new Color(128, 64, 0),
                    new Color(128, 128, 0),
                    new Color(255, 0, 0),
                    new Color(255, 0, 255)
            };

    /**
     * 注释: 在内存中创建一张验证码图片,并写入流
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws IOException 图片写入流时可能出现IO异常
     */
    public void createVerifyCodeImg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CreateImage ci = new CreateImage();
        BufferedImage img = ci.getImage(WIDTH, HEIGHT, COLOR_ARRAY, CHAR_SEQUENCE);
        // 将随机字符存在session中
        request.getSession().setAttribute(SESSION_NAME, ci.getRandomString());
        // 设置响应头通知浏览器以图片的形式打开
        response.setContentType("image/jpeg");// 等同于response.setHeader("Content-Type", "image/jpeg");
        // 设置响应头控制浏览器不要缓存
        response.setDateHeader("expries", -1);
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        ImageIO.write(img, "jpeg", response.getOutputStream());
    }

    /**
     * 类注释: 在内存中创建图片的工具类
     *
     * @Title: CreateImage
     * @Author moinros
     * @Blog https://www.moinros.com
     * @Date 2019年11月18日 下午10:18:15
     * @Version 1.0
     */
    private class CreateImage {

        private String randomString;

        /**
         * 注释: 传入指定参数， 生成验证码图片
         *
         * @param width      生成的图片长度/单位px
         * @param height     生成的图片宽度/单位px
         * @param color      生成的随机字符的字体颜色
         * @param randomChar 指定随机字符序列
         * @return BufferedImage 返回在内存中生成的图片对象
         */
        public BufferedImage getImage(int width, int height, Color[] color, char[] randomChar) {
            // 1.在内存中创建一张图片
            BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            // 2.得到图片
            Graphics g = bi.getGraphics();
            // 3.设置图片的背影色
            setBackGround(g, Color.WHITE, width, height);
            // 4.设置图片的边框
            setBorder(g, Color.WHITE, width, height);
            // 5.在图片上画干扰线
            drawRandomLine(g, width, height);
            // 6.在图片上写随机字符
            randomString = drawRandomNum((Graphics2D) g, width, height, color, randomChar);
            return bi;
        }

        /**
         * 注释: 设置图片的背影颜色
         */
        public void setBackGround(Graphics g, Color color, int width, int height) {
            // 设置背影颜色
            g.setColor(color);
            // 填充区域
            g.fillRect(0, 0, width, height);
        }

        /**
         * 注释: 设置图片的边框
         */
        public void setBorder(Graphics g, Color color, int width, int height) {
            // 设置边框颜色
            g.setColor(color);
            // 边框区域
            g.drawRect(1, 1, width - 2, height - 2);
        }

        /**
         * 注释: 在图片上画随机样式的线条
         */
        public void drawRandomLine(Graphics g, int width, int height) {
            int x;
            // 设置线条个数并画线
            for (int i = 0; i < 24; i++) {
                // 设置随机颜色的线条
                g.setColor(new Color(getRandomNum(), getRandomNum(), getRandomNum()));
                x = getRandomNum();
                // 在图片上画随机位置+随机长度的直线或者弧线
                if (x % 2 == 0) {
                    g.drawLine(getRandomNum(width), getRandomNum(height), getRandomNum(width), getRandomNum(height));
                } else {
                    g.drawArc(getRandomNum(width), getRandomNum(height), width, height, getRandomNum(getRandomNum()), getRandomNum());
                }
            }
        }

        /**
         * 注释: 在图片上写随机字符
         *
         * @param g        图片
         * @param width    图片长度
         * @param height   图片高度
         * @param color    字体颜色
         * @param sequence 随机字符序列
         */
        public String drawRandomNum(Graphics2D g, int width, int height, Color[] color, char[] sequence) {

            // 设置字体样式.分别为字体样式,类型,大小
            int size = (int) (height * 0.7);
            g.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, size));

            StringBuilder sb = new StringBuilder();
            // 根据图片长和宽,计算生成的随机字符个数
            int num = getCharNum(width, height);

            int x = (int) (height * 0.3);
            int index = width / num;
            for (int i = 0; i < num; i++) {
                // 设置字体颜色
                g.setColor(color[getRandomNum(color.length)]);
                // 生成随机字符
                String c = getRandomChar(sequence) + "";
                // 随机设置字体旋转角度
                int degree = getRandomNum(90) - 45;
                // 正向旋转
                g.rotate(Math.toRadians(degree), x, size);
                g.drawString(c, x, size);
                // 反向旋转
                g.rotate(Math.toRadians(-degree), x, size);
                sb.append(c);
                x += index;
            }
            return sb.toString();
        }

        /**
         * 注释:根据图片长和宽,计算生成的随机字符个数
         *
         * @param width  图片长度
         * @param height 图片宽度
         */
        private int getCharNum(int width, int height) {
            return width > height ? division(width, height) : division(height, width);
        }

        // 除法
        private int division(int x, int y) {
            return x / y;
        }

        /**
         * 注释: 产生0~255的随机数
         */
        private int getRandomNum() {
            return (int) (Math.random() * 256);
        }

        /**
         * 注释:获取 0~x 之间的随机数
         */
        private int getRandomNum(int x) {
            return (int) (Math.random() * x);
        }

        /**
         * 注释: 生成随机字符
         */
        private char getRandomChar(char[] randomChar) {
            int randomNum = (int) (Math.random() * randomChar.length);
            return randomChar[randomNum];
        }

        /**
         * 注释: 获取生成的随机字符串
         */
        public String getRandomString() {
            return randomString;
        }
    }

}
