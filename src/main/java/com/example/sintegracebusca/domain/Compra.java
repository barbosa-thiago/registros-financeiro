package com.example.sintegracebusca.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@Getter
@Setter
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Compra extends BaseEntity {

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

    @OneToMany(fetch = FetchType.LAZY)
    List<Pagamento> pagamentos = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "compra")
    List<Agendamento> agendamentos = new ArrayList<>();

    @Column(name = "agendado")
    Boolean agendado;

    @OrderBy("id asc")
    @ToString.Exclude
    @Builder.Default
    @OneToMany(mappedBy = "compra", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Produto> produtos = new ArrayList<>();

    public Boolean isPago() {
        return this.pagamentos.stream().mapToDouble(Pagamento::getValor).sum() == this.valor;
    }

    public Boolean getAgendado() {
        if(isNull(this.agendamentos)) return false;
        return !this.agendamentos.isEmpty();
    }

}
