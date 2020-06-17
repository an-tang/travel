package com.travel.dao;

import com.travel.bean.ImageBean;
import com.travel.dbconnection.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ImageDAO extends BaseDAO{
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    public ImageDAO() throws Exception {
        super();
    }

    public ArrayList<ImageBean> GetImagesByTourInfoID(int tourInfoID){
        ArrayList<ImageBean> listImages = new ArrayList<>();
        try {
            connection = DBConnection.getConnect();
            String sql = "SELECT * FROM images WHERE tour_info_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, tourInfoID);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
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

}
