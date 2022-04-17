package com.loja.virtual.services;

import com.loja.virtual.domain.Categoria;
import com.loja.virtual.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    public CategoriaRepository categoriaRepository;

    public Categoria create(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }


    public Categoria findCategoriaById(Integer id) {
        return categoriaRepository.findCategoriaById(id);
    }

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public Categoria update(Categoria categoria) {
        Categoria newCategoria = findCategoriaById(categoria.getId());

        if (categoria.getDescricao() == null) {
            categoria.setDescricao(newCategoria.getDescricao());
        } else {
            newCategoria.setDescricao(categoria.getDescricao());
        }

        if (categoria.getProdutos() == null) {
            categoria.setProdutos(newCategoria.getProdutos());
        } else {
            newCategoria.setProdutos(categoria.getProdutos());
        }

        return categoria;
    }

    public void delete(Integer id) {
        categoriaRepository.deleteById(id);
    }
}
