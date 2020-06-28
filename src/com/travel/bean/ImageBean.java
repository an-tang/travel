package com.travel.bean;

public class ImageBean {
    int id;
    String url;
    int tourInfoID;
    String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getTourInfoID() {
        return tourInfoID;
    }

    public void setTourInfoID(int tourInfoID) {
        this.tourInfoID = tourInfoID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ImageBean(int id, String url, int tourInfoID, String description) {
        this.id = id;
        this.url = url;
        this.tourInfoID = tourInfoID;
        this.description = description;
    }

    public ImageBean(String url, int tourInfoID, String description) {
        this.url = url;
        this.tourInfoID = tourInfoID;
        this.description = description;
    }

    public ImageBean(String url, String description) {
        this.url = url;
        this.description = description;
    }

    @Override
    public String toString() {
        return "ImageBean{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", tourInfoID=" + tourInfoID +
                ", description='" + description + '\'' +
                '}';
    }
}
