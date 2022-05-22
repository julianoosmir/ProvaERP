package com.juliano.provaerp.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class ItemPedidoDTO {
    @NotNull(message = "codigo necessario")
    private Integer codigo;
    @NotNull(message = "Produto necessario")
    private UUID produto;
    @NotNull(message = "quantidade necessaria")
    private Integer quantidade;

}
