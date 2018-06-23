package br.senac.validador;

import br.senac.exception.FuncionarioException;
import br.senac.model.Funcionario;

public class FuncionarioValidador {

    public static void validar(Funcionario fun) throws FuncionarioException {
        if (fun == null) {
            throw new FuncionarioException("Funcionario vazio");
        }
        if (fun.getNome()== null || fun.getNome().trim().isEmpty()) {
            throw new FuncionarioException("Nome do funcionario não fornecido");
        }
        if (fun.getSobrenome()== null || fun.getSobrenome().trim().isEmpty()) {
            throw new FuncionarioException("Sobrenome do funcionario não fornecido");
        }
        if (fun.getSenha() == null || fun.getSenha().trim().isEmpty()) {
            throw new FuncionarioException("Senha não fornecida");
        }
        if (fun.getTipo() == null) {
            throw new FuncionarioException("Tipo de funcionario não informado");
        }
    }
    
}
