package com.esmc.gestionPayement.Services;


import com.esmc.gestionPayement.Repositories.DebitServiceOutputRepo;
import com.esmc.gestionPayement.ServicesInterface.DebitServiceOutputService;
import com.esmc.gestionPayement.entities.DebitServiceOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DebitServiceOutputImp implements DebitServiceOutputService {

    @Autowired
    private DebitServiceOutputRepo debitServiceOutputRepo;
    @Override
    public List<DebitServiceOutput> getDebitServiceOutput() {
        return debitServiceOutputRepo.findAll();
    }

    @Override
    public DebitServiceOutput DebitServiceOutput(Long id) {
        return debitServiceOutputRepo.findById(id).get();
    }

    @Override
    public DebitServiceOutput saveDebitServiceOutput(DebitServiceOutput debitServiceOutput) {
        return debitServiceOutputRepo.save(debitServiceOutput);
    }

    @Override
    public void deleteDebitServiceOutput(Long id) {
        debitServiceOutputRepo.deleteById(id);
    }

    @Override
    public void updateDebitServiceOutput(Long id, DebitServiceOutput debitServiceOutput) {
        debitServiceOutputRepo.save(debitServiceOutput);
    }
}
