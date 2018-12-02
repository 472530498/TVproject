package com.example.a47253.tvproject.bean;

public class PosterBean {
    private String posterUrl;
    private String posterName;

    public PosterBean(String posterUrl, String posterName) {
        this.posterUrl = posterUrl;
        this.posterName = posterName;
    }

    public String getPosterName() {
        return posterName;
    }

    public void setPosterName(String posterName) {
        this.posterName = posterName;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }
}
