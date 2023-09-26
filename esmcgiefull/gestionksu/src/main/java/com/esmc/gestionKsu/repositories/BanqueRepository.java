package com.esmc.gestionKsu.repositories;

import com.esmc.gestionKsu.entities.Banque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

/**
 * Banque entity repository
 * @author katoh <katohdavid@gmail.com>
 */
@RepositoryRestResource
public interface BanqueRepository extends JpaRepository<Banque,Long> {

    @Query("select b from Banque b where trim(lower(b.libelle))=trim(lower(?1))")
    Optional<Banque> findByLibelle(String libelle);

}
