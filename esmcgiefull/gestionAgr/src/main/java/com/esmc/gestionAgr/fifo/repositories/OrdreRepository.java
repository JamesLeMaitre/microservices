package com.esmc.gestionAgr.fifo.repositories;


import com.esmc.gestionAgr.fifo.entities.Ordre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdreRepository extends JpaRepository<Ordre, Long> {
}
