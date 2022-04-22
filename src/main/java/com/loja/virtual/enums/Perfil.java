package com.loja.virtual.enums;

public enum Perfil {

    ADMIN("Administrador"),
    CLIENTE("Cliente");

    private String descricao;

    Perfil(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


}
