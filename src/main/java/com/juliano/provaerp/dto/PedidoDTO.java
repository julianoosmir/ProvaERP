package com.juliano.provaerp.dto;

import com.juliano.provaerp.Enum.PedidoSituacaoEnum;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class PedidoDTO {

    private BigDecimal desconto;
    @NotNull(message = "codigo necessario")
    private Integer codigoPedido;
    @NotNull(message = "item do pedido não pode ser nulo")
    private Integer codigoItemPedido;
    @NotNull(message = "insira o status do pedido")
    private PedidoSituacaoEnum situacao;
}
