package com.travel.service;

import com.travel.bean.TourBean;
import com.travel.repository.TourRepository;

import java.util.ArrayList;

public class TourService {
    TourRepository tourRepo = null;

    public TourService() throws Exception {
        tourRepo = new TourRepository();
    }

    public TourBean GetTourByID(int id) {
        return tourRepo.GetTourByID(id);
    }

    public ArrayList<TourBean> GetAllTours(int page, int perPage) {
        page = Math.max(page, 0);
        perPage = perPage < 0 ? 10 : perPage;
        return tourRepo.GetAllTours(page, perPage);
    }

    public ArrayList<TourBean> GetToursByName(String name, int page, int perPage) {
        page = Math.max(page, 0);
        perPage = perPage < 0 ? 10 : perPage;
        String keyword = name.replace(" ", "&");
        return tourRepo.GetToursByName(keyword, page, perPage);
    }
}
