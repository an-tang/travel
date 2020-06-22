package com.travel.bean;

public class FeedbackBean {
    int id;
    String username;
    String email;
    String title;
    String content;
    int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public FeedbackBean(int id, String username, String email, String title, String content, int status) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.title = title;
        this.content = content;
        this.status = status;
    }

    public FeedbackBean(String username, String email, String title, String content, int status) {
        this.username = username;
        this.email = email;
        this.title = title;
        this.content = content;
        this.status = status;
    }
}
