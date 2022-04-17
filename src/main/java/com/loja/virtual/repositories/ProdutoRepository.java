package com.loja.virtual.repositories;

import com.loja.virtual.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {


    Produto findProdutoById(Integer id);
}
