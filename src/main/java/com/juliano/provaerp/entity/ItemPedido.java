package com.juliano.provaerp.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Data
@Table(name = "tb_item_pedido")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ip_id", updatable = false, unique = true, nullable = false)
    private UUID id;
    @Size(min = 2,max = 25,message = "codigo errado")
    @NotNull(message = "codigo necessario")
    @Column(name = "ip_codigo", nullable = false, unique = true)
    private Integer codigo;
    @NotNull(message = "Produto não pode ser nulo")
    @ManyToOne()
    @JoinColumn(name = "pr_id", referencedColumnName = "pr_id")
    private Produto produto;
    @Size(min = 1,message = "codigo errado")
    @NotNull(message = "quantidade necessaria")
    @Min(value = 1)
    @Column(name = "ip_qtd")
    private Integer quantidade;
}
