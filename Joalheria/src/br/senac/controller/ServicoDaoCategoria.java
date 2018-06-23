package br.senac.controller;

import br.senac.db.dao.DaoCategoria;
import br.senac.exception.CategoriaException;
import br.senac.exception.DaoException;
import br.senac.model.CategoriaProduto;
import br.senac.validador.CategoriaValidador;
import java.util.List;

public class ServicoDaoCategoria {

    private static final ServicoDaoCategoria INSTANCE = new ServicoDaoCategoria();

    public ServicoDaoCategoria() {
    }

    public static ServicoDaoCategoria getIstance() {
        return INSTANCE;
    }

    
    // Cadastra categorias no banco
    public void cadastrarCategoria(CategoriaProduto c) throws DaoException, CategoriaException {
        // Valida se o obj do parametro esta valido
        CategoriaValidador.validar(c);
        try {
            // Chama a funcao de cadastrar categoria da DAO
            DaoCategoria.inserirCategoria(c);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("Erro na base de dados");
        }
    }

    // Pega todas as categorias cadastradas
    public List<CategoriaProduto> listarCategoria() throws DaoException {
        try {
            // Chama a funcao de pegar todas as categorias da DAO
            return DaoCategoria.getAllCategoria();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("Erro na base de dados");
        }
    }

    // Procura uma categoria por nome
    public CategoriaProduto pegaCategoriaByName(String nome) throws DaoException {
        try {
            // Chama a funcao de pegar uma categoria expecifica da DAO
            return DaoCategoria.getCategoriaByName(nome);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("Erro na base de dados");
        }
    }
    
    // Procura uma categoria por Id
    public CategoriaProduto pegaCategoriaById(int id) throws DaoException {
        try {
            // Chama a funcao de pegar uma categoria expecifica da DAO
            return DaoCategoria.getCategoriaById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("Erro na base de dados");
        }
    }

}
