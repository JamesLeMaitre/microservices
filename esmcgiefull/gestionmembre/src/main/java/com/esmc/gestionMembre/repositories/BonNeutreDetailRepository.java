package com.esmc.gestionMembre.repositories;

import com.esmc.gestionMembre.entities.BonNeutreDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BonNeutreDetailRepository extends JpaRepository<BonNeutreDetail, Long> {

    @Query("Select b from  BonNeutreDetail  b where b.bonNeutre = :x")
    public List<BonNeutreDetail> getBan(@Param("x") Long id);
}
