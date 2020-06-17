package com.travel.bean;

import java.io.Serializable;

public class AreaBean implements Serializable {
    int id;
    String name;

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

    public AreaBean(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

