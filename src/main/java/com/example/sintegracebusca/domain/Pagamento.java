package com.example.sintegracebusca.domain;

import com.example.sintegracebusca.enums.TipoPagamento;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SequenceGenerator(name = "INC_PAGAMENTO_ID", sequenceName = "GEN_ID_PAGAMENTO", allocationSize = 1)
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "INC_PAGAMENTO_ID")
    Long id;

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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agendamento_id")
    Agendamento agendamento;

}
