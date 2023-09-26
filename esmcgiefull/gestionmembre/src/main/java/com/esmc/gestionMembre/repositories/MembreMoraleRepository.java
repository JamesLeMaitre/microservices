package com.esmc.gestionMembre.repositories;

import com.esmc.gestionMembre.entities.Membre;
import com.esmc.gestionMembre.entities.MembreMorale;
import com.esmc.gestionMembre.entities.Physique;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MembreMoraleRepository extends JpaRepository<MembreMorale, Long> {

    @Query(value = "SELECT m FROM MembreMorale m WHERE m.codeMembreMorale = :x")
    MembreMorale ESMCSARLUmembreFondateurMembreMorale(@Param("x") String code);

    @Query(value = "SELECT m FROM MembreMorale m where m.raisonSociale=:x")
    List<MembreMorale> listEsmcMorale(@Param("x") String searchWords);

    @Query("SELECT p FROM MembreMorale p where p.dateIdentification=:y or p.raisonSociale=:y or p.numRegistreMembre=:y")
    Page<MembreMorale> searchByAttributeDateLieuNaiss(@Param("y") String y, Pageable pageable);



    /**
     * @author Amemorte9
     * @param codeMembreMorale
     */


    @Query("SELECT m from MembreMorale m where trim(lower(m.codeMembreMorale)) = trim(lower(:x)) or trim(lower(m.bpMembre)) = trim(lower(:y))")
    MembreMorale getMembreByCodeMembreMoraleOrNomMembre (@Param("x") String codeMembreMorale);
}
