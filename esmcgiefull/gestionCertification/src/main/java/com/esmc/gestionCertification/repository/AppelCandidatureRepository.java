package com.esmc.gestionCertification.repository;

import com.esmc.gestionCertification.entities.AppelCandidature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppelCandidatureRepository extends JpaRepository<AppelCandidature,Long> {

    @Query("Select a from AppelCandidature a where a.publier = true Order By a.id")
    List<AppelCandidature> listAppelACadiaturePublier();

    @Query("Select a from AppelCandidature a where a.idDetailAgrFranchiser =:x and a.publier = false Order By a.id")
    List<AppelCandidature> listAppelACadiatureById(@Param("x") Long id);


}
