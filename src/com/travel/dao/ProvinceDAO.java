package com.travel.dao;

import com.travel.dbconnection.DBConnection;
import com.travel.bean.ProvinceBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProvinceDAO extends BaseDAO {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    public ProvinceDAO() throws Exception {
        super();
    }

    public ProvinceBean GetProvinceByID(int id) {
        ProvinceBean province = null;
        try {
            connection = DBConnection.getConnect();
            String sql = "SELECT * FROM provinces WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                int areaID = rs.getInt("area_id");
                province = new ProvinceBean(id, name, areaID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDAO.closeConnection(preparedStatement, connection);
        }

        return province;
    }

    public ArrayList<ProvinceBean> GetProvincesByAreaID(int areaID) {
        ArrayList<ProvinceBean> listProvinces = new ArrayList<>();
        try {
            connection = DBConnection.getConnect();
            String sql = "SELECT * FROM provinces WHERE area_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, areaID);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                listProvinces.add(new ProvinceBean(id, name, areaID));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDAO.closeConnection(preparedStatement, connection);
        }

        return listProvinces;
    }

    public ArrayList<ProvinceBean> GetProvincesByName(String keyword) {
        ArrayList<ProvinceBean> listProvinces = new ArrayList<>();
        try {
            connection = DBConnection.getConnect();
            String sql = "SELECT * FROM provinces WHERE to_tsvector(convertnonunicode(name)) @@ to_tsquery(convertnonunicode(?)) ORDER BY NAME ASC;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, keyword);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int areaID = rs.getInt("area_id");
                listProvinces.add(new ProvinceBean(id, name, areaID));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDAO.closeConnection(preparedStatement, connection);
        }

        return listProvinces;
    }
}
