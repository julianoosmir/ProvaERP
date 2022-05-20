package com.juliano.provaerp.entity;

import com.juliano.provaerp.Enum.PedidoSituacaoEnum;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "tb_pedido")
@Data
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pe_id", updatable = false, unique = true, nullable = false)
    private UUID id;

    @Size(min = 2,max = 25,message = "codigo errado")
    @NotNull(message = "codigo necessario")
    @Column(name = "pe_codigo", nullable = false, unique = true)
    private Integer codigo;

    @Column(name = "pe_desconto", nullable = false)
    private BigDecimal desconto;

    @NotBlank(message = "insira o status do pedido")
    @Column(name = "pe_situacao", nullable = false)
    @Enumerated(EnumType.STRING)
    private PedidoSituacaoEnum situacao;

    @Column(name = "pe_total", nullable = false)
    @NotNull
    @Min(value = 1)
    private BigDecimal valorTotal;

    @ManyToOne()
    @JoinColumn(name = "ip_id", referencedColumnName = "ip_id")
    @NotNull(message = "item do pedido não pode ser nulo")
    private ItemPedido itemPedido;


}
