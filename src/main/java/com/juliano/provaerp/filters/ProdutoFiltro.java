package com.juliano.provaerp.filters;
import com.juliano.provaerp.entity.QProduto;
import com.juliano.provaerp.interfaces.RequestFilter;
import com.querydsl.core.types.dsl.BooleanExpression;


import java.beans.Expression;
import java.util.Map;
import java.util.Objects;

@RequestFilter
public class ProdutoFiltro extends FiltroGeral {


    public ProdutoFiltro(Map<String, String[]> requestMap) {
        super(requestMap);
        setNome(this.getValueFilter((String[]) requestMap.get("nome")));
    }

    private void setNome(String nome) {
        if (!Objects.isNull(nome)) {
            QProduto qproduto = QProduto.produto;
            BooleanExpression expression = qproduto.nome.containsIgnoreCase(nome);
            this.setBooleanExpression(expression);

        }
    }
}
