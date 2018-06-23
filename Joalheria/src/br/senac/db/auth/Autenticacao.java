package br.senac.db.auth;

import br.senac.db.utils.ConnectionUtils;
import br.senac.model.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Autenticacao {

    // Funcao de pegar o funcionario do banco
    public static Funcionario login(String nome, String senha) throws Exception {
        // Script banco
        String sql = "SELECT * FROM funcionario WHERE (nome=? AND senha=? AND enabled=?)";

        // Variaveis de manipulação do banco
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement pst = null;

        try {
            // Pega conexao com o banco
            con = ConnectionUtils.getConnection();
            pst = con.prepareStatement(sql);

            // Condicoes do select
            pst.setString(1, nome);
            pst.setString(2, senha);
            pst.setBoolean(3, true);

            // Executa o script do banco
            // Pega o resultado do banco
            rs = pst.executeQuery();

            if (rs.next()) {
                // Estrutura o obj que retornou do banco
                Funcionario func = new Funcionario();
                func.setIdFuncionario(rs.getInt("idFuncionario"));
                func.setNome(rs.getString("nome"));
                func.setSobrenome(rs.getString("sobrenome"));
                func.setSenha(rs.getString("senha"));
                func.setTipo(rs.getString("tipo"));

                return func;
            }

        } finally {
            // Fecha as conexoes com o banco
            if (con != null && !con.isClosed()) {
                con.close();
            }
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
            if (pst != null && !pst.isClosed()) {
                pst.close();
            }
        }
        
        // retorna null caso acha algum erro;
        return null;
    }

}
