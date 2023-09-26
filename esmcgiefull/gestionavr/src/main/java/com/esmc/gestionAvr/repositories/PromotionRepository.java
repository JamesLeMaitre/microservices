package com.esmc.gestionAvr.repositories;

import com.esmc.gestionAvr.entities.PromotionVague;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionRepository extends JpaRepository<PromotionVague, Long> {
}
