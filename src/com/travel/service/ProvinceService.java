package com.travel.service;

import com.travel.bean.ProvinceBean;
import com.travel.repository.ProvinceRepository;

import java.util.ArrayList;

public class ProvinceService {
    ProvinceRepository provinceRepo = null;

    public ProvinceService() throws Exception{
        provinceRepo = new ProvinceRepository();
    }

    public ProvinceBean GetProvinceByID(int id) {
        ProvinceBean province = null;
        try {
            province = provinceRepo.GetProvinceByID(id);
        } catch (Exception e){
            e.printStackTrace();
        }
        return province;
    }

    public ArrayList<ProvinceBean> GetProvincesByAreaID(int areaID){
        ArrayList<ProvinceBean> listProvinces = new ArrayList<>();
        try {
            listProvinces = provinceRepo.GetProvincesByAreaID(areaID);
        }catch (Exception e){
            e.printStackTrace();
        }
        return listProvinces;
    }

    public ArrayList<ProvinceBean> GetProvincesByName(String keyword){
        ArrayList<ProvinceBean> listProvinces = new ArrayList<>();
        try {
            keyword = keyword.replace(' ', '&');
            listProvinces = provinceRepo.GetProvincesByName(keyword);
        }catch (Exception e){
            e.printStackTrace();
        }
        return listProvinces;
    }
}
