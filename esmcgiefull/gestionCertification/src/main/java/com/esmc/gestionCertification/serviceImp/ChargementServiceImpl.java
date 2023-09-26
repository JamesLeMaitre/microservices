package com.esmc.gestionCertification.serviceImp;

import com.esmc.gestionCertification.entities.AppelCandidature;
import com.esmc.gestionCertification.entities.Chargement;
import com.esmc.gestionCertification.exceptions.AppelCandidatureException;
import com.esmc.gestionCertification.feign.PosteRestClient;
import com.esmc.gestionCertification.input.ChargementPostInput;
import com.esmc.gestionCertification.repository.*;
import com.esmc.gestionCertification.request.ChargementRequest;
import com.esmc.gestionCertification.services.ChargementService;
import com.esmc.models.Formatter;
import com.esmc.models.Poste;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ChargementServiceImpl implements ChargementService {

    @Autowired
    private ChargementRepository chargementRepository;

    @Autowired
    private PosteRestClient posteRestClient;

    @Autowired
    private TypeChargementRepository typeChargementRepository;

    @Autowired
    private AppelCandidatureRepository appelCandidatureRepository;

    @Autowired
    private CheckCandidatureRepository checkCandidatureRepository;
    @Autowired
    private CheckTraitementRepository checkTraitementRepository;

    @Override
    public List<Chargement> getChargement() {
        return chargementRepository.findAll();
    }

    @Override
    public Chargement ajouterChargementAppelCandidature(Chargement chargement) {

//        Chargement chargement1 = chargementRepository.findChargementByDetailAgr(chargement.getDetailAgr());


            AppelCandidature appelCandidature = appelCandidatureRepository.findById(chargement.getCandidature().getId()).orElse(null);

            assert appelCandidature != null :"ID null";
        System.out.println("==========");
        System.out.println(appelCandidature.getIdDetailAgrFranchiser());

            chargement.setIdDetailAgrFranchiser(appelCandidature.getIdDetailAgrFranchiser());
        chargement.setEtat(true);

            appelCandidature.setEtat("1");
            appelCandidatureRepository.save(appelCandidature);

        return   chargementRepository.save(chargement);
    }

    @Override
    public Chargement addChargement(ChargementRequest chargementRequest) throws AppelCandidatureException {
        AppelCandidature appelCandidature = appelCandidatureRepository.findById(chargementRequest.getCandidature().getId()).orElseThrow(()-> new AppelCandidatureException("Not found"));
        System.out.println("A" + appelCandidature);
        appelCandidature.setEtat("1");
        appelCandidatureRepository.save(appelCandidature);
        Chargement chargement = Chargement.builder()
                .typeChargement(chargementRequest.getTypeChargement())
                .etat(true)
                .candidature(chargementRequest.getCandidature())
                .idPoste(chargementRequest.getIdPoste())
                .idDetailAgrFranchiser(chargementRequest.getIdDetailAgrFranchiser())
                .libellePoste(chargementRequest.getLibellePoste())
                .detailAgr(chargementRequest.getDetailAgr())
                .build();
        return chargementRepository.save(chargement);
    }

    @Override
    public Chargement ajouterChargementPoste(ChargementPostInput chargement) {

        AppelCandidature appelCandidature = appelCandidatureRepository.findById(chargement.getCandidature().getId()).orElse(null);
        if(appelCandidature == null){
            return null;
        }

        Long[] postes = chargement.getIdPoste();

        Chargement charg = null;

        for (Long ps : postes) {

//            boolean res = checkExistPoste(ps, chargement.getDetailAgr());
//            if(res){
            Chargement chargement1 = chargementRepository.findChargementByPosteDAndDetailAgr(ps, chargement.getDetailAgr());
            if(chargement1 == null){
                Chargement ch = new Chargement();
                ch.setIdDetailAgrFranchiser(appelCandidature.getIdDetailAgrFranchiser());
                ch.setDetailAgr(chargement.getDetailAgr());
                ch.setTypeChargement(chargement.getTypeChargement());
                ch.setEtat(true);
                ch.setIdPoste(ps);
                Formatter<Poste> p = posteRestClient.getById(ps);
                Poste poste = p.getData();
                ch.setLibellePoste(poste.getLibelle());
                ch.setCandidature(chargement.getCandidature());

                charg = chargementRepository.save(ch);
            }

        }

        return charg;
    }

    @Override
    public boolean checkExist(Chargement chargement) {
        Boolean res = false;
        Chargement chargement1 = chargementRepository.findChargementByPosteDAndDetailAgrNoPoste(chargement.getDetailAgr(),chargement.getIdDetailAgrFranchiser(),chargement.getCandidature().getId());

        if (chargement1 == null){
            return !res;
        }else{
            return res;
        }
    }

    @Override
    public boolean checkExistNew(ChargementRequest chargement) {
        Chargement chargement1 = chargementRepository.findChargementByPosteDAndDetailAgrNoPoste(chargement.getDetailAgr(),chargement.getIdDetailAgrFranchiser(),chargement.getCandidature().getId());
        return chargement1 != null;
    }


    @Override
    public Chargement checkExistGet(ChargementPostInput chargement) {
        return chargementRepository.findChargementByPosteDAndDetailAgrNoPostev1(chargement.getDetailAgr(),chargement.getTypeChargement().getId(),chargement.getCandidature().getId());
    }


    @Override
    public boolean checkExistPoste(ChargementPostInput chargementPostInput) {
        Long[] postes = chargementPostInput.getIdPoste();
        int count = 0;
        for (Long ps : postes) {
            Chargement chargement1 = chargementRepository.findChargementByPosteDAndDetailAgr(ps, chargementPostInput.getDetailAgr());
            if(chargement1 == null){
                count +=1;
            }
        }
        return count > 0;
    }


    @Override
    public Chargement getChargementbyId(Long id) {

        return chargementRepository.findById(id).get();
    }

    @Override
    public void deleteChargement(Long id) {
            chargementRepository.deleteById(id);
    }

    @Override
    public List<Chargement> listTypeChargement(Long id) {
        return chargementRepository.findChargementBycandidature(id);
    }

    @Override
    public List<Chargement> listCandidature(Long id) {
        return chargementRepository.findChargementBycandidature(id);
    }

    @Override
    public List<Chargement> listPoste(Long id) {
        return chargementRepository.findChargementByPoste(id);
    }

    @Override
    public List<Chargement> listDetailAgrAndTypeChargement(Long id1, Long id2) {
        return chargementRepository.findChargementByTypeChargementAndDetailAgr(id1,id2);
    }

    @Override
    public Chargement getDetailAgrAndTypeChargement(Long id1, Long id2) {
        return chargementRepository.getChargementByTypeChargementAndDetailAgr(id1,id2);
    }

    @Override
    public List<Chargement> listChargementByIdDetailAgrAndCanditure(Long id1, Long id2) {
        return chargementRepository.findChargementByTypeChargementAndDetailAgr(id1,id2);
    }

    @Override
    public List<Chargement> listChargementByIdDetailAgrAndCanditurePoste(Long id1, Long id2) {
        return chargementRepository.listChargementByIdDetailAgrAndCanditure(id1,id2);
    }

    @Override
    public List<Chargement> listChargementByIdDetailAgr(Long idDetailAgr) {
        return chargementRepository.listChargementByIdDetailAgr(idDetailAgr);
    }

    @Override
    public List<Chargement> addAppelCandidatureAndDetailAgr(Long id, Chargement chargement) {
        return null;
    }

    @Override
    public void getById(Long id) {

    }

    @Override
    public Chargement getChargementByIdDetailAgrAndPoste(Long id1, Long id2) {
        return chargementRepository.getChargementByDetailAgrAndPoste(id1,id2);
    }

}
