package br.senac.model;
public class ItemVenda {
    
    private int idVenda;
    private int quant;
    private float precoUni;
    private Produto produto;

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public int getQuant() {
        return quant;
    }

    public void setQuant(int quant) {
        this.quant = quant;
    }

    public float getPrecoUni() {
        return precoUni;
    }

    public void setPrecoUni(float precoUni) {
        this.precoUni = precoUni;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    
}
