package com.moinros.project.result.vo;

import com.moinros.project.result.Reply;

import java.util.HashMap;
import java.util.Map;

/**
 * 注释: 设置响应结果
 *
 * @Author moinros
 * @WebSite www.moinros.com
 * @Date 2020/1/16 2:26
 * @Verison 1.0
 */
public class WebResult implements Reply<Map<String, Object>> {

    /**
     * content : 响应内容
     */
    private Map<String, Object> content;

    /**
     * 添加结果集
     *
     * @param key   键
     * @param value 值
     */
    public void put(String key, Object value) {
        if (content == null) {
            content = new HashMap<>();
        }
        content.put(key, value);
    }

    /**
     * 根据键获取值
     *
     * @param key 键
     * @return 值
     */
    public Object get(String key) {
        return content == null ? null : content.get(key);
    }

    @Override
    public void setContent(Map<String, Object> content) {
        this.content = content;
    }

    @Override
    public Map<String, Object> getContent() {
        return content;
    }
}
