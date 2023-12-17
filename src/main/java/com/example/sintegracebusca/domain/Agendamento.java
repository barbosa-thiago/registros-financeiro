package com.example.sintegracebusca.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Agendamento extends BaseEntity {

    @Column(name = "data_pagamento", nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    LocalDate dataPagamento;

    @Column(name = "valor", nullable = false)
    Double valor;

    @Column(name = "fornecedor", nullable = false)
    String fornecedor;

    @Column(name = "pago")
    Boolean pago = false;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pagamento_id")
    Pagamento pagamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "compra_id")
    Compra compra;
}
