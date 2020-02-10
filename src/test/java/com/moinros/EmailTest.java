package com.moinros;

import com.moinros.project.tool.email.sub.ReadHtmlFile;

import java.util.HashMap;
import java.util.Map;

/**
 * 注释:
 *
 * @Author moinros
 * @Date 2020/2/10 14:29
 * @Verison 1.0
 */
public class EmailTest {
    public static void main(String[] args) {
        Map map = new HashMap();
        map.put("${link}", "verify");
        String html = ReadHtmlFile.readHtmlFile("static/template/mail-register.html", map);
        System.out.println(html);
    }
}
