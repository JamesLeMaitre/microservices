package com.esmc.gestionAvr.services;

import com.esmc.gestionAvr.controllers.AvrController;
import com.esmc.gestionAvr.entities.Avr;
import com.esmc.gestionAvr.entities.Fifo;
import com.esmc.gestionAvr.feign.TeClient;
import com.esmc.gestionAvr.repositories.AvrRepository;
import constants.SupportMarchandConstant;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
@AllArgsConstructor
public class AutomateService {
    @Autowired
    private AvrServices avrService;

    @Autowired
    private AvrRepository avrRepository;

    @Autowired
    private FifoService fifoService ;
    @Autowired
    private AvrController avrController;

    @Autowired
    TeClient teClient;

    public void operateTransactionv2(){

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
        System.out.println(currentFirstVente);
        if(currentFirstVente == null) return;
        Long currentFirstVenteTe;

        Long currentFirstVenteKsu = fifoService.getKsu(currentFirstVente.getAvr().getDetailAgr());
        currentFirstVenteTe = teClient.getTeByDetailAgr(currentFirstVente.getDetailAgr()).getId();
        System.out.println(currentFirstVenteTe+"    "+currentFirstVenteKsu);

        System.out.println(" ------------------------------------------ Vente traité -------------------------------");
        System.out.println("Fifo Vente Id : "+ currentFirstVente.getId() + " ---  Te :"+currentFirstVenteKsu+"  avr:"+currentFirstVente.getAvr().getId() + "--- Avr:"+currentFirstVente.getAvr().getLibelle()+" --- Montant : "+currentFirstVente.getDefaultAmountFirstType() );


        //Montant a traiter
        Double venteAmountFirst = currentFirstVente.getActionAmountFirstType();//match to current Ban vakue
        Double venteAmountSecond = currentFirstVente.getActionAmountSecondType();//match to current BCI vakue
        System.out.println("----------------------------------------- FIFO ACHAT REQUIS POUR SON TRAITEMENT -------------------------------");
        while(venteAmountFirst >0 ) {
            System.out.println("boucle launsh"+venteAmountFirst);
            //recuperation de l'achat courant
            Fifo currentFirstAchat = fifoService.getFirstAchatFromExtern(true,currentFirstVenteKsu );
            if(currentFirstAchat == null) break;

            System.out.println("Fifo Achat Found "+currentFirstAchat.toString());
            Long currentFirstAchatTe = teClient.getTeByDetailAgr(currentFirstAchat.getDetailAgr()).getId();
            System.out.println(""+currentFirstAchatTe+"  ---- "+currentFirstAchat.getDetailAgr() );


            Double achatAmountFirst = currentFirstAchat.getActionAmountFirstType();
            Double achatAmountSecond = currentFirstAchat.getActionAmountSecondType();
            Double tempAchatAmount = achatAmountFirst;  //Ban Value
            if(achatAmountFirst <= venteAmountFirst){
                System.out.println("Premiere Niveau");
                venteAmountFirst = venteAmountFirst - achatAmountFirst;
                venteAmountSecond = venteAmountSecond - achatAmountSecond;
                //Get amount for transactions fifo achat used
                Double AcheteurAmountBci = achatAmountSecond;
                Double VendeurAmountBan = achatAmountFirst;


                System.out.println("Generation des bons effectue");
                //checking account
                Double AcheteurTotalAmount = teClient.cumuleAvailableAmountInKsuWithTeByTypeSupportSmEchange(SupportMarchandConstant.supportMarchandBan,currentFirstAchatTe );
                Double VendeurTotalAmount = teClient.cumuleAvailableAmountInKsuWithTeByTypeSupportSmEchange(SupportMarchandConstant.supportMarchandBCi,currentFirstVenteTe );
                //vendeur

                System.out.println("acheteur  amount :" + AcheteurTotalAmount + " --- required:"+VendeurAmountBan+"    Vendeur amount:" + VendeurTotalAmount+" reauired:"+AcheteurAmountBci);
                if(AcheteurTotalAmount >= VendeurAmountBan && VendeurTotalAmount >= AcheteurAmountBci) {

                    teClient.registerNewDecaissement(AcheteurAmountBci, SupportMarchandConstant.supportMarchandBCi, currentFirstVenteTe);
                    teClient.registerEncaissement(VendeurAmountBan, SupportMarchandConstant.supportMarchandMPRg, currentFirstVenteTe);
                    System.out.println("Operation vendeur  effectue");
                    //Achat

                    teClient.registerNewDecaissement(VendeurAmountBan, SupportMarchandConstant.supportMarchandBan, currentFirstAchatTe);
                    teClient.registerEncaissement(AcheteurAmountBci, SupportMarchandConstant.supportMarchandBCi, currentFirstAchatTe);
                    System.out.println("Operation acheteur  effectue");

                    //emission des bons
//                    fifoService.removeById(currentFirstAchat.getId());
                    System.out.println("\t Achat utilisé: " + currentFirstAchat.getId() + "\tMontant:" + currentFirstAchat.getActionAmountFirstType() + "\tReste a traité:" + venteAmountFirst);

                    fifoService.CreateBonV2("BC",currentFirstVente, 1,currentFirstAchat,AcheteurAmountBci,VendeurAmountBan);
                    fifoService.CreateBonV2("BL",currentFirstAchat, 1,currentFirstVente,VendeurAmountBan,AcheteurAmountBci);
                    fifoService.CreateBonV2("BRBL",currentFirstVente, 1,currentFirstAchat,AcheteurAmountBci,VendeurAmountBan);
                    fifoService.CreateBonV2("FD",currentFirstAchat, 1,currentFirstVente,VendeurAmountBan,AcheteurAmountBci);
                    fifoService.CreateBonV2("BESM",currentFirstVente, 1,currentFirstAchat,AcheteurAmountBci,VendeurAmountBan);
                    fifoService.CreateBonV2("BRBE",currentFirstAchat, 1,currentFirstVente,VendeurAmountBan,AcheteurAmountBci);


                }else{
                    break;
                }
            }
            else{
                System.out.println("Deuxieme Niveau");
                achatAmountFirst = achatAmountFirst - venteAmountFirst;
                achatAmountSecond = achatAmountSecond - venteAmountSecond;
                Double AcheteurAmountBci = achatAmountSecond;
                Double VendeurAmountBan = achatAmountFirst;



                Double AcheteurTotalAmount = teClient.cumuleAvailableAmountInKsuWithTeByTypeSupportSmEchange(SupportMarchandConstant.supportMarchandBan,currentFirstAchatTe );
                Double VendeurTotalAmount = teClient.cumuleAvailableAmountInKsuWithTeByTypeSupportSmEchange(SupportMarchandConstant.supportMarchandBCi,currentFirstVenteTe );

                System.out.println("acheteur  amount :" + AcheteurTotalAmount + " --- required:"+VendeurAmountBan+"    Vendeur amount:" + VendeurTotalAmount+" reauired:"+AcheteurAmountBci);


                if(AcheteurTotalAmount >= VendeurAmountBan && VendeurTotalAmount >= AcheteurAmountBci) {


                    //vendeur

                    teClient.registerNewDecaissement(AcheteurAmountBci,SupportMarchandConstant.supportMarchandBCi,currentFirstVenteTe);
                    teClient.registerEncaissement(VendeurAmountBan, SupportMarchandConstant.supportMarchandMPRg,currentFirstVenteTe);
                    System.out.println("Operation vendeur  effectue");
                    //Achat

                    teClient.registerNewDecaissement(VendeurAmountBan,SupportMarchandConstant.supportMarchandBan,currentFirstAchatTe);
                    teClient.registerEncaissement(AcheteurAmountBci,SupportMarchandConstant.supportMarchandBCi,currentFirstAchatTe);

                    System.out.println("Operation acheteur  effectue");

                    currentFirstAchat.setActionAmountFirstType(achatAmountFirst);
                    currentFirstAchat.setActionAmountSecondType(achatAmountSecond);
                    //emission des bons
                    fifoService.CreateBonV2("BC",currentFirstVente, 1,currentFirstAchat,AcheteurAmountBci,VendeurAmountBan);
                    fifoService.CreateBonV2("BL",currentFirstAchat, 1,currentFirstVente,VendeurAmountBan,AcheteurAmountBci);
                    fifoService.CreateBonV2("BRBL",currentFirstVente, 1,currentFirstAchat,AcheteurAmountBci,VendeurAmountBan);
                    fifoService.CreateBonV2("FD",currentFirstAchat, 1,currentFirstVente,VendeurAmountBan,AcheteurAmountBci);
                    fifoService.CreateBonV2("BESM",currentFirstVente, 1,currentFirstAchat,AcheteurAmountBci,VendeurAmountBan);
                    fifoService.CreateBonV2("BRBE",currentFirstAchat, 1,currentFirstVente,VendeurAmountBan,AcheteurAmountBci);
                    System.out.println("Generation des bons effectue");

                    fifoService.saveFifo(currentFirstAchat);
                    venteAmountFirst=0.0;
                    venteAmountSecond=0.0;
                    System.out.println("\t ----------------- Finalisation Achat utilisé: "+currentFirstAchat.getId()+"\tMontant initial: "+ tempAchatAmount+"\t Montant Restant dans l'achat :"+currentFirstAchat.getActionAmountFirstType());
                }
                break;
            }
        }
        System.out.println("-------------------------------------------------------------------------------------------------------");

        if(venteAmountFirst<=0){
            //SUPPRESSION DU FIFO TRAITE
//            fifoService.removeById(currentFirstVente.getId());
            currentFirstVente.setTreated(true);
            fifoService.saveFifo(currentFirstVente);
            // FIFO - AVR TRAITER
            Avr avr = avrService.findAvrById(currentFirstVente.getAvr().getId());
            avr.setEtat(false);
            avrRepository.save(avr);
        }else{
            currentFirstVente.setActionAmountFirstType(venteAmountFirst);
            currentFirstVente.setActionAmountSecondType(venteAmountSecond);
            fifoService.saveFifo(currentFirstVente);
        }


        System.out.println("--------------------------------------------------------------------------------------------------");

        return ;




    }


    public void demo() {
        System.out.println("start cron job");
    }

    public String getMessage() {
        return "demo cron text";
    }
}
