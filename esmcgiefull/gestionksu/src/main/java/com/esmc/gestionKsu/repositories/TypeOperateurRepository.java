package com.esmc.gestionKsu.repositories;

import com.esmc.gestionKsu.entities.TypeOperateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

/**
 * TypeOperateur entity repository
 * @author katoh <katohdavid@gmail.com>
 */
@RepositoryRestResource
public interface TypeOperateurRepository extends JpaRepository<TypeOperateur,Long> {

    @Query("select t from TypeOperateur t where trim(lower(t.libelle))=trim(lower(?1))")
    Optional<TypeOperateur> findByLibelle(String libelle);

}
