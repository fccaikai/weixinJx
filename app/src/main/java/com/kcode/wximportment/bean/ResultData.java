package com.kcode.wximportment.bean;

import java.util.List;

/**
 * Created by caik on 16/5/13.
 */
public class ResultData<T> {
    private List<T> list;
    private int totalPage;
    private int ps;
    private int pno;

    public ResultData(List<T> list, int totalPage, int ps, int pno) {

        this.list = list;
        this.totalPage = totalPage;
        this.ps = ps;
        this.pno = pno;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPs() {
        return ps;
    }

    public void setPs(int ps) {
        this.ps = ps;
    }

    public int getPno() {
        return pno;
    }

    public void setPno(int pno) {
        this.pno = pno;
    }
}
