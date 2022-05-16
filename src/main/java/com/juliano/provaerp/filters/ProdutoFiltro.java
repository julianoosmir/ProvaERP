package com.juliano.provaerp.filters;

import com.juliano.provaerp.interfaces.RequestFilter;

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
            
        }
    }
}
