package com.travel.model;

public class ImageModel {
    int id;
    String url;
    int tour_info_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getTour_info_id() {
        return tour_info_id;
    }

    public void setTour_info_id(int tour_info_id) {
        this.tour_info_id = tour_info_id;
    }

    public ImageModel(int id, String url, int tour_info_id) {
        this.id = id;
        this.url = url;
        this.tour_info_id = tour_info_id;
    }

    public ImageModel(String url, int tour_info_id) {
        this.url = url;
        this.tour_info_id = tour_info_id;
    }
}
