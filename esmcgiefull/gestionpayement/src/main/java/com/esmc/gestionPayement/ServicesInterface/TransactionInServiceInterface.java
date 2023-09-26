package com.esmc.gestionPayement.ServicesInterface;

import com.esmc.gestionPayement.entities.TransactionIns;
import com.esmc.gestionPayement.inputs.CorisInput;
import com.esmc.gestionPayement.inputs.TmoneyInput;
import com.esmc.gestionPayement.inputs.TransactionInInput;

import java.util.List;

public interface TransactionInServiceInterface {
     TransactionIns createTransactionIn(TransactionInInput data);

    public TransactionIns createTeTransactionIn(TransactionInInput data, Long idTe, Long idKsu);
    List<TransactionIns> getAll();

    TransactionIns getTransactionInsByReference(String reference);

    TransactionIns getTransactionInsById(Long id);

    Object decaissement(TmoneyInput data, Long idTe, Long idKsu);

    List<TransactionIns> getTransactionInByIdTe(Long idTe);

}
