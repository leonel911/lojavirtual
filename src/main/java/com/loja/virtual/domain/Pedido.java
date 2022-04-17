package com.loja.virtual.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "PEDIDO")
public class Pedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String codigo;
    private Date instante;

    private Endereco enderecoEntrega;

    private Cliente cliente;

    private StatusPagamentoEnum status;

    private List<ItemPedido> itens;

    public Pedido() {
    }

    public Pedido(Integer id, String codigo, Date instante, Endereco enderecoEntrega, Cliente cliente, StatusPagamentoEnum status, List<ItemPedido> itens) {
        this.id = id;
        this.codigo = codigo;
        this.instante = instante;
        this.enderecoEntrega = enderecoEntrega;
        this.cliente = cliente;
        this.status = status;
        this.itens = itens;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getInstante() {
        return instante;
    }

    public void setInstante(Date instante) {
        this.instante = instante;
    }

    public Endereco getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(Endereco enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public StatusPagamentoEnum getStatus() {
        return status;
    }

    public void setStatus(StatusPagamentoEnum status) {
        this.status = status;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        return id.equals(pedido.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
