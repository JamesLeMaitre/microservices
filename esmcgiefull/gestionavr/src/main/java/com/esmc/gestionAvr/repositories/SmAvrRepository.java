package com.esmc.gestionAvr.repositories;

import com.esmc.gestionAvr.entities.SMAvr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SmAvrRepository extends JpaRepository<SMAvr, Long> {
    @Query("SELECT a FROM SMAvr a WHERE a.typeSmAvr.id= :x")
    public List<SMAvr> listSMAvrByTypeSmAvr(@Param("x") Long id);

}