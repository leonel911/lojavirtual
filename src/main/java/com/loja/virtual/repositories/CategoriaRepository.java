package com.loja.virtual.repositories;

import com.loja.virtual.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {


    Categoria findCategoriaById(Integer id);


}
