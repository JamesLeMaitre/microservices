package com.esmc.gestionAchatFranchise.repositories;

import com.esmc.gestionAchatFranchise.entities.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
    @Query("select rg from Region rg where  rg.pays.id= :x")
    public List<Region> findRegionByPays(@Param("x") Long id);

    @Query("select count(rg) from Region rg where  rg.pays.id= :x and rg.isBuy=false")
    int getCountFreeRegionByPays(@Param("x") Long idFranchise);

    @Query("select count(rg) from Region rg where  rg.pays.id= :x and rg.isBuy=true")
    int getCountBuyRegionByPays(@Param("x")  Long idFranchise);
}
