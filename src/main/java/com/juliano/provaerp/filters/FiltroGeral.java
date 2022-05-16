package com.juliano.provaerp.filters;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Map;
import java.util.Objects;

public class FiltroGeral {
    private BooleanExpression booleanExpression;
    private int size = 10;
    private int page = 0;
    private Sort sort;

    public FiltroGeral(Map<String, String[]> requestMap) {
        this.setPage(this.getValueFilter((String[]) requestMap.get("page")));
        this.setSize(this.getValueFilter((String[]) requestMap.get("size")));
        this.setSort(this.getValueFilter((String[])requestMap.get("direction")), this.getValueFilter((String[])requestMap.get("sort")));
    }

    public String getValueFilter(String[] objFilter) {
        return objFilter != null && !"".equals(objFilter[0]) ? String.valueOf(objFilter[0]) : null;
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
            this.sort = new Sort(new Sort.Order[]{order});
        }
    }

    public void setSortIgnoreCase(String direction, String fieldSort) {
        if (!Objects.isNull(fieldSort)) {
            Sort.Order order = createSortOrder(direction, fieldSort);
            this.sort = new Sort(new Sort.Order[]{order.ignoreCase()});
        }
    }

    public PageRequest getPageRequest() {
        PageRequest pageRequest;
        if (this.sort == null) {
            pageRequest = new PageRequest(this.page, this.size);
        } else {
            pageRequest = new PageRequest(this.page, this.size, this.sort);
        }

        return pageRequest;
    }


    private static Sort.Order createSortOrder(String directionStr, String fieldSort) {
        Sort.Direction direction = getSortDirectionFromString(directionStr);
        return new Sort.Order(direction, fieldSort);
    }

    private static Sort.Direction getSortDirectionFromString(String directionStr) {
        return Objects.isNull(directionStr) ? Sort.Direction.ASC : Sort.Direction.fromString(directionStr);
    }
}
