package br.senac.db.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtils {
    public static Connection getConnection() throws SQLException {
        Connection con = null;
        String dbUrl = "jdbc:mysql://localhost:3306/db_pi";
        
        Properties prt = new Properties();
        prt.put("user", "root");
        prt.put("password", "");
        try {
            con = DriverManager.getConnection(dbUrl, prt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}
