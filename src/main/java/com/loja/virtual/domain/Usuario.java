package com.loja.virtual.domain;

import com.loja.virtual.enums.Perfil;

import javax.persistence.*;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String cpfOuCnpj;
    private String senha;

    @Column(name = "PERFIS")
    @Enumerated(EnumType.STRING)
    private Perfil perfil;

    public Usuario() {
    }

    public Usuario(Integer id, String cpfOuCnpj, String senha, Perfil perfil) {
        this.id = id;
        this.cpfOuCnpj = cpfOuCnpj;
        this.senha = senha;
        this.perfil = perfil;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }
}
