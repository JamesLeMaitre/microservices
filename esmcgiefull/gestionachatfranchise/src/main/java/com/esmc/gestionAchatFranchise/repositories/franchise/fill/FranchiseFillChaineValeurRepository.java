package com.esmc.gestionAchatFranchise.repositories.franchise.fill;

import com.esmc.gestionAchatFranchise.entities.felm.fill.FillChaineValeur;
import com.esmc.gestionAchatFranchise.entities.franchise.fill.FranchiseFillChaineDistribution;
import com.esmc.gestionAchatFranchise.entities.franchise.fill.FranchiseFillChaineValeur;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FranchiseFillChaineValeurRepository extends org.springframework.data.jpa.repository.JpaRepository<FranchiseFillChaineValeur, Long> {
    @Query("select fcv from  FranchiseFillChaineValeur fcv, FranchiseFillInstitution fi " +
            "where fcv.fillChaineValeur.id = :y and fcv.idInstitution= fi.id and fi.idCible=:x")
    List<FranchiseFillChaineValeur> getByTypeCibleElement(@Param("x") Long cible, @Param("y")  Long idFranchise);

    @Query("select fcv from  FranchiseFillChaineValeur fcv, FranchiseFillInstitution fi " +
            "where fcv.fillChaineValeur.id = :y and fcv.idInstitution= fi.id and fi.idIndicateur=:x")
    List<FranchiseFillChaineValeur> getByTypeIndicateurElement(@Param("x") Long indicateur, @Param("y") Long idFranchise);

    @Query("select count(fcv) from FranchiseFillChaineValeur fcv where  fcv.idInstitution= :x and fcv.isBuy=false")
    int getCountFillChaineValeurByFillInstitution(@Param("x") Long idFranchise);

    @Query("select count(fcv) from FranchiseFillChaineValeur fcv where  fcv.idInstitution= :x and fcv.isBuy=true")
    int getCountFillChaineValeurBuyByFillInstitution(@Param("x") Long idFranchise);

}
