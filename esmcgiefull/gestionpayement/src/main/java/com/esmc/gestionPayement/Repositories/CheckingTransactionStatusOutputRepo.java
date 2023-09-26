package com.esmc.gestionPayement.Repositories;


import com.esmc.gestionPayement.entities.CheckingTransactionStatusOutput;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckingTransactionStatusOutputRepo extends JpaRepository<CheckingTransactionStatusOutput, Long> {
}
