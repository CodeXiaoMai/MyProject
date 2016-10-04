package com.xiaomai.myproject.entity;

/**
 * Created by XiaoMai on 2016/8/29.
 */
public class MyItem {

    private int type;

    private String title;

    private int resId;

    public MyItem(int type, String title, int resId) {
        this.type = type;
        this.title = title;
        this.resId = resId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    @Override
    public String toString() {
        return "MyItem{" +
                "type=" + type +
                ", title='" + title + '\'' +
                ", resId=" + resId +
                '}';
    }
}
