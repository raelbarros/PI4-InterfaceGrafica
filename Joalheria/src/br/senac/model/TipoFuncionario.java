package br.senac.model;
public enum TipoFuncionario {
    Administrador("admin"),
    Usuario("user");
    
    private String tipo;
    
    TipoFuncionario(String tipo) {
        this.tipo = tipo;
    }
    
    public String getTipoFuncionario(){
        return this.tipo;
    }
    
}
