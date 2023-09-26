package com.esmc.gestionPayement.Services;


import com.esmc.gestionPayement.Repositories.CheckingTransactionStatusOutputRepo;
import com.esmc.gestionPayement.ServicesInterface.CheckingTransactionStatusOutputService;
import com.esmc.gestionPayement.entities.CheckingTransactionStatusOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckingTransactionStatusOutputImp implements CheckingTransactionStatusOutputService {

    @Autowired
    private CheckingTransactionStatusOutputRepo checkingTransactionStatusOutputRepo;

    @Override
    public List<CheckingTransactionStatusOutput> getCheckingTransactionStatusOutput() {
        return checkingTransactionStatusOutputRepo.findAll();
    }

    @Override
    public CheckingTransactionStatusOutput CheckingTransactionStatusOutput(Long id) {
        return checkingTransactionStatusOutputRepo.findById(id).get();
    }

    @Override
    public CheckingTransactionStatusOutput saveCheckingTransactionStatusOutput(CheckingTransactionStatusOutput checkingTransactionStatusOutput) {
        return checkingTransactionStatusOutputRepo.save(checkingTransactionStatusOutput);
    }

    @Override
    public void updateCheckingTransactionStatusOutput(Long id, CheckingTransactionStatusOutput checkingTransactionStatusOutput) {
        checkingTransactionStatusOutputRepo.save(checkingTransactionStatusOutput);
    }

    @Override
    public void deleteCheckingTransactionStatusOutput(Long id) {
        checkingTransactionStatusOutputRepo.deleteById(id);
    }

}
