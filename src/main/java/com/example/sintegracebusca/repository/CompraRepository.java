package com.example.sintegracebusca.repository;

import com.example.sintegracebusca.domain.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface CompraRepository extends JpaRepository<Compra, Long> {

    @Query(value = "select c.* from COMPRA as c " +
        "where c.data_compra >= :primeiroDiaPesquisa and c.data_compra <= :ultimoDiaPesquisa " +
        "order by c.data_compra desc", nativeQuery = true)
    List<Compra> findByDataCompraMonth(LocalDate primeiroDiaPesquisa, LocalDate ultimoDiaPesquisa);
}
