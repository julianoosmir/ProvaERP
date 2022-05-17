package com.juliano.provaerp.filters;

import com.juliano.provaerp.entity.QProduto;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.util.Map;
import java.util.Objects;
public class ProdutoFiltro  extends FiltroGeral{
     public ProdutoFiltro(Map<String, String> requestMap) {
        this.setPage((String) requestMap.get("page"));
        this.setSize((String) requestMap.get("size"));
        this.setSort((String) requestMap.get("direction"), (String) requestMap.get("sort"));
        setNome((String) requestMap.get("nome"));
    }

    private void setNome(String nome) {
        if (!Objects.isNull(nome)) {
            QProduto qproduto = QProduto.produto;
            BooleanExpression expression = qproduto.nome.containsIgnoreCase(nome);
            this.setBooleanExpression(expression);

        }
    }
}
