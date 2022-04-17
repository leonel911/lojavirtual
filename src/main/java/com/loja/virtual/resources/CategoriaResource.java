package com.loja.virtual.resources;

import com.loja.virtual.domain.Categoria;
import com.loja.virtual.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    public CategoriaService categoriaService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Categoria create(@Valid @RequestBody Categoria categoria) {
        return categoriaService.create(categoria);
    }

    @RequestMapping(value = "/find/{id}", method = RequestMethod.GET)
    public Categoria findCategoriaById(@PathVariable Integer id) {
        return categoriaService.findCategoriaById(id);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Categoria> findAll() {
        return categoriaService.findAll();
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public Categoria update(@PathVariable Integer id, @Valid @RequestBody Categoria categoria) {
        categoria.setId(id);
        return categoriaService.update(categoria);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Integer id) {
        categoriaService.delete(id);
    }

}
