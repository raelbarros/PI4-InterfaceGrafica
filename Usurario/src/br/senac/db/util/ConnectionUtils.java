package br.senac.db.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtils {

    public static Connection getConnection() throws SQLException {
        Connection con = null;
        String dbUrl = "jdbc:mysql://localhost:3306/usuario_test";

        Properties properties = new Properties();
        properties.put("user", "root");
        properties.put("password", "adminadmin");

        try {
            con = DriverManager.getConnection(dbUrl, properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}