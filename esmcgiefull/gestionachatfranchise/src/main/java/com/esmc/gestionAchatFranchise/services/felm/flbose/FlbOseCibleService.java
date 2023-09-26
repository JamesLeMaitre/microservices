package com.esmc.gestionAchatFranchise.services.felm.flbose;

import com.esmc.gestionAchatFranchise.entities.felm.flbOse.FlbOseAgenceOdd;
import com.esmc.gestionAchatFranchise.entities.felm.flbOse.FlbOseCible;
import com.esmc.gestionAchatFranchise.repositories.felm.flbose.FlbOseAgenceOddRepository;
import com.esmc.gestionAchatFranchise.repositories.felm.flbose.FlbOseCibleRepository;
import com.esmc.gestionAchatFranchise.servicesinterfaces.felm.flbose.FlbOseCibleInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utiles.UseFullFunctions;

import java.util.List;

@Service
public class FlbOseCibleService implements FlbOseCibleInterface {
    @Autowired
    private FlbOseCibleRepository repository;

    @Autowired
    private FlbOseAgenceOddRepository parentRepository;

    private UseFullFunctions useFullFunctions = new UseFullFunctions();



    @Override
    public List<FlbOseCible> getAll() {
        return repository.findAll();
    }

    @Override
    public FlbOseCible getById(Long id) {
        return repository.getFlbOseCibleById(id);
    }


    @Override
    public FlbOseCible create(FlbOseCible data) {
        FlbOseCible element = new FlbOseCible();

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
    public FlbOseCible update(Long id, FlbOseCible data) {
        FlbOseCible element = repository.getFlbOseCibleById(id);

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
    public FlbOseCible enable(Long id) {
        FlbOseCible element = repository.getFlbOseCibleById(id);
        element.setStatus(true);
        return repository.save(element);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<FlbOseCible> getListByAgenceOddId(Long id) {
        return  repository.getFlbOseCibleByIdAgenceOdd(id);
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
    public FlbOseCible disable(Long id) {
        FlbOseCible element = repository.getFlbOseCibleById(id);
        element.setStatus(false);
        return repository.save(element);
    }
}
