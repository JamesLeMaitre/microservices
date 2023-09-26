package com.esmc.gestionFranchise.services;

import com.esmc.gestionFranchise.repositories.CalendrierRepo;
import com.esmc.gestionFranchise.entities.Calendrier;
import com.esmc.gestionFranchise.servicesInterface.CalendrierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class CalendrierServiceImp implements CalendrierService {
    @Autowired
    private CalendrierRepo calendrierRepo;
    @Override
    public List<Calendrier> getCalendrier() {
        return calendrierRepo.findAll();
    }

    @Override
    public Calendrier ajouterCalendrier(Calendrier calendrier) {
        calendrierRepo.save(calendrier);
        return calendrier;
    }

    @Override
    public Calendrier getCalendrierbyId(Long id) {
        return calendrierRepo.findById(id).get();
    }

    @Override
    public void deleteCalendrier(Long id) {
        calendrierRepo.deleteById(id);
    }
}
