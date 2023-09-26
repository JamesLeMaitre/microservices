package com.esmc.gestionPayement.ServicesInterface;

import com.esmc.gestionPayement.entities.TransactionAdmins;
import com.esmc.gestionPayement.entities.TransactionInRequest;
import com.esmc.gestionPayement.entities.TransactionIns;
import com.esmc.gestionPayement.entities.Transactions;
import com.esmc.gestionPayement.inputs.CinetPayWebhooksInput;
import com.esmc.gestionPayement.inputs.TransactionInInput;
import com.esmc.gestionPayement.inputs.TransactionInput;

import java.util.List;

public interface TransactionInRequestServiceInterface {
    TransactionInRequest createTransactionInRequest(TransactionInInput data);
    public TransactionInRequest createTeTransactionInRequest(TransactionInInput data, Long idTe, Long idKsu);
    List<TransactionInRequest> getAll();

    TransactionInRequest getTransactionInRequestByReference(String reference);

    TransactionInRequest getTransactionInRequestById(Long id);

    List<TransactionInRequest> getTransactionInRequestByIdTe(Long idTe);

    Object checkTransaction();

    Object createDecaissement(double amount, String phone, String prefix, String nom, String prenom, String email, TransactionAdmins transactionAdmins);

    Object  checkTransactionAdmin();

    void cinetPayWebhooksVerifyTransaction(String transactionId);

    void cinetPayWebhooksVerifyTransactionDepot(String transactionId);
}
