package br.senac.controller;

import br.senac.db.dao.DaoCliente;
import br.senac.exception.ClienteException;
import br.senac.exception.DaoException;
import br.senac.model.Cliente;
import br.senac.validador.ClienteValidador;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

public class ServicoDaoCliente {

    private static final ServicoDaoCliente INSTANCE = new ServicoDaoCliente();

    public ServicoDaoCliente() {
    }

    public static ServicoDaoCliente getIstance() {
        return INSTANCE;
    }

    // Funcao de cadastrar um cliente no banco
    public void cadastraCliente(Cliente c) throws DaoException, ClienteException {
        // Valida se o obj passado como paramento esta correto
        ClienteValidador.validar(c);
        try {
            // Chama a funcao de cadastrar cliente da DAO
            DaoCliente.inserirCliente(c);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("Erro na base de dados");
        }
    }

    // Pega cliente por ID
    public Cliente pegaClienteById(int id) throws DaoException {
        try {
            // Chama a funcao da DAO de pegar um cliente expecifico
            return DaoCliente.getClienteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("Erro na base de dados");
        }
    }

    // Pega cliente por RG
    public Cliente pegaClienteByRg(String rg) throws DaoException {
        try {
            // Chama a funcao da DAO de pegar um cliente expecifico
            return DaoCliente.getClienteByRg(rg);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("Erro na base de dados");
        }
    }

    // Pega todos os Clientes
    public List<Cliente> listarCliente() throws DaoException {
        try {
            // Chama a funcao da DAO de pegar todos cliente
            return DaoCliente.getAllCliente();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("Erro na base de dados");
        }
    }

    // Atualiza um cliente
    public void updateCliente(Cliente c) throws DaoException, ClienteException {
        // Valida se o obj passado como paramento esta correto
        ClienteValidador.validar(c);
        try {
            // Chama a funcao de atualizar da DAO
            DaoCliente.updateCliente(c);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("Erro na base de dados");
        }
    }

    // Deletar cliente
    public void deletarCliente(int id) throws DaoException {
        try {
            // Chama a funcao de deletar da DAO
            DaoCliente.deleteCliente(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("Erro na base de dados");
        }
    }
    
    // Funcao para buscar um cliente por nome
    public List<Cliente> buscarCliente(String val) throws DaoException {
        try {
            // se o nome passado no parametro for null ele retorna todos
            if (val == null || "".equals(val) || val.trim().isEmpty()) {
                return DaoCliente.getAllCliente();
            } else {
                return DaoCliente.buscarCliente(val);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("Erro na base de dados");
        }
    }

    // Funcao de autoComplete da tela de Vendas
    public DefaultComboBoxModel autoComplete(String val) throws DaoException {
        try {
            // Chama a funcao de autoComplete da DAO
            return DaoCliente.autoComplete(val);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("Erro na base de dados");
        }
    }

}
