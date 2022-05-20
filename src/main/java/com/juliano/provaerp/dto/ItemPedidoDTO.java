package com.juliano.provaerp.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
public class ItemPedidoDTO {
    @Size(min = 2,max = 25,message = "codigo errado")
    @NotNull(message = "codigo necessario")
    private Integer codigo;
    @NotNull(message = "Produto não pode ser nulo")
    private UUID produto;
    @Min(value = 1)
    @NotNull
    private Integer quantidade;

}
