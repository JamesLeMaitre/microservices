package com.esmc.gestionPayement.Repositories;

import com.esmc.gestionPayement.entities.TransactionInRequest;
import com.esmc.gestionPayement.entities.TransactionIns;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionInRequestRepo extends JpaRepository<TransactionInRequest, Long> {
    TransactionInRequest getTransactionInRequestByReference(String refernce);

    TransactionInRequest getTransactionInRequestById(Long id);

    @Query("select t from TransactionInRequest t where t.confirmed=?1")
    List<TransactionInRequest> getTransactionInRequestByConfirmed( boolean used);

    @Query("select t from TransactionInRequest t where t.idTe=?1")
    List<TransactionInRequest> getTransactionInRequestByIdTe(Long idTe);


    @Query("select tri from TransactionInRequest tri ")
    List<TransactionInRequest> getLast(PageRequest of);
}