package com.esmc.gestionAgr.fifo.repositories;


import com.esmc.gestionAgr.fifo.entities.CategorieAvr;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryAvrRepository extends JpaRepository<CategorieAvr, Long> {
}
