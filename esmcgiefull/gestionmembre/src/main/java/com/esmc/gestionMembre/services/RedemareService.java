package com.esmc.gestionMembre.services;

import com.esmc.gestionMembre.entities.*;
import com.esmc.gestionMembre.feign.KsuRestClient;
import com.esmc.gestionMembre.input.Result;
import com.esmc.gestionMembre.repositories.CreditReppository;
import com.esmc.gestionMembre.repositories.PlaceRepository;
import com.esmc.gestionMembre.serviceInterfaces.ExistenceMembreServiceInterface;
import com.esmc.gestionMembre.serviceInterfaces.recherche.RedemareServiceInterface;
import com.esmc.models.Formatter;
import com.esmc.models.Ksu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RedemareService implements RedemareServiceInterface {

    @Autowired
    public CreditReppository creditReppository;

    @Autowired
    public PlaceRepository placeRepository;

    @Autowired
    private ExistenceMembreServiceInterface existenceMembreServiceInterface;

    @Autowired
    private KsuRestClient ksuRestClient;



    /**
     * @param code
     * @return
     */

    /**
     * @author Amemorte

     *
     *  reviews et amelioration
     */
    @Override
    public Result<Long, List<Credit>> getCreditCNCS(String code, Long idKsu) {

        List<Credit> listCredit = new ArrayList<Credit>();

        Result<Long, List<Credit>> result = new Result<Long, List<Credit>>();

        Physique objPred = existenceMembreServiceInterface.RedemarrepersonnePhysique(code);

        if (objPred == null) {
            result.setKey(0L);
            result.setValue(null);
        }

        Ksu k = ksuRestClient.getById(idKsu);

        if (objPred == null) {
            result.setKey(3L);
            result.setValue(null);
        }


        if (code.contains("P") == true) {

            String nomk = k.getNom().replace(" ", "");

            String nom_membre = objPred.getNom().replace(" ", "");

            String prenom = k.getPrenom().replace(" ", "");

            String premon_membre = objPred.getPrenom().replace(" ", "");


            if (nomk.equalsIgnoreCase(nom_membre) && prenom.equalsIgnoreCase(premon_membre)) {
                listCredit = creditReppository.passifsRedemarreCncs(code);

                if (listCredit.size() > 0) {
                    result.setKey(1L);
                    result.setValue(listCredit);
                } else {
                    result.setKey(2L);
                    result.setValue(null);
                }
            }
        }  else {
            result.setKey(4L);
            result.setValue(null);
        }


        return result;
    }

    /**
     * @param code
     * @return
     */
    @Override
    public Result<Long, List<Place>> getPlaceCnp(String code, Long idKsu) {

        List<Place> listPlace = new ArrayList<Place>();

        Result<Long, List<Place>> result = new Result<Long, List<Place>>();

        Physique objPred = existenceMembreServiceInterface.RedemarrepersonnePhysique(code);

        if (objPred == null) {
            result.setKey(0L);
            result.setValue(null);
        }
        Ksu k = ksuRestClient.getById(idKsu);

        if (k == null) {
            result.setKey(6L);
            result.setValue(null);
            return result;
        }

        Morale objMred = existenceMembreServiceInterface.RedemarrepersonneMorale(code);

        if (objMred == null) {
            result.setKey(7L);
            result.setValue(null);
        }

        if (code.contains("P") == true) {


            String nomk = k.getNom().replace(" ", "");;
            String prenom = k.getPrenom().replace(" ", "");
            String nom_membre = objPred.getNom().replace(" ", "");
            String premon_membre = objPred.getPrenom().replace(" ", "");

                if (nomk.equalsIgnoreCase(nom_membre) && prenom.equalsIgnoreCase(premon_membre)) {
                    listPlace = placeRepository.passifsRedemarreCnp(code);

                    if (listPlace.size() > 0) {
                        result.setKey(1L);
                        result.setValue(listPlace);
                    } else {
                        result.setKey(2L);
                        result.setValue(null);
                    }
                }

        } else {


            Formatter<Ksu> ksa = ksuRestClient.ksuPar(k.getCodeKsuRepresentant());

            Ksu ks = ksa.getData();

            if (ks == null) {
                result.setKey(5L);
                result.setValue(null);
                return result;
            }

            String nomk = ks.getNom().replace(" ", "");

            String representant = objMred.getRepresentant().replace(" ", "");

            String prenom = ks.getPrenom().replace(" ", "");


            String obj = nomk + " " + prenom;

            if (obj.equalsIgnoreCase(representant)) {
                listPlace = placeRepository.passifsRedemarreCnp(code);

                if (listPlace.size() > 0) {
                    result.setKey(3L);
                    result.setValue(listPlace);
                } else {
                    result.setKey(4L);
                    result.setValue(null);
                }
            }

        }

        return result;
    }
}
