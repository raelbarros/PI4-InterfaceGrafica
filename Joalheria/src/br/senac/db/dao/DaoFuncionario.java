package br.senac.db.dao;

import br.senac.db.utils.ConnectionUtils;
import br.senac.model.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoFuncionario {

    public static void inserirFuncionario(Funcionario f) throws SQLException, Exception {
        String sql = "INSERT INTO funcionario (nome, sobrenome, senha, tipo, enabled) VALUES (?,?,?,?,?)";

        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = ConnectionUtils.getConnection();
            pst = con.prepareStatement(sql);

            pst.setString(1, f.getNome());
            pst.setString(2, f.getSobrenome());
            pst.setString(3, f.getSenha());
            pst.setString(4, f.getTipo());
            pst.setBoolean(5, true);

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

    public static List<Funcionario> getAllFuncionario() throws SQLException {
        String sql = "SELECT * FROM funcionario WHERE (enabled=?)";

        List<Funcionario> listaFuncionario = null;

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet result = null;

        try {
            con = ConnectionUtils.getConnection();
            pst = con.prepareStatement(sql);

            pst.setBoolean(1, true);
            result = pst.executeQuery();

            while (result.next()) {
                if (listaFuncionario == null) {
                    listaFuncionario = new ArrayList<>();
                }

                Funcionario f = new Funcionario();

                f.setIdFuncionario(result.getInt("idFuncionario"));
                f.setNome(result.getString("nome"));
                f.setSobrenome(result.getString("sobrenome"));
                f.setTipo(result.getString("tipo"));

                listaFuncionario.add(f);
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

        return listaFuncionario;
    }

    public static Funcionario getFuncionario(int id) throws SQLException, Exception {
        String sql = "SELECT idFuncionario, nome, sobrenome, senha, tipo FROM funcionario WHERE (idFuncionario=?) AND (enabled=?)";

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
                Funcionario f = new Funcionario();

                f.setIdFuncionario(result.getInt("idFuncionario"));
                f.setNome(result.getString("nome"));
                f.setSobrenome(result.getString("sobrenome"));
                f.setTipo(result.getString("tipo"));
                f.setSenha(result.getString("senha"));

                return f;
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

    public static void updateFuncionario(Funcionario f) throws SQLException, Exception {
        String sql = "UPDATE funcionario SET nome=?, sobrenome=?, senha=?, tipo=? WHERE (idFuncionario=?)";

        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = ConnectionUtils.getConnection();
            pst = con.prepareStatement(sql);

            pst.setString(1, f.getNome());
            pst.setString(2, f.getSobrenome());
            pst.setString(3, f.getSenha());
            pst.setString(4, f.getTipo());
            pst.setInt(5, f.getIdFuncionario());

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

    public static void deleteFuncionario(int id) throws SQLException, Exception {
        String sql = "UPDATE funcionario SET enabled=? WHERE (idFuncionario=?)";

        Connection con = null;
        PreparedStatement pst = null;

        try {
            con = ConnectionUtils.getConnection();
            pst = con.prepareStatement(sql);

            pst.setBoolean(1, false);
            pst.setInt(2, id);

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

    public static List<Funcionario> buscarFuncionario(String val) throws SQLException, Exception {
        String sql = "SELECT * FROM  funcionario WHERE ((UPPER(nome) LIKE UPPER(?) OR (UPPER(sobrenome)) LIKE UPPER(?)) AND enabled=?)";

        List<Funcionario> listaFuncionarios = null;

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
                if (listaFuncionarios == null) {
                    listaFuncionarios = new ArrayList<>();
                }

                Funcionario f = new Funcionario();

                f.setIdFuncionario(result.getInt("idFuncionario"));
                f.setNome(result.getString("nome"));
                f.setSobrenome(result.getString("sobrenome"));
                f.setTipo(result.getString("tipo"));

                listaFuncionarios.add(f);
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
        return listaFuncionarios;
    }

    public static List<Funcionario> filtroFuncionario(String val) throws SQLException, Exception {
        String sql = "SELECT * FROM funcionario WHERE (tipo=?) AND (enabled=?)";

        List<Funcionario> listaFuncionarios = null;

        PreparedStatement pst = null;
        Connection con = null;
        ResultSet result = null;

        try {
            con = ConnectionUtils.getConnection();
            pst = con.prepareStatement(sql);

            pst.setString(1, val);
            pst.setBoolean(2, true);
            
            result = pst.executeQuery();
            
            while (result.next()) {
                if (listaFuncionarios == null) {
                    listaFuncionarios = new ArrayList<>();
                }

                Funcionario f = new Funcionario();
                f.setIdFuncionario(result.getInt("idFuncionario"));
                f.setNome(result.getString("nome"));
                f.setSobrenome(result.getString("sobrenome"));
                f.setTipo(result.getString("tipo"));
                
                listaFuncionarios.add(f);
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
        return listaFuncionarios;
    }

}
