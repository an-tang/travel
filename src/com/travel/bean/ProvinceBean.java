package com.travel.bean;

import java.io.Serializable;

public class ProvinceBean implements Serializable {
    int id;
    String name;
    int areaID;

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

    public int getAreaID() {
        return areaID;
    }

    public void setAreaID(int area_id) {
        this.areaID = area_id;
    }

    public ProvinceBean(int id, String name, int areaID) {
        this.id = id;
        this.name = name;
        this.areaID = areaID;
    }

    @Override
    public String toString() {
        return "ProvinceBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", areaID=" + areaID +
                '}';
    }
}
