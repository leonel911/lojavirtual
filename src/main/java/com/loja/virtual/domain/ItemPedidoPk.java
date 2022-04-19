package com.loja.virtual.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class ItemPedidoPk {

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "itemPedidoPk")
    private Pedido pedido;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "itemPedidoPk")
    private Produto produto;






}
