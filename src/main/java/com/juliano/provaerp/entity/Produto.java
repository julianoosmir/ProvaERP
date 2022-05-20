package com.juliano.provaerp.entity;

import com.juliano.provaerp.Enum.ProdutoCategoriaEnum;
import com.juliano.provaerp.Enum.ProdutoSituacaoEnum;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @Size(min = 2,max = 25,message = "codigo errado")
    @NotNull(message = "codigo necessario")
    @Column(name = "pr_codigo", nullable = false, unique = true)
    private Integer codigo;

    @NotNull(message = "preço necessario")
    @Min(value = 1)
    @Column(name = "pr_preco", nullable = false)
    private BigDecimal preco;

    @Size(min = 3,max = 50, message = "nome errado")
    @NotBlank
    @Column(name = "pr_nome", nullable = false)
    private String nome;
    @Column(name = "pr_categoria")

    @NotBlank(message = "insira a categoria do produto")
    @Enumerated(EnumType.STRING)
    private ProdutoCategoriaEnum categoria;
    @Column(name = "pr_situacao")
    @NotBlank(message = "insira o status do produto")
    @Enumerated(EnumType.STRING)
    private ProdutoSituacaoEnum situacao;

}
