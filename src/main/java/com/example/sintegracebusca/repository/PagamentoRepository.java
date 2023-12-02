package com.example.sintegracebusca.repository;

import com.example.sintegracebusca.domain.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
