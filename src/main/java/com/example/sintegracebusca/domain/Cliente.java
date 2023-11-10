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
}
