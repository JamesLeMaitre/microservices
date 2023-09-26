package com.esmc.gestionPayement.ServicesInterface;


import com.esmc.gestionPayement.entities.CheckTransactionInput;

import java.util.List;

public interface CheckTransactionInputService {
    List<CheckTransactionInput> getCheckTransactionInput();
    CheckTransactionInput CheckTransactionInput(Long id);
    CheckTransactionInput saveCheckTransactionInput(CheckTransactionInput checkTransactionInput);
    void deleteCheckTransactionInput(Long id);
    void updateCheckTransactionInput(CheckTransactionInput checkTransactionInput);
}
