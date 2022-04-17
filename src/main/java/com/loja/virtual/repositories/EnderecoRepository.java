package com.loja.virtual.repositories;

import com.loja.virtual.domain.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {


    Endereco findEnderecoById(Integer id);
}
