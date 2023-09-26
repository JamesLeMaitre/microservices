package com.esmc.gestionte.repositories;

import com.esmc.gestionte.entities.SupportMarchandEnchage;
import com.esmc.gestionte.entities.TypeOrdre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Amemorte
 * @since 05/05/2022
 */


@Repository
public interface TypeOrdreRepository extends JpaRepository<TypeOrdre,Long> {

    @Query("SELECT a FROM TypeOrdre a WHERE trim(lower(a.libelle)) =trim(lower(?1)) ")
    Optional<SupportMarchandEnchage> findTypeOrdreByLibelle(String libelle);

    @Query("SELECT t FROM TypeOrdre t WHERE t.libelle = :x")
    TypeOrdre getTypeOrdreByLibelle(@Param("x") String libelle);
}
