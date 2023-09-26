package com.esmc.gestionAchatFranchise.repositories;

import com.esmc.gestionAchatFranchise.entities.ZoneMonnetaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZoneMonetaireRepository extends JpaRepository<ZoneMonnetaire, Long> {
    @Query("select zf from ZoneMonnetaire zf where  zf.continent.id= :x")
    public List<ZoneMonnetaire> findZoneMonnetaireByContinent(@Param("x") Long id);
    @Query(value = "select count(p) from Pays p where  p.zoneMonnetaire.id= :x")
    public int countPaysByZoneMonetaire(@Param("x") Long id);
    @Query(value = "select count(z.id) from zone_monnetaire z where z.id_continent=:x", nativeQuery = true)
    int getCountByContinent(@Param("x")  Long id);

    @Query("select count(zf) from ZoneMonnetaire zf where  zf.continent.id= :x and zf.isBuy=false")
    int getCountFreeZoneMonnetaireByContinent(@Param("x")  Long idFranchise);

    @Query("select count(zf) from ZoneMonnetaire zf where  zf.continent.id= :x and zf.isBuy=true")
    int getCountBuyZoneMonnetaireByContinent(@Param("x")  Long idFranchise);
}
