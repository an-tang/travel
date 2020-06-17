package com.travel.bean;

import java.io.Serializable;
import java.util.ArrayList;

public class RegionalTourBean implements Serializable {
    private String region;
    private ArrayList<HomeTourBean> tours;

    public RegionalTourBean(String region, ArrayList<HomeTourBean> tours) {
        this.region = region;
        this.tours = tours;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public ArrayList<HomeTourBean> getTours() {
        return tours;
    }

    public void setTours(ArrayList<HomeTourBean> tours) {
        this.tours = tours;
    }
}
