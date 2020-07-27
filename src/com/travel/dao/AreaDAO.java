package com.travel.dao;

import com.travel.dbconnection.DBConnection;
import com.travel.bean.AreaBean;
import com.travel.viewmodel.ChartValue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AreaDAO extends BaseDAO {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    public AreaDAO() throws Exception {
        super();
    }

    public ArrayList<AreaBean> GetListArea() {
        ArrayList<AreaBean> listAreas = new ArrayList<>();
        try {
            connection = DBConnection.getConnect();
            String sql = "SELECT * FROM areas ORDER BY ID ASC";

            preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                listAreas.add(new AreaBean(id, name));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDAO.closeConnection(preparedStatement, connection);
        }

        return listAreas;
    }

    public ArrayList<ChartValue> AreaWithOrders() {
        ArrayList<ChartValue> values = new ArrayList<>();
        try {
            connection = DBConnection.getConnect();
            String sql = "SELECT a.id, a.name, count(*) from orders o INNER JOIN tours t ON o.tour_id = t.id"
                    + " INNER JOIN provinces p ON t.province_id = p.id"
                    + " INNER JOIN areas a ON p.area_id = a.id"
                    + " GROUP BY a.id, a.name "
                    + " ORDER BY a.id ASC";
            preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            int sum = 0;
            while (rs.next()) {
                String key = rs.getString("name");
                int value = rs.getInt("count");
                sum += value;
                values.add(new ChartValue(key, value));
            }

            int percent = 0;
            for (ChartValue v : values) {
                int p = v.getValue() * 100 / sum;
                v.setValue(p);
                percent += p;
            }
            if (percent != 100) {
                values.get(0).setValue(values.get(0).getValue() + 100 - percent);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDAO.closeConnection(preparedStatement, connection);
        }

        return values;
    }

    public AreaBean GetAreaByID(int id) {
        AreaBean areaBean = null;
        try {
            connection = DBConnection.getConnect();
            String sql = "SELECT * FROM areas WHERE id = ? LIMIT 1";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                areaBean = new AreaBean(id, name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDAO.closeConnection(preparedStatement, connection);
        }

        return areaBean;
    }
}
