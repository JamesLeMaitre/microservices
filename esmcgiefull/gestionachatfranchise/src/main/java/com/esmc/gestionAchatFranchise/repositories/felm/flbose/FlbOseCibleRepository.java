package com.esmc.gestionAchatFranchise.repositories.felm.flbose;

import com.esmc.gestionAchatFranchise.entities.felm.flbOe.FlbOeChaineValeur;
import com.esmc.gestionAchatFranchise.entities.felm.flbOse.FlbOseCible;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FlbOseCibleRepository extends org.springframework.data.jpa.repository.JpaRepository<FlbOseCible, Long> {
    FlbOseCible getFlbOseCibleById(Long id);

    @Query("select f from FlbOseCible f where f.idAgenceOdd = :x")
    List<FlbOseCible> getFlbOseCibleByIdAgenceOdd(@Param("x") Long id);
    @Query(value = "SELECT count(f.id) FROM flb_ose_cible f where f.id_agence_odd=:x", nativeQuery = true)
    int getCountByAgenceOdd(@Param("x")  Long id);
    @Query(value = "SELECT count(f.id) FROM flb_ose_cible f", nativeQuery = true)
    int getCount();
}