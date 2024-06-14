package database;

import java.sql.*;

// create docker sql database
// docker run --name mysqlbd -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root mysql                                                                           ✔

public class Connect {
    private static final String url = "jdbc:mysql://localhost:3306/users?useSSL=true&allowPublicKeyRetrieval=true";
    private static final String username = "root";
    private static final String password = "root";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}