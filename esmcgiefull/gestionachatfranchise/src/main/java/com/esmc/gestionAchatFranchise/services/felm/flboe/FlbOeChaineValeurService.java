package com.esmc.gestionAchatFranchise.services.felm.flboe;

import com.esmc.gestionAchatFranchise.entities.felm.flbOe.FlbOeChaineValeur;
import com.esmc.gestionAchatFranchise.entities.felm.flbOe.FlbOeChambre;
import com.esmc.gestionAchatFranchise.repositories.felm.flboe.FlbOeChaineValeurRepository;
import com.esmc.gestionAchatFranchise.repositories.felm.flboe.FlbOeChambreRepository;
import com.esmc.gestionAchatFranchise.servicesinterfaces.felm.flboe.FlbOeChaineValeurInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utiles.UseFullFunctions;

import java.util.List;
@Service
public class FlbOeChaineValeurService implements FlbOeChaineValeurInterface {
    @Autowired
    private FlbOeChaineValeurRepository repository;

    @Autowired
    private FlbOeChambreRepository parentRepository;

    private UseFullFunctions useFullFunctions = new UseFullFunctions();



    @Override
    public List<FlbOeChaineValeur> getAll() {
        return repository.findAll();
    }

    @Override
    public FlbOeChaineValeur getById(Long id) {
        return repository.getFlbOeChaineValeurById(id);
    }


    @Override
    public FlbOeChaineValeur create(FlbOeChaineValeur data) {
        FlbOeChaineValeur element = new FlbOeChaineValeur();

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

        if(data.getIdChambre() != null){
            FlbOeChambre parent = parentRepository.getFlbOeChambreById(data.getIdChambre());
            element.setIdChambre(parent.getId());
        }

        return repository.save(element);
    }

    @Override
    public FlbOeChaineValeur update(Long id, FlbOeChaineValeur data) {
        FlbOeChaineValeur element = repository.getFlbOeChaineValeurById(id);

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

        if(data.getIdChambre() != null){
            FlbOeChambre parent = parentRepository.getFlbOeChambreById(data.getIdChambre());
            element.setIdChambre(parent.getId());
        }
        return repository.save(element);
    }



    @Override
    public FlbOeChaineValeur enable(Long id) {
        FlbOeChaineValeur element = repository.getFlbOeChaineValeurById(id);
        element.setStatus(true);
        return repository.save(element);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public int getCountByChambre(Long id) {
        return repository.getCountByChambre(id);
    }

    @Override
    public List<FlbOeChaineValeur> getListByChambreId(Long id) {
        return  repository.getFlbOeChaineValeurByIdChambre(id);
    }

    @Override
    public int getCountAll() {
        return (int) this.repository.count();
    }

    @Override
    public FlbOeChaineValeur disable(Long id) {
        FlbOeChaineValeur element = repository.getFlbOeChaineValeurById(id);
        element.setStatus(false);
        return repository.save(element);
    }
}
