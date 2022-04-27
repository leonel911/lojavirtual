package com.loja.virtual.services;

import com.loja.virtual.domain.Cliente;
import com.loja.virtual.domain.Endereco;
import com.loja.virtual.domain.dto.ClienteNewDto;
import com.loja.virtual.repositories.ClienteRepository;
import com.loja.virtual.repositories.EnderecoRepository;
import com.loja.virtual.services.exceptions.AuthorizationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente create(Cliente cliente) {
        Integer idade = cliente.getIdade(cliente.getDataNasc());
        if (idade == null || idade < 18) {
            throw new AuthorizationException("Você não tem a idade mínima para se cadastrar!");
        }


        clienteRepository.save(cliente);
        enderecoRepository.saveAll(cliente.getEnderecos());
        return cliente;


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

    public Cliente getCliente() {
        Object currentUser = UserService.authenticated();
        currentUser = clienteRepository.findByCpfOuCnpj(currentUser.toString());
        if (currentUser == null) {
            throw new AuthorizationException("Acesso negado, faça login ou verifique suas permissões de acesso!");
        } else {
            return (Cliente) currentUser;
        }
    }

    public Cliente toCliente(ClienteNewDto clienteNewDto) {
        Cliente cliente = new Cliente(null,clienteNewDto.getNome(),clienteNewDto.getDataNasc(),clienteNewDto.getEmail(), clienteNewDto.getSenha(),clienteNewDto.getPerfil(),clienteNewDto.getCpfOuCnpj());
        Endereco endereco = new Endereco(null, clienteNewDto.getCep(), clienteNewDto.getLogradouro(), clienteNewDto.getBairro(), clienteNewDto.getCidade(), clienteNewDto.getNumero(), clienteNewDto.getEstado(), cliente);
        cliente.getEnderecos().addAll(Arrays.asList(endereco));

        return cliente;
    }
}
