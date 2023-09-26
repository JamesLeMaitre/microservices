package com.esmc.gestionAvr.repositories;

import com.esmc.gestionAvr.entities.Fifo;
import com.esmc.gestionAvr.entities.Vague;
import com.esmc.gestionAvr.entities.VagueHistory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VagueRepo  extends JpaRepository<Vague, Long> {
    Vague getVagueById(Long id);
    Vague getVagueByLabel(String label);

    VagueHistory findTopByOrderByIdDesc();

    @Query("select v from Vague v where v.status = true")
    Vague getActiveVague(PageRequest of);

    @Query("select v from Vague v where v.idPromotion = :x ")
    List<Vague> getByPromotion(@Param("x") Long id);
}