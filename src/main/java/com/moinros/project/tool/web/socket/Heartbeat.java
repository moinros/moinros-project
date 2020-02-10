package com.moinros.project.tool.web.socket;

/**
 * 注释:
 *
 * @Author moinros
 * @Date 2020/2/10 2:22
 * @Verison 1.0
 */
public class Heartbeat<T> {

    private String sid; // sessionID
    private long timer; // 计时器
    private int sate; // 状态码
    private T task; // 任务

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public long getTimer() {
        return timer;
    }

    public void setTimer(long timer) {
        this.timer = timer;
    }

    public int getSate() {
        return sate;
    }

    public void setSate(int sate) {
        this.sate = sate;
    }

    public T getTask() {
        return task;
    }

    public void setTask(T task) {
        this.task = task;
    }
}
