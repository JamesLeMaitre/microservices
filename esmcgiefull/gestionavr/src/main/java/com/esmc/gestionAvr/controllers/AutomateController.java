package com.esmc.gestionAvr.controllers;

import com.esmc.gestionAvr.entities.Avr;
import com.esmc.gestionAvr.entities.Fifo;
import com.esmc.gestionAvr.feign.DetailAgrClient;
import com.esmc.gestionAvr.feign.KsuClient;
import com.esmc.gestionAvr.feign.TeClient;
import com.esmc.gestionAvr.repositories.AvrRepository;
import com.esmc.gestionAvr.services.AvrServices;
import com.esmc.gestionAvr.services.FifoService;
import com.esmc.models.DetailsAgr;
import com.esmc.models.Ksu;
import constants.SupportMarchandConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/automate/demo")
public class AutomateController {


    @Autowired
    KsuClient ksuClient;

    @Autowired
    private AvrServices avrService;

    @Autowired
    private AvrRepository avrRepository;

    @Autowired
    private FifoService fifoService;
    @Autowired
    private AvrController avrController;

    @Autowired
    TeClient teClient;


    @Autowired
    DetailAgrClient detailAgrClient;

    @GetMapping("run")
    /*@Scheduled(cron = "0/10 * * * * * ")*/
    public void operateTransaction() {

        //Get last Vente and last
        //Fifo lastVente fifo
        /**
         *                  MODE DE FONCTIONNEMENT DE L'ALGORITHME
         *                  Nous allons recuperer en premier la premiere vente courant du systeme
         *                  et allons le servir grace aux achats
         *                  le temps estime de l'action est de 1000 ms soit 1 seconde pour le mode test
         *
         *                  une fois satisfait nous supprimons l'enregistrement fifo correspondant aux actions
         *                  menant a cet aboutissement
         *
         *                  cette fonction a pour objectif e statisfaire la premiere vente avec tous les
         *                  achats possibles
         *
         *                  A la fin de l'operation le vendeur recupere un bon de bci et l'acheteur un bon de ban
         *
         *
         * */


        System.out.println("Processus Fifo");
        // recuperation de la vente a traiter
        Fifo currentFirstVente = fifoService.getFirst(false);
        DetailsAgr detailAgrVente = detailAgrClient.getDetailById(currentFirstVente.getAvr().getDetailAgr());
        Ksu ksuVente = ksuClient.getById(detailAgrVente.getKsu());
        System.out.println(ksuVente);

        System.out.println(currentFirstVente);
        if (currentFirstVente == null) return;
        Long currentFirstVenteTe;
        currentFirstVenteTe = teClient.getTeByDetailAgr(currentFirstVente.getDetailAgr()).getId();


        System.out.println(" ------------------------------------------ Vente traité -------------------------------");
        System.out.println("Fifo Vente Id : " + currentFirstVente.getId() + " ---  Te : " + currentFirstVente.getAvr().getId() + "--- Avr:" + currentFirstVente.getAvr().getLibelle() + " --- Montant : " + currentFirstVente.getDefaultAmountFirstType());


        //Montant a traiter
        Double venteAmountFirst = currentFirstVente.getActionAmountFirstType();//match to current Ban vakue
        Double venteAmountSecond = currentFirstVente.getActionAmountSecondType();//match to current BCI vakue
        System.out.println("----------------------------------------- FIFO ACHAT REQUIS POUR SON TRAITEMENT -------------------------------");
        while (venteAmountFirst > 0) {
            System.out.println("boucle launsh" + venteAmountFirst);
            //recuperation de l'achat courant
            Fifo currentFirstAchat = fifoService.getFirstDiffKsu(ksuVente.getId());
            DetailsAgr detailAgrAchat = detailAgrClient.getDetailById(currentFirstAchat.getAvr().getDetailAgr());
            Ksu ksuAchat = ksuClient.getById(detailAgrAchat.getKsu());
            System.out.println("Ksu Achat =" + ksuAchat);
            if (ksuVente == ksuAchat) {
                System.out.println("C'est le même Ksu");
                break;
            }
            if (currentFirstAchat == null) {
                System.out.println("Pas d'achat dispo");
                break;
            }
            System.out.println("Fifo Achat Found/" + currentFirstAchat.toString());
            Long currentFirstAchatTe = teClient.getTeByDetailAgr(currentFirstAchat.getDetailAgr()).getId();
            Double achatAmountFirst = currentFirstAchat.getActionAmountFirstType();
            Double achatAmountSecond = currentFirstAchat.getActionAmountSecondType();
            Double tempAchatAmount = achatAmountFirst;  //Ban Value

            if (achatAmountFirst <= venteAmountFirst) {
                venteAmountFirst = venteAmountFirst - achatAmountFirst;
                venteAmountSecond = venteAmountSecond - achatAmountSecond;
                //Get amount for transactions fifo achat used
                Double AcheteurAmountBci = achatAmountSecond;
                Double VendeurAmountBan = achatAmountFirst;
                fifoService.CreateBonV2("BC", currentFirstVente, 1, currentFirstAchat, AcheteurAmountBci, VendeurAmountBan);
                fifoService.CreateBonV2("BL", currentFirstAchat, 1, currentFirstVente, VendeurAmountBan, AcheteurAmountBci);
                fifoService.CreateBonV2("BRBL", currentFirstVente, 1, currentFirstAchat, AcheteurAmountBci, VendeurAmountBan);
                fifoService.CreateBonV2("FD", currentFirstAchat, 1, currentFirstVente, VendeurAmountBan, AcheteurAmountBci);
                fifoService.CreateBonV2("BESM", currentFirstVente, 1, currentFirstAchat, AcheteurAmountBci, VendeurAmountBan);
                fifoService.CreateBonV2("BRBE", currentFirstAchat, 1, currentFirstVente, VendeurAmountBan, AcheteurAmountBci);

                //vendeur

                teClient.registerNewDecaissement(AcheteurAmountBci, SupportMarchandConstant.supportMarchandBCi, currentFirstVenteTe);
                teClient.registerEncaissement(VendeurAmountBan, SupportMarchandConstant.supportMarchandMPRg, currentFirstVenteTe);
                //Achat

                teClient.registerNewDecaissement(VendeurAmountBan, SupportMarchandConstant.supportMarchandBan, currentFirstAchatTe);
                teClient.registerEncaissement(AcheteurAmountBci, SupportMarchandConstant.supportMarchandBCi, currentFirstAchatTe);

                //emission des bons
//                fifoService.removeById(currentFirstAchat.getId());
                currentFirstAchat.setTreated(true);
                fifoService.saveFifo(currentFirstAchat);
                System.out.println("\t Achat utilisé: " + currentFirstAchat.getId() + "\tMontant:" + currentFirstAchat.getActionAmountFirstType() + "\tReste a traité:" + venteAmountFirst);
            } else {
                achatAmountFirst = achatAmountFirst - venteAmountFirst;
                achatAmountSecond = achatAmountSecond - venteAmountSecond;
                Double AcheteurAmountBci = achatAmountSecond;
                Double VendeurAmountBan = achatAmountFirst;
                //emission des bons
                currentFirstAchat.setActionAmountFirstType(achatAmountFirst);
                currentFirstAchat.setActionAmountSecondType(achatAmountSecond);
                fifoService.CreateBonV2("BC", currentFirstVente, 1, currentFirstAchat, AcheteurAmountBci, VendeurAmountBan);
                fifoService.CreateBonV2("BL", currentFirstAchat, 1, currentFirstVente, VendeurAmountBan, AcheteurAmountBci);
                fifoService.CreateBonV2("BRBL", currentFirstVente, 1, currentFirstAchat, AcheteurAmountBci, VendeurAmountBan);
                fifoService.CreateBonV2("FD", currentFirstAchat, 1, currentFirstVente, VendeurAmountBan, AcheteurAmountBci);
                fifoService.CreateBonV2("BESM", currentFirstVente, 1, currentFirstAchat, AcheteurAmountBci, VendeurAmountBan);
                fifoService.CreateBonV2("BRBE", currentFirstAchat, 1, currentFirstVente, VendeurAmountBan, AcheteurAmountBci);

                //vendeur

                teClient.registerNewDecaissement(AcheteurAmountBci, SupportMarchandConstant.supportMarchandBCi, currentFirstVenteTe);
                teClient.registerEncaissement(VendeurAmountBan, SupportMarchandConstant.supportMarchandMPRg, currentFirstVenteTe);
                //Achat

                teClient.registerNewDecaissement(VendeurAmountBan, SupportMarchandConstant.supportMarchandBan, currentFirstAchatTe);
                teClient.registerEncaissement(AcheteurAmountBci, SupportMarchandConstant.supportMarchandBCi, currentFirstAchatTe);
                fifoService.saveFifo(currentFirstAchat);
                venteAmountFirst = 0.0;
                venteAmountSecond = 0.0;
                System.out.println("\t ----------------- Finalisation Achat utilisé: " + currentFirstAchat.getId() + "\tMontant initial: " + tempAchatAmount + "\t Montant Restant dans l'achat :" + currentFirstAchat.getActionAmountFirstType());

                break;
            }
        }
        System.out.println("-------------------------------------------------------------------------------------------------------");

        if (venteAmountFirst <= 0) {
            //SUPPRESSION DU FIFO TRAITE
            fifoService.removeById(currentFirstVente.getId());
        } else {
            currentFirstVente.setActionAmountFirstType(venteAmountFirst);
            currentFirstVente.setActionAmountSecondType(venteAmountSecond);
            fifoService.saveFifo(currentFirstVente);
        }

        // FIFO - AVR TRAITER
        Avr avr = avrService.findAvrById(currentFirstVente.getAvr().getId());
        avr.setEtat(false);
        avrRepository.save(avr);
        System.out.println("--------------------------------------------------------------------------------------------------");
        return;


    }

}