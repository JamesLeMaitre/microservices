package com.esmc.gestionAchatFranchise.services.felm.flboe;

import com.esmc.gestionAchatFranchise.entities.felm.flbOe.FlbOeChambre;
import com.esmc.gestionAchatFranchise.repositories.felm.flboe.FlbOeChambreRepository;
import com.esmc.gestionAchatFranchise.servicesinterfaces.felm.flboe.FlbOeChambreInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utiles.UseFullFunctions;

import java.util.List;
@Service
public class FlbOeChambreService implements FlbOeChambreInterface {
    @Autowired
    private FlbOeChambreRepository repository;


    private UseFullFunctions useFullFunctions = new UseFullFunctions();



    @Override
    public List<FlbOeChambre> getAll() {
        return repository.findAll();
    }

    @Override
    public FlbOeChambre getById(Long id) {
        return repository.getFlbOeChambreById(id);
    }


    @Override
    public FlbOeChambre create(FlbOeChambre data) {
        FlbOeChambre element = new FlbOeChambre();

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
    public FlbOeChambre update(Long id, FlbOeChambre data) {
        FlbOeChambre element = repository.getFlbOeChambreById(id);

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
    public FlbOeChambre enable(Long id) {
        FlbOeChambre element = repository.getFlbOeChambreById(id);
        element.setStatus(true);
        return repository.save(element);
    }

    @Override
    public FlbOeChambre disable(Long id) {
        FlbOeChambre element = repository.getFlbOeChambreById(id);
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
