package br.senac.db.dao;

import br.senac.db.utils.ConnectionUtils;
import br.senac.model.CategoriaProduto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoCategoria {

    // Cadastra uma nova Categoria no banco
    public static void inserirCategoria(CategoriaProduto c) throws SQLException, Exception {
        // Script banco
        String sql = "INSERT INTO categoria_produto (nome, enabled) VALUES (?, ?)";

        // Variaveis de manipulação do banco
        Connection con = null;
        PreparedStatement pst = null;

        try {
            // Pega conexao com o banco
            con = ConnectionUtils.getConnection();
            pst = con.prepareStatement(sql);

            // Condicoes do select
            pst.setString(1, c.getNome());
            pst.setBoolean(2, true);

            // Executa o script do banco
            pst.execute();

        } finally {
            // Fecha as conexoes com o banco
            if (pst != null && !pst.isClosed()) {
                pst.close();
            }
            if (con != null && !con.isClosed()) {
                con.close();
            }
        }
    }

    // Pega todas as Categorias do Banco
    public static List<CategoriaProduto> getAllCategoria() throws SQLException, Exception {
        // Script banco
        String sql = "SELECT * FROM categoria_produto WHERE (enabled=?)";

        // Lista para guardar os obj retornados do banco
        List<CategoriaProduto> listaCategoria = null;

        // Condicoes do select
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet result = null;

        try {
            // Pega conexao com o banco
            con = ConnectionUtils.getConnection();
            pst = con.prepareStatement(sql);

            // Condicoes do select
            pst.setBoolean(1, true);

            // Executa o script do banco
            // Pega o resultado do banco
            result = pst.executeQuery();

            while (result.next()) {
                // Inicializa a lista 
                if (listaCategoria == null) {
                    listaCategoria = new ArrayList<>();
                }

                // Estrutura o obj que retornou do banco
                CategoriaProduto c = new CategoriaProduto();
                c.setIdCategoria(result.getInt("idCategoria"));
                c.setNome(result.getString("nome"));

                // add o obj na lista
                listaCategoria.add(c);
            }
        } finally {
            // Fecha as conexoes com o banco
            if (result != null && !result.isClosed()) {
                result.isClosed();
            }
            if (pst != null && !pst.isClosed()) {
                pst.isClosed();
            }
            if (con != null && !con.isClosed()) {
                con.isClosed();
            }
        }

        // retorna a lista com as categorias
        return listaCategoria;
    }

    // Pega uma categoria por nome
    public static CategoriaProduto getCategoriaByName(String nome) throws SQLException, Exception {
        // Script banco
        String sql = "SELECT idCategoria FROM categoria_produto WHERE (nome=?) AND (enabled=?)";

        // Variaveis de manipulação do banco
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet result = null;

        // Obj que sera recebido do banco
        CategoriaProduto p = null;

        try {
            // Pega conexao com o banco
            con = ConnectionUtils.getConnection();
            pst = con.prepareStatement(sql);

            // Condicoes do select
            pst.setString(1, nome);
            pst.setBoolean(2, true);

            // Executa o script do banco
            // Pega o resultado do banco
            result = pst.executeQuery();

            while (result.next()) {
                // Inicializa o obj
                p = new CategoriaProduto();
                p.setIdCategoria(result.getInt("idCategoria"));
            }
        } finally {
            // Fecha as conexoes com o banco
            if (result != null && !result.isClosed()) {
                result.isClosed();
            }
            if (pst != null && !pst.isClosed()) {
                pst.isClosed();
            }
            if (con != null && !con.isClosed()) {
                con.isClosed();
            }
        }

        // retorna o obj
        return p;
    }

    // Pega uma categoria por Id
    public static CategoriaProduto getCategoriaById(int id) throws SQLException, Exception {
        // Script banco
        String sql = "SELECT nome FROM categoria_produto WHERE (idCategoria=?) AND (enabled=?)";

        // Variaveis de manipulação do banco
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet result = null;

        // Obj que sera recebido do banco
        CategoriaProduto c = null;

        try {
            // Pega conexao com o banco
            con = ConnectionUtils.getConnection();
            pst = con.prepareStatement(sql);

            // Condicoes do select 
            pst.setInt(1, id);
            pst.setBoolean(2, true);

            // Executa o script do banco
            // Pega o resultado do banco
            result = pst.executeQuery();

            while (result.next()) {
                // Inicializa o obj
                c = new CategoriaProduto();
                c.setNome(result.getString("nome"));
            }
        } finally {
            // Fecha as conexoes com o banco
            if (result != null && !result.isClosed()) {
                result.isClosed();
            }
            if (pst != null && !pst.isClosed()) {
                pst.isClosed();
            }
            if (con != null && !con.isClosed()) {
                con.isClosed();
            }
        }
        // retorna o obj
        return c;
    }

}
