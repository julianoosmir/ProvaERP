package com.juliano.provaerp.services;

import com.juliano.provaerp.entity.Produto;
import com.juliano.provaerp.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> buscarTodosProdutos(){
        return produtoRepository.findAll();
    }
    public Produto salvarProduto(Produto produto){
        return produtoRepository.save(produto);
    }


}
