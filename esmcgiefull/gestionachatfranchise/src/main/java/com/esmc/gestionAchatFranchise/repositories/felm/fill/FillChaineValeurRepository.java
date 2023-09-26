package com.esmc.gestionAchatFranchise.repositories.felm.fill;

import com.esmc.gestionAchatFranchise.entities.felm.fill.FillChaineValeur;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FillChaineValeurRepository extends org.springframework.data.jpa.repository.JpaRepository<FillChaineValeur, Long> {
    @Query("select f from FillChaineValeur f where f.idInstitution = :x")
    List<FillChaineValeur> getFillChaineValeurByIdInstitution(@Param("x") Long id);
    FillChaineValeur getFillChaineValeurById(Long id);

    @Query("select count(fd) from FillChaineDistribution fd, FillChaineValeur fv where fd.idChaineValeur = fv.id and fv.idInstitution = :x")
    int getNbDistributorByPartenId(@Param("x")  Long id);
    @Query(value = "select count(cv.id) from fill_chaine_valeur cv where cv.id_institution=:x", nativeQuery = true)
    int getCountByInstitution(@Param("x")  Long id);
}
