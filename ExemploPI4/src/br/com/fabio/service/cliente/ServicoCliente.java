package br.com.fabio.service.cliente;

import br.com.fabio.db.dao.DaoCliente;
import br.com.fabio.exceptions.ClienteException;
import br.com.fabio.exceptions.DataSourceException;
import br.com.fabio.model.clientes.Cliente;
import br.com.fabio.model.validador.cliente.ValidadorCliente;
import java.util.List;

//Classe de serviço de cliente
public class ServicoCliente {
    private static final ServicoCliente INSTANCE = new ServicoCliente();

    private ServicoCliente() {
        
    }
    
    public static ServicoCliente getInstance() {
        return INSTANCE;
    }
    
    //Insere um cliente na fonte de dados
    public void cadastrarCliente(Cliente cliente)
            throws ClienteException, DataSourceException {

        //Chama o validador para verificar o cliente
        ValidadorCliente.validar(cliente);

        try {
            //Realiza a chamada de inserção na fonte de dados
            DaoCliente.inserir(cliente);
        } catch (Exception e) {
            //Imprime qualquer erro técnico no console e devolve
            //uma exceção e uma mensagem amigável a camada de visão
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
        }
    }

    //Atualiza um cliente na fonte de dados
    public void atualizarCliente(Cliente cliente)
            throws ClienteException, DataSourceException {
        
        //Chama o validador para verificar o cliente
        ValidadorCliente.validar(cliente);

        try {
            //Realiza a chamada de atualização na fonte de dados
            DaoCliente.atualizar(cliente);
            return;
        } catch (Exception e) {
            //Imprime qualquer erro técnico no console e devolve
            //uma exceção e uma mensagem amigável a camada de visão
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
        }
    }

    //Realiza a pesquisa de um cliente por nome na fonte de dados
    public List<Cliente> procurarCliente(String nome)
            throws ClienteException, DataSourceException {
        try {
            //Verifica se um parâmetro de pesquisa não foi informado.
            //Caso afirmativo, realiza uma listagem simples do DAO.
            //Caso contrário, realiza uma pesquisa com o parâmetro
            if (nome == null || "".equals(nome)) {
                return DaoCliente.listar();
            } else {
                return DaoCliente.procurar(nome);
            }
        } catch (Exception e) {
            //Imprime qualquer erro técnico no console e devolve
            //uma exceção e uma mensagem amigável a camada de visão
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
        }
    }

    //Obtem o cliente com ID informado
    public Cliente obterCliente(Integer id)
            throws ClienteException, DataSourceException {
        try {
            //Retorna o cliente obtido com o DAO
            return DaoCliente.obter(id);
        } catch (Exception e) {
            //Imprime qualquer erro técnico no console e devolve
            //uma exceção e uma mensagem amigável a camada de visão
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
        }
    }

    //Exclui o cliente com ID informado
    public void excluirCliente(Integer id)
            throws ClienteException, DataSourceException {
        try {
            //Solicita ao DAO a exclusão do cliente informado
            DaoCliente.excluir(id);
        } catch (Exception e) {
            //Imprime qualquer erro técnico no console e devolve
            //uma exceção e uma mensagem amigável a camada de visão
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
        }
    }
}
