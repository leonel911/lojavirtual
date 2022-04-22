package com.loja.virtual.services;

import com.loja.virtual.domain.Endereco;
import com.loja.virtual.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    public EnderecoRepository enderecoRepository;

    public Endereco create(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }


    public Endereco findEnderecoById(Integer id) {
        return enderecoRepository.findEnderecoById(id);
    }

    public List<Endereco> findAll() {
        return enderecoRepository.findAll();
    }

    public Endereco update(Endereco endereco) {
        Endereco newEndereco = findEnderecoById(endereco.getId());

        if (endereco.getBairro() == null) {
            endereco.setBairro(newEndereco.getBairro());
        } else {
            newEndereco.setBairro(endereco.getBairro());
        }

        if (endereco.getCep() == null) {
            endereco.setCep(newEndereco.getCep());
        } else {
            newEndereco.setCep(endereco.getCep());
        }

        if (endereco.getCidade() == null) {
            endereco.setCidade(newEndereco.getCidade());
        } else {
            newEndereco.setCidade(endereco.getCidade());
        }

        if (endereco.getCliente() == null) {
            endereco.setCliente(newEndereco.getCliente());
        } else {
            newEndereco.setCliente(endereco.getCliente());
        }

        if (endereco.getLogradouro() == null) {
            endereco.setLogradouro(newEndereco.getLogradouro());
        } else {
            newEndereco.setLogradouro(endereco.getLogradouro());
        }

        if (endereco.getNumero() == null) {
            endereco.setNumero(newEndereco.getNumero());
        } else {
            newEndereco.setNumero(endereco.getNumero());
        }

     /*   if (endereco.getPedidos() == null) {
            endereco.setPedidos(newEndereco.getPedidos());
        } else {
            newEndereco.setPedidos(endereco.getPedidos());
        } */

        return endereco;
    }

    public void delete(Integer id) {
        enderecoRepository.deleteById(id);
    }
}
