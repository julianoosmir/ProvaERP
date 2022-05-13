package com.juliano.provaerp.repository;

import com.juliano.provaerp.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.lang.ref.Reference;
import java.util.UUID;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, UUID> {

    public void deleteByCodigo(Integer codigo);

    public Produto findByCodigo(Integer codigo);
}
