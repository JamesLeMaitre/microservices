package com.esmc.gestionPayement.Repositories;

import com.esmc.gestionPayement.entities.TransactionIns;
import com.esmc.gestionPayement.entities.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionInRepo extends JpaRepository<TransactionIns, Long> {
    TransactionIns getTransactionInsByReference(String refernce);

    TransactionIns getTransactionInsById(Long id);

    @Query("select t from TransactionIns t where t.confirmed=?1")
    List<TransactionIns> getTransactionInByConfirmed( boolean used);

    @Query("select t from TransactionIns t where t.idTe=?1")
    List<TransactionIns> getTransactionInByIdTe(Long idTe);

}