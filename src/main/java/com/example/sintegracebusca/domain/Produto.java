package com.example.sintegracebusca.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Produto extends BaseEntity {

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
