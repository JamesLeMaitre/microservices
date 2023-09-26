package com.esmc.gestionAchatFranchise.repositories.felm.flbose;

import com.esmc.gestionAchatFranchise.entities.felm.flbOse.FlbOseCible;
import com.esmc.gestionAchatFranchise.entities.felm.flbOse.FlbOseIndicateur;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FlbOseIndicateurRepository extends org.springframework.data.jpa.repository.JpaRepository<FlbOseIndicateur, Long> {
    FlbOseIndicateur getFlbOseIndicateurById(Long id);

    @Query("select f from FlbOseIndicateur f where f.idAgenceOdd = :x")
    List<FlbOseIndicateur> getFlbOseIndicateurByIdAgenceOdd(@Param("x") Long id);
    @Query(value = "SELECT count(f.id) FROM flb_ose_indicateur f where f.id_agence_odd=:x", nativeQuery = true)
    int getCountByAgenceOdd(@Param("x")  Long id);
    @Query(value = "SELECT count(f.id) FROM flb_ose_indicateur f", nativeQuery = true)
    int getCount();
}
