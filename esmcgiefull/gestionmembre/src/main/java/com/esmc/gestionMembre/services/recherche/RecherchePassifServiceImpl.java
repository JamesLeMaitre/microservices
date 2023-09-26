package com.esmc.gestionMembre.services.recherche;


import com.esmc.gestionMembre.entities.*;
import com.esmc.gestionMembre.input.Result;
import com.esmc.gestionMembre.model.RechercheInputModel;
import com.esmc.gestionMembre.serviceInterfaces.*;
import com.esmc.gestionMembre.serviceInterfaces.recherche.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

import static com.esmc.gestionMembre.utils.Constants.*;

/**
 * @author Amemorte99
 */
@Service
@Transactional
public class RecherchePassifServiceImpl implements RecherchePassifService {

    @Autowired
    private MoraleServiceInterface moraleServiceInterface;


    @Autowired
    private PhysiqueServiceInterface physiqueServiceInterface;

    @Autowired
    private PlaceServiceInterface placeServiceInterface;


    @Autowired
    private CreditServiceInterface creditServiceInterface;







//   	 MCNP


    @Autowired
    private AncienDetailsSmsmoneyService ancienDetailsSmsmoneyService;


    @Autowired
    private DetailMf107ServiceInterface detailMf107ServiceInterface;

    @Autowired
    private MembreFondateur107ServiceInterface membreFondateur107ServiceInterface;


    @Autowired
    private MembreFondateur11000ServiceInterface membreFondateur11000ServiceInterface;


    @Autowired
    private RepartitionMf107ServiceInterface repartitionMf107ServiceInterface;


    @Autowired
    private RepartitionMF11000ServiceInterface repartitionMF11000ServiceInterface;

    @Autowired
    private AncienMembreServiceInterface ancienMembreServiceInterface;

    @Autowired
    private AncienCompteCreditServiceInterface ancienCompteCreditServiceInterface;


    @Autowired
    private AncienGcpServiceInterface gcpServiceInterface;


    @Autowired
    private AncienEscompteServiceInterface escompteServiceInterface;

    @Autowired
    private AncienEchangeServiceInterface ancienEchangeServiceInterface;




   // ESMC SARLU
    @Autowired
    private MembreServiceInterface membreServiceInterface;

    @Autowired
    private MembreMoraleInterface membreMoraleInterface;


    @Autowired
    private SouscriptionServiceInterface souscriptionServiceInterface;

    @Autowired
    private IntegrateurServiceInterface integrateurServiceInterface;
    @Autowired
    private BonNeutreServiceInterface bonNeutreServiceInterface;

    @Autowired
    private CompteCreditServiceInterface compteCreditServiceInterface;

    @Autowired
    private GcpServiceInterface anInterface;







    @Override
    public Result<Long,List<Object>> getRechercheByCodeMemebre(RechercheInputModel rechercheInputModel) {

        List<Object> objects=new ArrayList<>();



        Result<Long, List<Object>> result = new Result();
        switch (rechercheInputModel.getTypeSysteme()){

                case REDEMARREVALUE:

                    if(rechercheInputModel.getTypePersonne().equals(REDEMARRETABLEPHISIQUE)){
                        Physique  physique;

                        if(rechercheInputModel.getCodeMembreOrName()==null) {
                            physique=physiqueServiceInterface.getReDeMaRePhysiqueByNom(rechercheInputModel.getNom(),rechercheInputModel.getPrenom());

                        }else{

                            physique=physiqueServiceInterface.getReDeMaRePhysiqueByCode(rechercheInputModel.getCodeMembreOrName());

                        }


                        List<Credit> credits=creditServiceInterface.getCreditByCodeMemebre(physique.getNumIdentp());

                        List<Place> places=placeServiceInterface.getPacleByCodeMemebre(physique.getNumIdentp());

                        objects.add(credits);
                        objects.add(places);

                        if(objects==null){
                            result.setKey(3L);

                        }else {
                            result.setKey(2l);
                        }

                        result.setValue(objects);
                        return result;

                    } else {
                        Morale morale;


                        if(rechercheInputModel.getCodeMembreOrName()==null) {
                             morale=moraleServiceInterface.getReDeMaReByMoraleNom(rechercheInputModel.getNom());

                        }else {

                             morale=moraleServiceInterface.getReDeMaReByMoraleNom(rechercheInputModel.getCodeMembreOrName());

                        }


                        List<Credit> credits=creditServiceInterface.getCreditByCodeMemebre(morale.getNumIdentm());

                        List<Place> places=placeServiceInterface.getPacleByCodeMemebre(morale.getNumIdentm());

                        objects.add(credits);
                        objects.add(places);
                        if(objects==null){
                            result.setKey(3L);

                        } else if (objects==null && morale==null) {
                            result.setKey(1L);
                        }else {
                            result.setKey(2l);
                        }

                        result.setValue(objects);
                        return result;

                    }

            case MCNPVALUE:


                AncienMembre ancienMembre;
                if(rechercheInputModel.getCodeMembreOrName()==null){

                    ancienMembre =ancienMembreServiceInterface.ancienMembreByNameAndLastName(rechercheInputModel.getNom(),rechercheInputModel.getPrenom());

                }else {
                    ancienMembre =ancienMembreServiceInterface.ancienMembreByCodeOrName(rechercheInputModel.getCodeMembreOrName());

                }




                ///MF11000
                List<MembreFondateur11000> membreFondateur11000s=membreFondateur11000ServiceInterface.getByMembreFondateur11000CodeMembre(ancienMembre.getCodeMembre());
                List<RepartitionMF11000> repartitionMF11000s=repartitionMF11000ServiceInterface.getRepartitionMF11000ByCode(ancienMembre.getCodeMembre());
                List<AncienDetailsSmsmoney> ancienDetailsSmsmonies=ancienDetailsSmsmoneyService.getByAncienDetailsSmsmoneyCodeMembre(ancienMembre.getCodeMembre());

                objects.add(membreFondateur11000s);
                objects.add(repartitionMF11000s);
                objects.add(ancienDetailsSmsmonies);

                //MF107
                List<MembreFondateur107> membreFondateur107s=membreFondateur107ServiceInterface.getByMembreFondateur107Code(ancienMembre.getCodeMembre());
                List<RepartitionMf107> repartitionMf107s=repartitionMf107ServiceInterface.getRepartitionMf107ByCode(ancienMembre.getCodeMembre());
                List<DetailMf107> detailMf107s=detailMf107ServiceInterface.getDetailMf107ByCode(ancienMembre.getCodeMembre());


                objects.add(membreFondateur107s);
                objects.add(repartitionMf107s);
                objects.add(detailMf107s);


                //credit
                List<AncienCompteCredit> ancienCompteCredits=ancienCompteCreditServiceInterface.getAncienCompteCreditByCode(ancienMembre.getCodeMembre());
                List<AncienEchange> ancienEchanges=ancienEchangeServiceInterface.getAncienEchangeByCode(ancienMembre.getCodeMembre());
                List<AncienEscompte> ancienEscomptes=escompteServiceInterface.getAncienEscompteByCode(ancienMembre.getCodeMembre());
                List<AncienGcp> ancienGcps=gcpServiceInterface.getAncienGcpByCode(ancienMembre.getCodeMembre());




                objects.add(ancienCompteCredits);
                objects.add(ancienEchanges);
                objects.add(ancienEscomptes);
                objects.add(ancienGcps);

                if(objects==null){
                    result.setKey(3L);

                } else if (objects==null && ancienMembre==null) {
                    result.setKey(1L);
                }else {
                    result.setKey(2l);
                }

                result.setValue(objects);
                return result;


            case ESMCSARLUVALUE:
                if(rechercheInputModel.getTypePersonne().equals(ESMCSARLUTABLEPHYSIQUE)){
                    Membre membre;

                    if(rechercheInputModel.getCodeMembreOrName()==null){
                         membre=membreServiceInterface.getMembreByCodeMembreOrNomPrenom(rechercheInputModel.getNom(),rechercheInputModel.getPrenom());
                    }else {
                        membre=membreServiceInterface.getMembreByCodeMembreOrNomMembre(rechercheInputModel.getCodeMembreOrName());
                    }



                    List<Souscription> souscriptions=souscriptionServiceInterface.getSouscriptionByCodeOrName(membre.getCodeMembre());
                    List<Integrateur> integrateurs=integrateurServiceInterface.getIntegrateurByCodeMembre(membre.getCodeMembre());
                    List<BonNeutre> bonNeutres=bonNeutreServiceInterface.getBonNeutreByCode(membre.getCodeMembre());
                    List<CompteCredit> compteCredits=compteCreditServiceInterface.getCompteCreditByCode(membre.getCodeMembre());
                    List<Gcp> gcps=anInterface.getGcpByCode(membre.getCodeMembre());

                    objects.add(souscriptions);
                    objects.add(integrateurs);
                    objects.add(bonNeutres);
                    objects.add(compteCredits);
                    objects.add(gcps);

                    if(objects==null){
                        result.setKey(3L);

                    } else if (objects==null && membre==null) {
                        result.setKey(1L);
                    }else {
                        result.setKey(2l);
                    }

                    result.setValue(objects);
                    return result;


                }else {

                    MembreMorale morale;

                    if(rechercheInputModel.getCodeMembreOrName()==null){
                        morale=membreMoraleInterface.getMembreByCodeMembreMoraleOrNomMembre(rechercheInputModel.getNom());
                    }else {
                        morale= membreMoraleInterface.getMembreByCodeMembreMoraleOrNomMembre(rechercheInputModel.getCodeMembreOrName());
                    }


                    List<Souscription> souscriptions=souscriptionServiceInterface.getSouscriptionByCodeOrName(morale.getCodeMembreMorale());
                    List<Integrateur> integrateurs=integrateurServiceInterface.getIntegrateurByCodeMembre(morale.getCodeMembreMorale());
                    List<BonNeutre> bonNeutres=bonNeutreServiceInterface.getBonNeutreByCode(morale.getCodeMembreMorale());
                    List<CompteCredit> compteCredits=compteCreditServiceInterface.getCompteCreditByCode(morale.getCodeMembreMorale());
                    List<Gcp> gcps=anInterface.getGcpByCode(morale.getCodeMembreMorale());




                    objects.add(souscriptions);
                    objects.add(integrateurs);
                    objects.add(bonNeutres);
                    objects.add(compteCredits);
                    objects.add(gcps);

                    if(objects==null){
                        result.setKey(3L);

                    } else if (objects==null && morale==null) {
                        result.setKey(1L);
                    }else {
                        result.setKey(2l);
                    }

                    result.setValue(objects);
                    return result;



                }



        }

        return result;

    }





    @Override
    public Result<Long,Object> rechercheCompteMarchand(RechercheInputModel rechercheInputModel) {

        Object object;



        Result<Long, Object> result = new Result();
        switch (rechercheInputModel.getTypeSysteme()){

            case REDEMARREVALUE:

                if(rechercheInputModel.getTypePersonne().equals(REDEMARRETABLEPHISIQUE)){

                    Physique  physique;

                    if(rechercheInputModel.getCodeMembreOrName()==null){
                        physique =physiqueServiceInterface.getReDeMaRePhysiqueByNom(rechercheInputModel.getNom(),rechercheInputModel.getPrenom());

                    }else {

                        physique =physiqueServiceInterface.getReDeMaRePhysiqueByCode(rechercheInputModel.getCodeMembreOrName());

                    }



                   object=physique;

                   result.setValue(object);


                    if(object==null){
                        result.setKey(0L);

                    }else {
                        result.setKey(2l);
                    }


                    return result;

                } else {

                    if(rechercheInputModel.getCodeMembreOrName()==null){
                        Morale morale=moraleServiceInterface.getReDeMaReByMoraleNom(rechercheInputModel.getNom());
                        object=morale;

                    }else {

                        Morale morale=moraleServiceInterface.getReDeMaReByMoraleNom(rechercheInputModel.getCodeMembreOrName());
                        object=morale;
                    }





                    result.setValue(object);
                    if(object==null){
                        result.setKey(0L);

                    }else {
                        result.setKey(2l);
                    }


                    return result;

                }

            case MCNPVALUE:

                AncienMembre ancienMembre;
                if(rechercheInputModel.getCodeMembreOrName()==null){

                    ancienMembre =ancienMembreServiceInterface.ancienMembreByNameAndLastName(rechercheInputModel.getNom(),rechercheInputModel.getPrenom());

                }else {
                    ancienMembre =ancienMembreServiceInterface.ancienMembreByCodeOrName(rechercheInputModel.getCodeMembreOrName());

                }


                object=ancienMembre;

                result.setValue(object);

                if(object==null){
                    result.setKey(1L);

                }else {
                    result.setKey(2l);
                }


                return result;


            case ESMCSARLUVALUE:
                if(rechercheInputModel.getTypePersonne().equals(ESMCSARLUTABLEPHYSIQUE)){


                    Membre membre;

                    if(rechercheInputModel.getCodeMembreOrName()==null){
                        membre=membreServiceInterface.getMembreByCodeMembreOrNomPrenom(rechercheInputModel.getNom(),rechercheInputModel.getPrenom());
                    }else {
                        membre=membreServiceInterface.getMembreByCodeMembreOrNomMembre(rechercheInputModel.getCodeMembreOrName());
                    }

                    object=membre;

                    result.setValue(object);

                    if(object==null){
                        result.setKey(3L);

                    } else {
                        result.setKey(2l);
                    }


                    return result;


                }else {
                    MembreMorale morale=new MembreMorale();
                    if (rechercheInputModel.getCodeMembreOrName()==null){
                        morale    =membreMoraleInterface.getMembreByCodeMembreMoraleOrNomMembre(rechercheInputModel.getNom());

                    }else {

                        morale    =membreMoraleInterface.getMembreByCodeMembreMoraleOrNomMembre(rechercheInputModel.getCodeMembreOrName());


                    }


                    object=morale;

                    result.setValue(object);

                    if(morale==null){
                        result.setKey(3L);

                    }else {
                        result.setKey(2l);
                    }


                    return result;



                }



        }

        return result;

    }






}
