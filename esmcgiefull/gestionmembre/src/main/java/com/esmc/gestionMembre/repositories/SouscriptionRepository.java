package com.esmc.gestionMembre.repositories;

import com.esmc.gestionMembre.entities.Membre;
import com.esmc.gestionMembre.entities.Souscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SouscriptionRepository extends JpaRepository<Souscription, Long>  {
    @Query("SELECT a FROM Souscription a WHERE a.souscriptionId = :x and a.publier = 3 and a.souscriptionProgramme like 'CMFH'")
    public Souscription passifsESMCSARLUSouscription(@Param("x") Long id);


    @Query("SELECT a FROM Souscription a WHERE trim(lower(a.souscriptionNom)) =trim(lower( :x)) or trim(lower(a.codeActivite)) =trim(lower( :x))  ")
    public List<Souscription> findSouscriptionByCodeOrName(@Param("x") String codeorName);



}
