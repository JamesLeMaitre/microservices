package com.esmc.gestionPayement.Repositories;

import com.esmc.gestionPayement.entities.TransactionAdmins;
import com.esmc.gestionPayement.entities.TransactionIns;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionAdminRepo extends JpaRepository<TransactionAdmins, Long> {
    @Query("select t from TransactionAdmins t")
    List<TransactionAdmins> findAllByStatus();

    @Query("select t from TransactionAdmins t where t.idTe = :x")
    List<TransactionAdmins> findAllTrasactionByStatus(@Param("x") Long id);

    @Query("select t from TransactionAdmins t where t.idTe = :x and t.status = 0")
    List<TransactionAdmins> findAllByStatusFalse(@Param("x") Long id);

    @Query("select t from TransactionAdmins t where t.idTe = :x and t.status = 2")
    List<TransactionAdmins> findAllByStatusTrue(@Param("x") Long id);

    @Query("select tri from TransactionAdmins tri where tri.status = 1 ")
    List<TransactionAdmins> getLast(PageRequest of);

    @Query("Select  t from TransactionAdmins t where t.id = :x")
    TransactionAdmins getTransactiontByIdTransaction(@Param("x") Long id);

    @Query("Select  t from TransactionAdmins t where t.reference = :x")
    TransactionAdmins getTransactiontByIdTransactionCinetPayId(@Param("x") String x);
    @Query("Select  t from TransactionAdmins t where t.transactionId = :x")
    TransactionAdmins getTransactiontByIdTransactionId(@Param("x")  String transactionId);
}
