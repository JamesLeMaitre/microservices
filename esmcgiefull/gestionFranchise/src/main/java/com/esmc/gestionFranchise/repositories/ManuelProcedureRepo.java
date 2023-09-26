package com.esmc.gestionFranchise.repositories;


import com.esmc.gestionFranchise.entities.FichePoste;
import com.esmc.gestionFranchise.entities.ManuelProcedure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManuelProcedureRepo extends JpaRepository<ManuelProcedure,Long> {

    @Query("select mp from  ManuelProcedure mp where  mp.tache.id= :x")
    List<ManuelProcedure> getManuelByTache(@Param("x") Long id);

    @Query("select m from ManuelProcedure m where  m.tache.id=?1")
    public ManuelProcedure findManuelProcedureBytache(Long id);

}
