package com.esmc.gestionMembre.repositories;

import com.esmc.gestionMembre.entities.MembreFondateur11000;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MembreFondateur11000Repository extends JpaRepository<MembreFondateur11000, Long> {

    @Query("SELECT a FROM MembreFondateur11000 a WHERE a.numBon = :x")
    MembreFondateur11000 passifsMCNPmembreFondateur11000(@Param("x") String code);



    @Query("SELECT a FROM MembreFondateur11000 a WHERE trim(lower(a.codeMembre)) =trim(lower(:x)) or trim(lower(a.nom)) =trim(lower(:x))")
    List<MembreFondateur11000> findByMembreFondateur11000CodeMembre(@Param("x") String code);


}
