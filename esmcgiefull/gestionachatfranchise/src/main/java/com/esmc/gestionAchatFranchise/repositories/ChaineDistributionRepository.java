package com.esmc.gestionAchatFranchise.repositories;

import com.esmc.gestionAchatFranchise.entities.ChaineDistribution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChaineDistributionRepository extends JpaRepository<ChaineDistribution, Long> {
    @Query("select cf from ChaineDistribution cf where  cf.chaineValeur.id= :x")
    public List<ChaineDistribution> findChaineDistributionByChaineValeur(@Param("x") Long id);

}
