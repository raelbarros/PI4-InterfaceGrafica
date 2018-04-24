package br.senac.model;

import java.util.Date;

public class Usuario {
    private Integer _id;
    private String _nome;
    private String _senha;
    private Date _dataCad;
    private String _perfil;
    private Boolean _enabled;

    public Usuario() {
    }

    public Integer getId() {
        return _id;
    }

    public void setId(Integer _id) {
        this._id = _id;
    }

    public String getNome() {
        return _nome;
    }

    public void setNome(String _nome) {
        this._nome = _nome;
    }

    public String getSenha() {
        return _senha;
    }

    public void setSenha(String _senha) {
        this._senha = _senha;
    }

    public Date getDataCad() {
        return _dataCad;
    }

    public void setDataCad(Date _dataCad) {
        this._dataCad = _dataCad;
    }

    public String getPerfil() {
        return _perfil;
    }

    public void setPerfil(String _perfil) {
        this._perfil = _perfil;
    }

    public Boolean getEnabled() {
        return _enabled;
    }

    public void setEnabled(Boolean _enabled) {
        this._enabled = _enabled;
    }
    
    
    
}
