package com.xiaomai.myproject.entity;

/**
 * Created by XiaoMai on 2017/1/13 19:51.
 */
public class MultiSelectBean {

    private String title;

    private boolean isSelect;

    public MultiSelectBean(String title, boolean isSelect) {
        this.title = title;
        this.isSelect = isSelect;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
