package com.esmc.gestionPayement.Repositories;


import com.esmc.gestionPayement.entities.CheckTransactionOutput;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckTransactionOutputRepo extends JpaRepository<CheckTransactionOutput, Long> {
}
