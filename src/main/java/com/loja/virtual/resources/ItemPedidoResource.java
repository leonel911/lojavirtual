package com.loja.virtual.resources;

import com.loja.virtual.domain.Endereco;
import com.loja.virtual.domain.ItemPedido;
import com.loja.virtual.domain.ItemPedidoPk;
import com.loja.virtual.services.ItemPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "itens pedidos")
public class ItemPedidoResource {

    @Autowired
    public ItemPedidoService itemPedidoService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ItemPedido create(@Valid @RequestBody ItemPedido itemPedido) {
        return itemPedidoService.create(itemPedido);
    }

    @RequestMapping(value = "/find/{id}", method = RequestMethod.GET)
    public ItemPedido findItemPedidoById(@PathVariable ItemPedidoPk id) {
        return itemPedidoService.findItemPedidoById(id);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<ItemPedido> findAll() {
        return itemPedidoService.findAll();
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ItemPedido update(@PathVariable ItemPedidoPk id, @Valid @RequestBody ItemPedido itemPedido) {
        itemPedido.setId(id);
        return itemPedidoService.update(itemPedido);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable ItemPedidoPk id) {
        itemPedidoService.delete(id);
    }



}
