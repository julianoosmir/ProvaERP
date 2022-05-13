package com.juliano.provaerp.entity;

import com.juliano.provaerp.Enum.ProdutoCategoriaEnum;
import com.juliano.provaerp.Enum.ProdutoSituacaoEnum;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@Table(name = "tb_produtos")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pr_id", updatable = false, unique = true, nullable = false)
    private UUID id;

    @Column(name = "pr_codigo", nullable = false, unique = true)
    private Integer codigo;

    @Column(name = "pr_preco", nullable = false)
    private BigDecimal preco;
    @Column(name = "pr_nome", nullable = false)
    private String nome;
    @Column(name = "pr_categoria")
    @Enumerated(EnumType.STRING)
    private ProdutoCategoriaEnum categoria;
    @Column(name = "pr_situacao")
    @Enumerated(EnumType.STRING)
    private ProdutoSituacaoEnum situacao;

}
