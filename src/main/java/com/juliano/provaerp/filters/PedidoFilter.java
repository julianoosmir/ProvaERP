package com.juliano.provaerp.filters;

import com.juliano.provaerp.Enum.ProdutoCategoriaEnum;
import com.juliano.provaerp.entity.QItemPedido;
import com.juliano.provaerp.entity.QPedido;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.Objects;

public class PedidoFilter extends FiltroGeral {

    public PedidoFilter(Map<String, String> requestMap) {
        this.setPage((String) requestMap.get("page"));
        this.setSize((String) requestMap.get("size"));
        this.setSort((String) requestMap.get("direction"), (String) requestMap.get("sort"));
        setNomeProduto((String) requestMap.get("nome"));
        setCodigo((String) requestMap.get("codigo"));
        setCodigoDoPedido((String) requestMap.get("codigoPedido"));
        setCodigoProduto((String) requestMap.get("codigoProduto"));
        setCategoriaProduto((String) requestMap.get("categoriaProduto"));

    }

    private void setCodigo(String codigo) {
        if (!Objects.isNull(codigo) && StringUtils.isNumeric(codigo)) {
            BooleanExpression expression = QPedido.pedido.codigo.eq(Integer.parseInt(codigo));
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

    private void setCodigoDoPedido(String codigoPedido) {
        if (!Objects.isNull(codigoPedido) && StringUtils.isNumeric(codigoPedido)) {
            BooleanExpression expression = QPedido.pedido.itemPedido
                    .codigo.eq(Integer.parseInt(codigoPedido));
            this.setBooleanExpression(expression);

        }
    }

    private void setNomeProduto(String nome) {
        if (!Objects.isNull(nome)) {
            BooleanExpression expression = QPedido.pedido.itemPedido.
                    produto.nome.containsIgnoreCase(nome);
            this.setBooleanExpression(expression);

        }
    }

}
