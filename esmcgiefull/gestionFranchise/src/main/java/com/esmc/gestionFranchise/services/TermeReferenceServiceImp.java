package com.esmc.gestionFranchise.services;

import com.esmc.gestionFranchise.repositories.TermeReferenceRepo;
import com.esmc.gestionFranchise.entities.TermeReference;
import com.esmc.gestionFranchise.servicesInterface.TermeReferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class TermeReferenceServiceImp implements TermeReferenceService {

    @Autowired
    private TermeReferenceRepo termeReferenceRepo;

    @Override
    public List<TermeReference> getTermeReference() {
        return termeReferenceRepo.findAll();
    }

    @Override
    public TermeReference ajouterTermeReference(TermeReference TermeReference) {
        TermeReference.setDateCreate(new Date());
        TermeReference.setDateUpdate(new Date());
        return  termeReferenceRepo.save(TermeReference);
    }

    @Override
    public TermeReference getTermeReferencebyId(Long id) {
        return termeReferenceRepo.findById(id).get();
    }

    @Override
    public void deleteTermeReference(Long id) {
        termeReferenceRepo.deleteById(id);
    }

    @Override
    public List<TermeReference> listTermeReference(Long id) {
        return termeReferenceRepo.TermeReferenceByIntervenant(id);
    }

}
