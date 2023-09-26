package com.esmc.gestionMembre.repositories;

import com.esmc.gestionMembre.entities.Gcp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GcpRepository extends JpaRepository<Gcp, Long> {
    /*@Query("SELECT a FROM Gcp a WHERE a.codeMembre = :x")
    Gcp passifsESMCSARLUGcp(@Param("x") String code);*/

    @Query("SELECT a FROM Gcp a WHERE a.codeMembre = :x")
    List<Gcp> listpassifsESMCSARLUGcp(@Param("x") String code);



    @Query("SELECT a FROM Gcp a WHERE trim(lower(a.codeMembre)) =trim(lower( :x))")
    List<Gcp> getGcpByCode(@Param("x") String code);
}
