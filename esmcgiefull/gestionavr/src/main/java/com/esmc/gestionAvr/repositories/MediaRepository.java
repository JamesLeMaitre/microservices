package com.esmc.gestionAvr.repositories;

import com.esmc.gestionAvr.entities.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MediaRepository extends JpaRepository<Media, Long> {

    @Query("SELECT m FROM Media m WHERE m.avr.id =: x")
    public List<Media> ListMediaByAvr (@Param("x") Long id);
}
