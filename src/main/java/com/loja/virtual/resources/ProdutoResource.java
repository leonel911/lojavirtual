package com.loja.virtual.resources;

import com.loja.virtual.domain.Produto;
import com.loja.virtual.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

    @Autowired
    public ProdutoService produtoService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Produto create(@Valid @RequestBody Produto produto) {
        return produtoService.create(produto);
    }

    @RequestMapping(value = "/find/{id}", method = RequestMethod.GET)
    public Produto findProdutoById(@PathVariable Integer id) {
        return produtoService.findProdutoById(id);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Produto> findAll() {
        return produtoService.findAll();
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public Produto update(@PathVariable Integer id, @Valid @RequestBody Produto produto) {
        produto.setId(id);
        return produtoService.update(produto);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Integer id) {
        produtoService.delete(id);
    }

}
