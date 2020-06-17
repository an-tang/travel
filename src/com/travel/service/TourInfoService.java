package com.travel.service;

import com.travel.bean.ImageBean;
import com.travel.bean.TourInfoBean;
import com.travel.dao.ImageDAO;
import com.travel.dao.TourInfoDAO;

import java.util.ArrayList;

public class TourInfoService {
    TourInfoDAO tourInfoDAO = null;
    ImageDAO imageDAO = null;

    public TourInfoService() throws Exception {
        tourInfoDAO = new TourInfoDAO();
        imageDAO = new ImageDAO();
    }

    public TourInfoBean GetTourInfoByTourID(int tourID) {
        TourInfoBean tourInfo = tourInfoDAO.GetTourInfoByTourID(tourID);
        ArrayList<ImageBean> images = imageDAO.GetImagesByTourInfoID(tourInfo.getId());
        tourInfo.setImages(images);

        return tourInfo;
    }
}
