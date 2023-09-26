package com.esmc.gestionMembre.repositories;

import com.esmc.gestionMembre.entities.BonNeutre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BonNeutreRepository extends JpaRepository<BonNeutre, Long> {
    @Query("SELECT a FROM BonNeutre a WHERE a.bonNeutreCodeMembre = :x")
    public BonNeutre passifsESMCSARLUBonNeutre(@Param("x") String code);



    @Query("SELECT a FROM BonNeutre a WHERE trim(lower(a.bonNeutreCodeMembre))= trim(lower(:x)) or  trim(lower(a.bonNeutreNom))= trim(lower(:x))  ")
    public List<BonNeutre> findBonNeutreByCode(@Param("x") String code);

}
