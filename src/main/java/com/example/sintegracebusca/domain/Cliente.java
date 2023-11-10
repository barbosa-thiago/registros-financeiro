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
@SequenceGenerator(name = "INC_CLIENTE", sequenceName = "GEN_CLIENTE_ID")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "INC_CLIENTE")
    Long id;

    @Column(name = "cnpj", unique = true)
    String cnpj;

    @Column(name = "razao_social")
    String razaoSocial;

    @Column(name = "logradouro")
    String logradouro;

    @Column(name = "numero")
    String numero;

    @Column(name = "complemento")
    String complemento;

    @Column(name = "bairro")
    String bairro;

    @Column(name = "cep")
    String cep;

    @Column(name = "uf")
    String uf;

    @Column(name = "municipio")
    String municipio;

    @Column(name = "inscricao_estadual")
    String inscricaoEstadual;

    @Column(name = "telefone")
    String telefone;
}
