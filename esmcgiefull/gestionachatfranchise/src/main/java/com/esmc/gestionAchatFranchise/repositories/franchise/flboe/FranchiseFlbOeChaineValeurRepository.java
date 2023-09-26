package com.esmc.gestionAchatFranchise.repositories.franchise.flboe;

import com.esmc.gestionAchatFranchise.entities.franchise.flbOe.FranchiseFlbOeChaineValeur;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FranchiseFlbOeChaineValeurRepository extends org.springframework.data.jpa.repository.JpaRepository<FranchiseFlbOeChaineValeur, Long> {

    @Query("select fcv from  FranchiseFlbOeChaineValeur fcv, FranchiseFlbOeChambre fi " +
            "where fcv.flbOeChaineValeur.id = :y and fcv.idChambre= fi.id and fi.idCible=:x")
    List<FranchiseFlbOeChaineValeur> getByTypeCibleElement(@Param("x") Long cible, @Param("y")  Long idFranchise);

    @Query("select fcv from  FranchiseFlbOeChaineValeur fcv, FranchiseFlbOeChambre fi " +
            "where fcv.flbOeChaineValeur.id = :y and fcv.idChambre= fi.id and fi.idIndicateur=:x")
    List<FranchiseFlbOeChaineValeur> getByTypeIndicateurElement(@Param("x") Long indicateur, @Param("y") Long idFranchise);

    @Query("select count(fcv) from FranchiseFlbOeChaineValeur fcv where  fcv.idChambre= :x and fcv.isBuy=false")
    int getCountFlbOeChaineValeurByFlbOeChambre(@Param("x") Long idFranchise);

    @Query("select count(fcv) from FranchiseFlbOeChaineValeur fcv where  fcv.idChambre= :x and fcv.isBuy=true")
    int getCountFlbOeChaineValeurBuyByFlbOeChambre(@Param("x") Long idFranchise);



}
