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

    public OrderReport(String area, String province, String tourName, long grandAmount) {
        this.area = area;
        this.province = province;
        this.tourName = tourName;
        this.grandAmount = grandAmount;
    }

    @Override
    public String toString() {
        return "OrderReport{" +
                "area='" + area + '\'' +
                ", province='" + province + '\'' +
                ", tourName='" + tourName + '\'' +
                ", grandAmount=" + grandAmount +
                '}';
    }
}
