package com.esmc.gestionPayement.Repositories;


import com.esmc.gestionPayement.entities.CreditServiceOutput;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditServiceOutputRepo extends JpaRepository<CreditServiceOutput, Long> {
}
