package com.esmc.gestionAchatFranchise.services.felm.fill;

import com.esmc.gestionAchatFranchise.entities.felm.fill.FillChaineDistribution;
import com.esmc.gestionAchatFranchise.entities.felm.fill.FillChaineValeur;
import com.esmc.gestionAchatFranchise.repositories.felm.fill.FillChaineDistributionRepository;
import com.esmc.gestionAchatFranchise.repositories.felm.fill.FillChaineValeurRepository;
import com.esmc.gestionAchatFranchise.servicesinterfaces.felm.fill.FillChaineDistributionInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utiles.UseFullFunctions;

import java.util.List;
@Service
public class FillChaineDistributionService implements FillChaineDistributionInterface {
    @Autowired
    private FillChaineDistributionRepository repository;

    @Autowired
    private FillChaineValeurRepository parentRepository;

    private UseFullFunctions useFullFunctions = new UseFullFunctions();



    @Override
    public List<FillChaineDistribution> getAll() {
        return repository.findAll();
    }

    @Override
    public FillChaineDistribution getById(Long id) {
        return repository.getFillChaineDistributionById(id);
    }


    @Override
    public FillChaineDistribution create(FillChaineDistribution data) {
        FillChaineDistribution element = new FillChaineDistribution();

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

        if(data.getIdChaineValeur() != null){
            FillChaineValeur parent = parentRepository.getFillChaineValeurById(data.getIdChaineValeur());
            element.setIdChaineValeur(parent.getId());
        }

        return repository.save(element);
    }

    @Override
    public FillChaineDistribution update(Long id, FillChaineDistribution data) {
        FillChaineDistribution element = repository.getFillChaineDistributionById(id);

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

        if(data.getIdChaineValeur() != null){
            FillChaineValeur parent = parentRepository.getFillChaineValeurById(data.getIdChaineValeur());
            element.setIdChaineValeur(parent.getId());
        }

        return repository.save(element);
    }



    @Override
    public FillChaineDistribution enable(Long id) {
        FillChaineDistribution element = repository.getFillChaineDistributionById(id);
        element.setStatus(true);
        return repository.save(element);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<FillChaineDistribution> getListByChaineValeurId(Long id) {
        return  repository.getFillChaineDistributionByIdChaineValeur(id);
    }

    @Override
    public int getCountByChaineValue(Long id) {
        return this.repository.getCountByChaineValue(id);
    }

    @Override
    public int getCountByInsitution(Long id) {
        return this.repository.getCountByInsitution(id);
    }

    @Override
    public int getCountAll() {
        return this.repository.getFill();
    }

    @Override
    public FillChaineDistribution disable(Long id) {
        FillChaineDistribution element = repository.getFillChaineDistributionById(id);
        element.setStatus(false);
        return repository.save(element);
    }
}
