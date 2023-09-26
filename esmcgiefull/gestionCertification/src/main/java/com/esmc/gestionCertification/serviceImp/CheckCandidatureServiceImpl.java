package com.esmc.gestionCertification.serviceImp;

import com.esmc.gestionCertification.entities.AppelCandidature;
import com.esmc.gestionCertification.entities.CheckCandidature;
import com.esmc.gestionCertification.entities.CheckTraitement;
import com.esmc.gestionCertification.feign.DetailAgrRestClient;
import com.esmc.gestionCertification.feign.KsuClient;
import com.esmc.gestionCertification.feign.PosteRestClient;
import com.esmc.gestionCertification.repository.AppelCandidatureRepository;
import com.esmc.gestionCertification.repository.ChargementRepository;
import com.esmc.gestionCertification.repository.CheckCandidatureRepository;
import com.esmc.gestionCertification.repository.CheckTraitementRepository;
import com.esmc.gestionCertification.services.CheckChargementService;
import com.esmc.gestionCertification.services.CheckTraitementService;
import com.esmc.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utiles.Messagerie;

import java.util.List;

@Service
@Transactional
public class CheckCandidatureServiceImpl extends Messagerie implements CheckChargementService {
    @Autowired
    private CheckCandidatureRepository checkChargementRepository;
    @Autowired
    private AppelCandidatureRepository appelCandidatureRepository;
    @Autowired
    private ChargementRepository chargementRepository;

    @Autowired
    private PosteRestClient posteRestClient;

    @Autowired
    private DetailAgrRestClient detailAgrRestClient;

    @Autowired
    private CheckTraitementRepository checkTraitementRepository;
    @Autowired
    private CheckTraitementService checkTraitementService;

    @Autowired
    private KsuClient ksuClient;
    @Override
    public List<CheckCandidature> getAll() {
        return checkChargementRepository.findAll();
    }

    @Override
    public CheckCandidature save(CheckCandidature checkCandidature) {
        AppelCandidature candidature = appelCandidatureRepository.findById(checkCandidature.getCandidature().getId()).orElse(null);
        checkCandidature.setCandidature(candidature);
        checkCandidature.setIdPoste(checkCandidature.getIdPoste());
        assert candidature != null;

        if(candidature.isPublier()){
            Ksu ksu = getKsu(checkCandidature.getIdDetailAgr());
            String off = "Votre soumission à l'apel à candidature "+ candidature.getLibelle() +" n'a pas été validé !" ;
            String on = "Votre soumission à l'apel à candidature "+ candidature.getLibelle() +" a été validé !" ;;
            String  telephone = ksu.getTelephone();
            String mail = ksu.getEmail();
            System.out.println("Mail :"+mail);

            // Poste Recepteur
//            Poste poste = posteRestClient.getById(checkCandidature.getIdposteReceveur()).getData();
//            checkCandidature.setIdposteEmetteur(poste.getId());



             CheckTraitement checkTraitement = checkTraitementRepository
                     .findCheckTraitementByCandidature(
                             checkCandidature.getDetailsAgrFranchiser(),
                             checkCandidature.getIdDetailAgr(),
                             checkCandidature.getCandidature().getId(),
                             checkCandidature.getIdPoste());

            System.out.println("CHECK TRAITEMENT CONTROLLER");
            System.out.println(checkTraitement);

            System.out.println("  ");

            if(checkCandidature.getCheckstatus()){

                if (checkTraitement != null){
                    checkCandidature.setCheckstatus(true);
                    checkTraitementService.update(
                            checkCandidature.getDetailsAgrFranchiser(),
                            checkCandidature.getIdDetailAgr(),
                            checkCandidature.getCandidature().getId(),
                            checkCandidature.getIdPoste());
                   // sendSms(telephone, on);
               sendMail(mail, "ESMC GIE", on);
                }
            } else{
                if (checkTraitement != null){
                    checkCandidature.setCheckstatus(false);
                    checkTraitementService.updateError(
                            checkCandidature.getDetailsAgrFranchiser(),
                            checkCandidature.getIdDetailAgr(),
                            checkCandidature.getCandidature().getId(),
                            checkCandidature.getIdPoste());
           //         sendSms(telephone, off);
                sendMail(mail, "ESMC GIE", off);
                }

            }
            return checkChargementRepository.save(checkCandidature);
        } else {
            return null;
        }

    }

    @Override
    public Object postePostule(Long idDetailAgr) {
        return posteRestClient.getById(idDetailAgr).getData();
    }

    @Override
    public Boolean checkExist(Long idPoste) {
        boolean res = false;
        try{
            checkChargementRepository.verifIfExist(idPoste);
            res = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public Ksu getKsu(Long idDetailAgr) {
        DetailsAgr detailsAgr = detailAgrRestClient.findDetailAgrById(idDetailAgr);
        return ksuClient.getById(detailsAgr.getKsu());
    }
}
