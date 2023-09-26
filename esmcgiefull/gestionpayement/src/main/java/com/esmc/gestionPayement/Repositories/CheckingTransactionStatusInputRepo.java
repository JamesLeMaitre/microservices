package com.esmc.gestionPayement.Repositories;


import com.esmc.gestionPayement.entities.CheckingTransactionStatusInput;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckingTransactionStatusInputRepo extends JpaRepository<CheckingTransactionStatusInput, Long> {
}
