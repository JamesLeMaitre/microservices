package com.esmc.gestionFranchise.repositories;

import com.esmc.gestionFranchise.entities.ManuelProcedure;
import com.esmc.gestionFranchise.entities.SupportUtilise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupportUtiliseRepo extends JpaRepository<SupportUtilise, Long> {
    @Query("select a from SupportUtilise a where a.tableDescriptionEp.id =:x")
    List<SupportUtilise> getByTdep(@Param("x") Long idTdep);
}
