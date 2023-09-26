package com.esmc.gestionAchatFranchise.repositories;

import com.esmc.gestionAchatFranchise.entities.ChaineValeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChaineValeurRepository extends JpaRepository<ChaineValeur, Long> {

    @Query("select cv from  ChaineValeur cv where  cv.detailDestination.id= :x")
    public List<ChaineValeur> findChaineValeurByDetailDestination(@Param("x") Long id);

}
