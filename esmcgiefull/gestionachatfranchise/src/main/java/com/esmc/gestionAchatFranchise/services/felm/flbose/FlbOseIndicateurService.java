package com.esmc.gestionAchatFranchise.services.felm.flbose;

import com.esmc.gestionAchatFranchise.entities.felm.flbOse.FlbOseAgenceOdd;
import com.esmc.gestionAchatFranchise.entities.felm.flbOse.FlbOseIndicateur;
import com.esmc.gestionAchatFranchise.repositories.felm.flbose.FlbOseAgenceOddRepository;
import com.esmc.gestionAchatFranchise.repositories.felm.flbose.FlbOseIndicateurRepository;
import com.esmc.gestionAchatFranchise.servicesinterfaces.felm.flbose.FlbOseIndicateurInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utiles.UseFullFunctions;

import java.util.List;

@Service
public class FlbOseIndicateurService implements FlbOseIndicateurInterface {
    @Autowired
    private FlbOseIndicateurRepository repository;

    @Autowired
    private FlbOseAgenceOddRepository parentRepository;

    private UseFullFunctions useFullFunctions = new UseFullFunctions();



    @Override
    public List<FlbOseIndicateur> getAll() {
        return repository.findAll();
    }

    @Override
    public FlbOseIndicateur getById(Long id) {
        return repository.getFlbOseIndicateurById(id);
    }


    @Override
    public FlbOseIndicateur create(FlbOseIndicateur data) {
        FlbOseIndicateur element = new FlbOseIndicateur();

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

        if(data.getIdAgenceOdd() != null){
            FlbOseAgenceOdd parent = parentRepository.getFlbOseAgenceOddById(data.getIdAgenceOdd());
            element.setIdAgenceOdd(parent.getId());
        }

        return repository.save(element);
    }

    @Override
    public FlbOseIndicateur update(Long id, FlbOseIndicateur data) {
        FlbOseIndicateur element = repository.getFlbOseIndicateurById(id);

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

        if(data.getIdAgenceOdd() != null){
            FlbOseAgenceOdd parent = parentRepository.getFlbOseAgenceOddById(data.getIdAgenceOdd());
            element.setIdAgenceOdd(parent.getId());
        }

        return repository.save(element);
    }



    @Override
    public FlbOseIndicateur enable(Long id) {
        FlbOseIndicateur element = repository.getFlbOseIndicateurById(id);
        element.setStatus(true);
        return repository.save(element);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<FlbOseIndicateur> getListByAgenceOddId(Long id) {
        return  repository.getFlbOseIndicateurByIdAgenceOdd(id);
    }

    @Override
    public int getCountByAgenceOdd(Long id) {
        return this.repository.getCountByAgenceOdd(id);
    }

    @Override
    public int getCountAll() {
        return this.repository.getCount();
    }

    @Override
    public FlbOseIndicateur disable(Long id) {
        FlbOseIndicateur element = repository.getFlbOseIndicateurById(id);
        element.setStatus(false);
        return repository.save(element);
    }
}
