package com.juliano.provaerp.repository;

import com.juliano.provaerp.entity.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, UUID> {
    @Query("SELECT pe FROM Pedido pe JOIN FETCH pe.itemPedido item JOIN FETCH item.produto")
    public List<Pedido> buscarTodosOsPedidos();

    @Query("SELECT pe FROM Pedido pe JOIN FETCH pe.itemPedido  item JOIN FETCH item.produto WHERE pe.codigo = ?1")
    public Pedido buscarPedidoPorCodigo(Integer codigo);

    public void deleteByCodigo(Integer codigo);

}
