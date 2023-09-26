package com.esmc.gestionMembre.repositories;

import com.esmc.gestionMembre.entities.CompteCredit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompteCreditRepository extends JpaRepository<CompteCredit, Long> {
    @Query("SELECT a FROM CompteCredit a WHERE a.codeMembre = :x")
    CompteCredit passifsESMCSARLUCompteCredit(@Param("x") String code);

    @Query("SELECT a from CompteCredit a where a.codeMembre = :x and a.codeProduit ='RPGnr' ")
    public List<CompteCredit> passifsEsmcSarluRpgNr(@Param("x") String code);

    @Query("SELECT a from CompteCredit a where a.codeMembre = :x and a.codeProduit ='RPGr' ")
    public List<CompteCredit> passifsEsmcSarluRpgr(@Param("x") String code);

    @Query("SELECT a from CompteCredit a where a.codeMembre = :x and a.codeProduit ='Inr' ")
    public List<CompteCredit> passifsEsmcSarluInr(@Param("x") String code);



    @Query("SELECT a from CompteCredit a where trim(lower(a.codeMembre)) =trim(lower(:x)) ")
    public List<CompteCredit> getCompteCreditByCode(@Param("x") String code);
}
