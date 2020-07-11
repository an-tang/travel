package com.travel.service;

import com.travel.bean.ImageBean;
import com.travel.bean.TourBean;
import com.travel.bean.TourInfoBean;
import com.travel.dao.TourDAO;
import com.travel.dao.TourInfoDAO;
import com.travel.enumerize.Status;
import com.travel.viewmodel.CreateTourRequest;

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

    public ArrayList<TourBean> GetToursByName(String name, String fieldName, String sortType, int start, int size) {
        start = Math.max(start, 0);
        size = size < 0 ? 10 : size;
        fieldName = ((fieldName == null) || (fieldName == "")) ? "name" : fieldName;
        sortType = ((sortType == null) || (sortType == "")) ? "ASC" : sortType;
        String keyword = name.replace(" ", "&");

        return tourDAO.GetToursByName(keyword, fieldName, sortType, start, size);
    }

    public ArrayList<TourBean> GetToursInProvinceByID(int provinceID, String fieldName, String sortType, int start, int size){
        start = Math.max(start, 0);
        size = size < 0 ? 10 : size;
        fieldName = ((fieldName == null) || (fieldName == "")) ? "name" : fieldName;
        sortType = ((sortType == null) || (sortType == "")) ? "ASC" : sortType;

        return tourDAO.GetToursInProvinceByID(provinceID, fieldName, sortType, start, size);
    }

    public ArrayList<TourBean> GetToursInProviceByName(String name, int provinceID, int page, int perPage) {
        page = Math.max(page, 0);
        perPage = perPage < 0 ? 10 : perPage;
        String keyword = name.replace(" ", "&");
        return tourDAO.GetToursInProvinceByName(keyword, provinceID, page, perPage);
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

    public boolean CreateTour(CreateTourRequest request) {
        TourBean tour = new TourBean(request.getName(), request.getImage(), request.getProvinceID());
        int tourID = tourDAO.CreateTour(tour);
        if (tourID == 0) {
            return false;
        }

        TourInfoBean tourInfo = new TourInfoBean(request.getTile(), request.getDetail(), request.getPrice(), Status.ACTIVE.getValue(), tourID);
        int tourInfoID;
        try {
            tourInfoID = new TourInfoDAO().CreateTourInfo(tourInfo);
            if (tourInfoID == 0) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        ArrayList<ImageBean> images = request.getImages();
        for (ImageBean image : images) {
            image.setTourInfoID(tourInfoID);
        }


        return true;
    }

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
