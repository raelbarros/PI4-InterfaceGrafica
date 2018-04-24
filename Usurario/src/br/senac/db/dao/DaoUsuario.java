package br.senac.db.dao;

import br.senac.db.util.ConnectionUtils;
import br.senac.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Date;

public class DaoUsuario {

    public static void inserir(Usuario newUser) throws Exception {
        Connection con = null;
        try {
            con = ConnectionUtils.getConnection();

            String sql = "INSERT INTO usuario (NOME, SENHA, DATA_CAD, PERFIL, ENABLED) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, newUser.getNome());
            pst.setString(2, newUser.getSenha());
            pst.setDate(3, new Date(newUser.getDataCad().getTime()));
            pst.setString(4, newUser.getPerfil());
            pst.setBoolean(5, true);

            pst.execute();
        } finally {
            if(con != null && !con.isClosed()){
                con.close();
            }
        }

    }
}
