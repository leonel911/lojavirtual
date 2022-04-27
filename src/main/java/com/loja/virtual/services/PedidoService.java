package com.loja.virtual.services;

import com.loja.virtual.domain.Cliente;
import com.loja.virtual.domain.ItemPedido;
import com.loja.virtual.domain.Pedido;
import com.loja.virtual.repositories.ItemPedidoRepository;
import com.loja.virtual.repositories.PedidoRepository;
import com.loja.virtual.services.exceptions.AuthorizationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido create(Pedido pedido) {

        Cliente cliente = clienteService.getCliente();
        if (cliente == null) {
            throw new AuthorizationException("Acesso negado, verifique suas permissões");
        }

        String codigoPedido = cliente.getId().toString() + pedido.getId() + cliente.getNome();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(codigoPedido.getBytes(), 0, codigoPedido.length());
            byte[] digest = messageDigest.digest();

            String hexa = new BigInteger(1, digest).toString(16).toUpperCase();
            pedido.setCodigo(hexa);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        pedido.setId(null);
        pedido.setInstante(new Date());
        pedido.setCliente(cliente);
        pedido.setEnderecoEntrega(cliente.getEnderecos().get(0));
        pedido = pedidoRepository.save(pedido);


        for (ItemPedido itemPedido: pedido.getItens()) {
            itemPedido.setProduto(produtoService.findProdutoById(itemPedido.getProduto().getId()));
            itemPedido.setPreco(itemPedido.getProduto().getPreco());
            itemPedido.setPedido(pedido);
        }

        itemPedidoRepository.saveAll(pedido.getItens());
        return pedido;
    }


    public Pedido findPedidoById(Integer id) {
        return pedidoRepository.findPedidoById(id);
    }

    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    public Pedido update(Pedido pedido) {
        Pedido newPedido = findPedidoById(pedido.getId());

        if (pedido.getCliente() == null) {
            pedido.setCliente(newPedido.getCliente());
        } else {
            newPedido.setCliente(pedido.getCliente());
        }

        if (pedido.getCodigo() == null) {
            pedido.setCodigo(newPedido.getCodigo());
        } else {
            newPedido.setCodigo(pedido.getCodigo());
        }

     /*   if (pedido.getEnderecoEntrega() == null) {
            pedido.setEnderecoEntrega(newPedido.getEnderecoEntrega());
        } else {
            newPedido.setEnderecoEntrega(pedido.getEnderecoEntrega());
        } */

        if (pedido.getInstante() == null) {
            pedido.setInstante(newPedido.getInstante());
        } else {
            newPedido.setInstante(pedido.getInstante());
        }

      /*  if (pedido.getItens() == null) {
            pedido.setItens(newPedido.getItens());
        } else {
            newPedido.setItens(pedido.getItens());
        }

        if (pedido.getStatus() == null) {
            pedido.setStatus(newPedido.getStatus());
        } else {
            newPedido.setStatus(pedido.getStatus());
        } */
        return pedido;
    }

    public void delete(Integer id) {
        pedidoRepository.deleteById(id);
    }
}
