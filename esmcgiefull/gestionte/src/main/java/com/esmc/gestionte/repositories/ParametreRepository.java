package com.esmc.gestionte.repositories;


import com.esmc.gestionte.entities.Parametre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ParametreRepository extends JpaRepository<Parametre,Long> {

    @Query("Select p from Parametre p where p.id = 1")
    Parametre getParametre();

}
