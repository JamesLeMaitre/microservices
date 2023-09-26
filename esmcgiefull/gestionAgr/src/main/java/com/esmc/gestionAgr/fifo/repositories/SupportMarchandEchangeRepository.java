package com.esmc.gestionAgr.fifo.repositories;


import com.esmc.gestionAgr.fifo.entities.SupportMarchandEnchage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SupportMarchandEchangeRepository extends JpaRepository<SupportMarchandEnchage, Long> {
    Optional<SupportMarchandEnchage> findById(Long id);
}
