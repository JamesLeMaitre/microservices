package com.esmc.gestionAchatFranchise.repositories.felm.flboe;

import com.esmc.gestionAchatFranchise.entities.felm.fill.FillChaineDistribution;
import com.esmc.gestionAchatFranchise.entities.felm.flbOe.FlbOeChaineDistribution;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FlbOeChaineDistributionRepository extends org.springframework.data.jpa.repository.JpaRepository<FlbOeChaineDistribution, Long> {
    FlbOeChaineDistribution getFlbOeChaineDistributionById(Long id);

    @Query("select f from FlbOeChaineDistribution f where f.idChaineValeur =:x")
    List<FlbOeChaineDistribution> getFlbOeChaineDistributionByIdChaineValeur(@Param("x") Long id);
    @Query(value = "SELECT count(*) FROM flb_oe_chaine_distribution f WHERE f.id_chaine_valeur=:x", nativeQuery = true)
    int getCountByChaineValue(@Param("x")  Long id);
    @Query(value = "SELECT count(f.id) FROM flb_oe_chaine_distribution f, flb_oe_chaine_valeur fv WHERE f.id_chaine_valeur=fv.id and fv.id_chambre=:x", nativeQuery = true)
    int getCountByChambre(@Param("x")  Long id);
    @Query(value = "SELECT count(f.id) FROM flb_oe_chaine_distribution f", nativeQuery = true)
    int getFill();
}