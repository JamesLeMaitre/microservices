package com.esmc.gestionAchatFranchise.repositories.franchise.fill;

import com.esmc.gestionAchatFranchise.entities.felm.fill.FillInstitution;
import com.esmc.gestionAchatFranchise.entities.franchise.fill.FranchiseFillChaineValeur;
import com.esmc.gestionAchatFranchise.entities.franchise.fill.FranchiseFillInstitution;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FranchiseFillInstitutionRepository extends org.springframework.data.jpa.repository.JpaRepository<FranchiseFillInstitution, Long> {
    @Query("select fi from FranchiseFillInstitution fi " +
            "where fi.fillInstitution.id = :y and fi.idCible=:x")
    List<FranchiseFillInstitution> getByTypeCibleElement(@Param("x") Long cible, @Param("y")  Long idFranchise);

    @Query("select fi from FranchiseFillInstitution fi " +
            "where fi.fillInstitution.id = :y and fi.idIndicateur=:x")
    List<FranchiseFillInstitution> getByTypeIndicateurElement(@Param("x") Long indicateur, @Param("y") Long idFranchise);

    @Query("select count(fcd) from FranchiseFillInstitution fcd where  fcd.idCible= :x and fcd.isBuy=true")
    int getCountFreeFlbChildFlbOseCible(@Param("x") Long idCible);

    @Query("select count(fcd) from FranchiseFillInstitution fcd where  fcd.idCible= :x and fcd.isBuy=false")
    int getCountBuyFlbChildFlbOseCible(@Param("x") Long cible);

    @Query("select count(fcd) from FranchiseFillInstitution fcd where  fcd.idIndicateur= :x and fcd.isBuy=true")
    int getCountFreeFlbChildFlbOseIndicateur(@Param("x") Long indicateur);

    @Query("select count(fcd) from FranchiseFillInstitution fcd where  fcd.idIndicateur= :x and fcd.isBuy=false")
    int getCountBuyFlbChildFlbOseIndicateur(@Param("x") Long indicateur);
}
