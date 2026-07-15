package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final  String driver = "com.mysql.cj.jdbc.Driver";
    private static final String jdbcUrl = "jdbc:mysql://localhost:3306/studentmanagementsystem";
    private static final  String user = "root";
    private static final   String password = "123456789";


    public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(jdbcUrl,user,password);
    }

}
