package com.esmc.gestionMembre.repositories;

import com.esmc.gestionMembre.entities.AncienEchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AncienEchangeRepository extends JpaRepository<AncienEchange, Long> {
    @Query("SELECT a FROM AncienEchange a WHERE a.codeMembre = :x")
    AncienEchange passifsMCNPAncienEchange(@Param("x") String code);

    @Query("SELECT coalesce(sum(a.montantEchange),0) FROM AncienEchange a WHERE a.codeMembre = :x and a.typeEchange like 'NB/NB' and a.codeProduit like 'GCP'")
    public Double sommepassifsMCNPAncienEchangeNbContreNb(@Param("x") String code);

    @Query("SELECT coalesce(sum(a.montantEchange),0) FROM AncienEchange a WHERE a.codeMembre = :x and a.typeEchange like 'NB/NN' and a.codeProduit like 'GCP'")
    public Double sommepassifsMCNPAncienEchangeNbContreNn(@Param("x") String code);

    @Query("SELECT a FROM AncienEchange a WHERE a.codeMembre = :x and a.typeEchange like 'NB/NB' and a.codeProduit like 'GCP'")
    public List<AncienEchange> listpassifsMCNPAncienEchangeNbContreNb(@Param("x") String code);

    @Query("SELECT a FROM AncienEchange a WHERE a.codeMembre = :x and a.typeEchange like 'NB/NN' and a.codeProduit like 'GCP'")
    public List<AncienEchange>  listpassifsMCNPAncienEchangeNbContreNn(@Param("x") String code);

    @Query("SELECT a FROM AncienEchange a WHERE a.codeMembre = :x and a.typeEchange like 'NR/NN' and a.codeProduit like '%CNCS' ")
    public List<AncienEchange> listCNCS(@Param("x") String code);



    @Query("SELECT a FROM AncienEchange a WHERE trim(lower(a.codeMembre))= trim(lower(:x))  ")
    public List<AncienEchange> findAncienEchangeByCode(@Param("x") String code);

}
