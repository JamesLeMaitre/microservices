package com.esmc.gestionPayement.Services;


import com.esmc.gestionPayement.Repositories.CheckTransactionInputRepo;
import com.esmc.gestionPayement.ServicesInterface.CheckTransactionInputService;
import com.esmc.gestionPayement.entities.CheckTransactionInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckTransactionInputImp implements CheckTransactionInputService {

    @Autowired
    private CheckTransactionInputRepo checkTransactionInputRepo;
    @Override
    public List<CheckTransactionInput> getCheckTransactionInput() {
        return checkTransactionInputRepo.findAll();
    }

    @Override
    public CheckTransactionInput CheckTransactionInput(Long id) {
        return checkTransactionInputRepo.findById(id).get();
    }

    @Override
    public CheckTransactionInput saveCheckTransactionInput(CheckTransactionInput checkTransactionInput) {
        return checkTransactionInputRepo.save(checkTransactionInput);
    }

    @Override
    public void deleteCheckTransactionInput(Long id) {
        checkTransactionInputRepo.deleteById(id);
    }

    @Override
    public void updateCheckTransactionInput(CheckTransactionInput checkTransactionInput) {
        checkTransactionInputRepo.save(checkTransactionInput);
    }
}
