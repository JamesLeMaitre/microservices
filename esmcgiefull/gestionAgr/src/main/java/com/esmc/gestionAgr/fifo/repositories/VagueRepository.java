package com.esmc.gestionAgr.fifo.repositories;


import com.esmc.gestionAgr.fifo.entities.Vague;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VagueRepository extends JpaRepository<Vague, Long> {

    Vague getVagueById(Long nextVague);

    @Query("select v from Vague v where v.status = true")
    Vague getActiveVague(PageRequest of);

    Vague findVagueByStatus(Boolean status);
}
