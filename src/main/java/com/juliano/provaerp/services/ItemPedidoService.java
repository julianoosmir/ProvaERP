package com.juliano.provaerp.services;

import com.juliano.provaerp.entity.ItemPedido;
import com.juliano.provaerp.repository.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemPedidoService {
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public List<ItemPedido> buscarTodosOsItensPedidos(){
        return itemPedidoRepository.findAll();
    }
    public ItemPedido salvarItemPedido(ItemPedido itemPedido){
        return itemPedidoRepository.save(itemPedido);
    }

}
