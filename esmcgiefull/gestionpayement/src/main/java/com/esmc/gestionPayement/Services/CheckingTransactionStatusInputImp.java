package com.esmc.gestionPayement.Services;


import com.esmc.gestionPayement.Repositories.CheckingTransactionStatusInputRepo;
import com.esmc.gestionPayement.ServicesInterface.CheckingTransactionStatusInputService;
import com.esmc.gestionPayement.entities.CheckingTransactionStatusInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckingTransactionStatusInputImp implements CheckingTransactionStatusInputService {

    @Autowired
    private CheckingTransactionStatusInputRepo checkingTransactionStatusInputRepo;

    @Override
    public List<CheckingTransactionStatusInput> getCheckingTransactionStatusInput() {
        return checkingTransactionStatusInputRepo.findAll();
    }

    @Override
    public CheckingTransactionStatusInput CheckingTransactionStatusInput(Long id) {
        return checkingTransactionStatusInputRepo.findById(id).get();
    }

    @Override
    public CheckingTransactionStatusInput saveCheckingTransactionStatusInput(CheckingTransactionStatusInput checkingTransactionStatusInput) {
        return checkingTransactionStatusInputRepo.save(checkingTransactionStatusInput);
    }
    @Override
    public void updateCheckingTransactionStatusInput(Long id,CheckingTransactionStatusInput checkingTransactionStatusInput) {
        checkingTransactionStatusInputRepo.save(checkingTransactionStatusInput);
    }

    @Override
    public void deleteCheckingTransactionStatusInput(Long id) {
        checkingTransactionStatusInputRepo.deleteById(id);
    }

}
