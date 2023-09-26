package com.esmc.gestionFranchise.repositories;


import com.esmc.gestionFranchise.entities.FichePoste;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface FichePosteRepo extends JpaRepository<FichePoste,Long> {



    @Query("select f from FichePoste f where  f.poste.id=:x")
    public List<FichePoste> findFichePosteByPost( @Param("x") Long id);



}
