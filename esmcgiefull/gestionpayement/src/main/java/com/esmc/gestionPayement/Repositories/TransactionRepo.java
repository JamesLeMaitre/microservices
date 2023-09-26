package com.esmc.gestionPayement.Repositories;

import com.esmc.gestionPayement.entities.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository<Transactions, Long> {

    Transactions getTransactionsByReference(String reference);

    Transactions getTransactionsById(Long id);

    @Query("select t from Transactions t where t.idTe=?1 and t.used=?2")
    List<Transactions> getTransactionByIdTe(Long idTe, boolean used);

    @Query("select t from Transactions t where t.idKsu= :x and t.idTe= :y")
    Transactions getTransactionByIdKsuAndIdTe(@Param("x") Long idKsu, @Param("y") Long idTe);

    @Query("select sum(t.montant) from Transactions t where t.idKsu= :x and t.idTe= :y")
    Double sommeMev(@Param("x") Long idKsu, @Param("y") Long idTe);
}