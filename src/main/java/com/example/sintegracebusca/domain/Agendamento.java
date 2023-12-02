package com.example.sintegracebusca.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SequenceGenerator(name = "INC_CLIENTE", sequenceName = "GEN_CLIENTE_ID", allocationSize = 1)
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "INC_CLIENTE")
    Long id;

    @Column(name = "data_pagamento", nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    LocalDate dataPagamento;

    @Column(name = "valor", nullable = false)
    Double valor;

    @Column(name = "fornecedor", nullable = false)
    String fornecedor;

    @Column(name = "pago")
    Boolean pago = false;
}