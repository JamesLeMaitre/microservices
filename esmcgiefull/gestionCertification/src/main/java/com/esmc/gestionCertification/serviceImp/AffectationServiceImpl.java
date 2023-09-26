package com.esmc.gestionCertification.serviceImp;


import com.esmc.gestionCertification.entities.Affectation;
import com.esmc.gestionCertification.entities.Chargement;
import com.esmc.gestionCertification.feign.DetailAgrRestClient;
import com.esmc.gestionCertification.feign.FormationRestClient;
import com.esmc.gestionCertification.feign.KsuClient;
import com.esmc.gestionCertification.feign.PosteRestClient;
import com.esmc.gestionCertification.input.AffectationInput;
import com.esmc.gestionCertification.repository.AffectationRepository;
import com.esmc.gestionCertification.repository.AppelCandidatureRepository;
import com.esmc.gestionCertification.repository.ChargementRepository;
import com.esmc.gestionCertification.services.AffectationService;
import com.esmc.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AffectationServiceImpl implements AffectationService {
    @Autowired
    private AffectationRepository affectationRepository;

    @Autowired

    private ChargementRepository chargementRepository;

    @Autowired
    private AppelCandidatureRepository appelCandidatureRepository;

    @Autowired
    private PosteRestClient posteRestClient;

    @Autowired
    private DetailAgrRestClient detailAgrRestClient;

    @Autowired
    private KsuClient ksuClient;

    @Autowired
    private  FormationRestClient formationRestClient;


    @Override
    public List<Affectation> getAffectation() {
        return affectationRepository.findAll();
    }

    @Override
    public Affectation ajouterAffectation(AffectationInput affectation) {
        DetailsAgr detailsAgr = detailAgrRestClient.findDetailAgrById(affectation.getIdDetailAgrFranchiser());

        Affectation aff = null;

       Long [] poste = affectation.getIdPoste();

        //System.out.println(poste.length);

      if (detailsAgr.isFranchise()) {
            for (Long id: poste) {
                Affectation af = new Affectation();
                af.setIdPoste(id);
                af.setCandidature(affectation.getCandidature());
                af.setIdDetailAgrFranchiser(detailsAgr.getId());
                aff = affectationRepository.save(af);


            }
        }
        return aff;
    }



    @Override
    public int countAlready(AffectationInput affectation) {
//       Long [] poste = affectation.getIdPoste();
//        int count = 0;
//        for(Long id : poste){
//            count +=  checkChargementRepository.countCheck(id);
//        }
        return 1;
    }

    @Override
    public Affectation getAffectationbyId(Long id) {

          return affectationRepository.findById(id).get();
    }

    @Override
    public void deleteAffectation(Long id) {
        affectationRepository.deleteById(id);
    }

    @Override
    public List<Affectation> listAffectationCandidature(Long id) {
        return affectationRepository.findAffectationByCandidature(id);
    }

    @Override
    public Object listPosteByAppelCadidature(Long idAc,Long idAgr) {

        List<Poste> listp = new ArrayList<Poste>();

        List<Affectation> listaf = affectationRepository.findAffectationByCandidature(idAc);

        for (Affectation a : listaf) {

            Formatter<Poste> p = posteRestClient.getById(a.getIdPoste());

          //  List<CheckChargement> checkChargements = checkChargementRepository.getWithoutSaved(a.getCandidature().getId());


            Poste poste = p.getData();
            System.out.println(poste);


            Chargement chargement=chargementRepository.findChargementByPosteDAndDetailAgr(a.getIdPoste(),idAgr);




            if (chargement==null){
                listp.add(poste);

            }

          /*     for(CheckChargement id : checkChargements){
                    List<Affectation> afs = checkChargementRepository.getWithoutSaved(id.getIdposte());
                }*/
        }

        return listp;
    }

    public <T> T getFirstElement(List<T> listElements){
        T element = null;
        if(listElements.size() > 0){
            element = listElements.get(0);
        }

        return element;
    }

    @Override
    public Affectation getLibelle(Long idAc) {
        Affectation ac = getFirstElement(listAffectationCandidature(idAc));

        //  String a = ac.getCandidature().getLibelle();
        return ac;
    }

    @Override
    public Object getKSU(Long idDetAgr) {
        DetailsAgr d = detailAgrRestClient.findDetailAgrById(idDetAgr);
        Ksu ksuCl = ksuClient.getById(d.getKsu());
        return ksuCl;
    }






}
