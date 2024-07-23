package com.subakcine.vo;

import java.util.Date;

public class UserVO {
    public String userId;
    public String email;
    public String password;
    public Date createDate;

    public UserVO() {
        super();
    }

    public UserVO(String userId, String email, String password, Date createDate) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.createDate = createDate;
    }

    public UserVO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
