package com.juliano.provaerp.service;

import com.juliano.provaerp.Enum.ProdutoCategoriaEnum;
import com.juliano.provaerp.Enum.ProdutoSituacaoEnum;
import com.juliano.provaerp.entity.Produto;
import com.juliano.provaerp.filters.ProdutoFiltro;
import com.juliano.provaerp.repository.ProdutoRepository;
import com.juliano.provaerp.services.ProdutoService;
import com.juliano.provaerp.util.ProdutoUtil;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.*;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
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
        Produto produto = ProdutoUtil.criarProduto();

        List<Produto> produtos = new ArrayList<>();
        produtos.add(produto);

        Pageable pages = PageRequest.of(1, 10);

        Page<Produto> produtosPaginados = new PageImpl<>(produtos, pages, 10);


        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("size", "10");
        requestMap.put("page", "1");

        ProdutoFiltro produtoFiltro = new ProdutoFiltro(requestMap);

        when(produtoService.salvarProduto(produto)).thenReturn(produto);
        when(produtoService.buscarPorCodigo(produto.getCodigo())).thenReturn(produto);
        when(produtoService.buscarTodosProdutos(produtoFiltro)).thenReturn(produtosPaginados);


        produtoService.salvarProduto(produto);
        produtoService.buscarPorCodigo(1);
        produtoService.buscarTodosProdutos(produtoFiltro);


        verify(produtoRepository, times(1)).save(produto);
        verify(produtoRepository, times(1)).findByCodigo(produto.getCodigo());
        verify(produtoRepository, times(1))
                .findAll(produtoFiltro.getBooleanExpression(), produtoFiltro.getPageRequest());

    }

    @Test
    public void buscarTodos(){
        List<Produto> produtos = produtoService.buscarTodos();
        Assertions.assertThat(produtos)
                .isNotNull()
                .isEmpty();
    }
}
