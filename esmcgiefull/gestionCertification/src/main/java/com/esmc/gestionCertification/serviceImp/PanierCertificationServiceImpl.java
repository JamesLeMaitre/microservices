package com.esmc.gestionCertification.serviceImp;

import com.esmc.gestionCertification.entities.Chargement;
import com.esmc.gestionCertification.entities.DataCertification;
import com.esmc.gestionCertification.entities.PanierCertification;
import com.esmc.gestionCertification.feign.PosteRestClient;
import com.esmc.gestionCertification.input.PanierInput;
import com.esmc.gestionCertification.repository.ChargementRepository;
import com.esmc.gestionCertification.repository.DataCertificationRepository;
import com.esmc.gestionCertification.repository.PanierCertificationRepository;
import com.esmc.gestionCertification.services.PanierCertificationService;
import com.esmc.models.Formatter;
import com.esmc.models.Poste;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PanierCertificationServiceImpl implements PanierCertificationService {

    @Autowired
    private PanierCertificationRepository panierCertificationRepository;

    @Autowired
    private PosteRestClient posteRestClient;

    @Autowired
    private ChargementRepository chargementRepository;

    @Autowired
    private DataCertificationRepository  dataCertificationRepository;


    @Override
    public List<PanierCertification> getPanierCertification() {
        return null;
    }

    @Override
    public PanierCertification ajouterPanierCertification(PanierInput p) {

        Chargement ch = chargementRepository.getChargementByDetailAgrAndPoste(p.getIdDetailAgr(), p.getIdPoste());
//        System.out.println("=======================");
//        System.out.println(ch);

        Formatter<Poste> poste = posteRestClient.getById(p.getIdPoste());

        PanierCertification pan = new PanierCertification();
        Poste post = poste.getData();
        pan.setLibellePoste(post.getLibelle());
        pan.setIdPoste(post.getId());
        pan.setIdDetailAgr(p.getIdDetailAgr());
        pan.setIdDetailAgrFranchiser(ch.getIdDetailAgrFranchiser());

        DataCertification dataCertification = new DataCertification();
        dataCertification.setPanierCertification(pan);
        dataCertification.setData(p.getData());
        dataCertification.setIdPoste(pan.getIdPoste());
        dataCertification.setIdDetailAgr(ch.getIdDetailAgrFranchiser());

        dataCertificationRepository.save(dataCertification);

        return panierCertificationRepository.save(pan);
    }


    @Override
    public Boolean checkPanier(PanierInput panierCertification) {
        PanierCertification panierCertification1 = panierCertificationRepository.
                getPanierCertByDetailAgrAndPoste(panierCertification.getIdDetailAgr(),
                        panierCertification.getIdPoste(),panierCertification.getIdDetailAgrFranchiser());
        return panierCertification1 == null;
    }

    @Override
    public PanierCertification getPanierCertificationbyId(Long id) {
        return null;
    }

    @Override
    public PanierCertification updatePanierCertification(Long id, PanierCertification p) {
        return null;
    }

    @Override
    public List<PanierCertification> listPanierCertification(Long idDetailAgr, Long idPoste) {
        return panierCertificationRepository.listPanierCertByDetailAgrAndPoste(idDetailAgr, idPoste);
    }

    @Override
    public PanierCertification getPanierCertification(Long idDetailAgr, Long idPoste,Long idDetailAgrFranchiser) {
        return panierCertificationRepository.getPanierCertByDetailAgrAndPoste(idDetailAgr, idPoste,idDetailAgrFranchiser);
    }

    @Override
    public void deletePanierCertification(Long id) {

    }
}
