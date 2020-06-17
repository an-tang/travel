package com.travel.bean;

public class ImageBean {
    int id;
    String url;
    int tour_info_id;
    String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ImageBean(int id, String url, int tour_info_id, String description) {
        this.id = id;
        this.url = url;
        this.tour_info_id = tour_info_id;
        this.description = description;
    }

    public ImageBean(String url, int tour_info_id, String description) {
        this.url = url;
        this.tour_info_id = tour_info_id;
        this.description = description;
    }

    @Override
    public String toString() {
        return "ImageBean{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", tour_info_id=" + tour_info_id +
                ", description='" + description + '\'' +
                '}';
    }
}
