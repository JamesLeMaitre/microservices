package com.esmc.gestionte.repositories;

import com.esmc.gestionte.entities.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;


public interface PromotionRepository extends JpaRepository<Promotion, Long> {

    @Query("select p from Promotion p where p.dateDebut = :x and p.dateFin = :y")
    public Promotion getPromotionByDateDebutAndDateFin(@Param("x") Date dateDebut, @Param("y") Date dateFin);

    @Query("select p from Promotion p where p.etat = true")
    public Promotion getPromotionByEtatTrue();

}
