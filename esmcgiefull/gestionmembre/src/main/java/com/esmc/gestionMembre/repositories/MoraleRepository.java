package com.esmc.gestionMembre.repositories;

import com.esmc.gestionMembre.entities.Morale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoraleRepository extends JpaRepository<Morale, Long> {

   // public boolean existsBynumIdentm(String numIdentm);
   @Query("SELECT a FROM Morale a WHERE a.numIdentm = :x")
   Morale RedemarrepersonneMorale(@Param("x") String code);

   @Query("SELECT a FROM Morale a where a.nomm=:x")
   List<Morale> listRedemarePM(@Param("x") String searchWords);



   @Query("SELECT a FROM Morale a WHERE a.representant = :x")
   Page<Morale> pasgeMoraleSearch(@Param("x") String y, Pageable p);




   /**
    * @author Amemorte9
    * @param nomOrCode
    */


   @Query("SELECT m from Morale m where trim(lower(m.nomm))=trim(lower(:x))  or trim(lower(m.codeMembre))=trim(lower(:x))")
   Morale findReDeMaReByMoraleNom (@Param("x") String  nomOrCode);

}
