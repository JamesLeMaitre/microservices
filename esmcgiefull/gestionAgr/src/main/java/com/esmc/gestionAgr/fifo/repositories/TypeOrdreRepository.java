package com.esmc.gestionAgr.fifo.repositories;


import com.esmc.gestionAgr.fifo.entities.TypeOrdre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TypeOrdreRepository extends JpaRepository<TypeOrdre, Long> {
    @Query("SELECT t FROM TypeOrdre t WHERE t.libelle = :x")
    TypeOrdre getTypeOrdreByLibelle(@Param("x") String libelle);
}
