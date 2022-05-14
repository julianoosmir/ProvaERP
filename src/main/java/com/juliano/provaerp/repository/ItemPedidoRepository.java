package com.juliano.provaerp.repository;

import com.juliano.provaerp.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, UUID> {
    @Query("SELECT item FROM ItemPedido item JOIN FETCH item.produto WHERE item.codigo = ?1")
    public ItemPedido buscarPorCodigo(Integer codigo);

    @Query("SELECT item FROM ItemPedido item JOIN FETCH item.produto")
    public List<ItemPedido> buscarTodos();

    public void deleteByCodigo(Integer codigo);
}
