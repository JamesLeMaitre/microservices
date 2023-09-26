package com.esmc.gestionAchatFranchise.repositories;

import com.esmc.gestionAchatFranchise.entities.Pays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaysRepository extends JpaRepository<Pays, Long> {
    @Query("select ps from Pays ps where  ps.zoneMonnetaire.id= :x")
    public List<Pays> findPaysByZoneMonnetaire(@Param("x") Long id);

    @Query(value = "select count(p.id) from pays p where p.id_zone_monnetaire=:x", nativeQuery = true)
    int getCountByZoneMonetaire(@Param("x")  Long id);

    @Query(value = "select count(p.id) from pays p, zone_monnetaire z where p.id_zone_monnetaire=z.id and z.id_continent=:x", nativeQuery = true)
    int getCountByContinent(@Param("x")  Long id);

    @Query("select count(ps) from Pays ps where  ps.zoneMonnetaire.id= :x and ps.isBuy=false")
    int getCountFreePaysByZoneMonnetaire(@Param("x") Long idFranchise);
    @Query("select count(ps) from Pays ps where  ps.zoneMonnetaire.id= :x and ps.isBuy=true")
    int getCountBuyPaysByZoneMonnetaire(@Param("x")  Long idFranchise);
}
