package com.esmc.gestionAvr.repositories;

import com.esmc.gestionAvr.entities.Echange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EchangeRepository extends JpaRepository<Echange, Long> {

    /*@Query("SELECT e FROM Echange e WHERE e.typeEchange.id= :x")
    public List<Echange> getEchangeByTypeEchange(@Param("x") Long id);*/
}
