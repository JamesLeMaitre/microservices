package com.esmc.gestionFranchise.repositories;


import com.esmc.gestionFranchise.entities.TableDescriptionEp;
import com.esmc.gestionFranchise.entities.Tache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TacheRepo  extends JpaRepository<Tache,Long> {

    @Query("select t from  Tache t where  t.fichePoste.id= :x")
    List<Tache> getTachebyfichedeposte(@Param("x") Long id);


    @Query("select t from Tache t where  t.fichePoste.id=?1")
    public List<Tache> findTacheByAgentRecruteur(Long id);

}
