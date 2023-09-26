package com.esmc.gestionPayement.Repositories;


import com.esmc.gestionPayement.entities.CheckTransactionInput;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckTransactionInputRepo extends JpaRepository<CheckTransactionInput,Long> {
}
