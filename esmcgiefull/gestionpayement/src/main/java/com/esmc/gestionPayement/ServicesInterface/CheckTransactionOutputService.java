package com.esmc.gestionPayement.ServicesInterface;


import com.esmc.gestionPayement.entities.CheckTransactionOutput;

import java.util.List;

public interface CheckTransactionOutputService {
    List<CheckTransactionOutput> getCheckTransactionOutput();
    CheckTransactionOutput CheckTransactionOutput(Long id);
    CheckTransactionOutput saveCheckTransactionOutput(CheckTransactionOutput checkTransactionOutput);
    void deleteCheckTransactionOutput(Long id);
    void updateCheckTransactionOutput(CheckTransactionOutput checkTransactionOutput);
}
