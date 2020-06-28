package com.travel.bean;

import java.io.Serializable;

public class TourBean implements Serializable {
    int id;
    String name;
    String image;
    int provinceID;
    long price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public TourBean(int id, String name, String image, int provinceID, long price) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.provinceID = provinceID;
        this.price = price;
    }

    public TourBean(String name, String image, int provinceID, long price) {
        this.name = name;
        this.image = image;
        this.provinceID = provinceID;
        this.price = price;
    }

    public TourBean(String name, String image, int provinceID) {
        this.name = name;
        this.image = image;
        this.provinceID = provinceID;
    }

    @Override
    public String toString() {
        return "TourBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", provinceID=" + provinceID +
                ", price=" + price +
                '}';
    }
}
