package com.travel.dao;

import com.travel.bean.ImageBean;
import com.travel.dbconnection.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ImageDAO extends BaseDAO {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    public ImageDAO() throws Exception {
        super();
    }

    public boolean CreateImage(String params) {
        try {
            connection = DBConnection.getConnect();
            String sql = "INSERT INTO images (url, tour_info_id, description, created_at, updated_at) VALUES "
                    + params + " ;";

            preparedStatement = connection.prepareStatement(sql);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating image failed, no rows affected.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDAO.closeConnection(preparedStatement, connection);
        }

        return true;
    }

    public ArrayList<ImageBean> GetImagesByTourInfoID(int tourInfoID) {
        ArrayList<ImageBean> listImages = new ArrayList<>();
        try {
            connection = DBConnection.getConnect();
            String sql = "SELECT * FROM images WHERE tour_info_id = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, tourInfoID);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String url = rs.getString("url");
                String description = rs.getString("description");
                listImages.add(new ImageBean(id, url, tourInfoID, description));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listImages;
    }

    public boolean UpdateImages(String params, int tourInfoID) {
        try {
            connection = DBConnection.getConnect();
            String sql = "DELETE FROM images WHERE tour_info_id = ?;"
                    + " INSERT INTO images (url, tour_info_id, description, created_at, updated_at) VALUES "
                    + params + " ;";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, tourInfoID);

            System.out.println(preparedStatement);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating image failed, no rows affected.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDAO.closeConnection(preparedStatement, connection);
        }

        return true;
    }

//    todo viet lai ham UpdateImage, hiện tại vô vòng lặp sẽ xóa cũ tạo mới => chỉ tạo giá trị cuối cùng
}
