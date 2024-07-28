package com.subakcine.vo;

public class CollectionSelectVO {
    String id;
    String media_type;
    String poster_path;

    public CollectionSelectVO(String id, String media_type, String poster_path) {
        this.id = id;
        this.media_type = media_type;
        this.poster_path = poster_path;
    }
    public CollectionSelectVO() {
    }

    @Override
    public String toString() {
        return "CollectionSelectVO{" +
                "id='" + id + '\'' +
                ", media_type='" + media_type + '\'' +
                ", poster_path='" + poster_path + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMedia_type() {
        return media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

}
