package com.esmc.gestionContrat.repositories;

import com.esmc.gestionContrat.entities.Contrat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContratRepository extends JpaRepository<Contrat, Long> {
    @Query("Select c from Contrat c where c.typeContrat.id = :x")
    public List<Contrat> listeContratByTypeContrat(@Param("x") Long id);


}
