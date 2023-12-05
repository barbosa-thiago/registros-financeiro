package com.example.sintegracebusca.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SequenceGenerator(name = "INC_PRODUTO_ID", sequenceName = "GEN_PRODUTO_ID", allocationSize = 1)
public class Produto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "INC_PRODUTO_ID")
    Long id;

    @Column(name = "preco", nullable = false)
    Double preco;

    @Column(name = "quantidade", length = 150, nullable = false)
    Integer quantidade;

    @Column(name = "descricao", length = 150)
    String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "compra_id")
    private Compra compra;

}
