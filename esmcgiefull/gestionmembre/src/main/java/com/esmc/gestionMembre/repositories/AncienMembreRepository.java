package com.esmc.gestionMembre.repositories;

import com.esmc.gestionMembre.entities.AncienMembre;
import com.esmc.gestionMembre.entities.Physique;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AncienMembreRepository extends JpaRepository<AncienMembre, Long> {
    @Query("SELECT a FROM AncienMembre a WHERE a.ancienCodeMembre = :x")
    AncienMembre MCNPancienMembre(@Param("x") String code);

    @Query("SELECT a FROM AncienMembre a WHERE a.nomMembre =:x and  a.ancienCodeMembre like '%P'")
    List<AncienMembre> listAncienMembrePP(@Param("x") String x);

    @Query("SELECT a FROM AncienMembre a WHERE a.raisonSocial=:x and a.ancienCodeMembre like '%M'")
    List<AncienMembre> listAncienMembrePM(@Param("x")String serachWord);

    @Query("SELECT a FROM AncienMembre a WHERE a.ancienCodeMembre Like '%P'")
    Page<AncienMembre> findByAncienMembrepPP(Pageable pageable);

    @Query("SELECT a FROM AncienMembre a WHERE a.ancienCodeMembre Like '%M'")
    Page<AncienMembre> findByAncienMembrepPM(Pageable pageable);

    @Query("SELECT p FROM AncienMembre p where p.nomMembre=:y or p.prenomMembre=:y and p.ancienCodeMembre like '%M'")
    Page<AncienMembre>  searchByAttributeNomPM(@Param("y") String y, Pageable pageable);

    @Query("SELECT p FROM AncienMembre p where p.nomMembre=:y or p.prenomMembre=:y  and p.ancienCodeMembre like '%P'")
    Page<AncienMembre>  searchByAttributeNomPP(@Param("y") String y,Pageable pageable);

    @Query("SELECT p FROM AncienMembre p where p.dateNaisMembre=:y ")
    Page<AncienMembre> searchByAttributeDateLieuNaiss(@Param("y") String y, Pageable pageable);




    @Query("SELECT a FROM AncienMembre a WHERE trim(lower(a.ancienCodeMembre)) = trim(lower(:x)) or trim(lower(a.codeMembre))=trim(lower(:x))")
    AncienMembre findancienMembreByCodeOrName(@Param("x") String codeOrName);



    @Query("SELECT a FROM AncienMembre a WHERE trim(lower(a.nomMembre)) = trim(lower(:x)) or trim(lower(a.prenomMembre))=trim(lower(:y))")
    AncienMembre findancienMembreByNomAndPrenom(@Param("x") String nom,@Param("y") String prenom);
}
