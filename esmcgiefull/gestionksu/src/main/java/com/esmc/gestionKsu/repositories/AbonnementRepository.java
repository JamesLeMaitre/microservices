package com.esmc.gestionKsu.repositories;

import com.esmc.gestionKsu.entities.Abonnement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * @author katoh <katohdavid@gmail.com>
 */
public interface AbonnementRepository extends JpaRepository<Abonnement,Long> {

    /**
     * Find by libelle
     * @param libelle
     * @return
     */
    @Query("select a from Abonnement a where trim(lower(a.libelle))=trim(lower(?1))")
    Optional<Abonnement> findByLibelle(String libelle);

    @Query("select a from Abonnement a where a.typeOffre.id = :x")
    public List<Abonnement> listAbonnementByTypeOffre(@Param("x") Long id);

}
