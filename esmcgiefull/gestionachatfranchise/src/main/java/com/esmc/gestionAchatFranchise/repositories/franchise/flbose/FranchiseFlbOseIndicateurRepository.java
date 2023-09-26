package com.esmc.gestionAchatFranchise.repositories.franchise.flbose;

import com.esmc.gestionAchatFranchise.entities.franchise.flbOse.FranchiseFlbOseCible;
import com.esmc.gestionAchatFranchise.entities.franchise.flbOse.FranchiseFlbOseIndicateur;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FranchiseFlbOseIndicateurRepository extends org.springframework.data.jpa.repository.JpaRepository<FranchiseFlbOseIndicateur, Long> {
    @Query("select fc from  FranchiseFlbOseIndicateur fc, FranchiseFlbOseAgenceOdd fag " +
            "where fc.flbOseIndicateur.id = :x and fc.idAgenceOdd=fag.id and fag.idCanton=:y")
    List<FranchiseFlbOseIndicateur> getByTypeCantonElement(@Param("x") Long idFranchise, @Param("y") Long idCanton);
    @Query("select count(fci) from FranchiseFlbOseIndicateur fci where  fci.idAgenceOdd=:x and fci.isBuy=false")
    int getCountFlbOseIndicateurByFlbOseAgenceOdd(@Param("x")  Long idFranchise);

}
