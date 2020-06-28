package com.travel.viewmodel;

import com.travel.bean.ImageBean;

import java.io.Serializable;
import java.util.ArrayList;

public class CreateTourRequest implements Serializable {
    String name;
    String image;
    int provinceID;
    String tile;
    String detail;
    long price;
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

    public String getTile() {
        return tile;
    }

    public void setTile(String tile) {
        this.tile = tile;
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

    public ArrayList<ImageBean> getImages() {
        return images;
    }

    public void setImages(ArrayList<ImageBean> images) {
        this.images = images;
    }

    public CreateTourRequest(String name, String image, int provinceID, String tile, String detail, long price, ArrayList<ImageBean> images) {
        this.name = name;
        this.image = image;
        this.provinceID = provinceID;
        this.tile = tile;
        this.detail = detail;
        this.price = price;
        this.images = images;
    }
}
