package com.esmc.gestionFranchise.repositories;

import com.esmc.gestionFranchise.entities.Intervenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IntervenantRepo extends JpaRepository<Intervenant,Long> {

    @Query("select i from  Intervenant i where  i.tableDescriptionEp.id= :x")
    List<Intervenant> getIntervenantBytdep(@Param("x") Long id);

    @Query("select count(i) from  Intervenant i where  i.tableDescriptionEp.id= :x")
    int countBy(@Param("x") Long id);

    @Query("select i from  Intervenant i where  i.tableDescriptionEp.id= :x and i.poste.id=:y")
    Intervenant getIntervenantByPosteTdep(@Param("x") Long idPoste,@Param("y") Long idTdep);

}
