package br.senac.controller;

import br.senac.db.dao.DaoVenda;
import br.senac.exception.DaoException;
import br.senac.exception.VendaException;
import br.senac.model.ItemVenda;
import br.senac.model.Venda;
import br.senac.validador.VendaValidador;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ServicoDaoVenda {

    private static final ServicoDaoVenda INSTANCE = new ServicoDaoVenda();

    public ServicoDaoVenda() {
    }

    public static ServicoDaoVenda getIstance() {
        return INSTANCE;
    }

    // Efetua uma venda
    public void efetuarVenda(Venda v) throws DaoException, VendaException {
        // Valida se o obj esta Correto
        VendaValidador.validar(v);
        try {
            // Chama a funcao de efetuar uma venda da DAO
            DaoVenda.inserirVenda(v);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("Erro na base de dados");
        }
    }

    // Pega todas as vendas
    public List<Venda> listarVenda() throws DaoException {
        try {
            // Chama a funcao de pegar vendas da DAO
            // em seguida devesse chamar a funcao formatLista
            // para estruturar os objetos retornados da DAO
            return formatLista(DaoVenda.getAllProduto());
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("Erro na base de dados");
        }
    }

    // Pega uma venda por ID
    public Venda pegarVendaById(int id) throws DaoException {
        try {
            // Chama a funcao de pegar uma venda expecifica da DAO
            return DaoVenda.getVendaById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("Erro na base de dados");
        }
    }

    // Filtra venda por Cliente
    // valor passado no paramentro deve ser o ID do cliente
    public List<Venda> filtroVenda(int val) throws DaoException {
        try {
            // Se o ID for 0 ele retorna todas as vendas
            if (val == 0) {
                return formatLista(DaoVenda.getAllProduto());
            } else {
                return formatLista(DaoVenda.filtroVenda(val));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("Erro na base de dados");
        }
    }

    // Filtra a as vendas por data realizada
    public List<Venda> filtroVendaByData(Timestamp inicio, Timestamp fim) throws DaoException {
        try {
            List<Venda> listVenda = DaoVenda.filtroVendaByData(inicio, fim);

            // Se o valor retornado do banco for != null
            // ele chama a funcao para estruturar o obj
            // senao, retorna null
            if (listVenda != null) {
                return formatLista(listVenda);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DaoException("Erro na base de dados");
        }
    }

    // Funcao para estruturar a lista de vendas recebida do Banco
    private List<Venda> formatLista(List<Venda> lista) {
        // Variaveis de lista aux para guardar o obj formatado 
        List<Venda> listVendaAux = new ArrayList<>();
        List<ItemVenda> listItemAux = null;

        // Variavel para verificar o id da venda anterior
        int auxId = 0;
        for (Venda v : lista) {
            for (ItemVenda i : v.getListaItensVendidos()) {
                // Inicializa as listas auxs
                if (listItemAux == null) {
                    listItemAux = new ArrayList<>();
                }
                // organiza a lista de itens vendidos
                // Caso o id do item da venda for == ao id da venda salva o obj na lista aux
                if (i.getIdVenda() == v.getIdVenda()) {
                    listItemAux.add(i);
                }
            }
            // Salva o a lista de itens aux na venda
            v.setListaItensVendidos(listItemAux);
            // Zera a lista de itens aux para a proxima interacao
            listItemAux = null;

            // Condicao para evitar vendas duplicadas
            // verificar se o id da venda anterior eh == ao do obj atual do for ele ignora a insercao
            if (v.getIdVenda() != auxId) {
                listVendaAux.add(v);
            }
            
            // Atribui o id da venda para a variavel aux;
            auxId = v.getIdVenda();
        }
        // returna a lista formatada
        return listVendaAux;
    }
}
