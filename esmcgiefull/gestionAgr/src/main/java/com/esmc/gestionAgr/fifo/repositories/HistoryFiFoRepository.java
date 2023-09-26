package com.esmc.gestionAgr.fifo.repositories;


import com.esmc.gestionAgr.fifo.entities.HistoryFiFo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryFiFoRepository extends JpaRepository<HistoryFiFo, Long> {
}
