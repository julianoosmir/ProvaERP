package com.juliano.provaerp.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ItemPedidoDTO {
    private Integer codigo;
    private UUID produto;
    private Integer quantidade;

}
