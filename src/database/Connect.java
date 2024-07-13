package database;

import java.sql.*;

public class Connect {
    private static final String url = "jdbc:mysql://localhost:3306/automotive";
    private static final String username = "root";
    private static final String password = "root";

    public static Connection getConnection() throws SQLException {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found", e);
        }

        try {
            return DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            throw new SQLException("Connection failed", e);
        }

    }

    public static void main(String[] args) {
        try {
            Connection connection = getConnection();
            System.out.println("Connection established successfully.");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}