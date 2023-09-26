package com.esmc.gestionAchatFranchise.services.felm.fill;

import com.esmc.gestionAchatFranchise.entities.felm.fill.FillChaineValeur;
import com.esmc.gestionAchatFranchise.entities.felm.fill.FillInstitution;
import com.esmc.gestionAchatFranchise.repositories.felm.fill.FillChaineValeurRepository;
import com.esmc.gestionAchatFranchise.repositories.felm.fill.FillInstitutionRepository;
import com.esmc.gestionAchatFranchise.servicesinterfaces.felm.fill.FillChaineValeurInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utiles.UseFullFunctions;

import java.util.List;
@Service
public class FillChaineValeurService implements FillChaineValeurInterface {
    @Autowired
    private FillChaineValeurRepository repository;

    @Autowired
    private FillInstitutionRepository parentRepository;

    private UseFullFunctions useFullFunctions = new UseFullFunctions();



    @Override
    public List<FillChaineValeur> getAll() {
        return repository.findAll();
    }

    @Override
    public FillChaineValeur getById(Long id) {
        return repository.getFillChaineValeurById(id);
    }


    @Override
    public FillChaineValeur create(FillChaineValeur data) {
        FillChaineValeur element = new FillChaineValeur();

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

        if(data.getIds() != null){
            FillChaineValeur linkedFillChaineDistribution = repository.getFillChaineValeurById(data.getIds());
            element.setIds(linkedFillChaineDistribution.getId());
            element.setLibelle(data.getLibelle());
        }


        if(data.getIdInstitution() != null){
            FillInstitution parent = parentRepository.getFillInstitutionById(data.getIdInstitution());
            element.setIdInstitution(parent.getId());
        }

        return repository.save(element);
    }

    @Override
    public FillChaineValeur update(Long id, FillChaineValeur data) {
        FillChaineValeur element = repository.getFillChaineValeurById(id);

        if(data.getLibelle() != null){
            element.setLibelle(data.getLibelle());
        }

        if(data.getDescription() != null){
            element.setDescription(data.getDescription());
        }

        if(data.getIds() != null){
            FillChaineValeur linkedFillChaineDistribution = repository.getFillChaineValeurById(data.getIds());
            element.setIds(linkedFillChaineDistribution.getId());
            element.setLibelle(data.getLibelle());
        }


        if(data.getCode() != null){
            element.setCode(data.getCode());
        }

        if(data.getImage() != null){
            element.setImage( useFullFunctions.getUploadImageByBase64(data.getImage()));
        }

        if(data.getIdInstitution() != null){
            FillInstitution parent = parentRepository.getFillInstitutionById(data.getIdInstitution());
            element.setIdInstitution(parent.getId());
        }
        return repository.save(element);
    }



    @Override
    public FillChaineValeur enable(Long id) {
        FillChaineValeur element = repository.getFillChaineValeurById(id);
        element.setStatus(true);
        return repository.save(element);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public int getNbDistributorByPartenId(Long id) {
        return this.repository.getNbDistributorByPartenId(id);
    }

    @Override
    public int getCountByInstitution(Long id) {
        return repository.getCountByInstitution(id);
    }

    @Override
    public List<FillChaineValeur> getListByInstitutionId(Long id) {
        return  repository.getFillChaineValeurByIdInstitution(id);
    }

    @Override
    public int getCountAll() {
        return (int) this.repository.count();
    }

    @Override
    public FillChaineValeur disable(Long id) {
        FillChaineValeur element = repository.getFillChaineValeurById(id);
        element.setStatus(false);
        return repository.save(element);
    }
}
