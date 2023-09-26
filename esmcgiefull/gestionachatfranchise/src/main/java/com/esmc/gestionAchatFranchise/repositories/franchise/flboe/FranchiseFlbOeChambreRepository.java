package com.esmc.gestionAchatFranchise.repositories.franchise.flboe;

import com.esmc.gestionAchatFranchise.entities.felm.flbOe.FlbOeChambre;
import com.esmc.gestionAchatFranchise.entities.franchise.flbOe.FranchiseFlbOeChambre;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FranchiseFlbOeChambreRepository extends org.springframework.data.jpa.repository.JpaRepository<FranchiseFlbOeChambre, Long> {
    @Query("select flbOe from FranchiseFlbOeChambre flbOe " +
            "where flbOe.flbOeChambre.id = :y and flbOe.idCible=:x")
    List<FranchiseFlbOeChambre> getByTypeCibleElement(@Param("x") Long cibler, @Param("y")  Long idFranchise);

    @Query("select flbOe from FranchiseFlbOeChambre flbOe " +
            "where flbOe.flbOeChambre.id = :y and flbOe.idIndicateur=:x")
    List<FranchiseFlbOeChambre> getByTypeIndicateurElement(@Param("x") Long indicateur, @Param("y") Long idFranchise);

    @Query("select count(fcd) from FranchiseFlbOeChambre fcd where  fcd.idCible= :x and fcd.isBuy=true")
    int getCountFreeFlbChildFlbOseCible(@Param("x") Long cible);

    @Query("select count(fcd) from FranchiseFlbOeChambre fcd where  fcd.idCible= :x and fcd.isBuy=false")
    int getCountBuyFlbChildFlbOseCible(@Param("x") Long cible);
    @Query("select count(fcd) from FranchiseFlbOeChambre fcd where  fcd.idCible= :x and fcd.isBuy=true")
    int getCountFreeFlbChildFlbOseIndicateur(@Param("x") Long indicateur);

    @Query("select count(fcd) from FranchiseFlbOeChambre fcd where  fcd.idCible= :x and fcd.isBuy=false")
    int getCountBuyFlbChildFlbOseIndicateur(@Param("x") Long indicateur);
}