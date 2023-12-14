package com.example.sintegracebusca.repository;

import com.example.sintegracebusca.domain.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
    ApplicationUser findByUsername(String username);
}