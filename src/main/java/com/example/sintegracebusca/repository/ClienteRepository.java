package com.example.sintegracebusca.repository;

import com.example.sintegracebusca.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
