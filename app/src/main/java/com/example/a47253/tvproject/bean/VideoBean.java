package com.example.a47253.tvproject.bean;

public class VideoBean {
    private PosterBean PosterBean; // 海报对象
    private String channelUrl; // 频道地址源
    private String channelTitle; // 频道标题
    private Integer typeid; // 频道分类id

    public VideoBean(PosterBean posterBean, String channelUrl, String channelTitle, Integer typeid) {
        PosterBean = posterBean;
        this.channelUrl = channelUrl;
        this.channelTitle = channelTitle;
        this.typeid = typeid;
    }

    public com.example.a47253.tvproject.bean.PosterBean getPosterBean() {
        return PosterBean;
    }

    public void setPosterBean(com.example.a47253.tvproject.bean.PosterBean posterBean) {
        PosterBean = posterBean;
    }

    public String getChannelUrl() {
        return channelUrl;
    }

    public void setChannelUrl(String channelUrl) {
        this.channelUrl = channelUrl;
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }
}
