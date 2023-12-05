package com.example.sintegracebusca.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SequenceGenerator(name = "INC_COMPRA_ID", sequenceName = "GEN_COMPRA_ID", allocationSize = 1)
public class Compra implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "INC_COMPRA_ID")
    Long id;

    @Column(name = "valor", nullable = false)
    Double valor;

    @Column(name = "a_vista")
    Boolean aVista;

    @Column(name = "fornecedor", length = 150, nullable = false)
    String fornecedor;

    @Column(name = "descricao", length = 150)
    String descricao;

    @Column(name = "data_compra", nullable = false)
    LocalDate dataCompra = LocalDate.now();

    @Column(name = "pago")
    Boolean pago;

    @OneToMany
    Set<Pagamento> pagamentos = new HashSet<>();

    @OrderBy("id asc")
    @ToString.Exclude
    @Builder.Default
    @OneToMany(mappedBy = "compra", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Produto> produtos = new HashSet<>();

    public Boolean isPago() {
        return this.pagamentos.stream().mapToDouble(Pagamento::getValor).sum() == this.valor;
    }

}
