package com.esmc.gestionMembre.repositories;

import com.esmc.gestionMembre.entities.AncienEscompte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AncienEscompteRepository extends JpaRepository<AncienEscompte, Long> {
    @Query("SELECT a FROM AncienEscompte a WHERE a.codeMembre = :x")
    AncienEscompte passifsMCNPAncienEscompte(@Param("x") String code);


    @Query("SELECT a FROM AncienEscompte a WHERE trim(lower(a.codeMembre))= trim(lower(:x))")
    List<AncienEscompte> findAncienEscompteByCode(@Param("x") String code);
}
