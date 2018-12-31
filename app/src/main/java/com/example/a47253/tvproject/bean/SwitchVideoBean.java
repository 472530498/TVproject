package com.example.a47253.tvproject.bean;

/**
 * Created by shuyu on 2016/12/7.
 */

public class SwitchVideoBean {
    private String url;
    private String name;

    public SwitchVideoBean(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}