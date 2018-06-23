package br.senac.validador;

import br.senac.exception.VendaException;
import br.senac.model.Venda;

public class VendaValidador {

    public static void validar(Venda v) throws VendaException {
        if (v.getDtVenda() == null) {
            throw new VendaException("Data da Venda não informada");
        }
        if (v.getTipoPagamento() == null || v.getTipoPagamento().trim().isEmpty()) {
            throw new VendaException("Tipo de Pagamento não informado");
        }
        if (v.getTotalVenda() == 0) {
            throw new VendaException("Venda não possui valores");
        }
        if (v.getCliente() == null) {
            throw new VendaException("Comprador nao informado");
        }
        if (v.getFuncionario() == null) {
            throw new VendaException("Vendedor nao informado");
        }
    }
}
