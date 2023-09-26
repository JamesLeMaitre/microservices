package com.esmc.gestionAvr.services;

import com.esmc.gestionAvr.entities.Vague;
import com.esmc.gestionAvr.entities.VagueHistory;
import com.esmc.gestionAvr.repositories.VagueHistoryRepo;
import com.esmc.gestionAvr.repositories.VagueRepo;
import com.esmc.gestionAvr.servicesInterfaces.VagueHistoryServiceInterface;
import com.esmc.gestionAvr.servicesInterfaces.VagueServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VagueHistoryService implements VagueHistoryServiceInterface {
    @Autowired
    private VagueHistoryRepo vagueHistoryRepo;


    @Override
    public List<VagueHistory> getAll() {
        return vagueHistoryRepo.findAll();
    }
}
