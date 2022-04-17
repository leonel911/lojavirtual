package com.loja.virtual.repositories;

import com.loja.virtual.domain.ItemPedido;
import com.loja.virtual.domain.ItemPedidoPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, ItemPedidoPk> {


    ItemPedido findItemPedidoById(ItemPedidoPk id);
}
