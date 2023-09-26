package com.esmc.gestionMembre.repositories;

import com.esmc.gestionMembre.entities.AncienCompteCredit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AncienCompteCreditRepository extends JpaRepository<AncienCompteCredit, Long> {
    @Query("SELECT a FROM AncienCompteCredit a WHERE a.codeMembre = :x")
    AncienCompteCredit passifsMCNPAncienCompteCredit(@Param("x") String code);

    @Query("select a from AncienCompteCredit a where a.codeMembre = :x and (a.codeProduit = 'CNCSr' OR a.codeProduit = 'CNCSnr')")
    List<AncienCompteCredit> listCNCS(@Param("x") String code);

    @Query("select a from AncienCompteCredit a where a.codeMembre = :x and (a.compteSource Like 'NR-TCNCS-%' OR a.compteSource Like 'NN-TCNCS-:%') ")
  //  @Query(value = "SELECT * FROM ancien_compte_credit WHERE code_membre = :x AND (compte_source = 'NR-TCNCS-%' OR compte_source = 'NN-TCNCS-%')", nativeQuery = true)
    List<AncienCompteCredit> listCNCSEchange(@Param("x") String code);

    @Query("select a from AncienCompteCredit a where a.codeMembre = :x and a.codeProduit like 'RPGr'")
    List<AncienCompteCredit> listRPGr(@Param("x") String code);

    @Query("select a from AncienCompteCredit a where a.codeMembre = :x and a.codeProduit like 'RPGnr'")
    List<AncienCompteCredit> listRPGnr(@Param("x") String code);

    @Query("select a from AncienCompteCredit a where a.codeMembre = :x and a.codeProduit like 'Ir'")
    List<AncienCompteCredit> listIr(@Param("x") String code);

    @Query("select a from AncienCompteCredit a where a.codeMembre = :x and a.codeProduit like 'Inr'")
    List<AncienCompteCredit> listInr(@Param("x") String code);




    @Query("select a from AncienCompteCredit a where trim(lower(a.codeMembre ))= trim(lower(:x))  ")
    List<AncienCompteCredit> findAncienCompteCreditByCode(@Param("x") String code);


}
