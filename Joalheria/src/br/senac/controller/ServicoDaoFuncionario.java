package br.senac.controller;

import br.senac.db.dao.DaoFuncionario;
import br.senac.exception.DaoException;
import br.senac.exception.FuncionarioException;
import br.senac.model.Funcionario;
import br.senac.validador.FuncionarioValidador;
import java.util.List;

public class ServicoDaoFuncionario {

    private static final ServicoDaoFuncionario INSTANCE = new ServicoDaoFuncionario();

    public ServicoDaoFuncionario() {
    }

    public static ServicoDaoFuncionario getIstance() {
        return INSTANCE;
    }

    // Cadastra um Funcionario
    public void cadastraFuncionario(Funcionario f) throws DaoException, FuncionarioException {
        // Valida se o obj passado como paramento esta correto
        FuncionarioValidador.validar(f);
        try {
            // Chama a funcao de cadastrar funcionario da DAO
            DaoFuncionario.inserirFuncionario(f);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("Erro na base de dados");
        }
    }

    // Funcao um funcionario por ID
    public Funcionario pegarFuncionarioById(int id) throws DaoException {
        try {
            // Chama a funcao de pegar um funcionario expecifico da DAO
            return DaoFuncionario.getFuncionario(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("Erro na base de dados");
        }
    }

    // Pega todos os Funcionarios
    public List<Funcionario> listarFuncionario() throws DaoException {
        try {
            // Chama a funcao de pegar todos os funcionarios da DAO
            return DaoFuncionario.getAllFuncionario();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("Erro na base de dados");
        }
    }

    // Atualiza um funcionario
    public void updateFuncionario(Funcionario f) throws DaoException, FuncionarioException {
        // Valida se o obj passado como paramento esta correto
        FuncionarioValidador.validar(f);
        try {
            // Chama a funcao de atualizar um funcionario da DAO
            DaoFuncionario.updateFuncionario(f);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("Erro na base de dados");
        }
    }

    // Deleta um Cliente
    public void deletarFuncionario(int id) throws DaoException {
        try {
            // Chama a funcao de deletar um da DAO
            DaoFuncionario.deleteFuncionario(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("Erro na base de dados");
        }
    }

    // Buscar funcionario por nome
    public List<Funcionario> buscarFuncionario(String val) throws DaoException {
        try {
            // se o nome passado no parametro for null ele retorna todos
            if (val == null || "".equals(val) || val.trim().isEmpty()) {
                return DaoFuncionario.getAllFuncionario();
            } else {
                return DaoFuncionario.buscarFuncionario(val);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("Erro na base de dados");
        }
    }

    // Funcao de filtrar funcionario por cargo
    public List<Funcionario> filtroFuncionario(String val) throws DaoException {
        try {
            if ("Todos".equals(val)) {
                return DaoFuncionario.getAllFuncionario();
            } else {
                return DaoFuncionario.filtroFuncionario(val);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("Erro na base de dados");
        }
    }

}
