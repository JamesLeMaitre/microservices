package com.esmc.gestionAgr.fifo.repositories;


import com.esmc.gestionAgr.fifo.entities.TerminalEchange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TerminalEchangeRepository extends JpaRepository<TerminalEchange, Long> {
    TerminalEchange findTerminalEchangeByDetailAgr(Long id);
}
