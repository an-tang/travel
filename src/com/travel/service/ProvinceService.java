package com.travel.service;

import com.travel.model.ProvinceModel;
import com.travel.repository.ProvinceRepository;

import java.util.ArrayList;

public class ProvinceService {
    ProvinceRepository provinceRepo = null;

    public ProvinceService() throws Exception{
        provinceRepo = new ProvinceRepository();
    }

    public ProvinceModel GetProvinceByID(int id) {
        ProvinceModel province = null;
        try {
            province = provinceRepo.GetProvinceByID(id);
        } catch (Exception e){
            e.printStackTrace();
        }
        return province;
    }

    public ArrayList<ProvinceModel> GetProvincesByAreaID(int areaID){
        ArrayList<ProvinceModel> listProvinces = new ArrayList<>();
        try {
            listProvinces = provinceRepo.GetProvincesByAreaID(areaID);
        }catch (Exception e){
            e.printStackTrace();
        }
        return listProvinces;
    }

    public ArrayList<ProvinceModel> GetProvincesByName(String keyword){
        ArrayList<ProvinceModel> listProvinces = new ArrayList<>();
        try {
            keyword = keyword.replace(' ', '&');
            listProvinces = provinceRepo.GetProvincesByName(keyword);
        }catch (Exception e){
            e.printStackTrace();
        }
        return listProvinces;
    }
}
