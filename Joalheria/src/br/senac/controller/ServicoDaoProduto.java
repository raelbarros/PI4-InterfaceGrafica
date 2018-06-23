package br.senac.controller;

import br.senac.db.dao.DaoProduto;
import br.senac.exception.DaoException;
import br.senac.exception.ProdutoException;
import br.senac.model.Produto;
import br.senac.validador.ProdutoValidador;
import java.util.List;

public class ServicoDaoProduto {

    private static final ServicoDaoProduto INSTANCE = new ServicoDaoProduto();

    public ServicoDaoProduto() {
    }

    public static ServicoDaoProduto getIstance() {
        return INSTANCE;
    }

    // Cadastra um Produto
    public void cadastraProduto(Produto p) throws DaoException, ProdutoException {
        // Valida se o obj passado como paramento esta correto
        ProdutoValidador.validar(p);
        try {
            // Chama a funcao da DAO para cadastro
            DaoProduto.cadastraProduto(p);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("Erro na base de dados");
        }
    }

    // Pega todos os produtos
    public List<Produto> listarProduto() throws DaoException {
        try {
            // Chama a funcao da DAO de pegar todos os produtos
            return DaoProduto.getAllProduto();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("Erro na base de dados");
        }
    }

    // Pega um produto por Id
    public Produto pegaProdutoById(int id) throws DaoException {
        try {
            // Chama a funcao para pegar um produto por id na DAO
            return DaoProduto.getProduto(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("Erro na base de dados");
        }
    }

    // Atualiza Produto
    public void updateProduto(Produto p) throws DaoException, ProdutoException {
        // Valida se o obj passado como paramento esta correto
        ProdutoValidador.validar(p);
        try {
            // Chama a funcao de update da DAO
            DaoProduto.updateProduto(p);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("Erro na base de dados");
        }
    }

    // Deleta um produto por Id
    public void deletarProduto(int id) throws DaoException {
        try {
            // Chama a funcao de delete da DAO
            DaoProduto.deleteProduto(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("Erro na base de dados");
        }
    }

    // Buscar produto por nome
    public List<Produto> buscarProduto(String val) throws DaoException {
        try {
            // Se o nome passado no parametro seja null ele retorna todos
            if (val == null || "".equals(val) || val.trim().isEmpty()) {
                return DaoProduto.getAllProduto();
            } else {
                return DaoProduto.buscarProduto(val);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("Erro na base de dados");
        }
    }

    // Filtra produto por Categoria
    public List<Produto> filtroProduto(int val) throws DaoException {
        try {
            // Se o valor for 0 ele retorna todos os produtos
            if (val == 0) {
                return DaoProduto.getAllProduto();
            } else {
                return DaoProduto.filtroProduto(val);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("Erro na base de dados");
        }
    }

}
