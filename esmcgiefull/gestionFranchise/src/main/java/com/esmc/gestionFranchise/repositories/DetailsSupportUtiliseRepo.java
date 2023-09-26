package com.esmc.gestionFranchise.repositories;

import com.esmc.gestionFranchise.entities.DetailsSupportUtilise;
import com.esmc.gestionFranchise.entities.ManuelProcedure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DetailsSupportUtiliseRepo  extends JpaRepository<DetailsSupportUtilise,Long> {
    @Query("select dsu from  DetailsSupportUtilise dsu where  dsu.tableDescriptionEp.id= :x")
    List<DetailsSupportUtilise> getDetailsSupportUtilisebyTdep(@Param("x") Long id);
}
