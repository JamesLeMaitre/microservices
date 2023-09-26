package com.esmc.gestionCertification.serviceImp;


import com.esmc.gestionCertification.entities.AppelCandidature;
import com.esmc.gestionCertification.feign.DetailAgrRestClient;
import com.esmc.gestionCertification.repository.AppelCandidatureRepository;
import com.esmc.gestionCertification.services.AppelCandidatureService;
import com.esmc.models.DetailsAgr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AppelCandidatureServiceImpl implements AppelCandidatureService {

    @Autowired
    private AppelCandidatureRepository appelCandidatureRepository;

    @Autowired
    private DetailAgrRestClient detailAgrRestClient;


    @Override
    public List<AppelCandidature> getAppelCandidature() {
        return appelCandidatureRepository.listAppelACadiaturePublier();
    }

    @Override
    public AppelCandidature ajouterAppelCandidature(AppelCandidature appelCandidature) {

        DetailsAgr detailsAgr = detailAgrRestClient.findDetailAgrById(appelCandidature.getIdDetailAgrFranchiser());

        if (detailsAgr.isFranchise()) {
            appelCandidature.setIdDetailAgrFranchiser(detailsAgr.getId());
        }

        return appelCandidatureRepository.save(appelCandidature);
    }

    @Override
    public AppelCandidature getAppelCandidaturebyId(Long id) {
        return appelCandidatureRepository.findById(id).get();
    }

    @Override
    public List<AppelCandidature> listAppelCandidaturebyId(Long id) {
        return appelCandidatureRepository.listAppelACadiatureById(id);
    }

    @Override
    public void deleteAppelCandidature(Long id) {
        appelCandidatureRepository.deleteById(id);
    }

    @Override
    public AppelCandidature publicationAppelCandidaure(Long idAp) {
        AppelCandidature appelCandidature = appelCandidatureRepository.findById(idAp).orElse(null);
        appelCandidature.setPublier(true);
        return appelCandidatureRepository.save(appelCandidature);
    }
}
