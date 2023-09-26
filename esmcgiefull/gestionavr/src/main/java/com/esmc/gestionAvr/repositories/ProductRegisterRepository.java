package com.esmc.gestionAvr.repositories;

import com.esmc.gestionAvr.entities.ProductRegistryValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRegisterRepository extends JpaRepository<ProductRegistryValue, Long> {
}
