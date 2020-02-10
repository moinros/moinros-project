package com.moinros.project.controller;

import com.moinros.project.common.annotation.tool.ParamIsNull;
import com.moinros.project.model.dto.ResultValue;
import com.moinros.project.model.dto.enums.UserServiceState;
import com.moinros.project.model.pojo.Comment;
import com.moinros.project.model.pojo.Friend;
import com.moinros.project.model.pojo.UserData;
import com.moinros.project.result.Reply;
import com.moinros.project.result.enums.Status;
import com.moinros.project.result.sub.StrConst;
import com.moinros.project.result.vo.WebReply;
import com.moinros.project.service.UserService;
import com.moinros.project.service.other.CommentService;
import com.moinros.project.service.other.FriendService;
import com.moinros.project.tool.email.RosSenderEmail;
import com.moinros.project.tool.email.bean.Recipient;
import com.moinros.project.tool.email.sub.ReadHtmlFile;
import com.moinros.project.tool.util.string.RegexString;
import com.moinros.project.tool.util.string.RegexUtil;
import com.moinros.project.tool.util.string.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 注释:
 *
 * @Author moinros
 * @WebSite www.moinros.com
 * @Date 2020/2/8 16:30
 * @Verison 1.0
 */
@Controller
public class ComtController {

    @Autowired
    private CommentService service;
    @Autowired
    private UserService userService;
    @Autowired
    private FriendService friendService;

    private static final Status ERROR = Status.error;
    private static final Status SUCCESS = Status.success;
    private static final Status EXCEPTION = Status.exception;

    /**
     * 保存评论
     *
     * @param uid      UID
     * @param username 用户昵称
     * @param imgSrc   用户头像
     * @param nickName 新用户昵称
     * @param email    新用户邮箱
     * @param link     新用户网站链接
     * @param url      提交评论页面URL
     * @param mark     提交评论页面标记
     * @param article  评论文本内容
     */
    @ResponseBody
    @PostMapping("/comment/save")
    @ParamIsNull(paramName = {"username", "imgSrc", "nickName", "email", "link", "url", "mark", "article"})
    public Reply save(Integer uid, String username, String imgSrc, String nickName, String email, String link, String url, String mark, String article, HttpSession session) {
        WebReply r = new WebReply();
        UserData user = null;
        // 判断是否传入USER数据
        if (uid != null && uid.intValue() > 0 && username != null && imgSrc != null) {
            user = (UserData) session.getAttribute(StrConst.USER_DATA);
            // 判断 session 中是否有USER数据
            if (user != null) {
                if (user.getUserId().equals(uid)) {
                    // 传入的 uid 与 session 中的匹配，验证通过
                } else {
                    // 传入的 uid 与 session 中的不匹配，拒绝请求
                    r.setState(Status.exception);
                    r.setContent("用户数据异常！服务器拒绝了您的请求！");
                    return r;
                }
            }
        }

        // 用户没有登录,判断是否为新用户
        if (user == null) {
            if (nickName != null && email != null) {
                // 匹配邮箱的正则表达式
                if (RegexUtil.equalsRegex(RegexString.REGEX_EMAIL, email)) {
                    // 生成24位的随机密码
                    String password = StringUtil.getRandomChar(StrConst.SEQUENCE, 24);
                    ResultValue<UserServiceState, UserData> rv = userService.register(nickName, email, password);
                    // 注册成功！向用户发送邮件提示
                    if (rv.getState() == UserServiceState.REGISTER_SUCCESS) {
                        RosSenderEmail rse = RosSenderEmail.getRosSenderEmail();
                        Map map = new HashMap();
                        map.put("${link}", "verify");
                        String html = ReadHtmlFile.readHtmlFile("static/template/mail-register.html", map);
                        Recipient recipient = new Recipient();
                        recipient.setSubject("新用户注册!");
                        recipient.setRecipient(email);
                        recipient.setContent(html);
                        rse.setRecipient(recipient);
                        rse.threadSend(() -> {

                            }, () -> {

                        });

                    }
                } else {
                    r.setState(ERROR);
                    r.setContent("邮箱格式不正确！");
                    return r;
                }
            } else {
                r.setState(ERROR);
                r.setContent("请填写完整的昵称和邮箱再评论！");
                return r;
            }
        }

        r.setState(Status.error);
        return r;
    }

    /**
     * @param article  评论内容
     * @param mark     评论页面标记
     * @param replyId  回复的评论ID，没有回复则为null
     * @param url      当前页面URL
     * @param nickName 用户昵称
     * @param email    用户邮箱
     * @param link     网站链接
     * @param request
     * @return
     */
    @ResponseBody
    @PostMapping("/comment/save/test")
    @ParamIsNull(paramName = {"article", "mark", "url", "nickName", "email", "link"})
    public Reply saveComment(String article, String mark, Integer replyId, String url, String nickName, String
            email, String link, HttpServletRequest request) {
        HttpSession session = request.getSession();
        WebReply r = new WebReply();

        UserData user = (UserData) session.getAttribute(StrConst.USER_DATA);
        if (user == null) {
            String password = StringUtil.getRandomChar(StrConst.SEQUENCE, 24);
            ResultValue<UserServiceState, UserData> rv = userService.register(nickName, email, password);
            if (rv.getState() == UserServiceState.REGISTER_SUCCESS) {
                user = rv.getValue();
            } else if (rv.getState() == UserServiceState.IS_EXIST) {
                user = rv.getValue();
            } else {
                throw new RuntimeException(rv.getState().getTips());
            }
            if (link != null) {
                Friend friend = new Friend();
                friend.setFriendName(nickName);
                friend.setFriendLink(link);
                boolean f = friendService.addFriend(friend);
                if (f) {

                }
            }
        }

        Comment comment = new Comment();
        comment.setReplyContent(article);
        comment.setPageMark(mark);
        comment.setReplyId(replyId);
        comment.setUserId(user.getUserId());
        boolean f = service.saveComment(comment);
        if (f) {
            StrConst.setMessage("发送成功！", url, url, session);
            r.setState(Status.success);
            r.setContent("发送成功！");
        } else {
            r.setState(Status.error);
            r.setContent("发送失败！");
        }
        return r;
    }
}
