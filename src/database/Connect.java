package database;

import java.sql.*;

// create docker sql database
// docker run --name mysqlbd -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root mysql                                                                           ✔

public class Connect {
    private static final String url = "jdbc:mysql://localhost:3306/solucoes-computacionais";
    private static final String username = "root";
    private static final String password = "root";

    public static Connection getConnection() throws SQLException {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found", e);
        }
        return DriverManager.getConnection(url, username, password);
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