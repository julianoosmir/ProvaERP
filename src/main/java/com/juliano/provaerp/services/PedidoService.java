package com.juliano.provaerp.services;

import com.juliano.provaerp.Enum.PedidoSituacaoEnum;
import com.juliano.provaerp.Enum.ProdutoCategoriaEnum;
import com.juliano.provaerp.dto.PedidoDTO;
import com.juliano.provaerp.entity.Pedido;
import com.juliano.provaerp.entity.Produto;
import com.juliano.provaerp.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ItemPedidoService itemPedidoService;

    public List<Pedido> buscarTodosOsPedidos() {
        return pedidoRepository.buscarTodosOsPedidos();
    }

    public Page<Pedido> buscarTodosOsPedidosPaginados(int page, int size) {
        List<Pedido> pedidos = buscarTodosOsPedidos();
        Pageable pages = PageRequest.of(page, size);
        return new PageImpl<>(pedidos, pages, size);
    }

    public Pedido buscarPorCodigo(Integer codigo) {
        return pedidoRepository.buscarPedidoPorCodigo(codigo);
    }

    public Pedido salvarPedido(PedidoDTO pedidoDTO) {
        Pedido pedido = new Pedido();
        pedido.setCodigo(pedidoDTO.getCodigoPedido());
        pedido.setDesconto(pedidoDTO.getDesconto());
        pedido.setItemPedido(itemPedidoService.buscarItemPorCodigo(pedidoDTO.getCodigoItemPedido()));
        pedido.setValorTotal(calcularValorTotal(pedido));
        pedido.setSituacao(pedidoDTO.getSituacao());
        return pedidoRepository.save(pedido);
    }

    public BigDecimal calcularValorTotal(Pedido pedido) {
        Produto produto = pedido.getItemPedido().getProduto();
        Integer quantidade = pedido.getItemPedido().getQuantidade();

        BigDecimal valorDosProdutos = calcularValorDosProdutos(pedido.getItemPedido()
                .getQuantidade(), produto.getPreco());

        if (produto.getCategoria().equals(ProdutoCategoriaEnum.BemDeConsumo)
                && pedido.getSituacao().equals(PedidoSituacaoEnum.Aberto)) {
            return calcularDesconto(valorDosProdutos, pedido.getDesconto());
        }
        return valorDosProdutos;
    }

    public BigDecimal calcularDesconto(BigDecimal valorDosProdutos, BigDecimal desconto) {
        BigDecimal valorInicial = valorDosProdutos;
        BigDecimal valorDesconto = valorDosProdutos.multiply(desconto).divide(BigDecimal.valueOf(100));
        return valorInicial.subtract(valorDesconto);
    }

    public BigDecimal calcularValorDosProdutos(Integer quantidade, BigDecimal preco) {
        return preco.multiply(BigDecimal.valueOf(quantidade));
    }

    public Pedido atualizarPedido(PedidoDTO dto) {
        Pedido pedido = buscarPorCodigo(dto.getCodigoPedido());
        pedido.setDesconto(dto.getDesconto());
        pedido.setItemPedido(itemPedidoService.buscarItemPorCodigo(dto.getCodigoItemPedido()));
        pedido.setValorTotal(calcularValorTotal(pedido));
        pedido.setSituacao(dto.getSituacao());
        return pedidoRepository.save(pedido);
    }

    public void deletarPedido(Integer codigo) {
        pedidoRepository.deleteByCodigo(codigo);
    }
}
