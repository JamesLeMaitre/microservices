package com.esmc.gestionPayement.ServicesInterface;


import com.esmc.gestionPayement.entities.CreditServiceOutput;

import java.util.List;

public interface CreditServiceOutputService {
    List<CreditServiceOutput> getCreditServiceOutput();

    CreditServiceOutput CreditServiceOutput(Long id);

    CreditServiceOutput saveCreditServiceOutput(CreditServiceOutput creditServiceOutput);

    void deleteCreditServiceOutput(Long id);

    void updateCreditServiceOutput(Long id,CreditServiceOutput creditServiceOutput);
}
