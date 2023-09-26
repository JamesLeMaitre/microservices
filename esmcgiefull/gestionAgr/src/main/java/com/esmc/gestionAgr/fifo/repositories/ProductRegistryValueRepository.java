package com.esmc.gestionAgr.fifo.repositories;


import com.esmc.gestionAgr.fifo.entities.ProductRegistryValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRegistryValueRepository extends JpaRepository<ProductRegistryValue, Long> {
}
