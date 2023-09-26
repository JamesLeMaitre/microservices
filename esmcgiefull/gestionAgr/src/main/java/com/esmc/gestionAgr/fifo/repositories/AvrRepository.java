package com.esmc.gestionAgr.fifo.repositories;


import com.esmc.gestionAgr.fifo.entities.Avr;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvrRepository extends JpaRepository<Avr, Long> {
    List<Avr> findAllByDetailAgr(Long detailAgr);
}
