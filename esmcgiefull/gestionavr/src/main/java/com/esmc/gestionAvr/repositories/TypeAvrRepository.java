package com.esmc.gestionAvr.repositories;

import com.esmc.gestionAvr.entities.TypeAvr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TypeAvrRepository extends JpaRepository<TypeAvr, Long> {
    @Query("SELECT t FROM TypeAvr t WHERE t.libelle like '%fifo%'")
    List<TypeAvr> listTypeAvrOfFifo();

    Optional<TypeAvr> findById(Long id);
}
