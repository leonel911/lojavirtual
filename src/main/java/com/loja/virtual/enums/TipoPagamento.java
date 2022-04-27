package com.loja.virtual.enums;

public enum TipoPagamento {

    BOLETO("Boleto"),
    CARTAO_CREDITO("Cartão de cŕedito"),
    CARTAO_DEBITO("Cartão de débito"),
    PIX("Pix");

    private String descricao;

    TipoPagamento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
