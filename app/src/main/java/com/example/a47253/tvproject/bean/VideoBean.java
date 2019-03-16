package com.example.a47253.tvproject.bean;

public class VideoBean {
    private PosterBean PosterBean; // 海报对象
    private String channelUrl; // 频道地址源
    private String channelTitle; // 频道标题
    private String video_zone_tags_name; // 频道分类name
    private String video_zone_tags_id; // 分类id

    public String getVideo_zone_tags_name() {
        return video_zone_tags_name;
    }

    public void setVideo_zone_tags_name(String video_zone_tags_name) {
        this.video_zone_tags_name = video_zone_tags_name;
    }

    public String getVideo_zone_tags_id() {
        return video_zone_tags_id;
    }

    public void setVideo_zone_tags_id(String video_zone_tags_id) {
        this.video_zone_tags_id = video_zone_tags_id;
    }

    public VideoBean(PosterBean posterBean, String channelUrl, String channelTitle, String video_zone_tags_name, String video_zone_tags_id) {
        PosterBean = posterBean;
        this.channelUrl = channelUrl;
        this.channelTitle = channelTitle;
        this.video_zone_tags_name = video_zone_tags_name;
        this.video_zone_tags_id = video_zone_tags_id;
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
}
