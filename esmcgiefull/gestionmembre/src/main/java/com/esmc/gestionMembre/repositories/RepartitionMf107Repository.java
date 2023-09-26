package com.esmc.gestionMembre.repositories;

import com.esmc.gestionMembre.entities.RepartitionMf107;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepartitionMf107Repository extends JpaRepository<RepartitionMf107, Long> {
    @Query("SELECT a FROM RepartitionMf107 a WHERE a.codeMembre = :x")
    List<RepartitionMf107> passifsMCNPmembreFondateurRepartitionMf107 (@Param("x") String code);


    @Query("SELECT a FROM RepartitionMf107 a WHERE trim(lower(a.codeMembre)) = trim(lower(:x))")
    List<RepartitionMf107> getRepartitionMf107ByCode (@Param("x") String code);
}
