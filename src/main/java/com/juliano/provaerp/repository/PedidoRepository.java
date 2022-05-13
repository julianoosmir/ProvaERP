package com.juliano.provaerp.repository;

import com.juliano.provaerp.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface PedidoRepository  extends JpaRepository<Pedido, UUID> {
}
