package com.esmc.gestionFranchise.services.organev2;

import com.esmc.gestionFranchise.entities.organev2.SupportIntervenant;
import com.esmc.gestionFranchise.repositories.organev2.SupportIntervenantRepo;
import com.esmc.gestionFranchise.servicesInterface.organev2.SupportIntervenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;



@Service
@Transactional
public class SupportIntervenantServiceImpl implements SupportIntervenantService {

    @Autowired
    SupportIntervenantRepo supportIntervenantRepo;



    @Override
    public List<SupportIntervenant> getAll() {

        return supportIntervenantRepo.findAll();
    }

    @Override
    public SupportIntervenant getById(Long id) {

        return supportIntervenantRepo.findById(id).get();
    }

    @Override
    public SupportIntervenant create(com.esmc.gestionFranchise.entities.organev2.SupportIntervenant data) {

        return supportIntervenantRepo.save(data);


    }

    @Override
    public SupportIntervenant update(Long id, com.esmc.gestionFranchise.entities.organev2.SupportIntervenant data) {

        com.esmc.gestionFranchise.entities.organev2.SupportIntervenant element=getById(id);

        if(data.getLibelle() != null){
            element.setLibelle(data.getLibelle());
        }
        if(data.getIdSupport() != null){
            element.setIdSupport(data.getIdSupport());
        }
        if(data.getTypeSupport() != null){
            element.setTypeSupport(data.getTypeSupport());
        }


        return supportIntervenantRepo.save(element);
    }

    @Override
    public SupportIntervenant disable(Long id) {

        com.esmc.gestionFranchise.entities.organev2.SupportIntervenant element = getById(id);
        element.setStatus(false);
        return supportIntervenantRepo.save(element);

    }

    @Override
    public SupportIntervenant enable(Long id) {

        com.esmc.gestionFranchise.entities.organev2.SupportIntervenant element = getById(id);
        element.setStatus(true);
        return supportIntervenantRepo.save(element);
    }

    @Override
    public void delete(Long id) {

        supportIntervenantRepo.deleteById(id);

    }
}
