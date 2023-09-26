package com.esmc.gestionAvr.putonchain.repositories;

import com.esmc.gestionAvr.putonchain.entities.CenterType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CenterTypeRepository extends JpaRepository<CenterType,Long> {
    Optional<CenterType> findById(Long id);
}
