package com.juliano.provaerp.entity;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "tb_item_pedido")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ip_id", updatable = false, unique = true, nullable = false)
    private UUID id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pr_id", referencedColumnName = "pr_id")
    private Produto produto;
    private Integer quantidade;
}
