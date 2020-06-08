package com.travel.repository;

import com.travel.dbconnection.DBConnection;

import java.sql.Connection;

public class BaseRepository {
    private Connection connection = null;

    public BaseRepository() throws Exception{
        try {
            connection = DBConnection.getConnect();
        }catch (Exception e){
            System.out.println("Failed to connection database: " + e.getMessage());
            e.printStackTrace();
            throw  e;
        }
    }
}
