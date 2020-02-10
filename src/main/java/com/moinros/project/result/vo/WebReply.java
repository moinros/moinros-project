package com.moinros.project.result.vo;

import com.moinros.project.result.Reply;
import com.moinros.project.result.State;

/**
 * 类注释: 设置响应消息的实体
 *
 * @param <C> [content]响应内容
 * @Type View Object
 */
public class WebReply<C> implements Reply<State, C> {

    /**
     * state : 响应状态
     */
    private State state;

    /**
     * content : 响应内容
     */
    private C content;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public C getContent() {
        return content;
    }

    public void setContent(C content) {
        this.content = content;
    }

}
