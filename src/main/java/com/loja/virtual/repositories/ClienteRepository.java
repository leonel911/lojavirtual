package com.loja.virtual.repositories;

import com.loja.virtual.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {


    Cliente findClienteById(Integer id);

    Cliente findByCpfOuCnpj(String cpfOuCnpj);
    Optional<Cliente> findByCpfOuCnpjIgnoreCase(String cpfOuCnpj);

}
