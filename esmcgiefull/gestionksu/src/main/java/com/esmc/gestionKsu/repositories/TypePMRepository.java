package com.esmc.gestionKsu.repositories;

import com.esmc.gestionKsu.entities.TypePM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TypePMRepository extends JpaRepository<TypePM,Long> {

    @Query("select t from TypePM t where trim(lower(t.libelle))=trim(lower(?1))")
    Optional<TypePM> findByLibelle(String libelle);
}
