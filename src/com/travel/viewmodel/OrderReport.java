package com.travel.viewmodel;

public class OrderReport {
    String area;
    String province;
    String tourName;
    long grandAmount;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public long getGrandAmount() {
        return grandAmount;
    }

    public void setGrandAmount(long grandAmount) {
        this.grandAmount = grandAmount;
    }
}
