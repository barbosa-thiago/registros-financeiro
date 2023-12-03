package com.example.sintegracebusca.repository;

import com.example.sintegracebusca.domain.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    @Query(value = "select a.* from AGENDAMENTO as a " +
        "where a.data_pagamento >= :primeiroDiaPesquisa and a.data_pagamento <= :ultimoDiaPesquisa", nativeQuery = true)
    List<Agendamento> findByDataPagamentoMonth(LocalDate primeiroDiaPesquisa, LocalDate ultimoDiaPesquisa);
}
