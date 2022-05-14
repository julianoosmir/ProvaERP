package com.juliano.provaerp.controllers;

import com.juliano.provaerp.entity.ItemPedido;
import com.juliano.provaerp.entity.Pedido;
import com.juliano.provaerp.services.ItemPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "/itens")
public class ItemPedidoController {
    @Autowired
    private ItemPedidoService itemPedidoService;
    @GetMapping("/paginado")
    public Page<ItemPedido> buscarTodosOsItensPedidos(@RequestParam("page") int page, @RequestParam("size") int size){
        return itemPedidoService.buscarTodosOsPedidosPaginados(page,size);
    }
    @GetMapping
    public List<ItemPedido> buscarTodosItens() {
        return itemPedidoService.buscarTodosOsItensPedidos();
    }
    public ItemPedido buscarItemPorCodigo(Integer codigo){
        return itemPedidoService.buscarItemPorCodigo(codigo);
    }

    @PostMapping
    public ItemPedido salvarItemPedido(@RequestBody ItemPedido itemPedido) {
        return itemPedidoService.salvarItemPedido(itemPedido);
    }

    @PutMapping
    public ItemPedido atualizarItemPedido(@RequestBody ItemPedido itemPedido) {
        return itemPedidoService.atualizarItemPedido(itemPedido);
    }

    @DeleteMapping("/{codigo}")
    public void deletarItemPedido(@PathVariable Integer codigo){
        itemPedidoService.deletarItemPedido(codigo);
    }

}
