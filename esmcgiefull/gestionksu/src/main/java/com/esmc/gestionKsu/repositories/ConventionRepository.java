package com.esmc.gestionKsu.repositories;

import com.esmc.gestionKsu.entities.Convention;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Convention repository
 * @author katoh <katohdavid@gmail.com>
 */
public interface ConventionRepository extends JpaRepository<Convention,Long> {

    @Query("select c from Convention c where c.ksu.id = :x")
    public Convention getConventionByKsu(@Param("x") Long idKsu);

    @Query("select c from Convention c where c.ksu.id = :x")
    public List<Convention> listConventionByKsu(@Param("x") Long idKsu);
}
