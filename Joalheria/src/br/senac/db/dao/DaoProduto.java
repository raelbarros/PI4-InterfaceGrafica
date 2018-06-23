package br.senac.db.dao;

import br.senac.db.utils.ConnectionUtils;
import br.senac.model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoProduto {

    public static void cadastraProduto(Produto p) throws SQLException, Exception {
        String sql = "INSERT INTO produto (nome, marca, material, tamanho, segmento, idCategoria, quant, precoUni, codigo, img, enabled) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = ConnectionUtils.getConnection();
            pst = con.prepareStatement(sql);

            pst.setString(1, p.getNome());
            pst.setString(2, p.getMarca());
            pst.setString(3, p.getMaterial());
            pst.setString(4, p.getTamanho());
            pst.setString(5, p.getSegmento());
            pst.setInt(6, p.getIdCategoria());
            pst.setInt(7, p.getQuant());
            pst.setFloat(8, p.getPrecoUni());
            pst.setFloat(9, p.getCodigo());
            pst.setBytes(10, p.getImg());
            pst.setBoolean(11, true);

            pst.execute();

        } finally {
            if (pst != null && !pst.isClosed()) {
                pst.isClosed();
            }
            if (con != null && !con.isClosed()) {
                con.isClosed();
            }
        }
    }

    public static List<Produto> getAllProduto() throws SQLException, Exception {
        String sql = "SELECT * FROM produto WHERE (enabled=?)";

        List<Produto> listaProduto = null;

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet result = null;

        try {
            con = ConnectionUtils.getConnection();
            pst = con.prepareStatement(sql);

            pst.setBoolean(1, true);

            result = pst.executeQuery();

            while (result.next()) {
                if (listaProduto == null) {
                    listaProduto = new ArrayList<>();
                }
                Produto p = new Produto();

                p.setIdProduto(result.getInt("idProduto"));
                p.setNome(result.getString("nome"));
                p.setMarca(result.getString("marca"));
                p.setQuant(result.getInt("quant"));
                p.setMaterial(result.getString("material"));
                p.setPrecoUni(result.getFloat("precoUni"));
                p.setSegmento(result.getString("segmento"));
                p.setCodigo(result.getInt("codigo"));
                p.setTamanho(result.getString("tamanho"));

                listaProduto.add(p);
            }
        } finally {
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
        return listaProduto;
    }

    public static Produto getProduto(int id) throws SQLException, Exception {
        String sql = "SELECT idProduto, nome, marca, quant, material, precoUni, segmento, tamanho, codigo, img, idCategoria FROM produto WHERE (idProduto=?) AND (enabled=?)";

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet result = null;

        try {
            con = ConnectionUtils.getConnection();
            pst = con.prepareStatement(sql);

            pst.setInt(1, id);
            pst.setBoolean(2, true);

            result = pst.executeQuery();

            while (result.next()) {
                Produto p = new Produto();

                p.setIdProduto(result.getInt("idProduto"));
                p.setNome(result.getString("nome"));
                p.setMarca(result.getString("marca"));
                p.setQuant(result.getInt("quant"));
                p.setCodigo(result.getInt("codigo"));
                p.setMaterial(result.getString("material"));
                p.setPrecoUni(result.getFloat("precoUni"));
                p.setSegmento(result.getString("segmento"));
                p.setTamanho(result.getString("tamanho"));
                p.setIdCategoria(result.getInt("idCategoria"));
                p.setImg(result.getBytes("img"));

                return p;
            }
        } finally {
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
        return null;
    }

    public static void updateProduto(Produto p) throws SQLException, Exception {
        String sql = "UPDATE produto SET nome=?, marca=?, quant=?, material=?, precoUni=?, segmento=?, tamanho=?, codigo=?, img=?, idCategoria=? WHERE (idProduto=?)";

        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = ConnectionUtils.getConnection();
            pst = con.prepareStatement(sql);

            pst.setString(1, p.getNome());
            pst.setString(2, p.getMarca());
            pst.setInt(3, p.getQuant());
            pst.setString(4, p.getMaterial());
            pst.setFloat(5, p.getPrecoUni());
            pst.setString(6, p.getSegmento());
            pst.setString(7, p.getTamanho());
            pst.setInt(8, p.getCodigo());
            pst.setBytes(9, p.getImg());
            pst.setInt(10, p.getIdCategoria());
            pst.setInt(11, p.getIdProduto());

            pst.execute();

        } finally {
            if (con != null || !con.isClosed()) {
                con.isClosed();
            }
            if (pst != null || !pst.isClosed()) {
                pst.isClosed();
            }
        }
    }

    public static void deleteProduto(int id) throws SQLException, Exception {
        String sql = "UPDATE produto SET enabled=? WHERE (idProduto=?)";

        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = ConnectionUtils.getConnection();
            pst = con.prepareStatement(sql);

            pst.setBoolean(1, false);
            pst.setInt(2, id);

            pst.execute();

        } finally {
            if (con != null || !con.isClosed()) {
                con.isClosed();
            }
            if (pst != null || !pst.isClosed()) {
                pst.isClosed();
            }
        }
    }
    
    public static List<Produto> buscarProduto(String val) throws SQLException, Exception {
        String sql = "SELECT * FROM  produto WHERE ((UPPER(nome) LIKE UPPER(?)) AND enabled=?)";

        List<Produto> listaProdutos = null;

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet result = null;

        try {
            con = ConnectionUtils.getConnection();
            pst = con.prepareStatement(sql);

            pst.setString(1, "%" + val + "%");
            pst.setBoolean(2, true);

            result = pst.executeQuery();

            while (result.next()) {
                if (listaProdutos == null) {
                    listaProdutos = new ArrayList<>();
                }

                Produto p = new Produto();
                
                p.setIdProduto(result.getInt("idProduto"));
                p.setNome(result.getString("nome"));
                p.setMarca(result.getString("marca"));
                p.setQuant(result.getInt("quant"));
                p.setCodigo(result.getInt("codigo"));
                p.setMaterial(result.getString("material"));
                p.setPrecoUni(result.getFloat("precoUni"));
                p.setSegmento(result.getString("segmento"));
                p.setTamanho(result.getString("tamanho"));
                p.setIdCategoria(result.getInt("idCategoria"));
    
                listaProdutos.add(p);
            }

        } finally {
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
        return listaProdutos;
    }

    public static List<Produto> filtroProduto(int val) throws SQLException, Exception {
        String sql = "SELECT * FROM produto WHERE (idCategoria=?) AND (enabled=?)";

        List<Produto> listaProdutos = null;

        PreparedStatement pst = null;
        Connection con = null;
        ResultSet result = null;

        try {
            con = ConnectionUtils.getConnection();
            pst = con.prepareStatement(sql);

            pst.setInt(1, val);
            pst.setBoolean(2, true);
            
            result = pst.executeQuery();
            
            while (result.next()) {
                if (listaProdutos == null) {
                    listaProdutos = new ArrayList<>();
                }

                Produto p = new Produto();
                
                p.setIdProduto(result.getInt("idProduto"));
                p.setNome(result.getString("nome"));
                p.setMarca(result.getString("marca"));
                p.setQuant(result.getInt("quant"));
                p.setCodigo(result.getInt("codigo"));
                p.setMaterial(result.getString("material"));
                p.setPrecoUni(result.getFloat("precoUni"));
                p.setSegmento(result.getString("segmento"));
                p.setTamanho(result.getString("tamanho"));
                p.setIdCategoria(result.getInt("idCategoria"));
                
                listaProdutos.add(p);
            }
            
        } finally {
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
        return listaProdutos;
    }

}
