package com.esmc.gestionContrat.repositories;


import com.esmc.gestionContrat.entities.TypeContrat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeContratRepository extends JpaRepository<TypeContrat, Long> {
    @Query("select tc from TypeContrat tc where  tc.code= :x")
     TypeContrat findByCode(@Param("x") String code);
}
