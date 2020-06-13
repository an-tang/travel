package com.travel.repository;

import com.travel.dbconnection.DBConnection;
import com.travel.model.TourModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TourRepository extends BaseRepository {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    public TourRepository() throws Exception {
        super();
    }

    public ArrayList<TourModel> GetAllTours(int page, int perPage) {
        ArrayList<TourModel> tours = new ArrayList<>();
        try {
            connection = DBConnection.getConnect();
            String sql = "SELECT * FROM tours ORDER BY NAME ASC LIMIT ? OFFSET ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, page);
            preparedStatement.setInt(2, perPage);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String image = rs.getString("image");
                int tourInfoID = rs.getInt("tour_info_id");
                tours.add(new TourModel(id, name, image, tourInfoID));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseRepository.closeConnection(preparedStatement, connection);
        }
        return tours;
    }

    public TourModel GetTourByID(int id) {
        TourModel tourModel = null;
        try {
            connection = DBConnection.getConnect();
            String sql = "SELECT * FROM";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String image = rs.getString("image");
                int tourInfoID = rs.getInt("tour_info_id");
                tourModel = new TourModel(name, image, tourInfoID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseRepository.closeConnection(preparedStatement, connection);
        }
        return tourModel;
    }

    public ArrayList<TourModel> GetToursByName(String name, int page, int perPage) {
        ArrayList<TourModel> listTours = new ArrayList<>();
        try {
            connection = DBConnection.getConnect();
            String sql = "SELECT * FROM tours WHERE to_tsvector(convertnonunicode(name)) @@ to_tsquery(convertnonunicode(?))"
                    + " ORDER BY NAME ASC LIMIT ? OFFSET ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, page);
            preparedStatement.setInt(3, perPage);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String tourName = rs.getString("name");
                String image = rs.getString("image");
                int tourInfoID = rs.getInt("tour_info_id");
                listTours.add(new TourModel(id, tourName, image, tourInfoID));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseRepository.closeConnection(preparedStatement, connection);
        }
        return listTours;
    }
}
