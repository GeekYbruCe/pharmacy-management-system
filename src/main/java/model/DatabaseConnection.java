package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/pharmacy_db";
    private static final String USER = "your_username";  // replace with your PostgreSQL username
    private static final String PASSWORD = "your_password";  // replace with your PostgreSQL password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
