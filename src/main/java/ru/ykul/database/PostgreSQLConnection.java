package ru.ykul.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQLConnection {

    private final static String URL = "jdbc:postgresql://localhost:5432/postgres";
    private final static String USERNAME = "postgres";
    private final static String PASSWORD = "8487ZX";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
