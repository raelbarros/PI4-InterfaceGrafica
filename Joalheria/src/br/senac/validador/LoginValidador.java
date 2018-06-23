package br.senac.validador;

import br.senac.exception.FuncionarioException;

public class LoginValidador {

    public static void validar(String nome, String senha) throws FuncionarioException {
        if (nome == null || nome.trim().isEmpty()) {
            throw new FuncionarioException("Nome incorreto");
        }
        if (senha == null || nome.trim().isEmpty()) {
            throw new FuncionarioException("Senha incorreto");
        }
    }
}
