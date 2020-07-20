package com.travel.viewmodel;

public class TourDetail {
    int id;
    String name;
    String title;
    String province;
    long price;

    public TourDetail(int id, String name, String title, String province, long price) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.province = province;
        this.price = price;
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
