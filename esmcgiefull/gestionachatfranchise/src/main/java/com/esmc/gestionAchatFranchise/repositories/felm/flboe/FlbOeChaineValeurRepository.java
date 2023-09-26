package com.esmc.gestionAchatFranchise.repositories.felm.flboe;

import com.esmc.gestionAchatFranchise.entities.felm.fill.FillChaineValeur;
import com.esmc.gestionAchatFranchise.entities.felm.flbOe.FlbOeChaineValeur;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FlbOeChaineValeurRepository extends org.springframework.data.jpa.repository.JpaRepository<FlbOeChaineValeur, Long> {
    FlbOeChaineValeur getFlbOeChaineValeurById(Long id);

    @Query("select f from FlbOeChaineValeur f where f.idChambre = :x")
    List<FlbOeChaineValeur> getFlbOeChaineValeurByIdChambre(@Param("x") Long id);

    @Query(value = "select count(cv.id) from flb_oe_chaine_valeur cv where cv.id_chambre=:x", nativeQuery = true)
    int getCountByChambre(@Param("x")  Long id);
}
