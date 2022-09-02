package com.juliano.provaerp.util;

import com.juliano.provaerp.Enum.ProdutoCategoriaEnum;
import com.juliano.provaerp.Enum.ProdutoSituacaoEnum;
import com.juliano.provaerp.entity.Produto;

import java.math.BigDecimal;
import java.util.UUID;

public  class ProdutoUtil {
    public static Produto criarProduto(){
        Produto produto = new Produto();
        produto.setId(UUID.randomUUID());
        produto.setCodigo(1);
        produto.setCategoria(ProdutoCategoriaEnum.BemDeConsumo);
        produto.setSituacao(ProdutoSituacaoEnum.Ativado);
        produto.setNome("teste");
        produto.setPreco(BigDecimal.ONE);
        return produto;
    }
}
