package com.loja.virtual.services;

import com.loja.virtual.domain.Pedido;
import com.loja.virtual.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    public PedidoRepository pedidoRepository;

    public Pedido create(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }


    public Pedido findPedidoById(Integer id) {
        return pedidoRepository.findPedidoById(id);
    }

    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    public Pedido update(Pedido pedido) {
        Pedido newPedido = findPedidoById(pedido.getId());

        if (pedido.getCliente() == null) {
            pedido.setCliente(newPedido.getCliente());
        } else {
            newPedido.setCliente(pedido.getCliente());
        }

        if (pedido.getCodigo() == null) {
            pedido.setCodigo(newPedido.getCodigo());
        } else {
            newPedido.setCodigo(pedido.getCodigo());
        }

        if (pedido.getEnderecoEntrega() == null) {
            pedido.setEnderecoEntrega(newPedido.getEnderecoEntrega());
        } else {
            newPedido.setEnderecoEntrega(pedido.getEnderecoEntrega());
        }

        if (pedido.getInstante() == null) {
            pedido.setInstante(newPedido.getInstante());
        } else {
            newPedido.setInstante(pedido.getInstante());
        }

        if (pedido.getItens() == null) {
            pedido.setItens(newPedido.getItens());
        } else {
            newPedido.setItens(pedido.getItens());
        }

        if (pedido.getStatus() == null) {
            pedido.setStatus(newPedido.getStatus());
        } else {
            newPedido.setStatus(pedido.getStatus());
        }
        return pedido;
    }

    public void delete(Integer id) {
        pedidoRepository.deleteById(id);
    }
}
