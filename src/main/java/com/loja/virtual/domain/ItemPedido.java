package com.loja.virtual.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

@Entity
public class ItemPedido implements Serializable {

    @JsonIgnore
    @EmbeddedId
    private ItemPedidoPk id = new ItemPedidoPk();
    private Double preco;
    private Integer quantidade;
    private Double desconto = 0.0;

    public ItemPedido() {
    }

    public ItemPedido(Pedido pedido, Produto produto, Double preco, Integer quantidade, Double desconto) {
        super();
        id.setPedido(pedido);
        id.setProduto(produto);
        this.preco = preco;
        this.quantidade = quantidade;
        this.desconto = desconto;
    }

    public double getSubTotal() {
        return (id.getProduto().getPreco() * quantidade);
    }

    @Override
    public String toString() {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Produto: ");
        stringBuffer.append(getProduto().getNome());
        stringBuffer.append("\nQuantidade: ");
        stringBuffer.append(getQuantidade());
        stringBuffer.append("\nPreço unitário: ");
        stringBuffer.append(numberFormat.format(getProduto().getPreco()));
        stringBuffer.append("\n");

        if (getDesconto() != null && getDesconto() > 0) {
            stringBuffer.append("Desconto: ");
            stringBuffer.append(numberFormat.format(getDesconto()));
        }
        stringBuffer.append("\nSub-total: ");
        stringBuffer.append(numberFormat.format(getSubTotal()));
        stringBuffer.append("\n");

        return stringBuffer.toString();

    }

    public ItemPedidoPk getId() {
        return id;
    }

    public void setId(ItemPedidoPk id) {
        this.id = id;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    @JsonIgnore
    public Pedido getPedido() {
        return id.getPedido();
    }

    public void setPedido(Pedido pedido) {
        id.setPedido(pedido);
    }

    public Produto getProduto() {
        return id.getProduto();
    }

    public void setProduto(Produto produto) {
        id.setProduto(produto);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemPedido that = (ItemPedido) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

