package com.travel.model;

import java.util.ArrayList;

public class TourInfoModel {
    int id;
    String title;
    String detail;
    long price;
    int status;
    int province_id;
    ArrayList<ImageModel> images;

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

    public int getProvince_id() {
        return province_id;
    }

    public void setProvince_id(int province_id) {
        this.province_id = province_id;
    }

    public ArrayList<ImageModel> getImages() {
        return images;
    }

    public void setImages(ArrayList<ImageModel> images) {
        this.images = images;
    }

    public TourInfoModel(int id, String title, String detail, long price, int status, int province_id, ArrayList<ImageModel> images) {
        this.id = id;
        this.title = title;
        this.detail = detail;
        this.price = price;
        this.status = status;
        this.province_id = province_id;
        this.images = images;
    }

    public TourInfoModel(String title, String detail, long price, int status, int province_id, ArrayList<ImageModel> images) {
        this.title = title;
        this.detail = detail;
        this.price = price;
        this.status = status;
        this.province_id = province_id;
        this.images = images;
    }
}
