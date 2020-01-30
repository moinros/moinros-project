package com.moinros.project.tool.web;

import com.moinros.project.common.annotation.base64.Base64Mark;

import java.util.List;

/**
 * 注释:
 *
 * @Author moinros
 * @WebSite www.moinros.com
 * @Date 2020/1/23 23:47
 * @Verison 1.0
 */
@Base64Mark
public class PageBean<T> {

    private List<T> list;
    private int pageNo; // 当前页码数
    private int dataSize; // 每页显示的记录数
    private int totalCount; // 总记录数
    private int totalPage; // 总页码数
    private int[] pageNumber;
    private int startNo;
    private int endNo;

    public PageBean(Integer pageNo, int dataSize, int totalCount) {
        this.dataSize = dataSize;
        this.totalCount = totalCount;
        setTotalPage();  // 设置总页码数
        setPageNo(pageNo); // 设置当前页码数
        setPageNumber();
        setDataBaseLimit();
    }

    private void setDataBaseLimit() {
        startNo = pageNo * dataSize - 10;
        endNo = startNo + 10;
    }

    public int getStartNo() {
        return startNo;
    }

    public int getEndNo() {
        return endNo;
    }

    private void setPageNumber() {
        int num = dataSize / 2;
        int start;
        int end;
        if (totalPage < 9) {
            start = pageNo - num;
            end = pageNo + num;
            if (start <= 0) {
                end = end + (0 - start);
                start = 1;
            }
            if (end > totalPage) {
                end = totalPage;
            }
        } else {
            start = 1;
            end = totalPage;
        }
        setPageNumber(start, end);
    }

    private void setPageNumber(int start, int count) {
        pageNumber = new int[count];
        for (int i = 0; i < count; i++) {
            pageNumber[i] = start;
            start++;
        }
    }

    public int[] getPageNumber() {
        return pageNumber;
    }

    public List<T> getList() {
        return list;
    }

    public T get(int index) {
        if (list != null && list.size() > 0) {
            return list.get(index);
        } else {
            return null;
        }
    }

    public boolean isNull() {
        return list != null && list.size() > 0 ? true : false;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        if (pageNo == null || pageNo.intValue() <= 1) {
            this.pageNo = 1;
        } else if (pageNo > totalPage) {
            this.pageNo = totalPage;
        } else {
            this.pageNo = pageNo;
        }
    }

    public int getDataSize() {
        return dataSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    // 设置总页码数
    private void setTotalPage() {
        if (totalCount % dataSize == 0) {
            this.totalPage = totalCount / this.dataSize;
        } else {
            this.totalPage = totalCount / this.dataSize + 1;
        }
    }
}
