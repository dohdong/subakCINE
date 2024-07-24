package com.subakcine.vo;

import java.util.Date;

public class CollectionVO {
    private String colectionId;
    private String collectionName;
    private Date collectionCreateDate;
    private Date collectionUpdateDate;
    private String userID;
    private int collectionItemOrder;
    private String collectionItemId;
    private String movieImageUrl;

    public String getColectionId() {
        return colectionId;
    }

    public void setColectionId(String colectionId) {
        this.colectionId = colectionId;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public Date getCollectionCreateDate() {
        return collectionCreateDate;
    }

    public void setCollectionCreateDate(Date collectionCreateDate) {
        this.collectionCreateDate = collectionCreateDate;
    }

    public Date getCollectionUpdateDate() {
        return collectionUpdateDate;
    }

    public void setCollectionUpdateDate(Date collectionUpdateDate) {
        this.collectionUpdateDate = collectionUpdateDate;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getCollectionItemOrder() {
        return collectionItemOrder;
    }

    public void setCollectionItemOrder(int collectionItemOrder) {
        this.collectionItemOrder = collectionItemOrder;
    }

    public String getCollectionItemId() {
        return collectionItemId;
    }

    public void setCollectionItemId(String collectionItemId) {
        this.collectionItemId = collectionItemId;
    }

    public String getMovieImageUrl() {
        return movieImageUrl;
    }

    public void setMovieImageUrl(String movieImageUrl) {
        this.movieImageUrl = movieImageUrl;
    }

    public CollectionVO(String colectionId, String collectionName, Date collectionCreateDate, Date collectionUpdateDate, String userID, int collectionItemOrder, String collectionItemId, String movieImageUrl) {
        this.colectionId = colectionId;
        this.collectionName = collectionName;
        this.collectionCreateDate = collectionCreateDate;
        this.collectionUpdateDate = collectionUpdateDate;
        this.userID = userID;
        this.collectionItemOrder = collectionItemOrder;
        this.collectionItemId = collectionItemId;
        this.movieImageUrl = movieImageUrl;
    }
    public CollectionVO() {}
}
