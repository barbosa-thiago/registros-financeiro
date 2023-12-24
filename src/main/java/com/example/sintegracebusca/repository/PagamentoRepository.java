package com.example.sintegracebusca.repository;

import com.example.sintegracebusca.domain.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
    @Query(value = "select p.* from PAGAMENTO as p " +
        "where p.created_at >= :primeiroDiaPesquisa and p.created_at <= :ultimoDiaPesquisa " +
        "order by p.created_at desc", nativeQuery = true)
    List<Pagamento> findByDataPagamentoMonth(LocalDate primeiroDiaPesquisa, LocalDate ultimoDiaPesquisa);
}
