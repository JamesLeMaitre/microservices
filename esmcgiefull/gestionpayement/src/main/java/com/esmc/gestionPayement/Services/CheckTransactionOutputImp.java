package com.esmc.gestionPayement.Services;


import com.esmc.gestionPayement.Repositories.CheckTransactionOutputRepo;
import com.esmc.gestionPayement.ServicesInterface.CheckTransactionOutputService;
import com.esmc.gestionPayement.entities.CheckTransactionOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckTransactionOutputImp implements CheckTransactionOutputService {

    @Autowired
    private CheckTransactionOutputRepo checkTransactionOutputRepo;
    @Override
    public List<CheckTransactionOutput> getCheckTransactionOutput() {
        return checkTransactionOutputRepo.findAll();
    }

    @Override
    public CheckTransactionOutput CheckTransactionOutput(Long id) {
        return checkTransactionOutputRepo.findById(id).get();
    }

    @Override
    public CheckTransactionOutput saveCheckTransactionOutput(CheckTransactionOutput checkTransactionOutput) {
        return checkTransactionOutputRepo.save(checkTransactionOutput);
    }

    @Override
    public void deleteCheckTransactionOutput(Long id) {
        checkTransactionOutputRepo.deleteById(id);
    }

    @Override
    public void updateCheckTransactionOutput(CheckTransactionOutput checkTransactionOutput) {
        checkTransactionOutputRepo.save(checkTransactionOutput);
    }
}
