package com.loja.virtual.repositories;

import com.loja.virtual.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {


    Pedido findPedidoById(Integer id);
}
