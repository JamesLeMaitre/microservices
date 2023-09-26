package com.esmc.gestionAvr.services;

import com.esmc.gestionAvr.entities.FifoHistory;
import com.esmc.gestionAvr.repositories.FifoHistoryRepository;
import com.esmc.gestionAvr.servicesInterfaces.FifoHistoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FifoHistoryService implements FifoHistoryInterface {
    @Autowired
    private FifoHistoryRepository repository;


    @Override
    public List<FifoHistory> getAll() {
        return repository.findAll();
    }

    @Override
    public FifoHistory getById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public List<FifoHistory> getListByType(Boolean isBuy) {
        return repository.getListByType(isBuy) ;
    }

    @Override
    public List<FifoHistory> getListByIdDetailAgr(Long id) {
        return repository.getListByIdDetailAgr(id);
    }

}
