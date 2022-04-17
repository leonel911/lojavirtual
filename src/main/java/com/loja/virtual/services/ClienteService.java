package com.loja.virtual.services;

import com.loja.virtual.domain.Cliente;
import com.loja.virtual.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    public ClienteRepository clienteRepository;

    public Cliente create(Cliente cliente) {
        return clienteRepository.save(cliente);

    }

    public Cliente findClienteById(Integer id) {
        return clienteRepository.findClienteById(id);
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente update(Cliente cliente) {
        Cliente newCliente = findClienteById(cliente.getId());
        if (cliente.getNome() == null) {
            cliente.setNome(newCliente.getNome());
        } else {
            newCliente.setNome(cliente.getNome());
        }

        if (cliente.getDataNasc() == null) {
            cliente.setDataNasc(newCliente.getDataNasc());
        } else {
            newCliente.setDataNasc(cliente.getDataNasc());
        }

        if (cliente.getEmail() == null) {
            cliente.setEmail(newCliente.getEmail());
        } else {
            newCliente.setEmail(cliente.getEmail());
        }

        if (cliente.getEnderecos() == null) {
            cliente.setEnderecos(newCliente.getEnderecos());
        } else {
            newCliente.setEnderecos(cliente.getEnderecos());
        }

        if (cliente.getSenha() == null) {
            cliente.setSenha(newCliente.getSenha());
        } else {
            newCliente.setSenha(cliente.getSenha());
        }

        if (cliente.getPedidos() == null) {
            cliente.setPedidos(newCliente.getPedidos());
        } else {
            newCliente.setPedidos(cliente.getPedidos());
        }
        return cliente;
    }

    public void delete(Integer id) {
        clienteRepository.deleteById(id);
    }
}
