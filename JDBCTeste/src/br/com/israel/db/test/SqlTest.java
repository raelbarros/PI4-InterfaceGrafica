/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.israel.db.test;

import br.com.israel.db.utils.ConnectionsUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 *
 * @author Israel
 */
public class SqlTest {

    // Representa um objeto de conexao
    private static Connection con = null;

    // Metodo principal
    public static void main(String[] args) {
        try {
            // Obtem a conexao com o banco
            con = ConnectionsUtils.getConnection();

            // Chama um metodo para execucao de um comando SQL
//            insert();
//            listAll();
//            update();
//            listAll();
//            delete();
//            listAll();
            interageUsuario();

            // Informa do sucesso da conexao
            System.out.println("Sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // tenta fechar a conexao
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Realiza a insercao de um elemento no bando de dados
    private static void insert(String nome, String genero, String endereco, int tel) throws SQLException {
        // Cria a string de comando SQL
        String sql = "INSERT INTO cliente (nome, genero, endereco, telefone) VALUES (?, ?, ?, ?)";

        // Cria um onjeto de "PreparedStatement", para execucao de comandos
        PreparedStatement pst = con.prepareStatement(sql);

        //Configura os parametros
        // pst.setInt(1, 0);
        pst.setString(1, nome);
        pst.setString(2, genero);
        pst.setString(3, endereco);
        pst.setInt(4, tel);

        //Executa o comando
        pst.execute();
    }

    private static void listAll() throws SQLException {
        // Cria o Select
        String sql = "SELECT * FROM cliente";

        // Cria o statment para execucao da consulta
        PreparedStatement pst = con.prepareStatement(sql);

        // Executa a consulta e armazena os resultados em um ResultSet
        ResultSet rs = pst.executeQuery();

        // Intera o ResultSet e obtem os dados de cada linha
        while (rs.next()) {
            System.out.println(rs.getInt("id") + " | " + rs.getString("nome") + " - " + rs.getString("genero")
                    + " - " + rs.getString("endereco") + " - " + rs.getInt("telefone")
            );
        }

    }

    private static void update(int id, String nome, String genero, String endereco, int tel) throws SQLException {
        // Mostra a string SQL
        String sql = "UPDATE cliente SET nome=?, genero=?, endereco=?, telefone=? WHERE id=?";

        // Cria um statement
        PreparedStatement pst = con.prepareStatement(sql);

        // Configura os parametros
        pst.setString(1, nome);
        pst.setString(2, genero);
        pst.setString(3, endereco);
        pst.setInt(4, tel);
        pst.setInt(5, id);

        // Executa o comando SQL
        pst.execute();
    }

    private static void delete(int id) throws SQLException {
        // Monta a string SQL
        String sql = "DELETE FROM cliente WHERE id=?";

        // Cria um statement
        PreparedStatement pst = con.prepareStatement(sql);

        // Configura os parametros
        pst.setInt(1, id);

        // Executa o comando SQL
        pst.execute();
    }

    private static void interageUsuario() throws SQLException {
        Scanner s = new Scanner(System.in);
        int op = 0;
        boolean exit = false;
        while (!exit) {
            System.out.println("\n **** Selecione alguma operacao **** ");
            System.out.println("(1) - Inserir novo dados\n(2) - Atualizar dados\n(3) - Deletar dados\n(4) - Listar tudo\n(5) - Sair");
            op = s.nextInt();

            switch (op) {
                case 1:
                    System.out.print("Informe o nome: ");
                    String nome = s.next();
                    System.out.print("Informe o genero (M/F): ");
                    String genero = s.next();
                    System.out.print("Informe o endereco: ");
                    String endereco = s.next();
                    System.out.print("Informe o telefone (Apenas numeros): ");
                    int tel = s.nextInt();

                    if (!nome.isEmpty()) {
                        insert(nome, genero, endereco, tel);
                    }
                    break;
                case 2:
                    System.out.print("Informe o id de qual dado vc quer atualizar: ");
                    int id = s.nextInt();

                    String sql = ("SELECT id FROM cliente");

                    PreparedStatement pst = con.prepareStatement(sql);
                    ResultSet rs = pst.executeQuery();

                    List<Integer> list = new ArrayList<>();

                    while (rs.next()) {
                        list.add(rs.getInt("id"));
                    }

                    if (id <= list.size()) {
                        System.out.print("Informe o nome: ");
                        String n = s.next();
                        System.out.print("Informe o genero (M/F): ");
                        String g = s.next();
                        System.out.print("Informe o endereco: ");
                        String e = s.next();
                        System.out.print("Informe o telefone (Apenas numeros): ");
                        int t = s.nextInt();

                        if (!n.isEmpty()) {
                            update(id, n, g, e, t);
                        }
                    } else {
                        System.out.println("Informe um id Valido");
                    }
                    break;
                case 3:
                    System.out.print("Informe o id de qual dado vc quer deletar: ");
                    int uid = s.nextInt();

                    String sql01 = ("SELECT id FROM cliente");

                    PreparedStatement pst01 = con.prepareStatement(sql01);
                    ResultSet rs01 = pst01.executeQuery();

                    List<Integer> list01 = new ArrayList<>();

                    while (rs01.next()) {
                        list01.add(rs01.getInt("id"));
                    }

                    if (uid <= list01.size()) {
                        delete(uid);

                    } else {
                        System.out.println("Informe um id Valido");
                    }
                    break;
                case 4:
                    listAll();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Informe uma operacao Valida");
                    break;
            }

        }

    }

}
