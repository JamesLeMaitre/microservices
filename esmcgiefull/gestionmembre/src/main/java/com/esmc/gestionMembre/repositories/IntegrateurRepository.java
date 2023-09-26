package com.esmc.gestionMembre.repositories;

import com.esmc.gestionMembre.entities.Integrateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IntegrateurRepository extends JpaRepository<Integrateur, Long> {
    @Query("SELECT a FROM Integrateur a WHERE a.codeMembre = :x")
   public Integrateur passifsESMCSARLUIntegrateur(@Param("x") String code);

    @Query("SELECT a FROM Integrateur a WHERE a.codeMembre = :x")
    public List<Integrateur> listpassifsIntegrateur(@Param("x") String code);


    @Query("SELECT a FROM Integrateur a WHERE trim(lower(a.codeMembre)) = trim(lower(:x))")
    public List<Integrateur> findIntegrateurByCodeMembre(@Param("x") String code);


}
