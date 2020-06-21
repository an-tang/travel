package com.travel.bean;

import java.io.Serializable;

public class HomeProvinceBean implements Serializable {
    int provinceID;
    String name;
    String url;

    public int getProvinceID() {
        return provinceID;
    }

    public void setProvinceID(int provinceID) {
        this.provinceID = provinceID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public HomeProvinceBean(int provinceID, String name, String url) {
        this.provinceID = provinceID;
        this.name = name;
        this.url = url;
    }

    @Override
    public String toString() {
        return "HomeProvinceBean{" +
                "provinceID=" + provinceID +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
