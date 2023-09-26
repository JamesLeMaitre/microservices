package com.esmc.gestionAvr.repositories;

import com.esmc.gestionAvr.entities.AffectationSMAvr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AffectationSMAvrRepository extends JpaRepository<AffectationSMAvr, Long> {


    @Query("SELECT a FROM AffectationSMAvr a WHERE a.typeSmAvr.id=:x")
    public List<AffectationSMAvr> AffSMAvrByTypeSmavr (@Param("x") Long id);

    @Query("SELECT a FROM AffectationSMAvr a WHERE a.echange.id=:x")
    public List<AffectationSMAvr> AffSMAvrByEchange (@Param("x") Long id);

    @Query("SELECT a FROM AffectationSMAvr a WHERE a.typeAvr.id=:x")
    public List<AffectationSMAvr> AffSMAvrByTypeAvr (@Param("x") Long id);

    @Query("SELECT a FROM AffectationSMAvr a WHERE a.typeAvr.id=:x and a.echange.id=:y and a.etat = true")
    public List<AffectationSMAvr> AffSMAvrByTypeAvrEchangeSMAVR (@Param("x") Long id, @Param("y") Long id2);

//    @Query("SELECT a FROM AffectationSMAvr a WHERE a.echange.libelle = :x and a.typeAvr.libelle = :y ")
//    public List<AffectationSMAvr> getSmavr(@Param("x") String typeSmAvr, @Param("y") String typeAvr);


}
