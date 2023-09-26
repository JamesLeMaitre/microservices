package com.esmc.gestionAchatFranchise.services.felm.flboe;

import com.esmc.gestionAchatFranchise.entities.felm.flbOe.FlbOeChaineDistribution;
import com.esmc.gestionAchatFranchise.entities.felm.flbOe.FlbOeChaineValeur;
import com.esmc.gestionAchatFranchise.repositories.felm.flboe.FlbOeChaineDistributionRepository;
import com.esmc.gestionAchatFranchise.repositories.felm.flboe.FlbOeChaineValeurRepository;
import com.esmc.gestionAchatFranchise.servicesinterfaces.felm.flboe.FlbOeChaineDistributionInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utiles.UseFullFunctions;

import java.util.List;

@Service
public class FlbOeChaineDistributionService implements FlbOeChaineDistributionInterface {
    @Autowired
    private FlbOeChaineDistributionRepository repository;

    @Autowired
    private FlbOeChaineValeurRepository parentRepository;

    private UseFullFunctions useFullFunctions = new UseFullFunctions();


    @Override
    public List<FlbOeChaineDistribution> getAll() {
        return repository.findAll();
    }

    @Override
    public FlbOeChaineDistribution getById(Long id) {
        return repository.getFlbOeChaineDistributionById(id);
    }


    @Override
    public FlbOeChaineDistribution create(FlbOeChaineDistribution data) {
        FlbOeChaineDistribution element = new FlbOeChaineDistribution();

        if (data.getLibelle() != null) {
            element.setLibelle(data.getLibelle());
        }

        if (data.getDescription() != null) {
            element.setDescription(data.getDescription());
        }

        if (data.getCode() != null) {
            element.setCode(data.getCode());
        }

        if (data.getImage() != null) {
            element.setImage(useFullFunctions.getUploadImageByBase64(data.getImage()));
        }

        if (data.getIdChaineValeur() != null) {
            FlbOeChaineValeur parent = parentRepository.getFlbOeChaineValeurById(data.getIdChaineValeur());
            element.setIdChaineValeur(parent.getId());
        }

        return repository.save(element);
    }

    @Override
    public FlbOeChaineDistribution update(Long id, FlbOeChaineDistribution data) {
        FlbOeChaineDistribution element = repository.getFlbOeChaineDistributionById(id);

        if (data.getLibelle() != null) {
            element.setLibelle(data.getLibelle());
        }

        if (data.getDescription() != null) {
            element.setDescription(data.getDescription());
        }

        if (data.getCode() != null) {
            element.setCode(data.getCode());
        }

        if (data.getImage() != null) {
            element.setImage(useFullFunctions.getUploadImageByBase64(data.getImage()));
        }

        if (data.getIdChaineValeur() != null) {
            FlbOeChaineValeur parent = parentRepository.getFlbOeChaineValeurById(data.getIdChaineValeur());
            element.setIdChaineValeur(parent.getId());
        }

        return repository.save(element);
    }


    @Override
    public FlbOeChaineDistribution enable(Long id) {
        FlbOeChaineDistribution element = repository.getFlbOeChaineDistributionById(id);
        element.setStatus(true);
        return repository.save(element);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<FlbOeChaineDistribution> getListByChaineValeurId(Long id) {
        return repository.getFlbOeChaineDistributionByIdChaineValeur(id);
    }

    @Override
    public int getCountByChaineValue(Long id) {
        return this.repository.getCountByChaineValue(id);
    }

    @Override
    public int getCountByChambre(Long id) {
        return this.repository.getCountByChambre(id);
    }

    @Override
    public int getCountAll() {
        return this.repository.getFill();
    }

    @Override
    public FlbOeChaineDistribution disable(Long id) {
        FlbOeChaineDistribution element = repository.getFlbOeChaineDistributionById(id);
        element.setStatus(false);
        return repository.save(element);
    }
}
