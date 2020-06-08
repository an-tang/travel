package com.travel.dbconnection;

import java.sql.*;

public class DBConnection {
    private static String url = "jdbc:postgresql://localhost:5432/travel_info";
    private static String user = "postgres";
    private static String password = "tangan1903";

    public static Connection getConnect() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found " + e);
        }
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("SQL exception occured" + e);
        }

        return connection;
    }
}
