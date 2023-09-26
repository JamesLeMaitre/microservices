package com.esmc.gestionContrat.repositories;


import com.esmc.gestionContrat.entities.Porte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PorteRepositorey extends JpaRepository<Porte, Long> {
    @Query("Select c from Porte c where c.typeContrat.id = :x")
    public List<Porte> listePorteByTypeContrat(@Param("x") Long id);

}
