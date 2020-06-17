package com.travel.dao;

import com.travel.dbconnection.DBConnection;
import com.travel.bean.TourBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TourDAO extends BaseDAO {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    public TourDAO() throws Exception {
        super();
    }

    public ArrayList<TourBean> GetAllTours(int page, int perPage) {
        ArrayList<TourBean> tours = new ArrayList<>();
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
                tours.add(new TourBean(id, name, image, tourInfoID));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDAO.closeConnection(preparedStatement, connection);
        }
        return tours;
    }

    public TourBean GetTourByID(int id) {
        TourBean tourBean = null;
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
                tourBean = new TourBean(name, image, tourInfoID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDAO.closeConnection(preparedStatement, connection);
        }
        return tourBean;
    }

    public ArrayList<TourBean> GetToursByName(String name, int page, int perPage) {
        ArrayList<TourBean> listTours = new ArrayList<>();
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
                listTours.add(new TourBean(id, tourName, image, tourInfoID));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDAO.closeConnection(preparedStatement, connection);
        }
        return listTours;
    }

    public ArrayList<TourBean> GetToursByAreaID(int areaID, int limit) {
        ArrayList<TourBean> tours = new ArrayList<>();
        try {
            connection = DBConnection.getConnect();
            String sql = "SELECT * FROM ("
                    + " SELECT t.id, a.name AS area_name, COUNT(t.id) as c FROM orders o inner join tours t on o.tour_id = t.id"
                    + " INNER JOIN provinces p ON t.province_id = p.id"
                    + " INNER JOIN areas a ON p.area_id = a.id"
                    + " WHERE a.id = ?"
                    + " GROUP BY t.id, a.name"
                    + " ORDER BY count(t.id) DESC"
                    + " LIMIT ?"
                    + " ) AS tem INNER JOIN tours ON tem.id =tours.id"
                    + " ORDER BY tem.c DESC;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, areaID);
            preparedStatement.setInt(2, limit);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String image = rs.getString("image");
                int provinceID = rs.getInt("province_id");
                tours.add(new TourBean(id, name, image, provinceID));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDAO.closeConnection(preparedStatement, connection);
        }

        return tours;
    }
}
