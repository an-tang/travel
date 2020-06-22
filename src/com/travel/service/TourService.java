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

    public ArrayList<TourBean> GetToursByAreaID(int areaID, int limit) {
        limit = Math.max(limit, 3);
        ArrayList<TourBean> tours = tourDAO.GetTopToursByAreaID(areaID, limit);
        int length = tours.size();
        if (length == limit) {
            return tours;
        } else if (length == 0) {
            ArrayList<TourBean> addedTours = tourDAO.GetTourByAreaID(areaID, limit);
            tours.addAll(addedTours);
        } else if (length > 0 && length < limit) {
            String params = parseTourIDToString(tours);
            ArrayList<TourBean> addedTours = tourDAO.GetToursExcludingIDs(params, areaID, limit - tours.size());
            tours.addAll(addedTours);
        }
        return tours;
    }

    public ArrayList<TourBean> GetToursTopOrder(int limit) {
        limit = Math.max(limit, 6);
        ArrayList<TourBean> tours = tourDAO.GetToursTopOrder(limit);
        int length = tours.size();
        if (length == limit) {
            return tours;
        } else if (length == 0) {
            ArrayList<TourBean> addedTours = tourDAO.GetAllTours(0, limit);
            tours.addAll(addedTours);
        } else if (length > 0 && length < limit) {
            String params = parseTourIDToString(tours);
            ArrayList<TourBean> addedTours = tourDAO.GetToursExcludingIDs(params, 3, limit - tours.size());
            tours.addAll(addedTours);
        }
        return tours;
    }

    public ArrayList<TourBean> GetToursByListIDs(int[] ids) {
        if (ids.length == 0) {
            return null;
        }

        String params = parseListIntToStringQuery(ids);
        return tourDAO.GetToursByListIDs(params);
    }

//    public boolean CreateTour(){
//
//        return true;
//    }

    private String parseTourIDToString(ArrayList<TourBean> tours) {
        StringBuilder builder = new StringBuilder();
        for (TourBean tour : tours) {
            builder.append(tour.getId() + ",");
        }
        builder.deleteCharAt(builder.length() - 1);

        return builder.toString();
    }

    private String parseListIntToStringQuery(int[] ids) {
        StringBuilder builder = new StringBuilder();
        for (int id : ids) {
            builder.append(id + ",");
        }
        builder.deleteCharAt(builder.length() - 1);

        return builder.toString();
    }
}
