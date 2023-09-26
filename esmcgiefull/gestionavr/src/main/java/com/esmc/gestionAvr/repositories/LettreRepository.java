package com.esmc.gestionAvr.repositories;

import com.esmc.gestionAvr.entities.Lettre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LettreRepository extends JpaRepository<Lettre, Long> {
}
