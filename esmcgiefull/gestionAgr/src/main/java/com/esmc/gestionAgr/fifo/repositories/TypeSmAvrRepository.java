package com.esmc.gestionAgr.fifo.repositories;


import com.esmc.gestionAgr.fifo.entities.TypeSmAvr;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeSmAvrRepository extends JpaRepository<TypeSmAvr, Long> {
    TypeSmAvr findByCode(String code);
}