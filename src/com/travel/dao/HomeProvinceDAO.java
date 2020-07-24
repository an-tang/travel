package com.travel.dao;

import com.travel.bean.HomeProvinceBean;
import com.travel.dbconnection.DBConnection;
import com.travel.viewmodel.ChartValue;

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
            String sql = "SELECT * FROM home_provinces ORDER BY province_id ASC LIMIT ?;";
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

    public ArrayList<ChartValue> ProvinceWithOrders(int limit){
        ArrayList<ChartValue> values = new ArrayList<>();
        try {
            connection = DBConnection.getConnect();
            String sql = "SELECT name, count FROM home_provinces h INNER JOIN "
                    + " (SELECT t.province_id, count(*) FROM orders o INNER JOIN tours t ON o.tour_id = t.id"
                    + " WHERE t.province_id IN (SELECT province_id FROM home_provinces)"
                    + " GROUP BY t.province_id) AS tem ON h.province_id = tem.province_id"
                    + " ORDER BY h.province_id ASC LIMIT ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, limit);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String key = rs.getString("name");
                int value = rs.getInt("count");
                values.add(new ChartValue(key, value));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDAO.closeConnection(preparedStatement, connection);
        }

        return values;
    }
}
