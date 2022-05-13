package com.juliano.provaerp.services;

import com.juliano.provaerp.entity.Pedido;
import com.juliano.provaerp.entity.Produto;
import com.juliano.provaerp.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> buscarTodosProdutos() {
        return produtoRepository.findAll();
    }

    public Produto buscarPorCodigo(Integer codigo){
       return produtoRepository.findByCodigo(codigo);
    }

    public Produto salvarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }


    public Produto atualizarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }
    @Transactional
    public void deletarProduto(Integer codigo){
        produtoRepository.deleteByCodigo(codigo);
    }
}
