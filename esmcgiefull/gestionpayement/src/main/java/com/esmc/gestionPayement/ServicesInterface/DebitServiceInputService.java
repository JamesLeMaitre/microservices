package com.esmc.gestionPayement.ServicesInterface;


import com.esmc.gestionPayement.entities.DebitServiceInput;
import com.esmc.gestionPayement.inputs.Input;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface DebitServiceInputService {

    List<DebitServiceInput> getDebitServiceInput();

    DebitServiceInput DebitServiceInput(Long id);

    DebitServiceInput saveDebitServiceInput(DebitServiceInput debitServiceInput);

    void deleteDebitServiceInput(Long id);

    void updateDebitServiceInput(Long id,DebitServiceInput debitServiceInput);

    String hashFunc(Input pass) throws NoSuchAlgorithmException;
}
