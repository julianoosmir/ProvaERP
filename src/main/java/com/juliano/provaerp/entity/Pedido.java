package com.juliano.provaerp.entity;

import com.juliano.provaerp.Enum.PedidoSituacaoEnum;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "tb_pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pe_id", updatable = false, unique = true, nullable = false)
    private UUID id;
    @Column(name = "pe_desconto", nullable = false)
    private BigDecimal desconto;
    @Column(name = "pe_situacao", nullable = false)
    private PedidoSituacaoEnum situacao;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ip_id", referencedColumnName = "ip_id")
    private ItemPedido itemPedido;


}
