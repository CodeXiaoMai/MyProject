package com.xiaomai.myproject.entity;

import java.io.Serializable;

/**
 * Created by XiaoMai on 2016/10/26 15:26.
 */
public class File implements Serializable {

    private int id;

    private String title;

    private String url;

    private int progress;

    public File(int id, String title, String url, int progress) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.progress = progress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}
