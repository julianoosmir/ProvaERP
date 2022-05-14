package com.juliano.provaerp.controllers;

import com.juliano.provaerp.dto.PedidoDTO;
import com.juliano.provaerp.entity.Pedido;
import com.juliano.provaerp.entity.Produto;
import com.juliano.provaerp.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public List<Pedido> buscarTodosOsPedidos(){
        return pedidoService.buscarTodosOsPedidos();
    }
    @GetMapping("/paginado")
    public Page<Pedido> buscarTodosOsPedidos(@RequestParam("page") int page, @RequestParam("size") int size){
        return pedidoService.buscarTodosOsPedidosPaginados(page,size);
    }
    @GetMapping("/{codigo}")
    public Pedido buscarPorProdutoId(@PathVariable Integer codigo){
        return pedidoService.buscarPorCodigo(codigo);
    }
    @PostMapping
    public Object salvarPedido(@RequestBody PedidoDTO dto){
        return pedidoService.salvarPedido(dto);
    }

    @PutMapping
    public Object atualizarPedido(@RequestBody PedidoDTO pedido) {
        return pedidoService.atualizarPedido(pedido);
    }
    @DeleteMapping("/{codigo}")
    public void deletarPedido(Integer codigo){
        pedidoService.deletarPedido(codigo);
    }


}
