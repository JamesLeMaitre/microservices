package com.esmc.gestionAchatFranchise.services.felm.flbose;

import com.esmc.gestionAchatFranchise.entities.felm.flbOse.FlbOseAgenceOdd;
import com.esmc.gestionAchatFranchise.repositories.felm.flbose.FlbOseAgenceOddRepository;
import com.esmc.gestionAchatFranchise.servicesinterfaces.felm.flbose.FlbOseAgenceOddInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utiles.UseFullFunctions;

import java.util.List;

@Service
public class FlbOseAgenceOddService implements FlbOseAgenceOddInterface {
    @Autowired
    private FlbOseAgenceOddRepository repository;


    private UseFullFunctions useFullFunctions = new UseFullFunctions();



    @Override
    public List<FlbOseAgenceOdd> getAll() {
        return repository.findAll();
    }

    @Override
    public FlbOseAgenceOdd getById(Long id) {
        return repository.getFlbOseAgenceOddById(id);
    }


    @Override
    public FlbOseAgenceOdd create(FlbOseAgenceOdd data) {
        FlbOseAgenceOdd element = new FlbOseAgenceOdd();

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
    public FlbOseAgenceOdd update(Long id, FlbOseAgenceOdd data) {
        FlbOseAgenceOdd element = repository.getFlbOseAgenceOddById(id);

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
    public FlbOseAgenceOdd enable(Long id) {
        FlbOseAgenceOdd element = repository.getFlbOseAgenceOddById(id);
        element.setStatus(true);
        return repository.save(element);
    }

    @Override
    public FlbOseAgenceOdd disable(Long id) {
        FlbOseAgenceOdd element = repository.getFlbOseAgenceOddById(id);
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
