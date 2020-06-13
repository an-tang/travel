package com.travel.service;

import com.travel.model.TourModel;
import com.travel.repository.TourRepository;

import java.sql.SQLException;
import java.util.ArrayList;

public class TourService {
    TourRepository tourRepo = null;

    public TourService() throws Exception {
        tourRepo = new TourRepository();
    }

    public TourModel GetTourByID(int id) {
        return tourRepo.GetTourByID(id);
    }

    public ArrayList<TourModel> GetAllTours(int page, int perPage) {
        page = Math.max(page, 0);
        perPage = perPage < 0 ? 10 : perPage;
        return tourRepo.GetAllTours(page, perPage);
    }

    public ArrayList<TourModel> GetToursByName(String name, int page, int perPage) {
        page = Math.max(page, 0);
        perPage = perPage < 0 ? 10 : perPage;
        String keyword = name.replace(" ", "&");
        return tourRepo.GetToursByName(keyword, page, perPage);
    }
}
