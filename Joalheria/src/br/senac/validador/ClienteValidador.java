package br.senac.validador;

import br.senac.exception.ClienteException;
import br.senac.model.Cliente;

public class ClienteValidador {

    public static void validar(Cliente cliente) throws ClienteException {
        if (cliente == null) {
            throw new ClienteException("Cliente vazio");
        }
        if (cliente.getNome() == null || cliente.getNome().trim().isEmpty()) {
            throw new ClienteException("Nome do cliente não fornecido");
        }
        if (cliente.getSobrenome() == null || cliente.getSobrenome().trim().isEmpty()) {
            throw new ClienteException("Sobrenome do cliente não fornecido");
        }
        if (cliente.getEmail() == null || cliente.getEmail().trim().isEmpty()) {
            throw new ClienteException("Email do cliente invalido");
        }

        if (cliente.getRg() == null || cliente.getRg().trim().isEmpty()) {
            throw new ClienteException("RG do cliente não fornecido");
        }
        if (cliente.getCpf() == null || cliente.getCpf().trim().isEmpty()) {
            throw new ClienteException("CPF do cliente não fornecido");
        }
        if (cliente.getEndereco() == null || cliente.getEndereco().trim().isEmpty()) {
            throw new ClienteException("Endereço do cliente não fornecido");
        }
        if (cliente.getTelefone() == null || cliente.getTelefone().trim().isEmpty()) {
            throw new ClienteException("Telefone do cliente não fornecido");
        }
        if (cliente.getSexo() == null || cliente.getSexo().trim().isEmpty()) {
            throw new ClienteException("Sexo do cliente não fornecido");
        }
        if (cliente.getDtNascimento() == null) {
            throw new ClienteException("Data de Nascimento invalida");
        }

    }

}
