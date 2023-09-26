package com.esmc.gestionKsu.repositories;

import com.esmc.gestionKsu.entities.TypeOffre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author katoh <katohdavid@gmail.com>
 */
public interface TypeOffreRepository extends JpaRepository<TypeOffre,Long> {
    Optional<TypeOffre> findByLibelle(String libelle);

}
