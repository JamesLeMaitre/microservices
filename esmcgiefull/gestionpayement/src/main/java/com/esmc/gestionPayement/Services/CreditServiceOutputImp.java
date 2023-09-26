package com.esmc.gestionPayement.Services;


import com.esmc.gestionPayement.Repositories.CreditServiceOutputRepo;
import com.esmc.gestionPayement.ServicesInterface.CreditServiceOutputService;
import com.esmc.gestionPayement.entities.CreditServiceOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditServiceOutputImp implements CreditServiceOutputService {

    @Autowired
    private CreditServiceOutputRepo creditServiceOutputRepo;

    @Override
    public List<CreditServiceOutput> getCreditServiceOutput() {
        return creditServiceOutputRepo.findAll();
    }

    @Override
    public CreditServiceOutput CreditServiceOutput(Long id) {
        return creditServiceOutputRepo.findById(id).get();
    }

    @Override
    public CreditServiceOutput saveCreditServiceOutput(CreditServiceOutput creditServiceOutput) {
        return creditServiceOutputRepo.save(creditServiceOutput);
    }

    @Override
    public void deleteCreditServiceOutput(Long id) {
        creditServiceOutputRepo.deleteById(id);
    }

    @Override
    public void updateCreditServiceOutput(Long id, CreditServiceOutput creditServiceOutput) {
        creditServiceOutputRepo.save(creditServiceOutput);
    }
}
