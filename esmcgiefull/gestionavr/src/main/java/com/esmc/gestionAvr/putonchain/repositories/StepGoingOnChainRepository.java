package com.esmc.gestionAvr.putonchain.repositories;

import com.esmc.gestionAvr.putonchain.entities.StepGoingOnChain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StepGoingOnChainRepository extends JpaRepository<StepGoingOnChain,Long> {
    Optional<StepGoingOnChain> findByKsuAndStateIsTrue(Long ksu);
}
