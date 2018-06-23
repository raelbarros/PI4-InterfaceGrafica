package br.senac.model;

import java.util.Date;
import java.util.List;

public class Venda {

    private int idVenda;
    private Date dtVenda;
    private String tipoPagamento;
    private int parcelas;
    private float totalVenda;
    private float desconto;
    private Cliente cliente;
    private Funcionario funcionario;

    private List<ItemVenda> ListaItensVendidos;

    public List<ItemVenda> getListaItensVendidos() {
        return ListaItensVendidos;
    }

    public void setListaItensVendidos(List<ItemVenda> ListaItensVendidos) {
        this.ListaItensVendidos = ListaItensVendidos;
    }

    public float getDesconto() {
        return desconto;
    }

    public void setDesconto(float desconto) {
        this.desconto = desconto;
    }

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public Date getDtVenda() {
        return dtVenda;
    }

    public void setDtVenda(Date dtVenda) {
        this.dtVenda = dtVenda;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public int getParcelas() {
        return parcelas;
    }

    public void setParcelas(int parcelas) {
        this.parcelas = parcelas;
    }

    public float getTotalVenda() {
        return totalVenda;
    }

    public void setTotalVenda(float totalVenda) {
        this.totalVenda = totalVenda;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

}
