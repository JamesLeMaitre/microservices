package com.esmc.gestionAvr.repositories;

import com.esmc.gestionAvr.entities.Vague;
import com.esmc.gestionAvr.entities.VagueHistory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VagueHistoryRepo  extends JpaRepository<VagueHistory, Long> {
    Vague getVagueHistoryById(Long id);

    VagueHistory findTopByOrderByIdDesc();
}