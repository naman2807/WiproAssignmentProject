package com.wipro.candidate.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/wiproassignment";
    private static final String root = "root";
    private static final String password = "";
    private static Connection connection;

    /**
     * @return Connection instance.
     */
    public static Connection getDBConn() {
        return connection;
    }

    public static void connectToDatabase() {
        try {
            connection = DriverManager.getConnection(URL, root, password);
            System.out.println("Database connected.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
