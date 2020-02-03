package com.moinros.project.result.vo;

import com.moinros.project.tool.util.date.DateInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 注释:
 *
 * @Author moinros
 * @WebSite www.moinros.com
 * @Date 2020/2/3 18:34
 * @Verison 1.0
 */
public class Archive<T> {

    private DateInfo date;

    private List<T> list;

    public boolean add(T obj) {
        if (list == null) {
            list = new ArrayList<T>();
        }
        return list.add(obj);
    }

    public DateInfo getDate() {
        return date;
    }

    public void setDate(DateInfo date) {
        this.date = date;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
