package com.moinros.project.controller;

import com.moinros.project.common.annotation.tool.LogInfo;
import com.moinros.project.common.annotation.tool.ParamIsNull;
import com.moinros.project.model.pojo.Comment;
import com.moinros.project.model.pojo.UserData;
import com.moinros.project.result.Reply;
import com.moinros.project.result.enums.Status;
import com.moinros.project.result.sub.StrConst;
import com.moinros.project.result.vo.WebReply;
import com.moinros.project.service.UserService;
import com.moinros.project.service.other.CommentService;
import com.moinros.project.service.other.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 注释:
 *
 * @Author moinros
 * @WebSite www.moinros.com
 * @Date 2020/2/8 16:30
 * @Verison 1.0
 */
@RestController
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

    @PostMapping("/comment/save")
    @ParamIsNull(paramName = {"url", "mark", "article"})
    @LogInfo(value = "保存评论", clazz = ComtController.class)
    public Reply save(Integer replyId, String url, String mark, String article, HttpSession session) {
        Reply r = new WebReply();
        UserData user = (UserData) session.getAttribute(StrConst.USER_DATA);
        if (user != null) {
            if (url != null && mark != null && article != null) {
                Comment comment = new Comment();
                comment.setPageMark(mark);
                comment.setReplyContent(article);
                comment.setUserId(user.getUserId());
                if (replyId != null) comment.setReplyId(replyId);
                boolean f = service.saveComment(comment);
                if (f) {
                    r.setState(Status.success);
                    r.setContent("评论发送成功！");
                    StrConst.setMessage("评论发送成功！", "返回" + url, url, session);
                } else {
                    r.setState(Status.error);
                    r.setContent("评论发送失败！");
                }
            }
        } else {
            r.setState(Status.error);
            r.setContent("你还没有登录！");
        }
        return r;
    }

    @GetMapping("/comment/find/list")
    @ParamIsNull(paramName = {"mark"})
    public Reply findComment(String mark) {
        Reply r = new WebReply();
        if (mark != null) {
            List list = service.findListByMark(mark);
            if (list != null) {
                r.setState(Status.success);
                r.setContent(list);
            } else {
                r.setState(Status.NO);
                r.setContent("没有查询到数据！");
            }
        } else {
            r.setState(Status.error);
            r.setContent("没有指定page mark！");
        }
        return r;
    }

}
