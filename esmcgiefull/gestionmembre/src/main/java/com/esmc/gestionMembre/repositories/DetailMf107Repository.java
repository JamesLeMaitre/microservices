package com.esmc.gestionMembre.repositories;

import com.esmc.gestionMembre.entities.DetailMf107;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailMf107Repository extends JpaRepository<DetailMf107, Long> {
    @Query("SELECT a FROM DetailMf107 a WHERE a.codeMembre = :x")
    DetailMf107 passifsMembreFondateurDetailMf107(@Param("x") String code);



    @Query("SELECT a FROM DetailMf107 a WHERE trim(lower(a.codeMembre)) = trim(lower(:x))")
    List<DetailMf107> findDetailMf107ByCode(@Param("x") String code);

}
