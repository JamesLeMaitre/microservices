package com.esmc.gestionAvr.controllers;

import com.esmc.gestionAvr.InterimFeign.AgrInterimClient;
import com.esmc.gestionAvr.InterimFeign.KsuInterimClient;
import com.esmc.gestionAvr.InterimFeign.TeInterimClient;
import com.esmc.gestionAvr.entities.*;
import com.esmc.gestionAvr.feign.DetailAgrClient;
import com.esmc.gestionAvr.feign.KsuClient;
import com.esmc.gestionAvr.feign.TeClient;
import com.esmc.gestionAvr.repositories.*;
import com.esmc.gestionAvr.services.AvrServices;
import com.esmc.gestionAvr.services.FifoService;
import com.esmc.gestionAvr.servicesInterfaces.PanierFifoInterface;
import com.esmc.models.DetailsAgr;
import com.esmc.models.Ksu;
import constants.AccountConstant;
import constants.SupportMarchandConstant;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "api/automate/")
public class AutomateControllerV2 {

    @Autowired
    private AvrServices avrService;

    @Autowired
    private AvrRepository avrRepository;

    @Autowired
    private FifoService fifoService ;
    @Autowired
    private AvrController avrController;

    @Autowired
    private FifoRepository fifoRepository;

    @Autowired
    private TeClient teClient;

    @Autowired
    private DetailAgrClient detailAgrClient;

    @Autowired
    private TeInterimClient teInterimClient;

    @Autowired
    private ProductRegistryValueRepository productRegistryValueRepository;

    @Autowired
    private KsuClient ksuClient;



    @Autowired
    private  KsuInterimClient ksuInterimClient;

    @Autowired
    private AgrInterimClient agrInterimClient;

    @Autowired
    private HistoryGieAchatRepository historyGieAchatRepository;

    @Autowired
    private FifoHistoryRepository fifoHistoryRepository;

    @Autowired
    private PanierFifoInterface panierFifoInterface;


    @GetMapping("run")
    /*@Scheduled(cron = "0/10 * * * * * ")*/
    public void operateTransaction(){

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

        currentFirstVenteTe = teClient.getTeByDetailAgr(currentFirstVente.getDetailAgr()).getId();

        System.out.println(" ------------------------------------------ Vente traité -------------------------------");
        System.out.println("Fifo Vente Id : "+ currentFirstVente.getId() + " ---  Te : "+currentFirstVente.getAvr().getId() + "--- Avr:"+currentFirstVente.getAvr().getLibelle()+" --- Montant : "+currentFirstVente.getDefaultAmountFirstType() );

        //Montant a traiter
        Double venteAmountFirst = currentFirstVente.getActionAmountFirstType();//match to current Ban value
        Double venteAmountSecond = currentFirstVente.getActionAmountSecondType();//match to current BCI value
        System.out.println("----------------------------------------- FIFO ACHAT REQUIS POUR SON TRAITEMENT -------------------------------");
        while(venteAmountFirst >0 ) {
            System.out.println("boucle launch"+venteAmountFirst);

            //recuperation de l'achat courant
            Fifo currentFirstAchat = fifoService.getFirst(true);
            if(currentFirstAchat == null) break;

            System.out.println("Fifo Achat Found/ "+currentFirstAchat.toString());

            Long currentFirstAchatTe = teClient.getTeByDetailAgr(currentFirstAchat.getDetailAgr()).getId();
            Double achatAmountFirst = currentFirstAchat.getActionAmountFirstType();
            Double achatAmountSecond = currentFirstAchat.getActionAmountSecondType();
            Double tempAchatAmount = achatAmountFirst;  //Ban Value
            if(achatAmountFirst <= venteAmountFirst){
                System.out.println("Premiere étape");
                venteAmountFirst = venteAmountFirst - achatAmountFirst;
                venteAmountSecond = venteAmountSecond - achatAmountSecond;
                //Get amount for transactions fifo achat used
                Double AcheteurAmountBci = achatAmountSecond;
                Double VendeurAmountBan = achatAmountFirst;
                fifoService.CreateBonV2("BC",currentFirstVente, 1,currentFirstAchat,AcheteurAmountBci,VendeurAmountBan);
                fifoService.CreateBonV2("BL",currentFirstAchat, 1,currentFirstVente,VendeurAmountBan,AcheteurAmountBci);
                fifoService.CreateBonV2("BRBL",currentFirstVente, 1,currentFirstAchat,AcheteurAmountBci,VendeurAmountBan);
                fifoService.CreateBonV2("FD",currentFirstAchat, 1,currentFirstVente,VendeurAmountBan,AcheteurAmountBci);
                fifoService.CreateBonV2("BESM",currentFirstVente, 1,currentFirstAchat,AcheteurAmountBci,VendeurAmountBan);
                fifoService.CreateBonV2("BRBE",currentFirstAchat, 1,currentFirstVente,VendeurAmountBan,AcheteurAmountBci);

                //vendeur

               /* teClient.registerNewDecaissement(AcheteurAmountBci,SupportMarchandConstant.supportMarchandBCi,currentFirstVenteTe);
                teClient.registerEncaissement(VendeurAmountBan, SupportMarchandConstant.supportMarchandMPRg,currentFirstVenteTe);
                //Achat

                teClient.registerNewDecaissement(VendeurAmountBan,SupportMarchandConstant.supportMarchandBan,currentFirstAchatTe);
                teClient.registerEncaissement(AcheteurAmountBci,SupportMarchandConstant.supportMarchandBCi,currentFirstAchatTe);*/

                //emission des bons
                fifoService.removeById(currentFirstAchat.getId());
                System.out.println("\t Achat utilisé: "+currentFirstAchat.getId()+"\tMontant:"+currentFirstAchat.getActionAmountFirstType()+"\tReste a traité:"+venteAmountFirst);
            }else{
                System.out.println("Deuxieme étape");
                achatAmountFirst = achatAmountFirst - venteAmountFirst;
                achatAmountSecond = achatAmountSecond - venteAmountSecond;
                Double AcheteurAmountBci = achatAmountSecond;
                Double VendeurAmountBan = achatAmountFirst;

                //emission des bons
                currentFirstAchat.setActionAmountFirstType(achatAmountFirst);
                currentFirstAchat.setActionAmountSecondType(achatAmountSecond);
                fifoService.CreateBonV2("BC",currentFirstVente, 1,currentFirstAchat,AcheteurAmountBci,VendeurAmountBan);
                fifoService.CreateBonV2("BL",currentFirstAchat, 1,currentFirstVente,VendeurAmountBan,AcheteurAmountBci);
                fifoService.CreateBonV2("BRBL",currentFirstVente, 1,currentFirstAchat,AcheteurAmountBci,VendeurAmountBan);
                fifoService.CreateBonV2("FD",currentFirstAchat, 1,currentFirstVente,VendeurAmountBan,AcheteurAmountBci);
                fifoService.CreateBonV2("BESM",currentFirstVente, 1,currentFirstAchat,AcheteurAmountBci,VendeurAmountBan);
                fifoService.CreateBonV2("BRBE",currentFirstAchat, 1,currentFirstVente,VendeurAmountBan,AcheteurAmountBci);

                //vendeur

               /* teClient.registerNewDecaissement(AcheteurAmountBci,SupportMarchandConstant.supportMarchandBCi,currentFirstVenteTe);
                teClient.registerEncaissement(VendeurAmountBan, SupportMarchandConstant.supportMarchandMPRg,currentFirstVenteTe);
                //Achat

                teClient.registerNewDecaissement(VendeurAmountBan,SupportMarchandConstant.supportMarchandBan,currentFirstAchatTe);
                teClient.registerEncaissement(AcheteurAmountBci,SupportMarchandConstant.supportMarchandBCi,currentFirstAchatTe);*/
                fifoService.saveFifo(currentFirstAchat);
                venteAmountFirst=0.0;
                venteAmountSecond=0.0;
                System.out.println("\t ----------------- Finalisation Achat utilisé: "+currentFirstAchat.getId()+"\tMontant initial: "+ tempAchatAmount+"\t Montant Restant dans l'achat :"+currentFirstAchat.getActionAmountFirstType());

                break;
            }
        }
        System.out.println("-------------------------------------------------------------------------------------------------------");

        if(venteAmountFirst<=0){
            //SUPPRESSION DU FIFO TRAITE
            fifoService.removeById(currentFirstVente.getId());
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

    @GetMapping("run/v3")
    public void operateTransactionv3(){
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
        Fifo currentAchat = fifoService.getFirst(true);
        List<PanierFifo> listD = panierFifoInterface.listPanierFifo();
        if (currentAchat == null) return;

        //System.out.println(currentFirstVente);

        Long currentFirstVenteTe = null;
        Long currentFirstVenteKsu = null;
        Double venteAmountFirst =0.0;
        Double venteAmountSecond =0.0;
        Boolean isGie=false;
        if(currentFirstVente == null) {
            System.out.println("is gie action");

            isGie = true;

//            if (!listD.isEmpty()) {
//                for (PanierFifo p : listD) {
//                    if (p.getIsBuy() == false) {
//                        isGie=false;
//                    } else {
//                        isGie=true;
//                    }
//                    break;
//                }
//            }
        }else {
            currentFirstVenteKsu = fifoService.getKsu(currentFirstVente.getAvr().getDetailAgr());
            currentFirstVenteTe = teClient.getTeByDetailAgr(currentFirstVente.getDetailAgr()).getId();
            System.out.println(currentFirstVenteTe+"    "+currentFirstVenteKsu);

            System.out.println(" ------------------------------------------ Vente traité -------------------------------");
            System.out.println("Fifo Vente Id : "+ currentFirstVente.getId() + " ---  Te :"+currentFirstVenteKsu+"  avr:"+currentFirstVente.getAvr().getId() + "--- Avr:"+currentFirstVente.getAvr().getLibelle()+" --- Montant : "+currentFirstVente.getDefaultAmountFirstType() );
            //Montant a traiter  
            venteAmountFirst = currentFirstVente.getActionAmountFirstType();//match to current Ban vakue
            venteAmountSecond = currentFirstVente.getActionAmountSecondType();//match to current BCI vakue
        }

       /* }else{
            System.out.println(" ------------------------------------------ Vendeur (GIE) -------------------------------");
            currentFirstVenteTe = AccountConstant.TeEsmcGieFranchiseZeroOBPSD;
            currentFirstVenteKsu = fifoService.getKsu(currentFirstVenteTe);
            isGie=true;
        }*/


        System.out.println("----------------------------------------- FIFO ACHAT REQUIS POUR SON TRAITEMENT -------------------------------");
        while(venteAmountFirst >0 || isGie) {
            System.out.println("boucle launsh : "+venteAmountFirst);

            DetailsAgr d = detailAgrClient.getDetailById(currentAchat.getAvr().getDetailAgr());

            Ksu k =ksuClient.getById(d.getKsu());

            Long currentAchatKsu = fifoService.getKsu(k.getId());

            System.out.println(currentAchatKsu);

            //recuperation de l'achat courant
            Fifo currentFirstAchatSimple = fifoService.getFirstAchatFromExtern(true, 2L);

            System.out.println(currentFirstAchatSimple);
            System.out.println("Achat ");
            if(currentFirstAchatSimple == null) return ;
            //System.out.println(isGie);

            if(isGie == true){
                ///aCHAT SIMPLE
                achatFifoGie_no_cron(currentFirstAchatSimple);
                break;

            }
            System.out.println(currentFirstVenteKsu);
            Fifo currentFirstAchat = fifoService.getFirstAchatFromExtern(true, currentFirstVenteKsu);
            System.out.println("Current First Achat");
            System.out.println(currentFirstAchat);

           //
            if(currentFirstAchat == null) return ;
            System.out.println("Fifo Achat Found "+currentFirstAchat.toString());
            //recuperation du ksu achat
            Long achatFirstVenteKsu = fifoService.getKsu(currentFirstAchat.getAvr().getDetailAgr());
//            Long currentFirstAchatTe = teInterimClient.getTeByDetailAgr(currentFirstAchat.getDetailAgr()).getId();
            Long currentFirstAchatTe = teClient.getTeByDetailAgr(currentFirstAchat.getDetailAgr()).getId();
            System.out.println(""+currentFirstAchatTe+"  ---- "+currentFirstAchat.getDetailAgr() );

           // System.out.println("Te Achat id:"+currentFirstAchatTe+"Te Vente"+currentFirstVenteTe );


            Double achatAmountFirst = currentFirstAchat.getActionAmountFirstType();
            Double achatAmountSecond = currentFirstAchat.getActionAmountSecondType();

            //-----------------------verification si vendeur est gie et creation d'un fifo correspondant a la vente --------------------------
            System.out.println("-----------------------verification si vendeur est gie et creation d'un fifo correspondant a la vente --------------------------");


            // ------------------------------------------------------------------------------------------------------------------------------

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
                Double AcheteurTotalAmount = teClient.checkSmTotalCurrent(SupportMarchandConstant.QsupportMarchandBan,currentFirstAchatTe );
                Double VendeurTotalAmount = productRegistryValueRepository.amountBciAvailable(currentFirstVenteKsu);
                //vendeur


                System.out.println("acheteur  amount :" + AcheteurTotalAmount + " --- required:"+VendeurAmountBan+"    Vendeur amount:" + VendeurTotalAmount+" reauired:"+AcheteurAmountBci);
                System.out.println(AcheteurTotalAmount >= VendeurAmountBan && VendeurTotalAmount >= AcheteurAmountBci);
                if(AcheteurTotalAmount >= VendeurAmountBan && VendeurTotalAmount >= AcheteurAmountBci) {
                    // Desactiver Avr Vendeur


                    Avr avr = avrRepository.findById(currentFirstVente.getAvr().getId()).orElse(null);
                    avr.setEtat(false);
                    avrRepository.save(avr);

                    System.out.println("ProductRegistry");

                    ProductRegistryValue productRegistryValue = productRegistryValueRepository.findById(currentFirstVente.getAvr().getProductRegistryValue().getId()).get();
                    productRegistryValue.setStatus(false);
                    productRegistryValueRepository.save(productRegistryValue);

                    //// ProductRegistry with remainingValue
                    System.out.println(" ------------------------------ ProductRegistry with remainingValue --------------------------");

                    // Etape 1 : reception des fonds par le vendeur
                    System.out.println(" ----------------- Etape 1 : reception des fonds par le vendeur----------------------");
                    // INTERIM vers vendeur
                     teClient.mutationPourAchatFifo(AccountConstant.TeEsmcGieFranchiseZeroOBPS,currentFirstVenteTe,achatAmountFirst);
                     System.out.println("mutation ok");
                    // Etape2 : Attribution du produit a l'acheteur

                    System.out.println(" ------------------ Etape2 : Attribution du produit a l'acheteur ---------------------");
                    // Creation du produit pour l acheteur
                    System.out.println(" ------------------Creation du produit pour l acheteur ---------------------");
                    ProductRegistryValue productRegistryValueAcheteur = new ProductRegistryValue();
                    productRegistryValueAcheteur.setStatus(true);
                    productRegistryValueAcheteur.setValue(achatAmountSecond);
                    productRegistryValueAcheteur.setIdKsu(achatFirstVenteKsu);
                    productRegistryValueAcheteur.setRefer(productRegistryValue.getId());
                    productRegistryValueRepository.save(productRegistryValueAcheteur);

                    /*//Creation de l avr associe
                    Avr avrAcheteur = new Avr();
                    BeanUtils.copyProperties(avr, avrAcheteur);
                    avrAcheteur.setEtat(true);
                    avrAcheteur.setPrixUnitaire(achatAmountSecond);
                    avrAcheteur.setProductRegistryValue(productRegistryValueAcheteur);
                    avrRepository.save(avrAcheteur);*/
                    if(venteAmountSecond > 0) {
                        //Creation du nouveau produit de vente au vendeur
                        System.out.println(" ------------------Creation du nouveau produit de vente au vendeur ---------------------");
                        ProductRegistryValue productRegistryValueVendeur = new ProductRegistryValue();
                        productRegistryValueVendeur.setValue(venteAmountSecond);
                        productRegistryValueVendeur.setIdKsu(currentFirstVenteKsu);
                        productRegistryValueVendeur.setRefer(productRegistryValue.getId());
                        productRegistryValueVendeur.setStatus(true);
                        productRegistryValueRepository.save(productRegistryValueVendeur);

                        //Creation de l avr associe
                        Avr avrVendeur = new Avr();
                        BeanUtils.copyProperties(avr, avrVendeur);
                        avrVendeur.setPrixUnitaire(venteAmountSecond);
                        avrVendeur.setProductRegistryValue(productRegistryValueVendeur);
                        avrVendeur.setEtat(true);
                        avrRepository.save(avrVendeur);

                        //Affectation du new avr au fifo
                        currentFirstVente.setAvr(avrVendeur);
                        currentFirstVente = fifoRepository.save(currentFirstVente);

                    }


                    System.out.println("Operation acheteur  effectue");


                    System.out.println("\t Achat utilisé: " + currentFirstAchat.getId() + "\tMontant : " + currentFirstAchat.getActionAmountFirstType() + "\tReste a traité:" + venteAmountFirst);

                    Extrant ext1 = fifoService.CreateBonV2_with_agr_no_cron("DA",currentFirstVente, 1,currentFirstAchat,VendeurAmountBan,VendeurAmountBan,currentFirstVente.getAvr(), null);
                    Extrant ext2 = fifoService.CreateBonV2_with_agr_no_cron("FPF",currentFirstAchat, 1,currentFirstVente,VendeurAmountBan,VendeurAmountBan,currentFirstVente.getAvr(),ext1.getId());

                    Extrant ex = fifoService.CreateBonV2_with_agr_no_cron("BC",currentFirstVente, 1,currentFirstAchat,VendeurAmountBan,VendeurAmountBan, currentFirstVente.getAvr(), ext2.getId());
                    Extrant ex1=fifoService.CreateBonV2_with_agr_no_cron("BL",currentFirstAchat, 1,currentFirstVente,VendeurAmountBan,VendeurAmountBan, currentFirstVente.getAvr(), ex.getId());

                    Extrant ex2=fifoService.CreateBonV2_with_agr_no_cron("BRBL",currentFirstVente, 1,currentFirstAchat,VendeurAmountBan,VendeurAmountBan, currentFirstVente.getAvr(), ex1.getId());
                    Extrant ex3=fifoService.CreateBonV2_with_agr_no_cron("FD",currentFirstAchat, 1,currentFirstVente,VendeurAmountBan,VendeurAmountBan, currentFirstVente.getAvr(), ex2.getId());

                    Extrant ex4= fifoService.CreateBonV2_with_agr_no_cron("BESM",currentFirstVente, 1,currentFirstAchat,VendeurAmountBan,VendeurAmountBan, currentFirstVente.getAvr(), ex3.getId());
                    Extrant ex5= fifoService.CreateBonV2_with_agr_no_cron("BRBE",currentFirstAchat, 1,currentFirstVente,VendeurAmountBan,VendeurAmountBan, currentFirstVente.getAvr(), ex4.getId());

                    //emission des bons

                    FifoHistory fifoHistory = new FifoHistory();
                   BeanUtils.copyProperties(currentFirstAchat, fifoHistory);
                    fifoHistoryRepository.save(fifoHistory);
                    fifoService.removeById(currentFirstAchat.getId());

                }else{
                    return ;
                }
            }
            else{
                System.out.println("Deuxieme Niveau");
                achatAmountFirst = achatAmountFirst - venteAmountFirst;
                achatAmountSecond = achatAmountSecond - venteAmountSecond;
                Double AcheteurAmountBci = achatAmountSecond;
                Double VendeurAmountBan = achatAmountFirst;


                Double AcheteurTotalAmount = teClient.checkSmTotalCurrent(SupportMarchandConstant.QsupportMarchandBan,currentFirstAchatTe );
                Double VendeurTotalAmount = productRegistryValueRepository.amountBciAvailable(currentFirstVenteKsu);

                System.out.println("acheteur  amount :" + AcheteurTotalAmount + " --- required:"+VendeurAmountBan+"    Vendeur amount:" + VendeurTotalAmount+" reauired:"+AcheteurAmountBci);

                if(AcheteurTotalAmount >= VendeurAmountBan && VendeurTotalAmount >= AcheteurAmountBci) {

                    System.out.println(" Desactiver Avr Vendeur");
                    //System.out.println(currentFirstVente);
                    //System.out.println(currentFirstVente.getAvr());
                    // System.out.println(currentFirstVente.getAvr().getId());
                    // Desactiver Avr Vendeur
                    Avr avr = avrRepository.findById(currentFirstVente.getAvr().getId()).get();
                    avr.setEtat(false);
                    avrRepository.save(avr);

                    System.out.println("ProductRegistry");

                    ProductRegistryValue productRegistryValue = productRegistryValueRepository.findById(currentFirstVente.getAvr().getProductRegistryValue().getId()).get();
                    productRegistryValue.setStatus(false);
                    productRegistryValueRepository.save(productRegistryValue);

                    //// ProductRegistry with remainingValue
                    System.out.println(" ------------------------- ProductRegistry with full value used -----------------");

                    // Etape 1 : reception des fonds par le vendeur
                    System.out.println(" ----------------- Etape 1 : reception des fonds par le vendeur----------------------");
                    System.out.println(currentFirstAchatTe+" "+currentFirstVenteTe+" "+VendeurAmountBan);










//                    teInterimClient.mutationPourAchatFifo(AccountConstant.TeEsmcGieFranchiseZeroOBPSD,currentFirstVenteTe,VendeurAmountBan);
                    teClient.mutationPourAchatFifo(AccountConstant.TeEsmcGieFranchiseZeroOBPS,currentFirstVenteTe,VendeurAmountBan);
                    System.out.println("mutation ok");
                    // Etape2 : Attribution du produit a l'acheteur

                    System.out.println(" ------------------ Etape2 : Attribution du produit a l'acheteur ---------------------");
                    // Creation du produit pour l acheteur
                    System.out.println(" ------------------Creation du produit pour l acheteur ---------------------");
                    ProductRegistryValue productRegistryValueAcheteur = new ProductRegistryValue();
                    productRegistryValueAcheteur.setStatus(true);
                    productRegistryValueAcheteur.setValue(venteAmountSecond);
                    productRegistryValueAcheteur.setIdKsu(achatFirstVenteKsu);
                    productRegistryValueAcheteur.setRefer(productRegistryValue.getId());
                    productRegistryValueRepository.save(productRegistryValueAcheteur);


                    //Creation de l avr associe
//                    Avr avrAcheteur = new Avr();
//                    BeanUtils.copyProperties(avr, avrAcheteur);
//                    avrAcheteur.setEtat(true);
//                    avrAcheteur.setPrixUnitaire(venteAmountSecond);
//                    avrAcheteur.setProductRegistryValue(productRegistryValueAcheteur);
//                    avrRepository.save(avrAcheteur);

                    System.out.println("Operation acheteur  effectue");

                    currentFirstAchat.setActionAmountFirstType(achatAmountFirst);
                    currentFirstAchat.setActionAmountSecondType(achatAmountSecond);
                    //emission des bons
                    Extrant ext1 = fifoService.CreateBonV2_with_agr_no_cron("DA",currentFirstVente, 1,currentFirstAchat,VendeurAmountBan,VendeurAmountBan,currentFirstVente.getAvr(), null);
                    Extrant ext2 = fifoService.CreateBonV2_with_agr_no_cron("FPF",currentFirstAchat, 1,currentFirstVente,VendeurAmountBan,VendeurAmountBan,currentFirstVente.getAvr(),ext1.getId());
                    Extrant ex = fifoService.CreateBonV2_with_agr_no_cron("BC",currentFirstVente, 1,currentFirstAchat,VendeurAmountBan,VendeurAmountBan, currentFirstVente.getAvr(),ext2.getId());
                    Extrant ex1 = fifoService.CreateBonV2_with_agr_no_cron("BL",currentFirstAchat, 1,currentFirstVente,VendeurAmountBan,VendeurAmountBan,currentFirstVente.getAvr(), ex.getId());
                    Extrant ex2 = fifoService.CreateBonV2_with_agr_no_cron("BRBL",currentFirstVente, 1,currentFirstAchat,VendeurAmountBan,VendeurAmountBan,currentFirstVente.getAvr(), ex1.getId());
                    Extrant ex3 = fifoService.CreateBonV2_with_agr_no_cron("FD",currentFirstAchat, 1,currentFirstVente,VendeurAmountBan,AcheteurAmountBci,currentFirstVente.getAvr(), ex2.getId());
                    Extrant ex4 = fifoService.CreateBonV2_with_agr_no_cron("BESM",currentFirstVente, 1,currentFirstAchat,VendeurAmountBan,VendeurAmountBan,currentFirstVente.getAvr(), ex3.getId());
                    Extrant ex5 = fifoService.CreateBonV2_with_agr_no_cron("BRBE",currentFirstAchat, 1,currentFirstVente,VendeurAmountBan,VendeurAmountBan,currentFirstVente.getAvr(), ex4.getId());
                 //   System.out.println("Generation des bons effectue");


                    fifoService.saveFifo(currentFirstAchat);
                    venteAmountFirst=0.0;
                    venteAmountSecond=0.0;
                    System.out.println("\t ----------------- Finalisation Achat utilisé: "+currentFirstAchat.getId()+"\tMontant initial: "+ tempAchatAmount+"\t Montant Restant dans l'achat :"+currentFirstAchat.getActionAmountFirstType());


                }
                return ;
            }
        }
        System.out.println("------------------------------------------ FIFO VENTE EXECUTED -------------------------------------------------------------");

        if(isGie == false){
            if(venteAmountFirst<=0){
                Avr avr = avrService.findAvrById(currentFirstVente.getAvr().getId());
                avr.setEtat(false);
                avrRepository.save(avr);

                //SUPPRESSION DU FIFO TRAITE

                FifoHistory fifoHistory = new FifoHistory();
                BeanUtils.copyProperties(currentFirstVente, fifoHistory);
                fifoHistoryRepository.save(fifoHistory);
                // FIFO - AVR TRAITER
                fifoService.removeById(currentFirstVente.getId());
            }else{
                currentFirstVente.setActionAmountFirstType(venteAmountFirst);
                currentFirstVente.setActionAmountSecondType(venteAmountSecond);
                fifoService.saveFifo(currentFirstVente);
            }
        }


        System.out.println("------------------------------------------- END  -------------------------------------------------------");

        return ;

    }


    @GetMapping("run/v2")
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

         * */


        System.out.println("Processus Fifo");
        // recuperation de la vente a traiter
        Fifo currentFirstVente = fifoService.getFirst(false);
        Fifo currentAchat = fifoService.getFirst(true);
        List<PanierFifo> listD = panierFifoInterface.listPanierFifo();
        if (currentAchat == null) return;
        //System.out.println(currentFirstVente);
        Long currentFirstVenteTe = null;
        Long currentFirstVenteKsu=null;
        Double venteAmountFirst =0.0;
        Double venteAmountSecond =0.0;
        Boolean isGie=false;
        if(currentFirstVente == null) {
            System.out.println("is gie action");
            isGie=true;

        }else {
            currentFirstVenteKsu = fifoService.getKsu(currentFirstVente.getAvr().getDetailAgr());
          //  currentFirstVenteTe = teInterimClient.getTeByDetailAgr(currentFirstVente.getDetailAgr()).getId();

            currentFirstVenteTe = teClient.getTeByDetailAgr(currentFirstVente.getDetailAgr()).getId();
            System.out.println(currentFirstVenteTe+"    "+currentFirstVenteKsu);

            System.out.println(" ------------------------------------------ Vente traité -------------------------------");
            System.out.println("Fifo Vente Id : "+ currentFirstVente.getId() + " ---  Te :"+currentFirstVenteKsu+"  avr:"+currentFirstVente.getAvr().getId() + "--- Avr:"+currentFirstVente.getAvr().getLibelle()+" --- Montant : "+currentFirstVente.getDefaultAmountFirstType() );
            //Montant a traiter
            venteAmountFirst = currentFirstVente.getActionAmountFirstType();//match to current Ban vakue
            venteAmountSecond = currentFirstVente.getActionAmountSecondType();//match to current BCI vakue
        }



       /* }else{
            System.out.println(" ------------------------------------------ Vendeur (GIE) -------------------------------");
            currentFirstVenteTe = AccountConstant.TeEsmcGieFranchiseZeroOBPSD;
            currentFirstVenteKsu = fifoService.getKsu(currentFirstVenteTe);
            isGie=true;
        }*/




        System.out.println("----------------------------------------- FIFO ACHAT REQUIS POUR SON TRAITEMENT -------------------------------");
        while(venteAmountFirst >0 || isGie) {
            System.out.println("boucle launsh"+venteAmountFirst);

           // DetailsAgr d = agrInterimClient.getDetailById(currentAchat.getAvr().getDetailAgr());
            DetailsAgr d = detailAgrClient.getDetailById(currentAchat.getAvr().getDetailAgr());

          //  Ksu k =ksuInterimClient.getById(d.getKsu());

            Ksu k =ksuClient.getById(d.getKsu());
            System.out.println(d);
            System.out.println(k);

            Long currentAchatKsu = fifoService.getKsu(k.getId());

            System.out.println("-------------KSU-----------------");

            System.out.println(currentAchatKsu);

            System.out.println("--------------------------------------");

            //recuperation de l'achat courant
            Fifo currentFirstAchatSimple = fifoService.getFirstAchatFromExtern(true, 2L);

            System.out.println(currentFirstAchatSimple);
            System.out.println("Achat ");
            if(currentFirstAchatSimple == null) return ;
            //System.out.println(isGie);

            if(isGie == true){
                ///aCHAT SIMPLE
                achatFifoGie(currentFirstAchatSimple);
                break;

            }
            System.out.println(currentFirstVenteKsu);
            Fifo currentFirstAchat = fifoService.getFirstAchatFromExtern(true, currentFirstVenteKsu);
            System.out.println("Current First Achat");
            System.out.println(currentFirstAchat);

            //
            if(currentFirstAchat == null) return ;
            System.out.println("Fifo Achat Found "+currentFirstAchat.toString());
            //recuperation du ksu achat
            Long achatFirstVenteKsu = fifoService.getKsu(currentFirstAchat.getAvr().getDetailAgr());
            //Long currentFirstAchatTe = teInterimClient.getTeByDetailAgr(currentFirstAchat.getDetailAgr()).getId();
            Long currentFirstAchatTe = teClient.getTeByDetailAgr(currentFirstAchat.getDetailAgr()).getId();
            System.out.println(""+currentFirstAchatTe+"  ---- "+currentFirstAchat.getDetailAgr() );

            // System.out.println("Te Achat id:"+currentFirstAchatTe+"Te Vente"+currentFirstVenteTe );


            Double achatAmountFirst = currentFirstAchat.getActionAmountFirstType();
            Double achatAmountSecond = currentFirstAchat.getActionAmountSecondType();

            //-----------------------verification si vendeur est gie et creation d'un fifo correspondant a la vente --------------------------
            System.out.println("-----------------------verification si vendeur est gie et creation d'un fifo correspondant a la vente --------------------------");


            // -------------------------------------------------------------------------------------------------------------------------------




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
              //  Double AcheteurTotalAmount = teInterimClient.checkSmTotalCurrent(SupportMarchandConstant.QsupportMarchandBan,currentFirstAchatTe );
                Double AcheteurTotalAmount = teClient.checkSmTotalCurrent(SupportMarchandConstant.QsupportMarchandBan,currentFirstAchatTe );

                System.out.println("================================================================================================");
                System.out.println("Montant total Acheteur : "+AcheteurTotalAmount);
                System.out.println("================================================================================================");

                //Double VendeurTotalAmount = teInterimClient.checkSmTotalCurrent(SupportMarchandConstant.supportMarchandBCi_BLGCp,currentFirstVenteTe );
                Double VendeurTotalAmount = teClient.checkSmTotalCurrent(SupportMarchandConstant.supportMarchandBCi_BLGCp,currentFirstVenteTe );

                System.out.println("================================================================================================");
                System.out.println("Montant total Vendeur : "+VendeurTotalAmount);
                System.out.println("================================================================================================");
                //vendeur

                System.out.println("acheteur  amount :" + AcheteurTotalAmount + " --- required:"+VendeurAmountBan+"    Vendeur amount:" + VendeurTotalAmount+" reauired:"+AcheteurAmountBci);
                System.out.println(AcheteurTotalAmount >= VendeurAmountBan && VendeurTotalAmount >= AcheteurAmountBci);
                if(AcheteurTotalAmount >= VendeurAmountBan && VendeurTotalAmount >= AcheteurAmountBci) {
                    // Desactiver Avr Vendeur

                    Avr avr = avrRepository.findById(currentFirstVente.getAvr().getId()).get();
                    avr.setEtat(false);
                    avrRepository.save(avr);

                    System.out.println("ProductRegistry");

                    ProductRegistryValue productRegistryValue = productRegistryValueRepository.findById(currentFirstVente.getAvr().getProductRegistryValue().getId()).get();
                    productRegistryValue.setStatus(false);
                    productRegistryValueRepository.save(productRegistryValue);

                    //// ProductRegistry with remainingValue
                    System.out.println(" ------------------------------ ProductRegistry with remainingValue --------------------------");

                    // Etape 1 : reception des fonds par le vendeur
                    System.out.println(" ----------------- Etape 1 : reception des fonds par le vendeur----------------------");
                    //teInterimClient.mutationPourAchatFifo(currentFirstAchatTe,currentFirstVenteTe,achatAmountFirst);
                    teClient.mutationPourAchatFifo(currentFirstAchatTe,currentFirstVenteTe,achatAmountFirst);
                    System.out.println("mutation ok");
                    // Etape2 : Attribution du produit a l'acheteur

                    System.out.println(" ------------------ Etape2 : Attribution du produit a l'acheteur ---------------------");
                    // Creation du produit pour l acheteur
                    System.out.println(" ------------------Creation du produit pour l acheteur ---------------------");
                    ProductRegistryValue productRegistryValueAcheteur = new ProductRegistryValue();
                    productRegistryValueAcheteur.setStatus(true);
                    productRegistryValueAcheteur.setValue(achatAmountSecond);
                    productRegistryValueAcheteur.setIdKsu(achatFirstVenteKsu);
                    productRegistryValueAcheteur.setRefer(productRegistryValue.getId());
                    productRegistryValueRepository.save(productRegistryValueAcheteur);

                    //Creation de l avr associe
                    Avr avrAcheteur = new Avr();
                    BeanUtils.copyProperties(avr, avrAcheteur);
                    avrAcheteur.setEtat(true);
                    avrAcheteur.setPrixUnitaire(achatAmountSecond);
                    avrAcheteur.setProductRegistryValue(productRegistryValueAcheteur);
                    avrRepository.save(avrAcheteur);
                    if(venteAmountSecond > 0) {
                        //Creation du nouveau produit de vente au vendeur
                        System.out.println(" ------------------Creation du nouveau produit de vente au vendeur ---------------------");
                        ProductRegistryValue productRegistryValueVendeur = new ProductRegistryValue();
                        productRegistryValueVendeur.setValue(venteAmountSecond);
                        productRegistryValueVendeur.setIdKsu(currentFirstVenteKsu);
                        productRegistryValueVendeur.setRefer(productRegistryValue.getId());
                        productRegistryValueVendeur.setStatus(true);
                        productRegistryValueRepository.save(productRegistryValueVendeur);

                        //Creation de l avr associe
                        Avr avrVendeur = new Avr();
                        BeanUtils.copyProperties(avr, avrVendeur);
                        avrVendeur.setPrixUnitaire(venteAmountSecond);
                        avrVendeur.setProductRegistryValue(productRegistryValueVendeur);
                        avrVendeur.setEtat(true);
                        avrRepository.save(avrVendeur);

                        //Affectation du nouvelle avr au fifo
                        currentFirstVente.setAvr(avrVendeur);
                        currentFirstVente = fifoRepository.save(currentFirstVente);
                    }




                    System.out.println("Operation acheteur  effectue");


                    System.out.println("\t Achat utilisé: " + currentFirstAchat.getId() + "\tMontant:" + currentFirstAchat.getActionAmountFirstType() + "\tReste a traité:" + venteAmountFirst);

                    Extrant ext1 = fifoService.CreateBonV2_with_agr("DA",currentFirstVente, 1,currentFirstAchat,VendeurAmountBan,VendeurAmountBan,currentFirstVente.getAvr(), null);
                    Extrant ext2 = fifoService.CreateBonV2_with_agr("FPF",currentFirstAchat, 1,currentFirstVente,VendeurAmountBan,VendeurAmountBan,currentFirstVente.getAvr(),ext1.getId());
                    Extrant ex = fifoService.CreateBonV2_with_agr("BC",currentFirstVente, 1,currentFirstAchat,VendeurAmountBan,VendeurAmountBan, currentFirstVente.getAvr(), ext2.getId());
                    Extrant ex1=fifoService.CreateBonV2_with_agr("BL",currentFirstAchat, 1,currentFirstVente,VendeurAmountBan,VendeurAmountBan, currentFirstVente.getAvr(), ex.getId());
                    Extrant ex2=fifoService.CreateBonV2_with_agr("BRBL",currentFirstVente, 1,currentFirstAchat,VendeurAmountBan,VendeurAmountBan, currentFirstVente.getAvr(), ex1.getId());
                    Extrant ex3=fifoService.CreateBonV2_with_agr("FD",currentFirstAchat, 1,currentFirstVente,VendeurAmountBan,VendeurAmountBan, currentFirstVente.getAvr(), ex2.getId());
                    Extrant ex4= fifoService.CreateBonV2_with_agr("BESM",currentFirstVente, 1,currentFirstAchat,VendeurAmountBan,VendeurAmountBan, currentFirstVente.getAvr(), ex3.getId());
                    Extrant ex5= fifoService.CreateBonV2_with_agr("BRBE",currentFirstAchat, 1,currentFirstVente,VendeurAmountBan,VendeurAmountBan, currentFirstVente.getAvr(), ex4.getId());


                    //HISTORY FIFO ACHAT
                    FifoHistory fifoHistory = new FifoHistory();
                    BeanUtils.copyProperties(currentFirstAchat, fifoHistory);
                    fifoHistoryRepository.save(fifoHistory);
                    System.out.println("register history full");
                    //emission des bons
                    fifoService.removeById(currentFirstAchat.getId());

                }else{
                    return ;
                }
            }
            else{
                System.out.println("Deuxieme Niveau");
                achatAmountFirst = achatAmountFirst - venteAmountFirst;
                achatAmountSecond = achatAmountSecond - venteAmountSecond;
                Double AcheteurAmountBci = achatAmountSecond;
                Double VendeurAmountBan = achatAmountFirst;



                //Double AcheteurTotalAmount = teInterimClient.checkSmTotalCurrent(SupportMarchandConstant.QsupportMarchandBan,currentFirstAchatTe );
               // Double VendeurTotalAmount = teInterimClient.checkSmTotalCurrent(SupportMarchandConstant.supportMarchandBCi_BLGCp,currentFirstVenteTe );

                Double AcheteurTotalAmount = teClient.checkSmTotalCurrent(SupportMarchandConstant.QsupportMarchandBan,currentFirstAchatTe );
                 Double VendeurTotalAmount = teClient.checkSmTotalCurrent(SupportMarchandConstant.supportMarchandBCi_BLGCp,currentFirstVenteTe );

                System.out.println("acheteur  amount :" + AcheteurTotalAmount + " --- required:"+VendeurAmountBan+"    Vendeur amount:" + VendeurTotalAmount+" reauired:"+AcheteurAmountBci);

                if(AcheteurTotalAmount >= VendeurAmountBan && VendeurTotalAmount >= AcheteurAmountBci) {

                    System.out.println(" Desactiver Avr Vendeur");
                    //System.out.println(currentFirstVente);
                    //System.out.println(currentFirstVente.getAvr());
                    // System.out.println(currentFirstVente.getAvr().getId());
                    // Desactiver Avr Vendeur
                    Avr avr = avrRepository.findById(currentFirstVente.getAvr().getId()).get();
                    avr.setEtat(false);
                    avrRepository.save(avr);

                    System.out.println("ProductRegistry");

                    ProductRegistryValue productRegistryValue = productRegistryValueRepository.findById(currentFirstVente.getAvr().getProductRegistryValue().getId()).get();
                    productRegistryValue.setStatus(false);
                    productRegistryValueRepository.save(productRegistryValue);

                    //// ProductRegistry with remainingValue
                    System.out.println(" ------------------------- ProductRegistry with full value used -----------------");

                    // Etape 1 : reception des fonds par le vendeur
                    System.out.println(" ----------------- Etape 1 : reception des fonds par le vendeur----------------------");
                    System.out.println(currentFirstAchatTe+" "+currentFirstVenteTe+" "+VendeurAmountBan);
                    //teInterimClient.mutationPourAchatFifo(currentFirstAchatTe,currentFirstVenteTe,VendeurAmountBan);
                    teClient.mutationPourAchatFifo(currentFirstAchatTe,currentFirstVenteTe,VendeurAmountBan);
                    System.out.println("mutation ok");
                    // Etape2 : Attribution du produit a l'acheteur

                    System.out.println(" ------------------ Etape2 : Attribution du produit a l'acheteur ---------------------");
                    // Creation du produit pour l acheteur
                    System.out.println(" ------------------Creation du produit pour l acheteur ---------------------");
                    ProductRegistryValue productRegistryValueAcheteur = new ProductRegistryValue();
                    productRegistryValueAcheteur.setStatus(true);
                    productRegistryValueAcheteur.setValue(venteAmountSecond);
                    productRegistryValueAcheteur.setIdKsu(achatFirstVenteKsu);
                    productRegistryValueAcheteur.setRefer(productRegistryValue.getId());
                    productRegistryValueRepository.save(productRegistryValueAcheteur);

                    //Creation de l avr associe
                    Avr avrAcheteur = new Avr();
                    BeanUtils.copyProperties(avr, avrAcheteur);
                    avrAcheteur.setEtat(true);
                    avrAcheteur.setPrixUnitaire(venteAmountSecond);
                    avrAcheteur.setProductRegistryValue(productRegistryValueAcheteur);
                    avrRepository.save(avrAcheteur);

                    System.out.println("Operation acheteur  effectue");

                    currentFirstAchat.setActionAmountFirstType(achatAmountFirst);
                    currentFirstAchat.setActionAmountSecondType(achatAmountSecond);
                    //emission des bons
                    Extrant ext1 = fifoService.CreateBonV2_with_agr("DA",currentFirstVente, 1,currentFirstAchat,VendeurAmountBan,VendeurAmountBan,currentFirstVente.getAvr(), null);
                    Extrant ext2 = fifoService.CreateBonV2_with_agr("FPF",currentFirstAchat, 1,currentFirstVente,VendeurAmountBan,VendeurAmountBan,currentFirstVente.getAvr(),ext1.getId());
                    Extrant ex = fifoService.CreateBonV2_with_agr("BC",currentFirstVente, 1,currentFirstAchat,VendeurAmountBan,VendeurAmountBan, currentFirstVente.getAvr(),ext2.getId());
                    Extrant ex1 = fifoService.CreateBonV2_with_agr("BL",currentFirstAchat, 1,currentFirstVente,VendeurAmountBan,VendeurAmountBan,currentFirstVente.getAvr(), ex.getId());
                    Extrant ex2 = fifoService.CreateBonV2_with_agr("BRBL",currentFirstVente, 1,currentFirstAchat,VendeurAmountBan,VendeurAmountBan,currentFirstVente.getAvr(), ex1.getId());
                    Extrant ex3 = fifoService.CreateBonV2_with_agr("FD",currentFirstAchat, 1,currentFirstVente,VendeurAmountBan,AcheteurAmountBci,currentFirstVente.getAvr(), ex2.getId());
                    Extrant ex4 = fifoService.CreateBonV2_with_agr("BESM",currentFirstVente, 1,currentFirstAchat,VendeurAmountBan,VendeurAmountBan,currentFirstVente.getAvr(), ex3.getId());
                    Extrant ex5 = fifoService.CreateBonV2_with_agr("BRBE",currentFirstAchat, 1,currentFirstVente,VendeurAmountBan,VendeurAmountBan,currentFirstVente.getAvr(), ex4.getId());
                    //System.out.println("Generation des bons effectue");

                    fifoService.saveFifo(currentFirstAchat);
                    venteAmountFirst=0.0;
                    venteAmountSecond=0.0;
                    System.out.println("\t ----------------- Finalisation Achat utilisé: "+currentFirstAchat.getId()+"\tMontant initial: "+ tempAchatAmount+"\t Montant Restant dans l'achat :"+currentFirstAchat.getActionAmountFirstType());
                }
                return ;
            }
        }
        System.out.println("------------------------------------------ FIFO VENTE EXECUTED -------------------------------------------------------------");

        if(isGie == false){
            if(venteAmountFirst<=0){
                Avr avr = avrService.findAvrById(currentFirstVente.getAvr().getId());
                avr.setEtat(false);
                avrRepository.save(avr);

                //SUPPRESSION DU FIFO TRAITE
                //HISTORIQUE FIFO
                FifoHistory fifoHistory = new FifoHistory();
                BeanUtils.copyProperties(currentFirstVente, fifoHistory);
                fifoHistoryRepository.save(fifoHistory);
                System.out.println("register full");
                // FIFO - AVR TRAITER
                fifoService.removeById(currentFirstVente.getId());

            }else{
                currentFirstVente.setActionAmountFirstType(venteAmountFirst);
                currentFirstVente.setActionAmountSecondType(venteAmountSecond);
                fifoService.saveFifo(currentFirstVente);
            }
        }



        System.out.println("------------------------------------------- END  -------------------------------------------------------");

        return ;

    }

    public void operateTransactionv21(){

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
        //System.out.println(currentFirstVente);
        Long currentFirstVenteTe = null;
        Long currentFirstVenteKsu=2L;
        Double venteAmountFirst =0.0;
        Double venteAmountSecond =0.0;
        Boolean isGie=false;
        if(currentFirstVente == null) {
            System.out.println("is gie action");
            isGie=true;

        }else {
            currentFirstVenteKsu = fifoService.getKsu(currentFirstVente.getAvr().getDetailAgr());
            currentFirstVenteTe = teInterimClient.getTeByDetailAgr(currentFirstVente.getDetailAgr()).getId();
            System.out.println(currentFirstVenteTe+"    "+currentFirstVenteKsu);

            System.out.println(" ------------------------------------------ Vente traité -------------------------------");
            System.out.println("Fifo Vente Id : "+ currentFirstVente.getId() + " ---  Te :"+currentFirstVenteKsu+"  avr:"+currentFirstVente.getAvr().getId() + "--- Avr:"+currentFirstVente.getAvr().getLibelle()+" --- Montant : "+currentFirstVente.getDefaultAmountFirstType() );
            //Montant a traiter
            venteAmountFirst = currentFirstVente.getActionAmountFirstType();//match to current Ban vakue
            venteAmountSecond = currentFirstVente.getActionAmountSecondType();//match to current BCI vakue
        }



       /* }else{
            System.out.println(" ------------------------------------------ Vendeur (GIE) -------------------------------");
            currentFirstVenteTe = AccountConstant.TeEsmcGieFranchiseZeroOBPSD;
            currentFirstVenteKsu = fifoService.getKsu(currentFirstVenteTe);
            isGie=true;
        }*/




        System.out.println("----------------------------------------- FIFO ACHAT REQUIS POUR SON TRAITEMENT -------------------------------");
        while(venteAmountFirst >0 || isGie) {
            System.out.println("boucle launsh"+venteAmountFirst);
            //recuperation de l'achat courant
            Fifo currentFirstAchat = fifoService.getFirstAchatFromExtern(true,2L );
            if(currentFirstAchat == null) break;
           // System.out.println(isGie);

            if(isGie == true){
                ///aCHAT SIMPLE
                achatFifoGie(currentFirstAchat);
                break;

            }


            System.out.println("Fifo Achat Found "+currentFirstAchat.toString());
            //recuperation du ksu achat
            Long achatFirstVenteKsu = fifoService.getKsu(currentFirstAchat.getAvr().getDetailAgr());
            Long currentFirstAchatTe = teInterimClient.getTeByDetailAgr(currentFirstAchat.getDetailAgr()).getId();
            System.out.println(""+currentFirstAchatTe+"  ---- "+currentFirstAchat.getDetailAgr() );


            Double achatAmountFirst = currentFirstAchat.getActionAmountFirstType();
            Double achatAmountSecond = currentFirstAchat.getActionAmountSecondType();

            //-----------------------verification si vendeur est gie et creation d'un fifo correspondant a la vente --------------------------
            System.out.println("-----------------------verification si vendeur est gie et creation d'un fifo correspondant a la vente --------------------------");
            /*if(isGie){
                ProductRegistryValue productRegistryValueGie = new ProductRegistryValue();
                productRegistryValueGie.setStatus(true);
                productRegistryValueGie.setValue(achatAmountSecond);
                productRegistryValueGie.setIdKsu(currentFirstVenteKsu);
                productRegistryValueRepository.save(productRegistryValueGie);

                //Creation de l avr associe
                Avr avrGie = new Avr();
                avrGie.setEtat(true);
                avrGie.setPrixUnitaire(achatAmountSecond);
                avrGie.setProductRegistryValue(productRegistryValueGie);
                avrRepository.save(avrGie);

                Fifo currentFirstVenteGie = new Fifo();
                currentFirstVenteGie.setAvr(avrGie);
                currentFirstVenteGie.setDefaultAmountFirstType(achatAmountFirst);
                currentFirstVenteGie.setDefaultAmountSecondType(achatAmountSecond);
                currentFirstVenteGie.setActionAmountFirstType(achatAmountFirst);
                currentFirstVenteGie.setDefaultAmountSecondType(achatAmountSecond);
                currentFirstVenteGie.setIsBuy(false);
                currentFirstVenteGie.setDetailAgr(currentFirstVenteTe);
                currentFirstVente = fifoRepository.save(currentFirstVenteGie);

                venteAmountFirst = currentFirstVente.getActionAmountFirstType();//match to current Ban vakue
                venteAmountSecond = currentFirstVente.getActionAmountSecondType();//match to current BCI vakue
            }*/


            // -------------------------------------------------------------------------------------------------------------------------------




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
                Double AcheteurTotalAmount = teInterimClient.checkSmTotalCurrent(SupportMarchandConstant.QsupportMarchandBan,currentFirstAchatTe );
                Double VendeurTotalAmount = teInterimClient.checkSmTotalCurrent(SupportMarchandConstant.supportMarchandBCi_BLGCp,currentFirstVenteTe );
                //vendeur

                System.out.println("acheteur  amount :" + AcheteurTotalAmount + " --- required:"+VendeurAmountBan+"    Vendeur amount:" + VendeurTotalAmount+" reauired:"+AcheteurAmountBci);
                System.out.println(AcheteurTotalAmount >= VendeurAmountBan && VendeurTotalAmount >= AcheteurAmountBci);
                if(AcheteurTotalAmount >= VendeurAmountBan && VendeurTotalAmount >= AcheteurAmountBci) {
                    // Desactiver Avr Vendeur

                    Avr avr = avrRepository.findById(currentFirstVente.getAvr().getId()).get();
                    avr.setEtat(false);
                    avrRepository.save(avr);

                    System.out.println("ProductRegistry");

                    ProductRegistryValue productRegistryValue = productRegistryValueRepository.findById(currentFirstVente.getAvr().getProductRegistryValue().getId()).get();
                    productRegistryValue.setStatus(false);
                    productRegistryValueRepository.save(productRegistryValue);

                    //// ProductRegistry with remainingValue
                    System.out.println(" ------------------------------ ProductRegistry with remainingValue --------------------------");

                    // Etape 1 : reception des fonds par le vendeur
                    System.out.println(" ----------------- Etape 1 : reception des fonds par le vendeur----------------------");
                    teInterimClient.mutationPourAchatFifo(currentFirstAchatTe,currentFirstVenteTe,achatAmountFirst);
                    System.out.println("mutation ok");
                    // Etape2 : Attribution du produit a l'acheteur

                    System.out.println(" ------------------ Etape2 : Attribution du produit a l'acheteur ---------------------");
                    // Creation du produit pour l acheteur
                    System.out.println(" ------------------Creation du produit pour l acheteur ---------------------");
                    ProductRegistryValue productRegistryValueAcheteur = new ProductRegistryValue();
                    productRegistryValueAcheteur.setStatus(true);
                    productRegistryValueAcheteur.setValue(achatAmountSecond);
                    productRegistryValueAcheteur.setIdKsu(achatFirstVenteKsu);
                    productRegistryValueAcheteur.setRefer(productRegistryValue.getId());
                    productRegistryValueRepository.save(productRegistryValueAcheteur);

                    //Creation de l avr associe
                    Avr avrAcheteur = new Avr();
                    BeanUtils.copyProperties(avr, avrAcheteur);
                    avrAcheteur.setEtat(true);
                    avrAcheteur.setPrixUnitaire(achatAmountSecond);
                    avrAcheteur.setProductRegistryValue(productRegistryValueAcheteur);
                    avrRepository.save(avrAcheteur);
                    if(venteAmountSecond > 0) {
                        //Creation du nouveau produit de vente au vendeur
                        System.out.println(" ------------------Creation du nouveau produit de vente au vendeur ---------------------");
                        ProductRegistryValue productRegistryValueVendeur = new ProductRegistryValue();
                        productRegistryValueVendeur.setValue(venteAmountSecond);
                        productRegistryValueVendeur.setIdKsu(currentFirstVenteKsu);
                        productRegistryValueVendeur.setRefer(productRegistryValue.getId());
                        productRegistryValueVendeur.setStatus(true);
                        productRegistryValueRepository.save(productRegistryValueVendeur);


                        //Creation de l avr associe
                        Avr avrVendeur = new Avr();
                        BeanUtils.copyProperties(avr, avrVendeur);
                        avrVendeur.setPrixUnitaire(venteAmountSecond);
                        avrVendeur.setProductRegistryValue(productRegistryValueVendeur);
                        avrVendeur.setEtat(true);
                        avrRepository.save(avrVendeur);

                        //Affectation du nouvelle avr au fifo
                        currentFirstVente.setAvr(avrVendeur);
                        currentFirstVente = fifoRepository.save(currentFirstVente);
                    }

                    System.out.println("Operation acheteur  effectue");

                    //emission des bons
                    fifoService.removeById(currentFirstAchat.getId());
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



                Double AcheteurTotalAmount = teInterimClient.checkSmTotalCurrent(SupportMarchandConstant.QsupportMarchandBan,currentFirstAchatTe );
                Double VendeurTotalAmount = teInterimClient.checkSmTotalCurrent(SupportMarchandConstant.supportMarchandBCi_BLGCp,currentFirstVenteTe );

                System.out.println("acheteur  amount :" + AcheteurTotalAmount + " --- required:"+VendeurAmountBan+"    Vendeur amount:" + VendeurTotalAmount+" reauired:"+AcheteurAmountBci);

                if(AcheteurTotalAmount >= VendeurAmountBan && VendeurTotalAmount >= AcheteurAmountBci) {

                    System.out.println(" Desactiver Avr Vendeur");
                    //System.out.println(currentFirstVente);
                    //System.out.println(currentFirstVente.getAvr());
                    // System.out.println(currentFirstVente.getAvr().getId());
                    // Desactiver Avr Vendeur
                    Avr avr = avrRepository.findById(currentFirstVente.getAvr().getId()).get();
                    avr.setEtat(false);
                    avrRepository.save(avr);

                    System.out.println("ProductRegistry");

                    ProductRegistryValue productRegistryValue = productRegistryValueRepository.findById(currentFirstVente.getAvr().getProductRegistryValue().getId()).get();
                    productRegistryValue.setStatus(false);
                    productRegistryValueRepository.save(productRegistryValue);

                    //// ProductRegistry with remainingValue
                    System.out.println(" ------------------------- ProductRegistry with full value used -----------------");

                    // Etape 1 : reception des fonds par le vendeur
                    System.out.println(" ----------------- Etape 1 : reception des fonds par le vendeur----------------------");
                    System.out.println(currentFirstAchatTe+" "+currentFirstVenteTe+" "+VendeurAmountBan);
                    teInterimClient.mutationPourAchatFifo(currentFirstAchatTe,currentFirstVenteTe,VendeurAmountBan);
                    // Etape2 : Attribution du produit a l'acheteur

                    System.out.println(" ------------------ Etape2 : Attribution du produit a l'acheteur ---------------------");
                    // Creation du produit pour l acheteur
                    System.out.println(" ------------------Creation du produit pour l acheteur ---------------------");
                    ProductRegistryValue productRegistryValueAcheteur = new ProductRegistryValue();
                    productRegistryValueAcheteur.setStatus(true);
                    productRegistryValueAcheteur.setValue(venteAmountSecond);
                    productRegistryValueAcheteur.setIdKsu(achatFirstVenteKsu);
                    productRegistryValueAcheteur.setRefer(productRegistryValue.getId());
                    productRegistryValueRepository.save(productRegistryValueAcheteur);

                    //Creation de l avr associe
                    Avr avrAcheteur = new Avr();
                    BeanUtils.copyProperties(avr, avrAcheteur);
                    avrAcheteur.setEtat(true);
                    avrAcheteur.setPrixUnitaire(venteAmountSecond);
                    avrAcheteur.setProductRegistryValue(productRegistryValueAcheteur);
                    avrRepository.save(avrAcheteur);

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
            fifoService.removeById(currentFirstVente.getId());
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

    public void achatFifoGie(Fifo achatFifo){


       // DetailsAgr d = agrInterimClient.getDetailById(achatFifo.getAvr().getDetailAgr());
        DetailsAgr d = detailAgrClient.getDetailById(achatFifo.getAvr().getDetailAgr());

        //Ksu k =ksuInterimClient.getById(d.getKsu());
        Ksu k =ksuClient.getById(d.getKsu());



        //Etape 1 :  Vente du produit par Gie a l'acheteur
        System.out.println("//Etape 1 :  Vente du produit par Gie a l'acheteur");
       // Long currentFirstAchatTe = teInterimClient.getTeByDetailAgr(achatFifo.getDetailAgr()).getId();

        Long currentFirstAchatTe = teClient.getTeByDetailAgr(achatFifo.getDetailAgr()).getId();

        System.out.println("Agr Acheteur : "+achatFifo.getDetailAgr());

        System.out.println("Te Acheteur : "+currentFirstAchatTe);

        Avr avr = avrRepository.findById(achatFifo.getAvr().getId()).get();
        avr.setEtat(false);
        avrRepository.save(avr);

        System.out.println("//crea eproduct");

            ProductRegistryValue productRegistryValue = new ProductRegistryValue();
            productRegistryValue.setValue(achatFifo.getDefaultAmountSecondType());
            ProductRegistryValue productRegistryValue1 =  productRegistryValueRepository.save(productRegistryValue);
            productRegistryValue1.setIdentifant("0000"+productRegistryValue1.getId());
            productRegistryValue1.setIdKsu(k.getId());
            productRegistryValueRepository.save(productRegistryValue1);

        System.out.println("//initiate avr");
            Avr a = new Avr();
            BeanUtils.copyProperties(avr, a);
            a.setEtat(true);
            a.setProductRegistryValue(productRegistryValue);
            Avr avrPersist = avrRepository.save(a);

            Long venteDetailAgr = AccountConstant.TeEsmcGieFranchiseZeroOBPS;
            Long achatDetailAgr = currentFirstAchatTe;
            Double amount = achatFifo.getActionAmountFirstType();

        System.out.println("//avr created");
        Extrant ext1 = fifoService.CreateBonV3_with_agr("DA",venteDetailAgr, 1,achatDetailAgr,amount,avrPersist, null);
        Extrant ext2 = fifoService.CreateBonV3_with_agr("FPF",achatDetailAgr, 1,venteDetailAgr,amount,avrPersist,ext1.getId());
        Extrant ex = fifoService.CreateBonV3_with_agr("BC",venteDetailAgr, 1,achatDetailAgr,amount,avrPersist, ext2.getId());
        Extrant ex1 = fifoService.CreateBonV3_with_agr("BL",achatDetailAgr, 1,venteDetailAgr,amount,avrPersist,ex.getId());
        Extrant ex2 = fifoService.CreateBonV3_with_agr("BRBL",venteDetailAgr, 1,achatDetailAgr,amount,avrPersist,ex1.getId());
        Extrant ex3 = fifoService.CreateBonV3_with_agr("FD",achatDetailAgr, 1,venteDetailAgr,amount,avrPersist, ex2.getId());
        Extrant ex4 = fifoService.CreateBonV3_with_agr("BESM",venteDetailAgr, 1,achatDetailAgr,amount,avrPersist, ex3.getId());
        Extrant ex5 =  fifoService.CreateBonV3_with_agr("BRBE",achatDetailAgr, 1,venteDetailAgr,amount,avrPersist, ex4.getId());

        Extrant ext4 = fifoService.CreateBonV3_with_agr("DFA",venteDetailAgr, 1,achatDetailAgr,amount,avrPersist, ex5.getId());
        Extrant ext5 = fifoService.CreateBonV3_with_agr("FA",achatDetailAgr, 1,venteDetailAgr,amount,avrPersist,ext4.getId());
        Extrant ext6 = fifoService.CreateBonV3_with_agr("BRFA",venteDetailAgr, 1,achatDetailAgr,amount,avrPersist, ext5.getId());
        Extrant ext7 = fifoService.CreateBonV3_with_agr("BLA",achatDetailAgr, 1,venteDetailAgr,amount,avrPersist,ext6.getId());
        System.out.println("bon exchanged");

        //Etape 2 : Payement a Gie par l'acheteur

        System.out.println("Montant : "+amount);

        //teInterimClient.mutationPourAchatFifoAdmin(currentFirstAchatTe,venteDetailAgr,amount);
        teClient.mutationPourAchatFifoAdmin(currentFirstAchatTe,venteDetailAgr,amount);
        System.out.println("mutation ok");

        //complement code for colorillage
        HistoryGieAchat historyGieAchat = new HistoryGieAchat();
        historyGieAchat.setBciValue(amount);
        historyGieAchat.setIdTe(currentFirstAchatTe);
        historyGieAchatRepository.save(historyGieAchat);
        //------------end--------------------

        fifoRepository.delete(achatFifo);
    }

    public void achatFifoGie_no_cron(Fifo achatFifo){


        DetailsAgr d = detailAgrClient.getDetailById(achatFifo.getAvr().getDetailAgr());

        Ksu k =ksuClient.getById(d.getKsu());

        //Etape 1 :  Vente du produit par Gie a l'acheteur
        System.out.println("//Etape 1 :  Vente du produit par Gie a l'acheteur");
        Long currentFirstAchatTe = teClient.getTeByDetailAgr(achatFifo.getDetailAgr()).getId();

        System.out.println("Agr Acheteur : "+achatFifo.getDetailAgr());

        System.out.println("Te Acheteur : "+currentFirstAchatTe);

        Avr avr = avrRepository.findById(achatFifo.getAvr().getId()).get();
        avr.setEtat(false);
        avrRepository.save(avr);

        System.out.println("//crea eproduct");

        ProductRegistryValue productRegistryValue = new ProductRegistryValue();
        productRegistryValue.setValue(achatFifo.getDefaultAmountSecondType());
        ProductRegistryValue productRegistryValue1 =  productRegistryValueRepository.save(productRegistryValue);
        productRegistryValue1.setIdentifant("0000"+productRegistryValue1.getId());
        productRegistryValue1.setIdKsu(k.getId());
        productRegistryValueRepository.save(productRegistryValue1);

        System.out.println("//initiate avr");
        Avr a = new Avr();
   //     BeanUtils.copyProperties(avr, a);
        a.setDetailAgr(avr.getDetailAgr());
        a.setLibelle(avr.getLibelle());
        a.setDescription(avr.getDescription());
        a.setQuantite(avr.getQuantite());
        a.setPrixUnitaire(avr.getPrixUnitaire());
        a.setCategorieAvr(avr.getCategorieAvr());
        a.setTypeAvr(avr.getTypeAvr());
        a.setProductRegistryValue(avr.getProductRegistryValue());
        a.setEtat(true);
        a.setProductRegistryValue(productRegistryValue);
        Avr avrPersist = avrRepository.save(a);

        Long venteDetailAgr = AccountConstant.TeEsmcGieFranchiseZeroOBPS;
        Long TeInterim = AccountConstant.TeEsmcGieFranchiseZeroOBPS;
        Long achatDetailAgr = currentFirstAchatTe;
        Double amount = achatFifo.getActionAmountFirstType();

        System.out.println("//avr created");
        Extrant ext1 = fifoService.CreateBonV3_with_agr_no_cron("DA",venteDetailAgr, 1,achatDetailAgr,amount,avrPersist, null);
        Extrant ext2 = fifoService.CreateBonV3_with_agr_no_cron("FPF",achatDetailAgr, 1,venteDetailAgr,amount,avrPersist,ext1.getId());
        Extrant ex = fifoService.CreateBonV3_with_agr_no_cron("BC",venteDetailAgr, 1,achatDetailAgr,amount,avrPersist, ext2.getId());
        Extrant ex1 = fifoService.CreateBonV3_with_agr_no_cron("BL",achatDetailAgr, 1,venteDetailAgr,amount,avrPersist,ex.getId());
        Extrant ex2 = fifoService.CreateBonV3_with_agr_no_cron("BRBL",venteDetailAgr, 1,achatDetailAgr,amount,avrPersist,ex1.getId());
        Extrant ex3 = fifoService.CreateBonV3_with_agr_no_cron("FD",achatDetailAgr, 1,venteDetailAgr,amount,avrPersist, ex2.getId());
        Extrant ex4 = fifoService.CreateBonV3_with_agr_no_cron("BESM",venteDetailAgr, 1,achatDetailAgr,amount,avrPersist, ex3.getId());
        Extrant ex5 =  fifoService.CreateBonV3_with_agr_no_cron("BRBE",achatDetailAgr, 1,venteDetailAgr,amount,avrPersist, ex4.getId());

        Extrant ext4 = fifoService.CreateBonV3_with_agr_no_cron("DFA",venteDetailAgr, 1,achatDetailAgr,amount,avrPersist, ex5.getId());
        Extrant ext5 = fifoService.CreateBonV3_with_agr_no_cron("FA",achatDetailAgr, 1,venteDetailAgr,amount,avrPersist,ext4.getId());
        Extrant ext6 = fifoService.CreateBonV3_with_agr_no_cron("BRFA",venteDetailAgr, 1,achatDetailAgr,amount,avrPersist, ext5.getId());
        Extrant ext7 = fifoService.CreateBonV3_with_agr_no_cron("BLA",achatDetailAgr, 1,venteDetailAgr,amount,avrPersist,ext6.getId());
        System.out.println("bon exchanged");

        //Etape 2 : Payement a Gie par l'acheteur

        System.out.println("Montant : "+amount);
        // ======================================================================> Paiement à GIE
        teClient.mutationPourAchatFifoAdmin(TeInterim,venteDetailAgr,amount);
        System.out.println("mutation ok");

        //complement code for colorillage
        HistoryGieAchat historyGieAchat = new HistoryGieAchat();
        historyGieAchat.setBciValue(amount);
        historyGieAchat.setIdTe(currentFirstAchatTe);
        historyGieAchatRepository.save(historyGieAchat);
        //------------end--------------------
        //HISTORIQUE FIFO
        FifoHistory fifoHistory = new FifoHistory();
      //  BeanUtils.copyProperties(achatFifo, fifoHistory);
        fifoHistory.setDetailAgr(achatFifo.getDetailAgr());
        fifoHistory.setAvr(achatFifo.getAvr());
        fifoHistory.setActionAmountFirstType(achatFifo.getActionAmountFirstType());
        fifoHistory.setActionAmountSecondType(achatFifo.getActionAmountSecondType());
        fifoHistory.setIsBuy(achatFifo.getIsBuy());
        fifoHistory.setDetailAgr(achatFifo.getDetailAgr());
        fifoHistory.setDefaultAmountFirstType(achatFifo.getDefaultAmountFirstType());
        fifoHistory.setDefaultAmountSecondType(achatFifo.getDefaultAmountSecondType());
        fifoHistory.setEndStatus(achatFifo.getEndStatus());
        fifoHistory.setProductRegistryValue(achatFifo.getProductRegistryValue());
//        fifoHistory.setNumOrdreAchat(achatFifo.getNumOrdreAchat());
//        fifoHistory.setNumOrdreVente(achatFifo.getNumOrdreVente());
        fifoHistoryRepository.save(fifoHistory);
        fifoRepository.delete(achatFifo);
    }

}
