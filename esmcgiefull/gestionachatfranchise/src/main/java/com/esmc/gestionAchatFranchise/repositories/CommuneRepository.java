package com.esmc.gestionAchatFranchise.repositories;

import com.esmc.gestionAchatFranchise.entities.Commune;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommuneRepository extends JpaRepository<Commune, Long> {

    @Query("select co from Commune co where  co.prefecture.id= :x")
    public List<Commune> findCommuneByPrefecture(@Param("x") Long id);
    @Query("select count(co) from Commune co where  co.prefecture.id= :x and co.isBuy=false")
    int getCountFreeCommuneByPrefecture(@Param("x") Long idFranchise);

    @Query("select count(co) from Commune co where  co.prefecture.id= :x and co.isBuy=true")
    int getCountBuyCommuneByPrefecture(@Param("x")  Long idFranchise);
}
