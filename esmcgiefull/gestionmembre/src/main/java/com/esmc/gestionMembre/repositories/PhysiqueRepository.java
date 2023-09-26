package com.esmc.gestionMembre.repositories;


import com.esmc.gestionMembre.entities.Physique;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhysiqueRepository extends JpaRepository<Physique, Long> {

   // public boolean existsBynumIdentp(String numIdentp);
    @Query("SELECT a FROM Physique a WHERE a.numIdentp = :x")
    Physique RedemarrepersonnePhysique(@Param("x") String code);

    @Query("SELECT a FROM Physique a where a.nom=:x")
    List<Physique> listRedemarePP(@Param("x") String searchWords);


    @Query("SELECT p FROM Physique p where p.dateNais=:y or p.lieuNais=:y or p.nom=:y or p.prenom=:y")
    Page<Physique> searchPhysiqueByAttributeDateLieuNaiss(@Param("y") String y, Pageable pageable);


 /**
  * @author Amemorte9
  * @param nom
  */


 @Query("SELECT p from Physique p where trim(lower(p.nom)) = trim(lower(:x)) and trim(lower(p.prenom)) = trim(lower(:y))  ")
 Physique findReDeMaRePhysiqueByNom (@Param("x") String  nom ,@Param("y") String  prenom);


 @Query("SELECT p from Physique p where trim(lower(p.numIdentp)) = trim(lower(:x))   ")
 Physique findReDeMaRePhysiqueByNom (@Param("x") String  code);

}
