package com.travel.service;

import com.travel.bean.TourBean;
import com.travel.dao.TourDAO;

import java.util.ArrayList;

public class TourService {
    TourDAO tourDAO = null;

    public TourService() throws Exception {
        tourDAO = new TourDAO();
    }

    public TourBean GetTourByID(int id) {
        return tourDAO.GetTourByID(id);
    }

    public ArrayList<TourBean> GetAllTours(int page, int perPage) {
        page = Math.max(page, 0);
        perPage = perPage < 0 ? 10 : perPage;

        return tourDAO.GetAllTours(page, perPage);
    }

    public ArrayList<TourBean> GetToursByName(String name, int page, int perPage) {
        page = Math.max(page, 0);
        perPage = perPage < 0 ? 10 : perPage;
        String keyword = name.replace(" ", "&");

        return tourDAO.GetToursByName(keyword, page, perPage);
    }

    public ArrayList<TourBean> GetToursByAreaID(int areaID, int limit){
        limit = Math.max(limit, 3);
        return tourDAO.GetToursByAreaID(areaID, limit);
    }
}
