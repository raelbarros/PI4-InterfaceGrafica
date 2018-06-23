package br.senac.model;
public enum TipoSexo {
    
    Masculino("M"),
    Feminino("F");
    
    private String tipo;

    private TipoSexo(String tipo) {
        this.tipo = tipo;
    }
    
    public String getSexoCliente(){
        return this.tipo;
    }
    
    
}
