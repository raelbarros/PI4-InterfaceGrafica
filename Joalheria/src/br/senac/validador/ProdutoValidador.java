package br.senac.validador;

import br.senac.exception.ProdutoException;
import br.senac.model.Produto;

public class ProdutoValidador {

    public static void validar(Produto p) throws ProdutoException {
        if (p == null) {
            throw new ProdutoException("Produto vazio");
        }
        if (p.getNome() == null || p.getNome().trim().isEmpty()) {
            throw new ProdutoException("Nome do produto não fornecida");
        }
        if (p.getMarca() == null || p.getMarca().trim().isEmpty()) {
            throw new ProdutoException("Marca do produto não fornecido");
        }
        if (p.getQuant() == 0) {
            throw new ProdutoException("Quantidade do produto nao fornecido");
        }
        if (p.getMaterial() == null || p.getMaterial().trim().isEmpty()) {
            throw new ProdutoException("Material do produto não fornecido");
        }
        if (p.getPrecoUni() == 0) {
            throw new ProdutoException("Preço do produto não fornecido");
        }
        if (p.getIdCategoria() == 0) {
            throw new ProdutoException("Categoria do produto não fornecido");
        }
    }
}
