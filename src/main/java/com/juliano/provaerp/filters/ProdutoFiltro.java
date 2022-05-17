package com.juliano.provaerp.filters;

import com.juliano.provaerp.entity.QProduto;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Map;
import java.util.Objects;
@Data
public class ProdutoFiltro {
    protected BooleanExpression booleanExpression;
    private int size = 10;
    private int page = 0;
    private Sort sort;

    public ProdutoFiltro(Map<String, String> requestMap) {
        this.setPage((String) requestMap.get("page"));
        this.setSize((String) requestMap.get("size"));
        this.setSort((String) requestMap.get("direction"), (String) requestMap.get("sort"));
        setNome((String) requestMap.get("nome"));
    }

    public int getSize() {
        return size;
    }

    public void setSize(String sizeStr) {
        if (sizeStr != null) {
            this.size = Integer.parseInt(sizeStr);
        }

    }

    public void setPage(String pageStr) {
        if (pageStr != null) {
            this.page = Integer.parseInt(pageStr) - 1;
        }

    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setSort(String direction, String fieldSort) {
        if (!Objects.isNull(fieldSort)) {
            Sort.Order order = createSortOrder(direction, fieldSort);
            this.sort = Sort.by(order);
        }
    }

    public void setSortIgnoreCase(String direction, String fieldSort) {
        if (!Objects.isNull(fieldSort)) {
            Sort.Order order = createSortOrder(direction, fieldSort);
            this.sort = Sort.by(order.ignoreCase());
        }
    }

    public PageRequest getPageRequest() {
        PageRequest pageRequest;
        if (this.sort == null) {
            pageRequest = PageRequest.of(this.page, this.size);
        } else {
            pageRequest = PageRequest.of(this.page, this.size, this.sort);
        }

        return pageRequest;
    }

    public BooleanExpression getBooleanExpression() {
        return booleanExpression;
    }


    private static Sort.Order createSortOrder(String directionStr, String fieldSort) {
        Sort.Direction direction = getSortDirectionFromString(directionStr);
        return new Sort.Order(direction, fieldSort);
    }

    private static Sort.Direction getSortDirectionFromString(String directionStr) {
        return Objects.isNull(directionStr) ? Sort.Direction.ASC : Sort.Direction.fromString(directionStr);
    }

    public void setBooleanExpression(BooleanExpression booleanExpression) {
        if (Objects.isNull(this.booleanExpression)) {
            this.booleanExpression = booleanExpression;
        }
        this.booleanExpression = this.booleanExpression.and(booleanExpression);

    }

    private void setNome(String nome) {
        if (!Objects.isNull(nome)) {
            QProduto qproduto = QProduto.produto;
            BooleanExpression expression = qproduto.nome.containsIgnoreCase(nome);
            this.setBooleanExpression(expression);

        }
    }
}
