package com.juliano.provaerp.filters;

import com.juliano.provaerp.Enum.ProdutoCategoriaEnum;
import com.juliano.provaerp.Enum.ProdutoSituacaoEnum;
import com.juliano.provaerp.entity.QItemPedido;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.Objects;

public class ItemPedidoFiltro extends FiltroGeral {

    public ItemPedidoFiltro(Map<String, String> requestMap) {
        this.setPage((String) requestMap.get("page"));
        this.setSize((String) requestMap.get("size"));
        this.setSort((String) requestMap.get("direction"), (String) requestMap.get("sort"));
        setNomeProduto((String) requestMap.get("nome"));
        setCodigo((String) requestMap.get("codigo"));
        setCodigoProduto((String) requestMap.get("codigoProduto"));
        setCategoriaProduto((String) requestMap.get("categoriaProduto"));
        setSituacaoProduto((String) requestMap.get("situacaoProduto"));
        setQuantidade((String) requestMap.get("quantidade"));
    }

    private void setQuantidade(String quantidade) {
        if (!Objects.isNull(quantidade) && StringUtils.isNumeric(quantidade)) {
            BooleanExpression expression = QItemPedido.itemPedido.produto
                    .codigo.eq(Integer.parseInt(quantidade));
            this.setBooleanExpression(expression);
        }
    }

    private void setSituacaoProduto(String situacaoProduto) {
        if (!Objects.isNull(situacaoProduto)) {
            BooleanExpression expression = QItemPedido.itemPedido.produto
                    .situacao.eq(ProdutoSituacaoEnum.valueOf(situacaoProduto));
            this.setBooleanExpression(expression);

        }
    }

    private void setCategoriaProduto(String categoriaProduto) {
        if (!Objects.isNull(categoriaProduto)) {
            BooleanExpression expression = QItemPedido.itemPedido.produto
                    .categoria.eq(ProdutoCategoriaEnum.valueOf(categoriaProduto));
            this.setBooleanExpression(expression);
        }
    }

    private void setCodigoProduto(String codigoProduto) {
        if (!Objects.isNull(codigoProduto) && StringUtils.isNumeric(codigoProduto)) {
            BooleanExpression expression = QItemPedido.itemPedido.produto
                    .codigo.eq(Integer.parseInt(codigoProduto));
            this.setBooleanExpression(expression);
        }
    }

    private void setCodigo(String codigo) {
        if (!Objects.isNull(codigo) && StringUtils.isNumeric(codigo)) {
            BooleanExpression expression = QItemPedido.itemPedido.codigo.eq(Integer.parseInt(codigo));
            this.setBooleanExpression(expression);

        }
    }

    private void setNomeProduto(String nome) {
        if (!Objects.isNull(nome)) {
            BooleanExpression expression = QItemPedido.itemPedido.produto.nome.containsIgnoreCase(nome);
            this.setBooleanExpression(expression);

        }
    }
}
