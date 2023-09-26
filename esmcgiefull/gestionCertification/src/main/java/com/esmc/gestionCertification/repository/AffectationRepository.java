package com.esmc.gestionCertification.repository;

import com.esmc.gestionCertification.entities.Affectation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AffectationRepository extends JpaRepository<Affectation,Long> {
    @Query("select af from Affectation af where  af.candidature.id= :x")
    public List<Affectation> findAffectationByCandidature(@Param("x") Long id);

    @Query("select af from Affectation af where  af.candidature.id=:x ")
    public List<Affectation> listWithoutLink(@Param("x") Long id);
}
