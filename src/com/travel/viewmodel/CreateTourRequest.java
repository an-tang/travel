package com.travel.viewmodel;

import com.travel.bean.ImageBean;

import java.io.Serializable;
import java.util.ArrayList;

public class CreateTourRequest implements Serializable {
    String name;
    String image;
    int provinceID;
    String province;
    String title;
    String detail;
    long price;
    int tourInfoID;
    ArrayList<ImageBean> images;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getProvinceID() {
        return provinceID;
    }

    public void setProvinceID(int provinceID) {
        this.provinceID = provinceID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String tile) {
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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public ArrayList<ImageBean> getImages() {
        return images;
    }

    public void setImages(ArrayList<ImageBean> images) {
        this.images = images;
    }

    public int getTourInfoID() {
        return tourInfoID;
    }

    public void setTourInfoID(int tourInfoID) {
        this.tourInfoID = tourInfoID;
    }

    public CreateTourRequest(String name, String image, int provinceID, String title, String detail, long price, ArrayList<ImageBean> images) {
        this.name = name;
        this.image = image;
        this.provinceID = provinceID;
        this.title = title;
        this.detail = detail;
        this.price = price;
        this.images = images;
    }

    public CreateTourRequest(String name, String image, int provinceID, String province, String title, String detail, long price, int tourInfoID) {
        this.name = name;
        this.image = image;
        this.provinceID = provinceID;
        this.province = province;
        this.title = title;
        this.detail = detail;
        this.price = price;
        this.tourInfoID = tourInfoID;
    }

    public CreateTourRequest(String name, String image, int provinceID, String province, String title, String detail, long price, int tourInfoID, ArrayList<ImageBean> images) {
        this.name = name;
        this.image = image;
        this.provinceID = provinceID;
        this.province = province;
        this.title = title;
        this.detail = detail;
        this.price = price;
        this.tourInfoID = tourInfoID;
        this.images = images;
    }

    @Override
    public String toString() {
        return "CreateTourRequest{" +
                "name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", provinceID=" + provinceID +
                ", province='" + province + '\'' +
                ", title='" + title + '\'' +
                ", detail='" + detail + '\'' +
                ", price=" + price +
                ", images=" + images +
                '}';
    }
}
