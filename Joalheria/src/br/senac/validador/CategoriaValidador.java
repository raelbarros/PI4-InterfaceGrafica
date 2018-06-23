
package br.senac.validador;

import br.senac.exception.CategoriaException;
import br.senac.model.CategoriaProduto;

public class CategoriaValidador {
    
    public static void validar (CategoriaProduto categoria) throws CategoriaException {
        if (categoria == null) {
            throw new CategoriaException("Categoria vazia");
        }
        if (categoria.getNome() == null || categoria.getNome().trim().isEmpty()) {
            throw new CategoriaException("Nome da Categoria nao fornecido");
        }
    }
    
}
