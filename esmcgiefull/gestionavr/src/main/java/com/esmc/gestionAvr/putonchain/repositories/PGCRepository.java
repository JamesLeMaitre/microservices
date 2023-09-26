package com.esmc.gestionAvr.putonchain.repositories;

import com.esmc.gestionAvr.putonchain.entities.ProductGoingOnChain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PGCRepository extends JpaRepository<ProductGoingOnChain,Long> {
}
