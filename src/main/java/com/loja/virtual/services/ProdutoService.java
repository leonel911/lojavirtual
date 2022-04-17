package com.loja.virtual.services;

import com.loja.virtual.domain.Produto;
import com.loja.virtual.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    public ProdutoRepository produtoRepository;

    public Produto create(Produto produto) {
        return produtoRepository.save(produto);
    }


    public Produto findProdutoById(Integer id) {
        return produtoRepository.findProdutoById(id);
    }

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }


    public Produto update(Produto produto) {
        Produto newProduto = findProdutoById(produto.getId());

        if (produto.getCodigoProduto() == null) {
            produto.setCodigoProduto(newProduto.getCodigoProduto());
        } else {
            newProduto.setCodigoProduto(produto.getCodigoProduto());
        }

        if (produto.getCategoria() == null) {
            produto.setCategoria(newProduto.getCategoria());
        } else {
            newProduto.setCategoria(produto.getCategoria());
        }

        if (produto.getDescricao() == null) {
            produto.setDescricao(newProduto.getDescricao());
        } else {
            newProduto.setDescricao(produto.getDescricao());
        }

        if (produto.getItens() == null) {
            produto.setItens(newProduto.getItens());
        } else {
            newProduto.setItens(produto.getItens());
        }

        if (produto.getNome() == null) {
            produto.setNome(newProduto.getNome());
        } else {
            newProduto.setNome(produto.getNome());
        }

        if (produto.getPreco() == null) {
            produto.setPreco(newProduto.getPreco());
        } else {
            newProduto.setPreco(produto.getPreco());
        }

        if (produto.getQuantEstoque() == null) {
            produto.setQuantEstoque(newProduto.getQuantEstoque());
        } else {
            newProduto.setQuantEstoque(produto.getQuantEstoque());
        }

        return produto;
    }

    public void delete(Integer id) {
        produtoRepository.deleteById(id);
    }
}
