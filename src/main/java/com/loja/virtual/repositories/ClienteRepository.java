package com.loja.virtual.repositories;

import com.loja.virtual.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {


    Cliente findClienteById(Integer id);
}
