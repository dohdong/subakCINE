package com.subakcine.vo;

public class CollectionItemVO {
    private String id;
    private String title;
    private String movieImgUrl;
    private int order;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMovieImgUrl() {
        return movieImgUrl;
    }

    public void setMovieImgUrl(String movieImgUrl) {
        this.movieImgUrl = movieImgUrl;
    }

    public CollectionItemVO(String id, String title, String movieImgUrl, int order, String type) {
        this.id = id;
        this.title = title;
        this.movieImgUrl = movieImgUrl;
        this.order = order;
        this.type = type;
    }
    public CollectionItemVO() {}
}
