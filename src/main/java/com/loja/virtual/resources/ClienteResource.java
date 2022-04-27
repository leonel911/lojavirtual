package com.loja.virtual.resources;

import com.loja.virtual.domain.AutenticacaoDto;
import com.loja.virtual.domain.Cliente;
import com.loja.virtual.domain.DadosLogin;
import com.loja.virtual.domain.dto.ClienteNewDto;
import com.loja.virtual.services.AutenticacaoService;
import com.loja.virtual.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private AutenticacaoService autenticacaoService;

    @Autowired
    private ClienteService clienteService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<AutenticacaoDto> create(@Valid @RequestBody ClienteNewDto clienteNewDto) {
        clienteService.create(clienteService.toCliente(clienteNewDto));
        AutenticacaoDto response = autenticacaoService.doLogin(new DadosLogin(clienteNewDto.getCpfOuCnpj(), clienteNewDto.getSenha()));

        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/find/{id}", method = RequestMethod.GET)
    public Cliente findClienteById(@PathVariable Integer id) {
        return clienteService.findClienteById(id);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Cliente> findAll() {
        return clienteService.findAll();
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public Cliente update(@PathVariable Integer id, @Valid @RequestBody Cliente cliente) {
        cliente.setId(id);
        return clienteService.update(cliente);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Integer id) {
        clienteService.delete(id);
    }

}
