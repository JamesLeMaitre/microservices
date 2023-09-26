package com.esmc.gestionFranchise.services;

import com.esmc.gestionFranchise.repositories.IntervenantRepo;
import com.esmc.gestionFranchise.entities.Intervenant;
import com.esmc.gestionFranchise.repositories.TableDescriptionEpRepo;
import com.esmc.gestionFranchise.repositories.organev2.PosteRepo;
import com.esmc.gestionFranchise.servicesInterface.IntervenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IntervenantServiceImp implements IntervenantService {
    @Autowired
    private IntervenantRepo intervenantRepo;
    @Autowired
    private PosteRepo posteRepo;

    @Autowired
    private TableDescriptionEpRepo tableDescriptionEpRepo;
    @Override
    public List<Intervenant> getIntervenant() {
        return intervenantRepo.findAll();
    }


    @Override
    public List<Intervenant> getIntervenantbytdep(Long id) {

        return intervenantRepo.getIntervenantBytdep(id);
    }

    @Override
    public int countBy(Long id) {
        return  intervenantRepo.countBy(id);
    }

    @Override
    public Intervenant ajouterIntervenant(Intervenant intervenant) {

        return intervenantRepo.save(intervenant);
    }

    @Override
    public Intervenant getIntervenantbyId(Long id) {
        return intervenantRepo.findById(id).get();
    }

    @Override
    public void deleteIntervenant(Long id) {
        intervenantRepo.deleteById(id);
    }

    @Override
    public Intervenant update(Long id, Intervenant data){
        Intervenant intervenant = new Intervenant();
        intervenant = intervenantRepo.findById(id).orElse(null);
        assert intervenant != null: "Id null";
        intervenant.setPoste(data.getPoste());
        intervenant.setEtat(data.isEtat());
        intervenant.setTableDescriptionEp(data.getTableDescriptionEp());
        return intervenantRepo.save(intervenant);
    }

    @Override
    public Intervenant getByPosteTdep(Long idPoste, Long idTdep){
    return intervenantRepo.getIntervenantByPosteTdep(idPoste,idTdep);
    }


}
