package com.juliano.provaerp.services;

import com.juliano.provaerp.entity.Pedido;
import com.juliano.provaerp.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido salvarPedido(Pedido pedido){
        return pedidoRepository.save(pedido);
    }
}
