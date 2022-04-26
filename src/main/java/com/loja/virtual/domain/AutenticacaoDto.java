package com.loja.virtual.domain;

public class AutenticacaoDto {

    private String token;
    private String tipo;

    public AutenticacaoDto() {
    }

    public AutenticacaoDto(String token, String tipo) {
        this.token = token;
        this.tipo = tipo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}

