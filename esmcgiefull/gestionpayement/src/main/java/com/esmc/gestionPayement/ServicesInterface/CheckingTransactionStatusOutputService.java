package com.esmc.gestionPayement.ServicesInterface;


import com.esmc.gestionPayement.entities.CheckingTransactionStatusOutput;

import java.util.List;

public interface CheckingTransactionStatusOutputService {
    List<CheckingTransactionStatusOutput> getCheckingTransactionStatusOutput();
    CheckingTransactionStatusOutput CheckingTransactionStatusOutput(Long id);
    CheckingTransactionStatusOutput saveCheckingTransactionStatusOutput(CheckingTransactionStatusOutput checkingTransactionStatusOutput);
    void updateCheckingTransactionStatusOutput(Long id, CheckingTransactionStatusOutput checkingTransactionStatusOutput);
    void deleteCheckingTransactionStatusOutput(Long id);
}
