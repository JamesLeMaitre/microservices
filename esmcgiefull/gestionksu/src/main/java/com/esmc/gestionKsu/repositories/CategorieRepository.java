package com.esmc.gestionKsu.repositories;

import com.esmc.gestionKsu.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * {@link Categorie} repository
 *
 * @author katoh <katohdavid@gmail.com>
 */
public interface CategorieRepository extends JpaRepository<Categorie, Long> {

    @Query("select c from Categorie c where lower(trim(c.libelle))=lower(trim(?1))")
    Optional<Categorie> findByLibelle(String libelle);

}
