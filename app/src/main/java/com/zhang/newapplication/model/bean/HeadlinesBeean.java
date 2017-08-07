package com.zhang.newapplication.model.bean;

/**
 * Created by zhang_shuai on 2017/7/19.
 * Del:头条的javabean
 */

public class HeadlinesBeean {

    private String title;
    private String data;
    private String author_name;
    private String thumbnail_pic_s;//图片

    public HeadlinesBeean() {
    }

    public HeadlinesBeean(String title, String data, String author_name, String thumbnail_pic_s) {
        this.title = title;
        this.data = data;
        this.author_name = author_name;
        this.thumbnail_pic_s = thumbnail_pic_s;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getThumbnail_pic_s() {
        return thumbnail_pic_s;
    }

    public void setThumbnail_pic_s(String thumbnail_pic_s) {
        this.thumbnail_pic_s = thumbnail_pic_s;
    }
}
