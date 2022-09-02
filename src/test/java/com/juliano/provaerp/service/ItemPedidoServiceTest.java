package com.juliano.provaerp.service;

import com.juliano.provaerp.Enum.ProdutoCategoriaEnum;
import com.juliano.provaerp.Enum.ProdutoSituacaoEnum;
import com.juliano.provaerp.dto.ItemPedidoDTO;
import com.juliano.provaerp.entity.ItemPedido;
import com.juliano.provaerp.entity.Pedido;
import com.juliano.provaerp.entity.Produto;
import com.juliano.provaerp.filters.ItemPedidoFiltro;
import com.juliano.provaerp.repository.ItemPedidoRepository;
import com.juliano.provaerp.services.ItemPedidoService;
import com.juliano.provaerp.services.ProdutoService;
import com.juliano.provaerp.util.ProdutoUtil;
import org.junit.Before;
import org.junit.Ignore;
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
@Ignore
public class ItemPedidoServiceTest {

    @InjectMocks
    private ItemPedidoService itemPedidoService;
    @Mock
    private ItemPedidoRepository itemPedidoRepository;
    @Mock
    private ProdutoService produtoService;

    Produto produto;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        produto = ProdutoUtil.criarProduto();
        produtoService.salvarProduto(produto);
    }

    @Test
    public void buscar() throws Exception {
        ItemPedidoDTO itemDTO = new ItemPedidoDTO();

        itemDTO.setCodigo(1);
        itemDTO.setProduto(produto.getId());
        itemDTO.setQuantidade(1);


        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setId(UUID.randomUUID());
        itemPedido.setCodigo(itemDTO.getCodigo());
        itemPedido.setQuantidade(itemDTO.getQuantidade());
        itemPedido.setProduto(produto);

        List<ItemPedido> itemPedidos = new ArrayList<>();
        itemPedidos.add(itemPedido);

        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("size", "10");
        requestMap.put("page", "1");

        ItemPedidoFiltro filtro = new ItemPedidoFiltro(requestMap);

        Pageable pages = PageRequest.of(1, 10);

        Page<ItemPedido> pedidosPaginados = new PageImpl<>(itemPedidos, pages, 10);
        when(itemPedidoService.buscarItemPorCodigo(itemDTO.getCodigo())).thenReturn(itemPedido);
        when(itemPedidoService.buscarTodosOsItensPedidos()).thenReturn(itemPedidos);
        when(itemPedidoService.buscarTodosOsPedidosPaginados(filtro)).thenReturn(pedidosPaginados);


        itemPedidoService.buscarItemPorCodigo(itemDTO.getCodigo());
        itemPedidoService.buscarTodosOsItensPedidos();
        itemPedidoService.buscarTodosOsPedidosPaginados(filtro);
        verify(itemPedidoRepository, times(1)).buscarPorCodigo(1);
        verify(itemPedidoRepository, times(1)).buscarTodos();
        verify(itemPedidoRepository,times(1)).findAll(filtro.getBooleanExpression(),filtro.getPageRequest());

    }
    @Test
    public void atualizar(){
        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setId(UUID.randomUUID());
        itemPedido.setCodigo(1);
        itemPedido.setQuantidade(1);
        itemPedido.setProduto(produto);
        when(itemPedidoService.atualizarItemPedido(itemPedido)).thenReturn(itemPedido);
        itemPedidoService.atualizarItemPedido(itemPedido);
        verify(itemPedidoRepository,times(1)).save(itemPedido);

    }
    @Test
    @Ignore
    public void delete(){
        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setId(UUID.randomUUID());
        itemPedido.setCodigo(1);
        itemPedido.setQuantidade(1);
        itemPedido.setProduto(produto);
        itemPedidoService.deletarItemPedido(1);
        verify(itemPedidoRepository,times(1)).deleteByCodigo(itemPedido.getCodigo());

    }
}
