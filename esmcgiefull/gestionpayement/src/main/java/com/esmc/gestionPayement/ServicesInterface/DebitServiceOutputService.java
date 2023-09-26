package com.esmc.gestionPayement.ServicesInterface;


import com.esmc.gestionPayement.entities.DebitServiceOutput;

import java.util.List;

public interface DebitServiceOutputService {
    List<DebitServiceOutput> getDebitServiceOutput();

    DebitServiceOutput DebitServiceOutput(Long id);

    DebitServiceOutput saveDebitServiceOutput(DebitServiceOutput debitServiceOutput);

    void deleteDebitServiceOutput(Long id);

    void updateDebitServiceOutput(Long id, DebitServiceOutput debitServiceOutput);
}
