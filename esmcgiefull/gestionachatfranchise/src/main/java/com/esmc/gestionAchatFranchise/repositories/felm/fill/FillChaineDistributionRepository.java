package com.esmc.gestionAchatFranchise.repositories.felm.fill;

import com.esmc.gestionAchatFranchise.entities.felm.fill.FillChaineDistribution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FillChaineDistributionRepository extends JpaRepository<FillChaineDistribution, Long > {
    @Query("select f from FillChaineDistribution f where f.idChaineValeur =:x")
    List<FillChaineDistribution> getFillChaineDistributionByIdChaineValeur(@Param("x") Long id);
    FillChaineDistribution getFillChaineDistributionById(Long id);

    @Query(value = "SELECT count(*) FROM fill_chaine_distribution f WHERE f.id_chaine_valeur=:x", nativeQuery = true)
    int getCountByChaineValue(@Param("x")  Long id);
    @Query(value = "SELECT count(f.id) FROM fill_chaine_distribution f, fill_chaine_valeur fv WHERE f.id_chaine_valeur=fv.id and fv.id_institution=:x", nativeQuery = true)
    int getCountByInsitution(@Param("x")  Long id);
    @Query(value = "SELECT count(f.id) FROM fill_chaine_distribution f", nativeQuery = true)
    int getFill();
}
