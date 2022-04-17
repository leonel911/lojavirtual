package com.loja.virtual.resources;

import com.loja.virtual.domain.Endereco;
import com.loja.virtual.services.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/enderecos")
public class EnderecoResource {

    @Autowired
    public EnderecoService enderecoService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Endereco create(@Valid @RequestBody Endereco endereco) {
        return enderecoService.create(endereco);
    }

    @RequestMapping(value = "/find/{id}", method = RequestMethod.GET)
    public Endereco findEnderecoById(@PathVariable Integer id) {
        return enderecoService.findEnderecoById(id);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Endereco> findAll() {
        return enderecoService.findAll();
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public Endereco update(@PathVariable Integer id, @Valid @RequestBody Endereco endereco) {
        endereco.setId(id);
        return enderecoService.update(endereco);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Integer id) {
        enderecoService.delete(id);
    }

}
