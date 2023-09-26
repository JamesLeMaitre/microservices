package com.esmc.gestionAchatFranchise.repositories;

import com.esmc.gestionAchatFranchise.entities.Prefecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrefectureRepository extends JpaRepository<Prefecture, Long> {
    @Query("select pr from Prefecture pr where  pr.region.id= :x")
    public List<Prefecture> findPrefectureByRegion(@Param("x") Long id);

    @Query("select count(pr) from Prefecture pr where  pr.region.id= :x and pr.isBuy=false")
    int getCountFreePrefectureByRegion(@Param("x")Long idFranchise);

    @Query("select count(pr) from Prefecture pr where  pr.region.id= :x and pr.isBuy=true")
    int getCountBuyPrefectureByRegion(@Param("x")  Long idFranchise);
}
