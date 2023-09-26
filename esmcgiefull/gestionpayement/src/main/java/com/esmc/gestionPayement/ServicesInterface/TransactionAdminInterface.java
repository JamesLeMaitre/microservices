package com.esmc.gestionPayement.ServicesInterface;

import com.esmc.gestionPayement.entities.TransactionAdmins;
import com.esmc.gestionPayement.entities.Transactions;
import com.esmc.gestionPayement.inputs.TransactionActionInput;

import java.util.List;

public interface TransactionAdminInterface {
    public TransactionAdmins createTeTransactionAdmins(TransactionActionInput data, Long idTe, Long idKsu);

    TransactionAdmins createTeTransactionAdminsV2(TransactionActionInput data, Long idTe, Long idKsu, Long transactionType);

    List<TransactionAdmins> getAll();

    TransactionAdmins activate(Long id, Long transactionType);

    List<TransactionAdmins> listTransactionAdminByTeCreate(Long idTe);

    List<TransactionAdmins> listTransactionAdminByTe(Long idTe);

    Transactions getCurrentTransactions(Long idKsu, Long idTe);

    TransactionAdmins getTransactionById(Long id);
}
