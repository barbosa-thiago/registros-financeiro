package com.example.sintegracebusca.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Cliente extends BaseEntity {

    @Column(name = "cnpj", unique = true, length = 14)
    String cnpj;

    @Column(name = "razao_social")
    String razaoSocial;

    @Column(name = "logradouro", length = 150)
    String logradouro;

    @Column(name = "numero", length = 7)
    String numero;

    @Column(name = "complemento", length = 20)
    String complemento;

    @Column(name = "bairro", length = 80)
    String bairro;

    @Column(name = "cep", length = 8)
    String cep;

    @Column(name = "uf", length = 2)
    String uf;

    @Column(name = "municipio", length = 80)
    String municipio;

    @Column(name = "inscricao_estadual", length = 10)
    String inscricaoEstadual;

    @Column(name = "telefone", length = 15)
    String telefone;

    @Column(name = "cnae")
    String cnae;

    @Column(name = "inicio_atividade")
    LocalDate inicioAtividade;
}
