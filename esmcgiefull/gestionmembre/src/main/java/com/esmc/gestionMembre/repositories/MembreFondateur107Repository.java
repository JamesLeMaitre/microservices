package com.esmc.gestionMembre.repositories;

import com.esmc.gestionMembre.entities.MembreFondateur107;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MembreFondateur107Repository extends JpaRepository<MembreFondateur107, Long> {
    @Query("SELECT a FROM MembreFondateur107 a WHERE a.codeMembre = :x")
    MembreFondateur107 passifsMCNPmembreFondateurMembreFondateur107 (@Param("x") String code);



    @Query("SELECT a FROM MembreFondateur107 a WHERE trim(lower(a.codeMembre)) = trim(lower(:x)) or trim(lower(a.nom)) = trim(lower(:x))  or trim(lower(a.prenom)) = trim(lower(:x)) ")
    List<MembreFondateur107> findByMembreFondateur107Code (@Param("x") String code);
}
