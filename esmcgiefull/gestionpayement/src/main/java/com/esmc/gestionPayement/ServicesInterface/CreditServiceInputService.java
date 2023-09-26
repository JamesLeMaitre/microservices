package com.esmc.gestionPayement.ServicesInterface;


import com.esmc.gestionPayement.entities.CreditServiceInput;

import java.util.List;

public interface CreditServiceInputService {

    List<CreditServiceInput> getCreditServiceInput();

    CreditServiceInput CreditServiceInput(Long id);

    CreditServiceInput saveCreditServiceInput(CreditServiceInput creditServiceInput);

    void deleteCreditServiceInput(Long id);

    void updateCreditServiceInput(Long id, CreditServiceInput creditServiceInput);
}
