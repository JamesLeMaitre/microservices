package com.esmc.gestionMembre.repositories;

import com.esmc.gestionMembre.entities.Membre;
import com.esmc.gestionMembre.entities.Physique;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MembreRepository extends JpaRepository<Membre, Long> {

    @Query("SELECT m from Membre m where m.codeMembre = :x")
    Membre ESMCSARLUmembreFondateurMembre (@Param("x") String code);

    @Query(value = "SELECT * from membre m where m.nom_membre =:x ", nativeQuery = true)
    List<Membre> listMembrePP(@Param("x" ) String x);

    @Query("SELECT p FROM Membre p where p.nomMembre=:y")
    Page<Membre> searchByAttributeNom(@Param("y") String y, Pageable pageable);

    @Query("SELECT p FROM Membre p where p.dateNaisMembre=:y")
    Page<Membre>  searchByAttributeDateLieuNaiss(@Param("y") String y,Pageable  pageable);


    /**
     * @author Amemorte9
     * @param code
     */


    @Query("SELECT m from Membre m where trim(lower(m.codeMembre)) = trim(lower(:x))   ")
    Membre getMembreByCodeMembreOrNomMembre (@Param("x") String code);





    @Query("SELECT m from Membre m where trim(lower(m.nomMembre))=trim(lower(:y)) and trim(lower(m.prenomMembre))=trim(lower(:z))  ")
    Membre getMembreByCodeMembreOrNomPrenom (@Param("y") String nom,@Param("z") String prenom);


}
