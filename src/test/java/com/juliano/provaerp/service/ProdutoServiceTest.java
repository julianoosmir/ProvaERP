package com.juliano.provaerp.service;

import com.juliano.provaerp.Enum.ProdutoCategoriaEnum;
import com.juliano.provaerp.Enum.ProdutoSituacaoEnum;
import com.juliano.provaerp.entity.Pedido;
import com.juliano.provaerp.entity.Produto;
import com.juliano.provaerp.repository.ProdutoRepository;
import com.juliano.provaerp.services.ProdutoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProdutoServiceTest {

    @InjectMocks
    private ProdutoService produtoService;
    @Mock
    private ProdutoRepository produtoRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void InserirEBuscar() {
        Produto produto = new Produto();
        produto.setId(UUID.randomUUID());
        produto.setCodigo(1);
        produto.setCategoria(ProdutoCategoriaEnum.BemDeConsumo);
        produto.setSituacao(ProdutoSituacaoEnum.Ativado);
        produto.setNome("teste");
        produto.setPreco(BigDecimal.ONE);

        List<Produto> produtos = new ArrayList<>();
        produtos.add(produto);

        Pageable pages = PageRequest.of(1, 10);

        Page<Produto> produtosPaginados = new PageImpl<>(produtos, pages, 10);

        when(produtoService.salvarProduto(produto)).thenReturn(produto);
        when(produtoService.buscarTodosProdutos()).thenReturn(produtos);
        when(produtoService.buscarTodosProdutosPaginado(1, 10)).thenReturn(produtosPaginados);
        when(produtoService.buscarPorCodigo(produto.getCodigo())).thenReturn(produto);


        produtoService.salvarProduto(produto);
        produtoService.buscarPorCodigo(1);
        produtoService.buscarTodosProdutos();
        produtoService.buscarTodosProdutosPaginado(1, 10);


        verify(produtoRepository, times(1)).save(produto);
        verify(produtoRepository, times(1)).findByCodigo(produto.getCodigo());
        verify(produtoRepository, times(1)).findAll();
        verify(produtoRepository, times(1)).findAll(pages);

    }

}
