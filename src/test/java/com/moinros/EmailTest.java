package com.moinros;

import com.moinros.project.result.sub.StrConst;
import com.moinros.project.tool.email.sub.ReadHtmlFile;
import com.moinros.project.tool.util.string.StringUtil;

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
        String checkcode = StringUtil.getRandomChar(StrConst.SEQUENCE, 10);
        Map map = new HashMap();
        map.put("${checkcode}", checkcode);
        String html = ReadHtmlFile.readHtmlFile("static/template/mail-checkcode.html", map);
        System.out.println(html);
    }
}
