package br.com.fabio.model.validador.cliente;

import br.com.fabio.exceptions.ClienteException;
import br.com.fabio.model.clientes.Cliente;

//Validador de dados do cliente
public class ValidadorCliente {
    public static  void validar(Cliente cliente) throws ClienteException {
        //Realização de validações de negócio
        if (cliente == null) {
            throw new ClienteException("Não foi informado um cliente");
        }
        if (cliente.getNome() == null || "".equals(cliente.getNome())) {
            throw new ClienteException("É necessário informar "
                    + "um nome de cliente");
        }
        if (cliente.getSobrenome() == null
                || "".equals(cliente.getSobrenome())) {
            throw new ClienteException("É necessário informar um "
                    + "sobrenome de cliente");
        }
        if (cliente.getDataNascimento() == null) {
            throw new ClienteException("É necessário informar um "
                    + "valor de idade válido");
        }
        if (cliente.getGenero() == null || "".equals(cliente.getGenero())
                || (!cliente.getGenero().equals("Masculino"))
                && !cliente.getGenero().equals("Feminino")) {
            throw new ClienteException("É necessário informar o "
                    + "gênero do cliente");
        }
    }
}
