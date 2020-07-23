package com.travel.viewmodel;

public class TourDetail {
    int id;
    String name;
    String title;
    String province;
    long price;
    int status;

    public TourDetail(int id, String name, String title, String province, long price, int status) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.province = province;
        this.price = price;
        this.status = status;
    }

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
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

    @Override
    public String toString() {
        return "TourDetail{" +
                "id=" + id +
                ", name=" + name +
                ", title=" + title +
                ", province='" + province + '\'' +
                ", price=" + price +
                '}';
    }
}
