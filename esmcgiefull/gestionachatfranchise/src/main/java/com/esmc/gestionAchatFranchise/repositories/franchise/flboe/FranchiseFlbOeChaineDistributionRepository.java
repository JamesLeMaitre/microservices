package com.esmc.gestionAchatFranchise.repositories.franchise.flboe;

import com.esmc.gestionAchatFranchise.entities.felm.flbOe.FlbOeChaineDistribution;
import com.esmc.gestionAchatFranchise.entities.franchise.fill.FranchiseFillChaineDistribution;
import com.esmc.gestionAchatFranchise.entities.franchise.flbOe.FranchiseFlbOeChaineDistribution;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FranchiseFlbOeChaineDistributionRepository extends org.springframework.data.jpa.repository.JpaRepository<FranchiseFlbOeChaineDistribution, Long> {

    @Query("select fcd from FranchiseFlbOeChaineDistribution fcd, FranchiseFlbOeChaineValeur fcv, FranchiseFlbOeChambre fi " +
            "where fcd.flbOeChaineDistribution.id = :y and fcd.idChaineValeur = fcv.id and fcv.idChambre= fi.id and fi.idCible=:x")
    List<FranchiseFlbOeChaineDistribution> getByTypeCibleElement(@Param("x") Long cible, @Param("y")  Long idFranchise);

    @Query("select fcd from FranchiseFlbOeChaineDistribution fcd, FranchiseFlbOeChaineValeur fcv, FranchiseFlbOeChambre fi " +
            "where fcd.flbOeChaineDistribution.id = :y and fcd.idChaineValeur = fcv.id and fcv.idChambre= fi.id and fi.idIndicateur=:x")
    List<FranchiseFlbOeChaineDistribution> getByTypeIndicateurElement(@Param("x") Long indicateur, @Param("y") Long idFranchise);

    @Query("select count(fcd) from FranchiseFlbOeChaineDistribution fcd where  fcd.idChaineValeur= :x and fcd.isBuy=false")
    int getCountFlbOeChaineDistributionByFlbOeChaineValeur(@Param("x") Long idFranchise);
    @Query("select fcd from FranchiseFlbOeChaineDistribution fcd where fcd.isBuy=true")
    List<FranchiseFlbOeChaineDistribution> distributor_flboe_available();
}