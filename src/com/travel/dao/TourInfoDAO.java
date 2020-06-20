package com.travel.dao;

import com.travel.bean.TourBean;
import com.travel.bean.TourInfoBean;
import com.travel.dbconnection.DBConnection;
import com.travel.enumerize.Status;
import org.postgresql.ds.common.BaseDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TourInfoDAO extends BaseDAO {
    Connection connection = null;
    PreparedStatement preparedStatement = null;


    public TourInfoDAO() throws Exception {
        super();
    }

    public TourInfoBean GetTourInfoByTourID(int tourID) {
        TourInfoBean tourInfo = null;
        try {
            this.connection = DBConnection.getConnect();
            String sql = "SELECT * FROM tour_infos WHERE tour_id = ? AND status = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, tourID);
            preparedStatement.setInt(2, Status.ACTIVE.getValue());

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String detail = rs.getString("detail");
                long price = rs.getLong("price");
                tourInfo = new TourInfoBean(id, title, detail, price, Status.ACTIVE.getValue(), tourID, null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDAO.closeConnection(preparedStatement, connection);
        }

        return tourInfo;
    }
}
