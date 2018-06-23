package br.senac.db.dao;

import br.senac.db.utils.ConnectionUtils;
import br.senac.model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

public class DaoCliente {

    public static void inserirCliente(Cliente c) throws SQLException, Exception {
        String sql = "INSERT INTO cliente (nome, sobrenome, email, rg, cpf, endereco, telefone, sexo, dtNascimento, enabled) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = ConnectionUtils.getConnection();
            pst = con.prepareStatement(sql);

            pst.setString(1, c.getNome());
            pst.setString(2, c.getSobrenome());
            pst.setString(3, c.getEmail());
            pst.setString(4, c.getRg());
            pst.setString(5, c.getCpf());
            pst.setString(6, c.getEndereco());
            pst.setString(7, c.getTelefone());
            pst.setString(8, c.getSexo());
            Timestamp t = new Timestamp(c.getDtNascimento().getTime());
            pst.setTimestamp(9, t);
            pst.setBoolean(10, true);

            pst.execute();
        } finally {

            if (pst != null && !pst.isClosed()) {
                pst.close();
            }
            if (con != null && !con.isClosed()) {
                con.close();
            }
        }
    }

    public static List<Cliente> getAllCliente() throws SQLException, Exception {

        String sql = "SELECT * FROM cliente WHERE (enabled=?)";

        List<Cliente> listaClientes = null;

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet result = null;

        try {
            con = ConnectionUtils.getConnection();
            pst = con.prepareStatement(sql);

            pst.setBoolean(1, true);
            result = pst.executeQuery();
            while (result.next()) {
                if (listaClientes == null) {
                    listaClientes = new ArrayList<>();
                }

                Cliente c = new Cliente();
                c.setIdCliente(result.getInt("idCliente"));
                c.setNome(result.getString("nome"));
                c.setSobrenome(result.getString("sobrenome"));
                c.setEmail(result.getString("email"));
                c.setRg(result.getString("rg"));
                c.setCpf(result.getString("cpf"));
                c.setEndereco(result.getString("endereco"));
                c.setTelefone(result.getString("telefone"));
                c.setSexo(result.getString("sexo"));
                Date d = new Date(result.getDate("dtNascimento").getTime());
                c.setDtNascimento(d);

                listaClientes.add(c);
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
        return listaClientes;
    }

    public static Cliente getClienteById(int id) throws SQLException, Exception {
        String sql = "SELECT idCliente, nome, sobrenome, email, rg, cpf, endereco, telefone, sexo, dtNascimento, enabled FROM cliente WHERE (idCliente=?) AND (enabled=?)";

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
                Cliente c = new Cliente();

                c.setIdCliente(result.getInt("idCliente"));
                c.setNome(result.getString("nome"));
                c.setSobrenome(result.getString("sobrenome"));
                c.setEmail(result.getString("email"));
                c.setRg(result.getString("rg"));
                c.setCpf(result.getString("cpf"));
                c.setEndereco(result.getString("endereco"));
                c.setTelefone(result.getString("telefone"));
                c.setSexo(result.getString("sexo"));
                Date d = new Date(result.getDate("dtNascimento").getTime());
                c.setDtNascimento(d);

                return c;

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
    
        public static Cliente getClienteByRg(String rg) throws SQLException, Exception {
        String sql = "SELECT idCliente, nome, sobrenome, email, rg, cpf, endereco, telefone, sexo, dtNascimento, enabled FROM cliente WHERE (rg=?) AND (enabled=?)";

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet result = null;

        try {
            con = ConnectionUtils.getConnection();
            pst = con.prepareStatement(sql);

            pst.setString(1, rg);
            pst.setBoolean(2, true);

            result = pst.executeQuery();

            while (result.next()) {
                Cliente c = new Cliente();

                c.setIdCliente(result.getInt("idCliente"));
                c.setNome(result.getString("nome"));
                c.setSobrenome(result.getString("sobrenome"));
                c.setEmail(result.getString("email"));
                c.setRg(result.getString("rg"));
                c.setCpf(result.getString("cpf"));
                c.setEndereco(result.getString("endereco"));
                c.setTelefone(result.getString("telefone"));
                c.setSexo(result.getString("sexo"));
                Date d = new Date(result.getDate("dtNascimento").getTime());
                c.setDtNascimento(d);

                return c;

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

    public static void updateCliente(Cliente c) throws SQLException, Exception {
        String sql = "UPDATE cliente SET nome=?, sobrenome=?, email=?, rg=?, cpf=?, endereco=?, telefone=?, sexo=?, dtNascimento=? WHERE (idCliente=?)";

        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = ConnectionUtils.getConnection();
            pst = con.prepareStatement(sql);

            pst.setString(1, c.getNome());
            pst.setString(2, c.getSobrenome());
            pst.setString(3, c.getEmail());
            pst.setString(4, c.getRg());
            pst.setString(5, c.getCpf());
            pst.setString(6, c.getEndereco());
            pst.setString(7, c.getTelefone());
            pst.setString(8, c.getSexo());
            Timestamp t = new Timestamp(c.getDtNascimento().getTime());
            pst.setTimestamp(9, t);
            pst.setInt(10, c.getIdCliente());

            pst.execute();
        } catch (Exception e) {

            if (pst != null && !pst.isClosed()) {
                pst.isClosed();
            }
            if (con != null && !con.isClosed()) {
                con.isClosed();
            }
        }
    }

    public static void deleteCliente(int id) throws SQLException, Exception {
        String sql = "UPDATE cliente SET enabled=? WHERE (idCliente=?)";

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
    
    public static List<Cliente> buscarCliente(String val) throws SQLException, Exception {
        String sql = "SELECT * FROM  cliente WHERE ((UPPER(nome) LIKE UPPER(?) OR (UPPER(sobrenome)) LIKE UPPER(?)) AND enabled=?)";

        List<Cliente> listaClientes = null;

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet result = null;

        try {
            con = ConnectionUtils.getConnection();
            pst = con.prepareStatement(sql);

            pst.setString(1, "%" + val + "%");
            pst.setString(2, "%" + val + "%");
            pst.setBoolean(3, true);

            result = pst.executeQuery();

            while (result.next()) {
                if (listaClientes == null) {
                    listaClientes = new ArrayList<>();
                }

                Cliente c = new Cliente();

                c.setIdCliente(result.getInt("idCliente"));
                c.setNome(result.getString("nome"));
                c.setSobrenome(result.getString("sobrenome"));
                c.setEmail(result.getString("email"));
                c.setRg(result.getString("rg"));
                c.setCpf(result.getString("cpf"));
                
                listaClientes.add(c);
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
        return listaClientes;
    }
    
    public static DefaultComboBoxModel autoComplete(String val) throws SQLException {
        String sql = "SELECT nome, sobrenome,rg, cpf  FROM cliente WHERE nome LIKE(?)";

        DefaultComboBoxModel model = null;

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet result = null;

        try {
            con = ConnectionUtils.getConnection();
            pst = con.prepareStatement(sql);
            pst.setString(1, val + "%");
            result = pst.executeQuery();

            while (result.next()) {
                if (model == null) {
                    model = new DefaultComboBoxModel();
                }
                String aux = result.getString("nome") + " " + result.getString("sobrenome") + " - RG:" + result.getString("rg") + " CPF:" + result.getString("cpf");
                model.addElement(aux);
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
        return model;
    }

}
