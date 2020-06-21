package com.travel.bean;

import java.io.Serializable;

public class CommentBean implements Serializable {

    int id;
    String userName;
    String content;
    int tourInfoID;
    int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getTourInfoID() {
        return tourInfoID;
    }

    public void setTourInfoID(int tourInfoID) {
        this.tourInfoID = tourInfoID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public CommentBean(int id, String userName, String content, int tourInfoID, int status) {
        this.id = id;
        this.userName = userName;
        this.content = content;
        this.tourInfoID = tourInfoID;
        this.status = status;
    }

    public CommentBean(String userName, String content, int tourInfoID, int status) {
        this.userName = userName;
        this.content = content;
        this.tourInfoID = tourInfoID;
        this.status = status;
    }

    @Override
    public String toString() {
        return "CommentBean{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", content='" + content + '\'' +
                ", tourInfoID=" + tourInfoID +
                ", status=" + status +
                '}';
    }
}
