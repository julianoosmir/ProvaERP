package com.juliano.provaerp.controllers;

import com.juliano.provaerp.entity.Produto;
import com.juliano.provaerp.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public List<Produto>  buscarTodosOsProdutos() {
        return produtoService.buscarTodosProdutos();
    }
    @GetMapping("/paginado")
    public Page<Produto> buscarTodosProdutosPaginado(@RequestParam("page") int page,@RequestParam("size") int size){
        return produtoService.buscarTodosProdutosPaginado(page,size);
    }
    @GetMapping("/{codigo}")
    public Produto buscarPorProdutoId(@PathVariable Integer codigo){
        return produtoService.buscarPorCodigo(codigo);
    }
    @PostMapping
    public Produto salvarProduto(@RequestBody Produto produto) {
        return produtoService.salvarProduto(produto);
    }

    @PutMapping
    public Produto atualizarProduto(@RequestBody Produto produto) {
        return produtoService.atualizarProduto(produto);
    }

    @DeleteMapping("/{codigo}")
    public void deletarProduto(@PathVariable Integer codigo) {
        produtoService.deletarProduto(codigo);
    }
}
