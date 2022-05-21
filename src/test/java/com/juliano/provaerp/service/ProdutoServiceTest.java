package com.juliano.provaerp.service;

import com.juliano.provaerp.Enum.ProdutoCategoriaEnum;
import com.juliano.provaerp.Enum.ProdutoSituacaoEnum;
import com.juliano.provaerp.entity.Produto;
import com.juliano.provaerp.filters.ProdutoFiltro;
import com.juliano.provaerp.repository.ProdutoRepository;
import com.juliano.provaerp.services.ProdutoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.*;

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


        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("size","10");
        requestMap.put("page","1");


        when(produtoService.salvarProduto(produto)).thenReturn(produto);
        when(produtoService.buscarPorCodigo(produto.getCodigo())).thenReturn(produto);
        when(produtoService.buscarTodosProdutos(new ProdutoFiltro(requestMap))).thenReturn(produtosPaginados);


        produtoService.salvarProduto(produto);
        produtoService.buscarPorCodigo(1);


        verify(produtoRepository, times(1)).save(produto);
        verify(produtoRepository, times(1)).findByCodigo(produto.getCodigo());

    }

//    public ProdutoFiltro criarFiltro(String chave,String valor){
//
//        Map<String, String> requestMap = new HashMap<>();
//        requestMap.put()
//
//
//        return new ProdutoFiltro(requestMap);
//    }

}
