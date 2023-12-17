package com.example.sintegracebusca.domain;

import com.example.sintegracebusca.enums.TipoPagamento;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Pagamento extends BaseEntity{

    @Column(name = "valor", nullable = false)
    Double valor;

    @Column(name = "data_pagamento")
    LocalDate dataPagamento = LocalDate.now();

    @Column(name = "pag_descricao", length = 150)
    String descricao;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", length = 15, nullable = false)
    TipoPagamento tipo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "compra_id")
    Compra compra;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "pagamento")
    Agendamento agendamento;

}
