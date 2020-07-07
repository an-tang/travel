package com.travel.bean;

import java.io.Serializable;
import java.util.ArrayList;

public class TourInfoBean implements Serializable {
    int id;
    String title;
    String detail;
    long price;
    int status;
    int tourID;
    ArrayList<ImageBean> images;

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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTourID() {
        return tourID;
    }

    public void setTourID(int tourID) {
        this.tourID = tourID;
    }

    public ArrayList<ImageBean> getImages() {
        return images;
    }

    public void setImages(ArrayList<ImageBean> images) {
        this.images = images;
    }

    public TourInfoBean(int id, String title, String detail, long price, int status, int tourID, ArrayList<ImageBean> images) {
        this.id = id;
        this.title = title;
        this.detail = detail;
        this.price = price;
        this.status = status;
        this.tourID = tourID;
        this.images = images;
    }

    public TourInfoBean(String title, String detail, long price, int status, int tourID, ArrayList<ImageBean> images) {
        this.title = title;
        this.detail = detail;
        this.price = price;
        this.status = status;
        this.tourID = tourID;
        this.images = images;
    }

    public TourInfoBean(String title, String detail, long price, int status, int tourID) {
        this.title = title;
        this.detail = detail;
        this.price = price;
        this.status = status;
        this.tourID = tourID;
    }

    public TourInfoBean(String title, String detail, long price, int tourID) {
        this.title = title;
        this.detail = detail;
        this.price = price;
        this.tourID = tourID;
    }

    @Override
    public String toString() {
        return "TourInfoBean{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", detail='" + detail + '\'' +
                ", price=" + price +
                ", status=" + status +
                ", tourID=" + tourID +
                ", images=" + images +
                '}';
    }
}
