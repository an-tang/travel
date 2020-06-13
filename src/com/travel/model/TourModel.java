package com.travel.model;

public class TourModel {
    int id;
    String name;
    String image;
    int tour_info_id;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getTour_info_id() {
        return tour_info_id;
    }

    public void setTour_info_id(int tour_info_id) {
        this.tour_info_id = tour_info_id;
    }

    public TourModel(int id, String name, String image, int tour_info_id) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.tour_info_id = tour_info_id;
    }

    public TourModel(String name, String image, int tour_info_id) {
        this.name = name;
        this.image = image;
        this.tour_info_id = tour_info_id;
    }
}
