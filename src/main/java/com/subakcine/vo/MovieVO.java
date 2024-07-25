package com.subakcine.vo;

public class MovieVO {
    private int id;
    private String title;
    private String movieImgUrl;
    private int order;

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
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

    public String getMovieImgUrl() {
        return movieImgUrl;
    }

    public void setMovieImgUrl(String movieImgUrl) {
        this.movieImgUrl = movieImgUrl;
    }

    public MovieVO(int id, String title, String movieImgUrl, int order) {
        this.id = id;
        this.title = title;
        this.movieImgUrl = movieImgUrl;
        this.order = order;
    }
    public MovieVO() {}
}
