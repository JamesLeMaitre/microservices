package com.esmc.gestionPayement.ServicesInterface;


import com.esmc.gestionPayement.entities.CheckingTransactionStatusInput;

import java.util.List;

public interface CheckingTransactionStatusInputService {

    List<CheckingTransactionStatusInput> getCheckingTransactionStatusInput();
    CheckingTransactionStatusInput CheckingTransactionStatusInput(Long id);
    CheckingTransactionStatusInput saveCheckingTransactionStatusInput(CheckingTransactionStatusInput checkingTransactionStatusInput);
    void updateCheckingTransactionStatusInput(Long id,CheckingTransactionStatusInput checkingTransactionStatusInput);
    void deleteCheckingTransactionStatusInput(Long id);
}
