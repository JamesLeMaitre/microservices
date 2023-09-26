package com.esmc.gestionKsu.repositories;

import com.esmc.gestionKsu.entities.PieceIdentite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * {@link PieceIdentite} repository
 * @author katoh <katohdavid@gmail.com>
 */
public interface PieceIdentiteRepository extends JpaRepository<PieceIdentite,Long> {

    @Query("select p from PieceIdentite p where trim(lower(p.libelle))=trim(lower(?1))")
    Optional<PieceIdentite> findByLibelle(String libelle);

}
