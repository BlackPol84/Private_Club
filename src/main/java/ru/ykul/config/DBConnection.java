package ru.ykul.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static String url = "jdbc:postgresql://localhost:5432/postgres";
    private static String username = "postgres";
    private static String password = "8487ZX";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public static void setConnectionParam(String url, String username, String password) {
        DBConnection.url = url;
        DBConnection.username = username;
        DBConnection.password = password;
    }
}

