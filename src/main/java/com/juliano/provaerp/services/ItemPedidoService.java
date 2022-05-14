package com.juliano.provaerp.services;

import com.juliano.provaerp.Enum.ProdutoSituacaoEnum;
import com.juliano.provaerp.dto.ItemPedidoDTO;
import com.juliano.provaerp.entity.ItemPedido;
import com.juliano.provaerp.entity.Pedido;
import com.juliano.provaerp.entity.Produto;
import com.juliano.provaerp.repository.ItemPedidoRepository;
import org.hibernate.engine.internal.JoinSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class ItemPedidoService {
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;
    @Autowired
    private ProdutoService produtoService;

    public List<ItemPedido> buscarTodosOsItensPedidos() {
        return itemPedidoRepository.buscarTodos();
    }

    public Page<ItemPedido> buscarTodosOsPedidosPaginados(int page, int size) {
        List<ItemPedido> itemPedidos = buscarTodosOsItensPedidos();
        Pageable pages = PageRequest.of(page, size);
        return new PageImpl<>(itemPedidos, pages, size);
    }

    public ItemPedido buscarItemPorCodigo(Integer codigo) {
        return itemPedidoRepository.buscarPorCodigo(codigo);
    }

    public ItemPedido salvarItemPedido(ItemPedidoDTO itemPedidoDTO) throws Exception {
        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setCodigo(itemPedidoDTO.getCodigo());
        itemPedido.setQuantidade(itemPedido.getQuantidade());
        itemPedido.setProduto(adcionarProdutoAoPedido(itemPedidoDTO.getProduto()));
        return itemPedidoRepository.save(itemPedido);
    }

    public Produto adcionarProdutoAoPedido(UUID idProduto) throws Exception {
        Produto produto = produtoService.buscarPorCodigo(idProduto);
        if (produto.getSituacao().equals(ProdutoSituacaoEnum.Desativado)) {
            throw new Exception("produto desativado");
        }
        return produto;
    }

    public ItemPedido atualizarItemPedido(ItemPedido itemPedido) {
        return itemPedidoRepository.save(itemPedido);
    }

    @Transactional
    public void deletarItemPedido(Integer codigo) {
        itemPedidoRepository.deleteByCodigo(codigo);
    }

}
