package com.juliano.provaerp.services;

import com.juliano.provaerp.entity.ItemPedido;
import com.juliano.provaerp.repository.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ItemPedidoService {
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public List<ItemPedido> buscarTodosOsItensPedidos() {
        return itemPedidoRepository.findAll();
    }

    public ItemPedido buscarItemPorCodigo(Integer codigo) {
        return itemPedidoRepository.findByCodigo(codigo);
    }

    public ItemPedido salvarItemPedido(ItemPedido itemPedido) {
        return itemPedidoRepository.save(itemPedido);
    }

    public ItemPedido atualizarItemPedido(ItemPedido itemPedido) {
        return itemPedidoRepository.save(itemPedido);
    }

    @Transactional
    public void deletarItemPedido(Integer codigo){
        itemPedidoRepository.deleteByCodigo(codigo);
    }

}
