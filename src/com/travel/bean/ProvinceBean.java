package com.travel.bean;

public class ProvinceBean {
    int id;
    String name;
    int area_id;

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

    public int getArea_id() {
        return area_id;
    }

    public void setArea_id(int area_id) {
        this.area_id = area_id;
    }

    public ProvinceBean(int id, String name, int area_id) {
        this.id = id;
        this.name = name;
        this.area_id = area_id;
    }
}
