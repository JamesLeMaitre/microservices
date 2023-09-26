package com.esmc.gestionAchatFranchise.services.felm.fill;

import com.esmc.gestionAchatFranchise.entities.felm.fill.FillInstitution;
import com.esmc.gestionAchatFranchise.repositories.felm.fill.FillInstitutionRepository;
import com.esmc.gestionAchatFranchise.servicesinterfaces.felm.fill.FillInstitutionInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utiles.UseFullFunctions;

import java.util.List;
@Service
public class FillInstitutionService implements FillInstitutionInterface {
    @Autowired
    private FillInstitutionRepository repository;


    private UseFullFunctions useFullFunctions = new UseFullFunctions();



    @Override
    public List<FillInstitution> getAll() {
        return repository.findAll();
    }

    @Override
    public FillInstitution getById(Long id) {
        return repository.getFillInstitutionById(id);
    }


    @Override
    public FillInstitution create(FillInstitution data) {
        FillInstitution element = new FillInstitution();

        if(data.getLibelle() != null){
            element.setLibelle(data.getLibelle());
        }

        if(data.getDescription() != null){
            element.setDescription(data.getDescription());
        }

        if(data.getCode() != null){
            element.setCode(data.getCode());
        }

        if(data.getImage() != null){
            element.setImage( useFullFunctions.getUploadImageByBase64(data.getImage()));
        }

        return repository.save(element);
    }

    @Override
    public FillInstitution update(Long id, FillInstitution data) {
        FillInstitution element = repository.getFillInstitutionById(id);

        if(data.getLibelle() != null){
            element.setLibelle(data.getLibelle());
        }

        if(data.getDescription() != null){
            element.setDescription(data.getDescription());
        }

        if(data.getCode() != null){
            element.setCode(data.getCode());
        }

        if(data.getImage() != null){
            element.setImage( useFullFunctions.getUploadImageByBase64(data.getImage()));
        }

        return repository.save(element);
    }

    @Override
    public FillInstitution enable(Long id) {
        FillInstitution element = repository.getFillInstitutionById(id);
        element.setStatus(true);
        return repository.save(element);
    }

    @Override
    public FillInstitution disable(Long id) {
        FillInstitution element = repository.getFillInstitutionById(id);
        element.setStatus(false);
        return repository.save(element);
    }

    @Override
    public int getCountAll() {
        return (int) this.repository.count();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
