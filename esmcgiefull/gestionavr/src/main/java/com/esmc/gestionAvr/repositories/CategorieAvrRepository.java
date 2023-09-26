package com.esmc.gestionAvr.repositories;

import com.esmc.gestionAvr.entities.CategorieAvr;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategorieAvrRepository extends JpaRepository<CategorieAvr, Long> {
        Optional<CategorieAvr> findById(Long id);
}
