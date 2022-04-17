package com.loja.virtual.services;

import com.loja.virtual.domain.ItemPedido;
import com.loja.virtual.domain.ItemPedidoPk;
import com.loja.virtual.repositories.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemPedidoService {

    @Autowired
    public ItemPedidoRepository itemPedidoRepository;

    public ItemPedido create(ItemPedido itemPedido) {
        return itemPedidoRepository.save(itemPedido);
    }


    public ItemPedido findItemPedidoById(ItemPedidoPk id) {
        return itemPedidoRepository.findItemPedidoById(id);
    }

    public List<ItemPedido> findAll() {
        return itemPedidoRepository.findAll();
    }

    public ItemPedido update(ItemPedido itemPedido) {
        ItemPedido newItemPedido = findItemPedidoById(itemPedido.getId());

        if (itemPedido.getDesconto() == null) {
            itemPedido.setDesconto(newItemPedido.getDesconto());
        } else {
            newItemPedido.setDesconto(itemPedido.getDesconto());
        }

        if (itemPedido.getPreco() == null) {
            itemPedido.setPreco(newItemPedido.getPreco());
        } else {
            newItemPedido.setPreco(itemPedido.getPreco());
        }

        if (itemPedido.getQuantidade() == null) {
            itemPedido.setQuantidade(newItemPedido.getQuantidade());
        } else {
            newItemPedido.setQuantidade(itemPedido.getQuantidade());
        }

        return itemPedido;
    }

    public void delete(ItemPedidoPk id) {
        itemPedidoRepository.deleteById(id);
    }

}
