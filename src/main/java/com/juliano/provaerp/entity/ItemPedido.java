package com.juliano.provaerp.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Table(name = "tb_item_pedido")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ip_id", updatable = false, unique = true, nullable = false)
    private UUID id;
    @Column(name = "ip_codigo", nullable = false, unique = true)
    private Integer codigo;
    @ManyToOne()
    @JoinColumn(name = "pr_id", referencedColumnName = "pr_id")
    private Produto produto;
    @Column(name = "ip_qtd")
    private Integer quantidade;
}
