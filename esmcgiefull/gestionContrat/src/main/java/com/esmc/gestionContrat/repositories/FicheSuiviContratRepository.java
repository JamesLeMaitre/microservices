package com.esmc.gestionContrat.repositories;


import com.esmc.gestionContrat.entities.FicheSuiviContrat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FicheSuiviContratRepository extends JpaRepository<FicheSuiviContrat, Long> {
    @Query("Select f from FicheSuiviContrat f where f.contrat.id = :x")
    public List<FicheSuiviContrat> listeFicheSuiviContratByContrat(@Param("x") Long id);
}
