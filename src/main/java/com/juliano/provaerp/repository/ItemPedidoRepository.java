package com.juliano.provaerp.repository;

import com.juliano.provaerp.entity.ItemPedido;
import com.juliano.provaerp.entity.Produto;
import com.juliano.provaerp.interfaces.JoinCapableRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ItemPedidoRepository extends JoinCapableRepository<ItemPedido, UUID> {
    @Query("SELECT item FROM ItemPedido item JOIN  item.produto WHERE item.codigo = ?1")
    public ItemPedido buscarPorCodigo(Integer codigo);

    @Query("SELECT item FROM ItemPedido item JOIN item.produto")
    public List<ItemPedido> buscarTodos();
    @Query("SELECT pro FROM ItemPedido item JOIN  item.produto pro WHERE pro.id = ?1")
    public List<Produto> buscarProdutosEmPedidos(UUID id);
    public void deleteByCodigo(Integer codigo);
}
