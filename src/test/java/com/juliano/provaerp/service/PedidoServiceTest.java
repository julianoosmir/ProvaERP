package com.juliano.provaerp.service;

import com.juliano.provaerp.Enum.PedidoSituacaoEnum;
import com.juliano.provaerp.Enum.ProdutoCategoriaEnum;
import com.juliano.provaerp.Enum.ProdutoSituacaoEnum;
import com.juliano.provaerp.dto.PedidoDTO;
import com.juliano.provaerp.entity.ItemPedido;
import com.juliano.provaerp.entity.Pedido;
import com.juliano.provaerp.entity.Produto;
import com.juliano.provaerp.filters.PedidoFiltro;
import com.juliano.provaerp.repository.PedidoRepository;
import com.juliano.provaerp.services.ItemPedidoService;
import com.juliano.provaerp.services.PedidoService;
import org.junit.Assert;
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
public class PedidoServiceTest {

    @InjectMocks
    private PedidoService pedidoService;
    @Mock
    private PedidoRepository pedidoRepository;
    @Mock
    private ItemPedidoService itemPedidoService;
    Produto produto;
    ItemPedido itemPedido;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        produto = new Produto();
        produto.setId(UUID.randomUUID());
        produto.setCodigo(1);
        produto.setCategoria(ProdutoCategoriaEnum.BemDeConsumo);
        produto.setSituacao(ProdutoSituacaoEnum.Ativado);
        produto.setNome("teste");
        produto.setPreco(BigDecimal.ONE);

        itemPedido = new ItemPedido();
        itemPedido.setId(UUID.randomUUID());
        itemPedido.setCodigo(1);
        itemPedido.setQuantidade(10);
        itemPedido.setProduto(produto);
    }

    @Test
    public void buscar() {
        PedidoDTO dto = new PedidoDTO();
        dto.setCodigoItemPedido(itemPedido.getCodigo());
        dto.setSituacao(PedidoSituacaoEnum.Aberto);
        dto.setDesconto(BigDecimal.valueOf(10));
        dto.setCodigoPedido(1);

        Pedido pedido = new Pedido();
        pedido.setItemPedido(itemPedido);
        pedido.setDesconto(dto.getDesconto());
        pedido.setSituacao(dto.getSituacao());
        pedido.setCodigo(dto.getCodigoPedido());
        pedido.setId(UUID.randomUUID());

        List<Pedido> pedidos = new ArrayList<>();
        pedidos.add(pedido);

        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("size", "10");
        requestMap.put("page", "1");

        PedidoFiltro filtro = new PedidoFiltro(requestMap);

        Pageable pages = PageRequest.of(1, 10);

        Page<Pedido> pedidosPaginados = new PageImpl<>(pedidos, pages, 10);


        when(pedidoService.buscarTodosOsPedidos()).thenReturn(pedidos);
        when(pedidoService.buscarPorCodigo(1)).thenReturn(pedido);
        when(pedidoService.buscarTodosOsPedidosPaginados(filtro)).thenReturn(pedidosPaginados);

        pedidoService.buscarTodosOsPedidos();
        pedidoService.buscarPorCodigo(1);
        pedidoService.buscarTodosOsPedidosPaginados(filtro);

        verify(pedidoRepository, times(1)).buscarTodosOsPedidos();
        verify(pedidoRepository, times(1)).buscarPedidoPorCodigo(1);
        verify(pedidoRepository,times(1)).findAll(filtro.getBooleanExpression(),
                filtro.getPageRequest());

    }

    @Test
    public void delete() {
        Pedido pedido = new Pedido();
        pedido.setItemPedido(itemPedido);
        pedido.setDesconto(BigDecimal.ONE);
        pedido.setSituacao(PedidoSituacaoEnum.Fechado);
        pedido.setCodigo(1);
        pedido.setId(UUID.randomUUID());
        pedidoService.deletarPedido(1);
        verify(pedidoRepository, times(1)).deleteByCodigo(1);
    }

    @Test
    public void calcularValorDosProdutos() {
        BigDecimal valordosProdutos = pedidoService
                .calcularValorDosProdutos(itemPedido.getQuantidade(), produto.getPreco());
        Assert.assertEquals(valordosProdutos, BigDecimal.TEN);
    }

    @Test
    public void calcularDesconto() {
        BigDecimal valorDesconto = pedidoService.calcularDesconto(BigDecimal.TEN, BigDecimal.TEN);
        Assert.assertEquals(valorDesconto, BigDecimal.valueOf(9));
    }

    @Test
    public void calcularValorTotalPedidoAberto(){
        Pedido pedido = new Pedido();
        pedido.setItemPedido(itemPedido);
        pedido.setDesconto(BigDecimal.TEN);
        pedido.setSituacao(PedidoSituacaoEnum.Aberto);
        pedido.setCodigo(1);
        pedido.setId(UUID.randomUUID());
        Assert.assertEquals(pedidoService.calcularValorTotal(pedido), BigDecimal.valueOf(9));
    }
    @Test
    public void calcularValorTotalPedidoFechado(){
        Pedido pedido = new Pedido();
        pedido.setItemPedido(itemPedido);
        pedido.setDesconto(BigDecimal.TEN);
        pedido.setSituacao(PedidoSituacaoEnum.Fechado);
        pedido.setCodigo(1);
        pedido.setId(UUID.randomUUID());
        Assert.assertEquals(pedidoService.calcularValorTotal(pedido), BigDecimal.valueOf(10));
    }


}
