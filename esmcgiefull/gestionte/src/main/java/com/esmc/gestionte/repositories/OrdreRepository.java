package com.esmc.gestionte.repositories;

import com.esmc.gestionte.entities.Ordre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrdreRepository extends JpaRepository<Ordre,Long> {
    @Query("SELECT a FROM Ordre a WHERE trim(lower(a.numeroOPI)) =trim(lower(?1)) ")
    Optional<Ordre> findOrdreByNumeroOPI(String numeroOPI);
}
