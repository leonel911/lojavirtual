package com.loja.virtual.resources;

import com.loja.virtual.domain.Cliente;
import com.loja.virtual.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    public ClienteService clienteService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Cliente create(@Valid @RequestBody Cliente cliente) {
        return clienteService.create(cliente);
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
