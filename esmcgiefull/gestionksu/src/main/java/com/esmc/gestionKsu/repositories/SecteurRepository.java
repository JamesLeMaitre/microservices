package com.esmc.gestionKsu.repositories;

import com.esmc.gestionKsu.entities.Secteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * {@link Secteur} repository
 * @author katoh <katohdavid@gmail.com>
 */
public interface SecteurRepository extends JpaRepository<Secteur,Long> {

    @Query("select s from Secteur s where lower(trim(s.libelle))=lower(trim(?1))")
    Optional<Secteur> findByLibelle(String libelle);

    @Query("select s from Secteur s where s.categorie.id = :x")
    public List<Secteur> findSecteurByCategorie(@Param("x") Long id);
}
