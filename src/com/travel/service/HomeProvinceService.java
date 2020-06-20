package com.travel.service;

import com.travel.bean.HomeProvinceBean;
import com.travel.dao.HomeProvinceDAO;

import java.util.ArrayList;

public class HomeProvinceService {
    HomeProvinceDAO homeProvinceDAO = null;

    public HomeProvinceService() throws Exception {
        homeProvinceDAO = new HomeProvinceDAO();
    }

    public ArrayList<HomeProvinceBean> GetHomePageProvinces(int limit){
        limit = Math.max(5, limit);
        return homeProvinceDAO.GetHomePageProvinces(limit);
    }
}
