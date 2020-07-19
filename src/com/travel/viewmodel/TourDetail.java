package com.travel.viewmodel;

import java.io.Serializable;

public class TourDetail implements Serializable {
    int id;
    String name;
    String created_at;
    String image;
    long price;
    int passenger;
    long totalAmount;

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

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public int getPassenger() {
        return passenger;
    }

    public void setPassenger(int passenger) {
        this.passenger = passenger;
    }

    public long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public TourDetail() {
    }

    public TourDetail(int id, String name, String created_at, String image, long price, int passenger) {
        this.id = id;
        this.name = name;
        this.created_at = created_at;
        this.image = image;
        this.price = price;
        this.passenger = passenger;
        this.totalAmount = price * passenger;
    }

    @Override
    public String toString() {
        return "TourDetail{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", created_at=" + created_at +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", passenger=" + passenger +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
