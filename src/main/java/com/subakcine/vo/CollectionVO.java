package com.subakcine.vo;

import java.util.ArrayList;
import java.util.Date;

public class CollectionVO {
    private String collectionId;
    private String collectionName;
    private Date collectionCreateDate;
    private Date collectionUpdateDate;
    private String userID;
    private ArrayList<CollectionItemVO> items;

    public ArrayList<CollectionItemVO> getItems() {
        return items;
    }

    public void setItems(ArrayList<CollectionItemVO> items) {
        this.items = items;
    }

    public String getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(String collectionId) {
        this.collectionId = collectionId;
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


    public CollectionVO(String collectionId, String collectionName, Date collectionCreateDate, Date collectionUpdateDate, String userID ,ArrayList<CollectionItemVO> items) {
        this.collectionId = collectionId;
        this.collectionName = collectionName;
        this.collectionCreateDate = collectionCreateDate;
        this.collectionUpdateDate = collectionUpdateDate;
        this.userID = userID;
        this.items = items;
    }
    public CollectionVO() {}
}
