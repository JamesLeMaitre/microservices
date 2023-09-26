package com.esmc.gestionAchatFranchise.repositories.franchise.flbose;

import com.esmc.gestionAchatFranchise.entities.franchise.flbOse.FranchiseFlbOseAgenceOdd;
import com.esmc.gestionAchatFranchise.entities.franchise.flbOse.FranchiseFlbOseIndicateur;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FranchiseFlbOseAgenceOddRepository extends org.springframework.data.jpa.repository.JpaRepository<FranchiseFlbOseAgenceOdd, Long> {
        @Query("select fag from FranchiseFlbOseAgenceOdd fag " +
                "where fag.flbOseAgenceOdd.id=:x and fag.idCanton=:y")
        List<FranchiseFlbOseAgenceOdd> getByTypeCantonElement(@Param("x") Long idFranchise, @Param("y") Long idCanton);
}
