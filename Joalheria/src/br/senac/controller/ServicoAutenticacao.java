package br.senac.controller;

import br.senac.db.auth.Autenticacao;
import br.senac.exception.AuthException;
import br.senac.exception.FuncionarioException;
import br.senac.model.Funcionario;
import br.senac.validador.LoginValidador;
import br.senac.view.Login;
import javax.swing.JFrame;

public class ServicoAutenticacao {

    //Instancia da telaLogin para ser chamada quando efetuar o logout
    Login login = null;

    private static final ServicoAutenticacao INSTANCE = new ServicoAutenticacao();

    private ServicoAutenticacao() {
    }

    public static ServicoAutenticacao getInstance() {
        return INSTANCE;
    }

    // Variavel para guardar o usuario logado
    private Funcionario userLoged = null;

    public Funcionario getUserLoged() {
        return userLoged;
    }

    public void setUserLoged(Funcionario userLoged) {
        this.userLoged = userLoged;
    }

    // Funcao de fazer login
    // Recebe o usuario e a senha informado na view   
    public Funcionario login(String nome, String senha) throws FuncionarioException, AuthException {
        LoginValidador.validar(nome, senha);
        try {
            
            // Guarda o funcionario que retornou do banco
            Funcionario func = Autenticacao.login(nome, senha);
            
            // caso o funcionario seja null ele interrompe a operacao
            if (func == null) {
                throw new AuthException("Funcionario null");
            }
            
            setUserLoged(func);
            return func;
        } catch (Exception e) {
            e.printStackTrace();
            throw new AuthException("Falha no acesso!");
        }
    }

    public void logout(JFrame tela) {
        setUserLoged(null);
        tela.dispose();

        if (login != null) {
            login.dispose();
        }
        login = new Login();
        login.setVisible(true);
        login.toFront();
//        System.exit(0);
    }

}
