package com.travel.dao;

import com.travel.bean.HomeProvinceBean;
import com.travel.dbconnection.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HomeProvinceDAO extends BaseDAO {

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    public HomeProvinceDAO() throws Exception {
        super();
    }

    public ArrayList<HomeProvinceBean> GetHomePageProvinces(int limit) {
        ArrayList<HomeProvinceBean> provinces = new ArrayList<>();
        try {
            connection = DBConnection.getConnect();
            String sql = "SELECT * FROM home_provinces ORDER BY province_id ASC LIMIT ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, limit);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("province_id");
                String name = rs.getString("name");
                String url = rs.getString("url");
                provinces.add(new HomeProvinceBean(id, name, url));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDAO.closeConnection(preparedStatement, connection);
        }

        return provinces;
    }
}
