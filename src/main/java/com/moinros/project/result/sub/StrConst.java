package com.moinros.project.result.sub;

import com.moinros.project.result.vo.MessageInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 注释:
 *
 * @Author moinros
 * @WebSite www.moinros.com
 * @Date 2020/1/16 17:12
 * @Verison 1.0
 */
public class StrConst {

    private StrConst() { }

    public static final char[] SEQUENCE =
            {
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                '2', '3', '4', '5', '6', '7', '8', '9'
            };

    public static final String USER_DATA = "USER_DATA";

    public static final String ADMIN_DATA = "ADMIN_DATA";

    public static final String ERROR_MESSAGE = "ERROR_MESSAGE";

    public static final String MESSAGE = "message";
    public static final String MSG_RESULT = "msg_result";

    public static final String USER_AGENT = "USER_AGENT";

    public static final String CHECKCODE = "CHECKCODE";

    public static final String ENCRYPT_KEY_NAME = "RSAEncryptKey";

    public static MessageInfo setMessage(String title, String content, String url, HttpSession session) {
        MessageInfo msg = new MessageInfo();
        msg.setTitle(title);
        msg.setContent(content);
        msg.setUrl(url);
        session.setAttribute(MESSAGE, msg);
        return msg;
    }

    /**
     * 设置错误页面信息
     */
    public static void setError(String message, HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().setAttribute(StrConst.ERROR_MESSAGE, message);
        response.sendRedirect("/error.html");
    }

}
