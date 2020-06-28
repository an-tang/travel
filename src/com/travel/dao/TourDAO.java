package com.travel.dao;

import com.travel.dbconnection.DBConnection;
import com.travel.bean.TourBean;
import com.travel.enumerize.Status;

import java.sql.*;
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
            String sql = "SELECT * FROM tours t INNER JOIN tour_infos ti ON t.id = ti.tour_id ORDER BY status DESC, name ASC LIMIT ? OFFSET ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, page * perPage + perPage);
            preparedStatement.setInt(2, page * perPage);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String image = rs.getString("image");
                int provinceID = rs.getInt("province_id");
                long price = rs.getLong("price");
                tours.add(new TourBean(id, name, image, provinceID, price));
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
            String sql = "SELECT * FROM tours t INNER JOIN tour_infos ti ON t.id = ti.tour_id WHERE t.id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String image = rs.getString("image");
                int provinceID = rs.getInt("province_id");
                long price = rs.getLong("price");
                tourBean = new TourBean(name, image, provinceID, price);
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
            String sql = "SELECT * FROM tours t INNER JOIN tour_infos ti ON t.id = ti.tour_id "
                    + " WHERE to_tsvector(convertnonunicode(name)) @@ to_tsquery(convertnonunicode(?))"
                    + " AND ti.status = ? ORDER BY NAME ASC LIMIT ? OFFSET ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, Status.ACTIVE.getValue());
            preparedStatement.setInt(3, page * perPage + perPage);
            preparedStatement.setInt(4, page * perPage);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String tourName = rs.getString("name");
                String image = rs.getString("image");
                int provinceID = rs.getInt("province_id");
                long price = rs.getLong("price");
                listTours.add(new TourBean(id, tourName, image, provinceID, price));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDAO.closeConnection(preparedStatement, connection);
        }
        return listTours;
    }

    public ArrayList<TourBean> GetToursInProvinceByName(String name, int provinceID, int page, int perPage) {
        ArrayList<TourBean> listTours = new ArrayList<>();
        try {
            connection = DBConnection.getConnect();
            String sql = "SELECT * FROM tours t INNER JOIN tour_infos ti ON t.id = ti.tour_id "
                    + " WHERE to_tsvector(convertnonunicode(name)) @@ to_tsquery(convertnonunicode(?))"
                    + " AND t.province_id = ? AND ti.status = ? ORDER BY NAME ASC LIMIT ? OFFSET ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, provinceID);
            preparedStatement.setInt(3, Status.ACTIVE.getValue());
            preparedStatement.setInt(4, page * perPage + perPage);
            preparedStatement.setInt(5, page * perPage);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String tourName = rs.getString("name");
                String image = rs.getString("image");
                long price = rs.getLong("price");
                listTours.add(new TourBean(id, tourName, image, provinceID, price));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDAO.closeConnection(preparedStatement, connection);
        }
        return listTours;
    }

    public ArrayList<TourBean> GetTopToursByAreaID(int areaID, int limit) {
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
                    + " INNER JOIN tour_infos ti ON tours.id = ti.tour_id"
                    + " WHERE ti.status = ? ORDER BY tem.c DESC;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, areaID);
            preparedStatement.setInt(2, limit);
            preparedStatement.setInt(3, Status.ACTIVE.getValue());

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String image = rs.getString("image");
                int provinceID = rs.getInt("province_id");
                long price = rs.getLong("price");
                tours.add(new TourBean(id, name, image, provinceID, price));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDAO.closeConnection(preparedStatement, connection);
        }

        return tours;
    }

    public ArrayList<TourBean> GetToursTopOrder(int limit) {
        ArrayList<TourBean> tours = new ArrayList<>();
        try {
            connection = DBConnection.getConnect();
            String sql = "SELECT * FROM ("
                    + " SELECT t.id, count(t.id) as c FROM orders o INNER JOIN tours t ON o.tour_id = t.id"
                    + " INNER JOIN provinces p ON t.province_id = p.id"
                    + " GROUP BY t.id "
                    + " ORDER BY count(t.id) DESC "
                    + " LIMIT ?"
                    + " ) AS tem INNER JOIN tours ON tem.id = tours.id"
                    + " INNER JOIN tour_infos ti ON tours.id = ti.tour_id"
                    + " WHERE ti.status = ? ORDER BY c desc";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, Status.ACTIVE.getValue());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String image = rs.getString("image");
                int provinceID = rs.getInt("province_id");
                long price = rs.getLong("price");
                tours.add(new TourBean(id, name, image, provinceID, price));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDAO.closeConnection(preparedStatement, connection);
        }

        return tours;
    }

    public ArrayList<TourBean> GetTourByAreaID(int areaID, int limit) {
        ArrayList<TourBean> tours = new ArrayList<>();
        try {
            connection = DBConnection.getConnect();
            String sql = "SELECT t.*, ti.price FROM tours t INNER JOIN provinces p ON t.province_id = p.id"
                    + " INNER JOIN areas a ON p.area_id = a.id"
                    + " INNER JOIN tour_infos ti ON t.id = ti.tour_id"
                    + " WHERE a.id = ? AND ti.status = ? ORDER BY t.id ASC LIMIT ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, areaID);
            preparedStatement.setInt(2, Status.ACTIVE.getValue());
            preparedStatement.setInt(3, limit);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String image = rs.getString("image");
                int provinceID = rs.getInt("province_id");
                long price = rs.getLong("price");
                tours.add(new TourBean(id, name, image, provinceID, price));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDAO.closeConnection(preparedStatement, connection);
        }
        return tours;
    }

    public ArrayList<TourBean> GetToursExcludingIDs(String params, int areaID, int limit) {
        ArrayList<TourBean> tours = new ArrayList<>();
        try {
            connection = DBConnection.getConnect();
            String sql = "SELECT t.*, ti.price FROM tours t INNER JOIN provinces p ON t.province_id = p.id "
                    + " INNER JOIN areas a ON p.area_id = a.id"
                    + " INNER JOIN tour_infos ti ON t.id = ti.tour_id"
                    + " WHERE a.id = ? AND t.id NOT IN("
                    + params
                    + ") AND ti.status = ? ORDER BY id ASC LIMIT ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, areaID);
            preparedStatement.setInt(2, Status.ACTIVE.getValue());
            preparedStatement.setInt(3, limit);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String image = rs.getString("image");
                int provinceID = rs.getInt("province_id");
                long price = rs.getLong("price");
                tours.add(new TourBean(id, name, image, provinceID, price));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDAO.closeConnection(preparedStatement, connection);
        }

        return tours;
    }

    public boolean DeactivateTour(int tourID) {
        try {
            connection = DBConnection.getConnect();
            String sql = "UPDATE tour_infos SET status = ?, updated_at = now() WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Status.DEACTIVE.getValue());
            preparedStatement.setInt(2, tourID);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Deactivate tour failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            BaseDAO.closeConnection(preparedStatement, connection);
        }

        return true;
    }

    public ArrayList<TourBean> GetToursByListIDs(String params) {
        ArrayList<TourBean> tours = new ArrayList<>();
        try {
            connection = DBConnection.getConnect();
            String sql = "SELECT t.*, ti.price FROM tours t INNER JOIN tour_info ti WHERE t.id IN ("
                    + params
                    + " ) ORDER BY price DESC;";
            preparedStatement = connection.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String image = rs.getString("image");
                int provinceID = rs.getInt("province_id");
                long price = rs.getLong("price");
                tours.add(new TourBean(id, name, image, provinceID, price));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tours;
    }

    public int CreateTour(TourBean tour) {
        int id = 0;
        try {
            connection = DBConnection.getConnect();
            String sql = "INSERT INTO tours (name, image, province_id, created_at, updated_at) VALUES (?, ?, ?, now(), now())";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, tour.getName());
            preparedStatement.setString(2, tour.getImage());
            preparedStatement.setInt(3, tour.getProvinceID());

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    id = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating tour failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } finally {
            BaseDAO.closeConnection(preparedStatement, connection);
        }

        return id;
    }
}
