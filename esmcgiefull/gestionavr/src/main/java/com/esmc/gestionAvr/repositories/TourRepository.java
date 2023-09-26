package com.esmc.gestionAvr.repositories;

import com.esmc.gestionAvr.entities.Tour;
import com.esmc.gestionAvr.utils.enums.KsuType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TourRepository extends JpaRepository<Tour,Long> {
    Optional<Tour> findByName(String name);

}
