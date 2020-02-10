package com.moinros.project.controller;

import com.moinros.project.result.Reply;
import com.moinros.project.result.enums.Status;
import com.moinros.project.result.vo.WebReply;
import com.moinros.project.tool.web.socket.Heartbeat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Hashtable;
import java.util.Map;

/**
 * 注释:
 *
 * @Author moinros
 * @Date 2020/2/10 1:38
 * @Verison 1.0
 */

@RestController
public class SocketController {

    private static final Status ERROR = Status.error;
    private static final Status SUCCESS = Status.success;
    private static final Status EXCEPTION = Status.exception;
    private static final Status OK = Status.OK;
    private static final Status NO = Status.NO;
    private static final Status MSG = Status.MSG;

    public static final Map<String, Heartbeat> CLIENT = new Hashtable<>();

    @GetMapping("/socket/ok")
    public Reply ok(HttpSession session) {
        WebReply r = new WebReply();
        String id = session.getId();
        System.out.println("OK -> " + id);
        Heartbeat value = CLIENT.get(id);
        if (value != null) {
            value.setSate(1);
            value.setTask("心跳检测！");
        }
        r.setState(MSG);
        r.setContent(value.getTask());
        return r;
    }

    @GetMapping("/socket/no")
    public Reply no(HttpSession session) {
        WebReply r = new WebReply();
        String id = session.getId();
        Heartbeat value = CLIENT.get(id);
        if (value != null) {
            value.setSate(0);
            value.setTask(null);
        }
        r.setState(MSG);
        r.setContent(OK);
        return r;
    }

    @GetMapping("/socket/port/heartbeat")
    public Reply comet(HttpSession session) {
        WebReply r = new WebReply();
        String id = session.getId();
    //    System.out.println("socket -> " + id);
        Heartbeat value = CLIENT.get(id);
        if (value != null) {
            value.setTimer(System.currentTimeMillis());

            // 有新消息
            if (value.getSate() == 1) {
                r.setState(MSG);
                r.setContent(value.getTask());
                value.setSate(0);
                value.setTask(null);
            } else {
                r.setState(SUCCESS);
                r.setContent(OK);
            }
        } else {
            r.setState(SUCCESS);
            r.setContent(OK);
            value = new Heartbeat();
            value.setSid(id);
            value.setTimer(System.currentTimeMillis());
            value.setSate(0);
            CLIENT.put(id, value);
        }
        return r;
    }

}
