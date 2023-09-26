package com.esmc.gestionPayement.Services;


import com.esmc.gestionPayement.Repositories.CreditServiceInputRepo;
import com.esmc.gestionPayement.ServicesInterface.CreditServiceInputService;
import com.esmc.gestionPayement.entities.CreditServiceInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditServiceInputImp implements CreditServiceInputService {

    @Autowired
    private CreditServiceInputRepo creditServiceInputRepo;
    @Override
    public List<CreditServiceInput> getCreditServiceInput() {
        return creditServiceInputRepo.findAll();
    }

    @Override
    public CreditServiceInput CreditServiceInput(Long id) {
        return creditServiceInputRepo.findById(id).get();
    }

    @Override
    public CreditServiceInput saveCreditServiceInput(CreditServiceInput creditServiceInput) {
        return creditServiceInputRepo.save(creditServiceInput);
    }

    @Override
    public void deleteCreditServiceInput(Long id) {
        creditServiceInputRepo.deleteById(id);
    }

    @Override
    public void updateCreditServiceInput(Long id, CreditServiceInput creditServiceInput) {
        creditServiceInputRepo.save(creditServiceInput);
    }
}
