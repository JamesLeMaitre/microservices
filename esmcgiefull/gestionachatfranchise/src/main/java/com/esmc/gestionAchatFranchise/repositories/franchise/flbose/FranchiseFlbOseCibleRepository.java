package com.esmc.gestionAchatFranchise.repositories.franchise.flbose;

import com.esmc.gestionAchatFranchise.entities.franchise.flbOse.FranchiseFlbOseCible;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FranchiseFlbOseCibleRepository extends org.springframework.data.jpa.repository.JpaRepository<FranchiseFlbOseCible, Long> {
    @Query("select fc from  FranchiseFlbOseCible fc, FranchiseFlbOseAgenceOdd fag " +
            "where fc.flbOseCible.id = :x and fc.idAgenceOdd=fag.id and fag.idCanton=:y")
    List<FranchiseFlbOseCible> getByTypeCantonElement(@Param("x") Long idFranchise, @Param("y") Long idCanton);

    @Query("select count(fc) from FranchiseFlbOseCible fc where  fc.idAgenceOdd=:x and fc.isBuy=false")
    int getCountFlbOseCibleByFlbOseAgenceOdd(@Param("x") Long idFranchise);

}