package com.travel.dao;

import com.travel.dbconnection.DBConnection;
import com.travel.bean.AreaBean;

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
}
