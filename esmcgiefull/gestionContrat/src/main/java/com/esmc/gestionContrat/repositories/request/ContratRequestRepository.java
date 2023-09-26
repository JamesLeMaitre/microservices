package com.esmc.gestionContrat.repositories.request;

import com.esmc.gestionContrat.entities.Contrat;
import com.esmc.gestionContrat.request.ContratRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContratRequestRepository extends JpaRepository<ContratRequest,Long> {
    @Query("SELECT c FROM ContratRequest c WHERE c.idDetailAgr = :x")
     List<ContratRequest> findByIdDetailsAgr(@Param("x") Long id);
}
