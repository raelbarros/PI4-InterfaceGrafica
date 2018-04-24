package br.senac.controller;

import br.senac.db.dao.DaoUsuario;
import br.senac.exception.DataSourceException;
import br.senac.exception.UsuarioException;
import br.senac.model.Usuario;
import br.senac.model.validador.ValidadorUsuario;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServicoUsuario {
    private static final ServicoUsuario INSTALNCE = new ServicoUsuario();
    private ServicoUsuario (){
    }
    
    public static ServicoUsuario getInstance(){
        return INSTALNCE;
    }
    public void inserir (Usuario user) throws UsuarioException, DataSourceException {
        ValidadorUsuario.validar(user);
        try {
            DaoUsuario.inserir(user);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DataSourceException("Erro na base de dados");
        }
    }
}
