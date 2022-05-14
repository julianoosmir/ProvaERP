package com.juliano.provaerp.dto;

import com.juliano.provaerp.Enum.PedidoSituacaoEnum;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PedidoDTO {
    private BigDecimal desconto;
    private Integer codigoPedido;
    private Integer codigoItemPedido;
    private PedidoSituacaoEnum situacao;
}
