/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.israel.db.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Israel
 */
public class ConnectionsUtils {
    
    // Armazena o objeto de conexao
    private static Connection connection = null;
    
    // Pbtem uma conexao com o banco de dados
    public static Connection getConnection() throws SQLException{
        // So tenta abrir uma conexao se nao existir ou estiver fechada
        if (connection == null || connection.isClosed()){
            // Endereco da conexao com o bando de dados + nome do banco
            String dbURL = "jdbc:mysql://localhost:3306/" + "jdbc_teste";
            
            // Propriedades para armazenamento de driver, usuario e senha
            Properties properties = new Properties();
            properties.put("user", "root");
            properties.put("password", "");
            
            // Realizxa a conexao com o banco
            connection = DriverManager.getConnection(dbURL, properties);
        }
        return connection;
    }

}
