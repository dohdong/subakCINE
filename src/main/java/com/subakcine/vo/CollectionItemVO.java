package com.subakcine.vo;

public class CollectionItemVO {
    private String id;
    private String title;
    private String movieImgUrl;
    private int order;
    private String type;
    private String collectionId;
    private String userId;
    private String userName;

    public CollectionItemVO(String id, String title, String movieImgUrl, int order, String type, String collectionId, String userId, String userName) {
        this.id = id;
        this.title = title;
        this.movieImgUrl = movieImgUrl;
        this.order = order;
        this.type = type;
        this.collectionId = collectionId;
        this.userId = userId;
        this.userName = userName;
    }

    public CollectionItemVO() {
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

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(String collectionId) {
        this.collectionId = collectionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "CollectionItemVO{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", movieImgUrl='" + movieImgUrl + '\'' +
                ", order=" + order +
                ", type='" + type + '\'' +
                ", collectionId='" + collectionId + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
