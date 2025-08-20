package app.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/social-app";
    private static final String USER = "root";   // change if needed
    private static final String PASSWORD = "Abd@11ah";   // your MySQL password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }


}
