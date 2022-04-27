package com.loja.virtual.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.loja.virtual.enums.StatusPagamentoEnum;
import com.loja.virtual.enums.TipoPagamento;

import javax.persistence.*;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
@Table(name = "PEDIDO")
public class Pedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String codigo;
    @Temporal(TemporalType.DATE)
    private Date instante;

    @ManyToOne
    @JoinColumn(name = "enderecoEntrega_id")
    private Endereco enderecoEntrega;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Enumerated(EnumType.STRING)
    private StatusPagamentoEnum status;

    @OneToMany(mappedBy = "id.pedido")
    private Set<ItemPedido> itens = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private TipoPagamento pagamento;


    public Pedido() {
        setStatus(StatusPagamentoEnum.PENDENTE);
    }

    public Pedido(Integer id, String codigo, Date instante, Endereco enderecoEntrega, Cliente cliente, StatusPagamentoEnum status, TipoPagamento pagamento) {
        this.id = id;
        this.codigo = codigo;
        this.instante = instante;
        this.enderecoEntrega = enderecoEntrega;
        this.cliente = cliente;
        this.pagamento = pagamento;
        setStatus(StatusPagamentoEnum.PENDENTE);
    }

    public double getValorTotal() {
        double soma = 0.0;
        for (ItemPedido itemPedido: itens) {
            soma = (soma + itemPedido.getSubTotal()) - itemPedido.getDesconto();
        }

        if (getPagamento() != null && getPagamento().equals(TipoPagamento.PIX) || getPagamento().equals(TipoPagamento.CARTAO_DEBITO)) {
            return (soma - (soma * 0.05));
        } else {
            return soma;
        }
    }

    @Override
    public String toString() {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Código: ");
        stringBuffer.append(getCodigo());
        stringBuffer.append("\nData/Hora: ");
        stringBuffer.append(simpleDateFormat.format(getInstante()));
        stringBuffer.append("\nCliente: ");
        stringBuffer.append(getCliente().getNome());
        stringBuffer.append("\nDetalhes: \n");

        for (ItemPedido itemPedido: getItens()) {
            stringBuffer.append(itemPedido.toString());
            stringBuffer.append("\n");
        }
        stringBuffer.append("Valor Total: ");
        stringBuffer.append(numberFormat.format(getValorTotal()));
        stringBuffer.append("\n");

        return stringBuffer.toString();
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

    public Set<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(Set<ItemPedido> itens) {
        this.itens = itens;
    }

    public TipoPagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(TipoPagamento pagamento) {
        this.pagamento = pagamento;
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
