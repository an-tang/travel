package com.travel.service;

import com.travel.bean.ProvinceBean;
import com.travel.dao.ProvinceDAO;

import java.util.ArrayList;

public class ProvinceService {
    ProvinceDAO provinceDAO = null;

    public ProvinceService() throws Exception {
        provinceDAO = new ProvinceDAO();
    }

    public ProvinceBean GetProvinceByID(int id) {
        ProvinceBean province = null;
        try {
            province = provinceDAO.GetProvinceByID(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return province;
    }

    public ArrayList<ProvinceBean> GetProvincesByAreaID(int areaID) {
        ArrayList<ProvinceBean> listProvinces = new ArrayList<>();
        try {
            listProvinces = provinceDAO.GetProvincesByAreaID(areaID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listProvinces;
    }

    public ArrayList<ProvinceBean> GetProvincesByName(String keyword) {
        ArrayList<ProvinceBean> listProvinces = new ArrayList<>();
        try {
            keyword = keyword.replace(' ', '&');
            listProvinces = provinceDAO.GetProvincesByName(keyword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listProvinces;
    }
}
