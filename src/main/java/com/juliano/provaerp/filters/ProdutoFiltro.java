package com.juliano.provaerp.filters;

import com.juliano.provaerp.Enum.ProdutoCategoriaEnum;
import com.juliano.provaerp.Enum.ProdutoSituacaoEnum;
import com.juliano.provaerp.entity.QProduto;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.Objects;

public class ProdutoFiltro extends FiltroGeral {
    public ProdutoFiltro(Map<String, String> requestMap) {
        this.setPage((String) requestMap.get("page"));
        this.setSize((String) requestMap.get("size"));
        this.setSort((String) requestMap.get("direction"), (String) requestMap.get("sort"));
        setNome((String) requestMap.get("nome"));
        setCodigo((String) requestMap.get("codigo"));
        setCategoria((String) requestMap.get("categoria"));
        setSituacao((String) requestMap.get("situacao"));
    }

    private void setSituacao(String situacao) {
        if (!Objects.isNull(situacao)) {
            QProduto qproduto = QProduto.produto;
            BooleanExpression expression = qproduto.situacao.eq(ProdutoSituacaoEnum.valueOf(situacao));
            this.setBooleanExpression(expression);

        }
    }

    private void setCategoria(String categoria) {
        if (!Objects.isNull(categoria)) {
            QProduto qproduto = QProduto.produto;
            BooleanExpression expression = qproduto.categoria.eq(ProdutoCategoriaEnum.valueOf(categoria));
            this.setBooleanExpression(expression);

        }
    }

    private void setNome(String nome) {
        if (!Objects.isNull(nome)) {
            QProduto qproduto = QProduto.produto;
            BooleanExpression expression = qproduto.nome.containsIgnoreCase(nome);
            this.setBooleanExpression(expression);

        }
    }

    private void setCodigo(String codigo) {
        if (!Objects.isNull(codigo) && StringUtils.isNumeric(codigo)) {
            QProduto qproduto = QProduto.produto;
            BooleanExpression expression = qproduto.codigo.eq(Integer.parseInt(codigo));
            this.setBooleanExpression(expression);

        }
    }
}
