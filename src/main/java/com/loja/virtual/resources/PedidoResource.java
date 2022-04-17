package com.loja.virtual.resources;

import com.loja.virtual.domain.Pedido;
import com.loja.virtual.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

    @Autowired
    public PedidoService pedidoService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Pedido create(@Valid @RequestBody Pedido pedido) {
        return pedidoService.create(pedido);
    }

    @RequestMapping(value = "/find/{id}", method = RequestMethod.GET)
    public Pedido findPedidoById(@PathVariable Integer id) {
        return pedidoService.findPedidoById(id);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Pedido> findAll() {
        return pedidoService.findAll();
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public Pedido update(@PathVariable Integer id, @Valid @RequestBody Pedido pedido) {
        pedido.setId(id);
        return pedidoService.update(pedido);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Integer id) {
        pedidoService.delete(id);
    }

}
