package com.esmc.gestionFranchise.services;

import com.esmc.gestionFranchise.repositories.ConseilAdministrationRepo;
import com.esmc.gestionFranchise.entities.ConseilAdministration;
import com.esmc.gestionFranchise.servicesInterface.ConseilAdministrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ConseilAdministrationServiceImp implements ConseilAdministrationService {

    @Autowired
    private ConseilAdministrationRepo conseilAdministrationRepo;


    @Override
    public List<ConseilAdministration> getallConseilAdmin() {
        return conseilAdministrationRepo.findAll();
    }

    @Override
    public List<ConseilAdministration> getConseilAdministrationbyFranchise(Long id) {
        return conseilAdministrationRepo.getConseilAdministrationbyFranchise( id);
    }

    @Override
    public ConseilAdministration ajouterConseilAdministration(ConseilAdministration conseilAdministration) {
        conseilAdministrationRepo.save(conseilAdministration);
        return conseilAdministration;
    }

    @Override
    public ConseilAdministration getConseilAdministrationbyId(Long id) {
        return conseilAdministrationRepo.findById(id).get();
    }

    @Override
    public void deleteConseilAdministration(Long id) {
        conseilAdministrationRepo.deleteById(id);
    }
}
