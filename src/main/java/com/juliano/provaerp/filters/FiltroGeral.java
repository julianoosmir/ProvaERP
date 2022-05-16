package com.juliano.provaerp.filters;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Map;

public class FiltroGeral {
    private BooleanExpression booleanExpression;
    private int size;
    private int page;
    private Sort sort;

    public FiltroGeral(Map<String, String[]> resquestMap) {
    }
}
