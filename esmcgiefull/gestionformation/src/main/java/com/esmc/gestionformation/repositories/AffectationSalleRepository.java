package com.esmc.gestionformation.repositories;

import com.esmc.gestionformation.entities.AffectationSalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AffectationSalleRepository extends JpaRepository<AffectationSalle, Long> {

    @Query("Select s from AffectationSalle s where s.typeSalle.id = :x")
    public List<AffectationSalle> listSalleByType(@Param("x") Long idType);

    @Query("Select s from AffectationSalle s where s.salleFormation.id = :x")
    public AffectationSalle getSalleByRaisonSociale(@Param("x") Long id);

    @Query("Select s from AffectationSalle s where s.id = :x")
    public AffectationSalle getSalleById(@Param("x") Long id);

    @Query("Select s from AffectationSalle s where s.idPoste = :x")
    public List<AffectationSalle> getSalleByIdPoste(@Param("x") Long id);

    @Query("Select s from AffectationSalle s where s.idPoste = :x and s.idAgr=:y and s.idDetailAgrFranchiser=:z ")
    public AffectationSalle getSalleByIdPosteAndAndIdDetailAgrFranchiser(@Param("x") Long id,@Param("y") Long idx,@Param("z") Long idz);

    @Query("Select s from AffectationSalle s where s.idDetailAgrFranchiser= :x")
    public List<AffectationSalle> listFormationByDetailsAgrFranchiser(@Param("x") Long idType);

    @Query("Select s from AffectationSalle s where s.idksu= :x")
    public List<AffectationSalle> getListFormationByKSU(@Param("x") Long id);

}
