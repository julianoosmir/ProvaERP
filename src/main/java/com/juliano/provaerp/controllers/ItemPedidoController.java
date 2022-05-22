package com.juliano.provaerp.controllers;

import com.juliano.provaerp.dto.ItemPedidoDTO;
import com.juliano.provaerp.entity.ItemPedido;
import com.juliano.provaerp.filters.ItemPedidoFiltro;
import com.juliano.provaerp.services.ItemPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "/itens")
public class ItemPedidoController {
    @Autowired
    private ItemPedidoService itemPedidoService;

    @GetMapping("/paginado")
    public Page<ItemPedido> buscarTodosOsItensPedidos(@RequestParam(required = false) Map<String, String> requestMap) {
        return itemPedidoService.buscarTodosOsPedidosPaginados(new ItemPedidoFiltro(requestMap));
    }

    @GetMapping
    public List<ItemPedido> buscarTodosItens() {
        return itemPedidoService.buscarTodosOsItensPedidos();
    }

    @GetMapping("/{codigo}")
    public ItemPedido buscarItemPorCodigo(@PathVariable Integer codigo) {
        return itemPedidoService.buscarItemPorCodigo(codigo);
    }

    @PostMapping
    public ItemPedido salvarItemPedido(@Valid @RequestBody ItemPedidoDTO itemPedido) throws Exception {
        return itemPedidoService.salvarItemPedido(itemPedido);
    }

    @PutMapping
    public ItemPedido atualizarItemPedido(@Valid @RequestBody ItemPedido itemPedido) {
        return itemPedidoService.atualizarItemPedido(itemPedido);
    }

    @DeleteMapping("/{codigo}")
    public void deletarItemPedido(@PathVariable Integer codigo) {
        itemPedidoService.deletarItemPedido(codigo);
    }

}
