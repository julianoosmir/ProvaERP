package com.juliano.provaerp.services;

import com.juliano.provaerp.entity.Pedido;
import com.juliano.provaerp.entity.Produto;
import com.juliano.provaerp.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public Page<Produto> buscarTodosProdutosPaginado(int page, int size) {
        Pageable pages = PageRequest.of(page, size);
        return produtoRepository.findAll(pages);
    }

    public Produto buscarPorCodigo(Integer codigo) {
        return produtoRepository.findByCodigo(codigo);
    }

    public Produto buscarPorId(UUID codigo) {
        return produtoRepository.findById(codigo).get();
    }

    public Produto salvarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }


    public Produto atualizarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    @Transactional
    public void deletarProduto(UUID codigo) {
        if (!verificarProdutoNoPedido(buscarPorId(codigo))) {
            produtoRepository.deleteById(codigo);
        }
    }

    public boolean verificarProdutoNoPedido(Produto produto) {
        List<Produto> produtosEmPedidos = produtoRepository.buscarProdutosEmPedidos(produto.getId());
        return produtosEmPedidos.isEmpty();
    }
}
