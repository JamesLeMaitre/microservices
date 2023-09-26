package com.esmc.gestionAgr.fifo.repositories;


import com.esmc.gestionAgr.fifo.entities.Extrant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExtrantRepository extends JpaRepository<Extrant, Long> {
}
