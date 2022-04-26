package com.loja.virtual.domain;

import java.io.Serializable;

public class DadosLogin implements Serializable {
    private String cpfOuCnpj;
    private String senha;

    public DadosLogin() {
    }

    public DadosLogin(String cpfOuCnpj, String senha) {
        this.cpfOuCnpj = cpfOuCnpj;
        this.senha = senha;
    }

    public String getCpfOuCnpj() {
        return cpfOuCnpj;
    }

    public void setCpfOuCnpj(String cpfOuCnpj) {
        this.cpfOuCnpj = cpfOuCnpj;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
