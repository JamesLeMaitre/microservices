package com.esmc.gestionAvr.repositories;

import com.esmc.gestionAvr.entities.Ksu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KsuRepository extends JpaRepository<Ksu,Long> {
    Ksu findKsuById(Long id);
}
