package com.juliano.provaerp.repository;

import com.juliano.provaerp.entity.Produto;
import com.juliano.provaerp.util.ProdutoUtil;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@DisplayName("Tests for Produto Repository")
@AutoConfigureTestDatabase
@RunWith(SpringRunner.class)
@DataJpaTest
public class ProdutoRepositoryTest {
    @Autowired
    private  ProdutoRepository produtoRepository;
    Produto produto = ProdutoUtil.criarProduto();
    @Test
    public void save(){
        produto.setId(null);
        Produto produtoSalvo = produtoRepository.save(produto);
        Assertions.assertThat(produtoSalvo).isNotNull();

        Assertions.assertThat(produtoSalvo.getId()).isNotNull();

        Assertions.assertThat(produtoSalvo.getNome()).isEqualTo(produto.getNome());

        Produto p = produtoRepository.getById(produtoSalvo.getId());

        Assertions.assertThat(p.getNome()).isEqualTo(produto.getNome());

    }
    @Test
    public void findByID(){
        Produto p = produtoRepository.getById(UUID.fromString("524f0863-92fb-4c35-a0a8-8d187553852b"));
        Assertions.assertThat(p.getNome()).isEqualTo("testando");
    }
}