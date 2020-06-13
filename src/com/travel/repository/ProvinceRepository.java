package com.travel.repository;

import com.travel.dbconnection.DBConnection;
import com.travel.model.ProvinceModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProvinceRepository extends BaseRepository {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    public ProvinceRepository() throws Exception {
        super();
    }

    public ProvinceModel GetProvinceByID(int id) {
        ProvinceModel province = null;
        try {
            connection = DBConnection.getConnect();
            String sql = "SELECT * FROM provinces WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                String name = rs.getString("name");
                int areaID = rs.getInt("area_id");
                province = new ProvinceModel(id, name, areaID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return province;
    }

    public ArrayList<ProvinceModel> GetProvincesByAreaID(int areaID){
        ArrayList<ProvinceModel> listProvinces = new ArrayList<>();
        try{
            connection = DBConnection.getConnect();
            String sql = "SELECT * FROM provinces WHERE area_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, areaID);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                listProvinces.add(new ProvinceModel(id, name, areaID));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listProvinces;
    }

    public ArrayList<ProvinceModel> GetProvincesByName(String keyword){
        ArrayList<ProvinceModel> listProvinces = new ArrayList<>();
        try{
            connection = DBConnection.getConnect();
            String sql = "SELECT * FROM provinces WHERE to_tsvector(convertnonunicode(name)) @@ to_tsquery(convertnonunicode(?)) ORDER BY NAME ASC;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, keyword);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int areaID = rs.getInt("area_id");
                listProvinces.add(new ProvinceModel(id, name, areaID));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listProvinces;
    }
}
