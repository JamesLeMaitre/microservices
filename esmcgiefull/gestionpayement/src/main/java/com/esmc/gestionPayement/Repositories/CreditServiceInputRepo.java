package com.esmc.gestionPayement.Repositories;


import com.esmc.gestionPayement.entities.CreditServiceInput;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditServiceInputRepo extends JpaRepository<CreditServiceInput, Long> {
}
