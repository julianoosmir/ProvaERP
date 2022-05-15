package com.juliano.provaerp.service;

import com.juliano.provaerp.Enum.ProdutoCategoriaEnum;
import com.juliano.provaerp.Enum.ProdutoSituacaoEnum;
import com.juliano.provaerp.dto.ItemPedidoDTO;
import com.juliano.provaerp.entity.ItemPedido;
import com.juliano.provaerp.entity.Produto;
import com.juliano.provaerp.repository.ItemPedidoRepository;
import com.juliano.provaerp.services.ItemPedidoService;
import com.juliano.provaerp.services.ProdutoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
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
        produto = new Produto();
        produto.setId(UUID.randomUUID());
        produto.setCodigo(1);
        produto.setCategoria(ProdutoCategoriaEnum.BemDeConsumo);
        produto.setSituacao(ProdutoSituacaoEnum.Ativado);
        produto.setNome("teste");
        produto.setPreco(BigDecimal.ONE);
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

        when(itemPedidoService.buscarItemPorCodigo(itemDTO.getCodigo())).thenReturn(itemPedido);
        when(itemPedidoService.buscarTodosOsItensPedidos()).thenReturn(itemPedidos);


        itemPedidoService.buscarItemPorCodigo(itemDTO.getCodigo());
        itemPedidoService.buscarTodosOsItensPedidos();
        verify(itemPedidoRepository, times(1)).buscarPorCodigo(1);
        verify(itemPedidoRepository, times(1)).buscarTodos();

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
