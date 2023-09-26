package com.esmc.gestionAchatFranchise.repositories.franchise.fill;

import com.esmc.gestionAchatFranchise.entities.felm.fill.FillChaineDistribution;
import com.esmc.gestionAchatFranchise.entities.franchise.fill.FranchiseFillChaineDistribution;
import com.esmc.gestionAchatFranchise.entities.franchise.flbOe.FranchiseFlbOeChaineDistribution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FranchiseFillChaineDistributionRepository extends JpaRepository<FranchiseFillChaineDistribution, Long > {
    @Query("select fcd from FranchiseFillChaineDistribution fcd, FranchiseFillChaineValeur fcv, FranchiseFillInstitution fi " +
            "where fcd.fillChaineDistribution.id = :y and fcd.idChaineValeur = fcv.id and fcv.idInstitution= fi.id and fi.idCible=:x")
    List<FranchiseFillChaineDistribution> getByTypeCibleElement(@Param("x") Long cible,@Param("y")  Long idFranchise);

    @Query("select fcd from FranchiseFillChaineDistribution fcd, FranchiseFillChaineValeur fcv, FranchiseFillInstitution fi " +
            "where fcd.fillChaineDistribution.id = :y and fcd.idChaineValeur = fcv.id and fcv.idInstitution= fi.id and fi.idIndicateur=:x")
    List<FranchiseFillChaineDistribution> getByTypeIndicateurElement(@Param("x") Long indicateur, @Param("y") Long idFranchise);
    @Query("select count(fcd) from FranchiseFillChaineDistribution fcd where  fcd.idChaineValeur= :x and fcd.isBuy=false")
    int getCountFillChaineDistributionByFillChaineValeur(@Param("x") Long idFranchise);

    @Query("select count(fcd) from FranchiseFillChaineDistribution fcd where  fcd.idChaineValeur= :x and fcd.isBuy=true")
    int getCountFillChaineDistributionBuyByFillChaineValeur(@Param("x") Long idFranchise);

    @Query("select fcd from FranchiseFillChaineDistribution fcd where fcd.isBuy=true")
    List<FranchiseFillChaineDistribution> distributor_fill_available();

}
