package com.esmc.gestionAgr.fifo.repositories;


import com.esmc.gestionAgr.fifo.entities.Ksu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KsuRepository extends JpaRepository<Ksu, Long> {
    Optional<Ksu> findFirstByCodeKsu(String code);

    Optional<Ksu> findFirstById(Long ksu);

    Ksu findKsuById(Long id);
}
