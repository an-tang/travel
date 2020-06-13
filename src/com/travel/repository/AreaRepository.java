package com.travel.repository;

import com.travel.dbconnection.DBConnection;
import com.travel.model.AreaModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AreaRepository extends BaseRepository {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    public AreaRepository() throws Exception {
        super();
    }

    public ArrayList<AreaModel> GetListArea() {
        ArrayList<AreaModel> listAreas = new ArrayList<>();
        try {
            connection = DBConnection.getConnect();
            String sql = "SELECT * FROM areas ORDER BY ID ASC";
            preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                listAreas.add(new AreaModel(id, name));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseRepository.closeConnection(preparedStatement, connection);
        }

        return listAreas;
    }
}
