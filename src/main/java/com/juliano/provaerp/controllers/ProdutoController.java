package com.juliano.provaerp.controllers;

import com.juliano.provaerp.entity.Produto;
import com.juliano.provaerp.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public List<Produto> buscarTodosOsProdutos() {
        return produtoService.buscarTodosProdutos();
    }

    public Produto salvarProduto(@RequestBody Produto produto) {
        return produtoService.salvarProduto(produto);
    }
}
