package com.travel.service;

import com.travel.dao.ImageDAO;

public class ImageService {
    ImageDAO imageDAO = null;

    public ImageService() throws Exception {
        imageDAO = new ImageDAO();
    }
}
