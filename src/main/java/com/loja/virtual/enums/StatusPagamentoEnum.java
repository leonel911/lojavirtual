package com.loja.virtual.enums;

public enum StatusPagamentoEnum {

    PAGO("Pago"),
    PENDENTE("Pendente"),
    PROCESSANDO("Processando"),
    ESTORNADO("Estornado"),
    CANCELADO("Cancelado");

    private String descricao;

    StatusPagamentoEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
