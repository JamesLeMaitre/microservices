package com.esmc.gestionAvr.services;

import com.esmc.gestionAvr.InterimFeign.AgrInterimClient;
import com.esmc.gestionAvr.InterimFeign.KsuInterimClient;
import com.esmc.gestionAvr.dao.VendeursEnAttenteDeQuota;
import com.esmc.gestionAvr.entities.*;
import com.esmc.gestionAvr.feign.DetailAgrClient;
import com.esmc.gestionAvr.feign.KsuClient;
import com.esmc.gestionAvr.repositories.*;
import com.esmc.gestionAvr.servicesInterfaces.FifoInterface;
import com.esmc.gestionAvr.utils.enums.KsuType;
import com.esmc.models.DetailsAgr;
import com.esmc.models.Ksu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.esmc.gestionAvr.constant.javaConst.PLAFOND_ACHETEUR;
import static com.esmc.gestionAvr.constant.javaConst.PLAFOND_VENDEUR;

@Service
@Transactional
public class FifoService implements FifoInterface {

    @Autowired
    DetailAgrClient detailAgrClient;

    @Autowired
    KsuClient ksuClient;

    @Autowired
    private FifoRepository fifoRepository;

    @Autowired
    private DetailSMAvrRepository detailSMAvrRepository;

    @Autowired
    private SmAvrRepository smAvrRepository;

    @Autowired
    private AvrRepository avrRepository;

    @Autowired
    private AvrServices avrServices;

    @Autowired
    private IntrantRepository intrantRepository;

    @Autowired
    private ExtrantRepository extrantRepository;

    @Autowired
    private TypeSmAvrService typeSmAvrService;

    @Autowired
    private AffectationSMAvrRepository affectationSMAvrRepository;

    @Autowired
    AgrInterimClient agrInterimClient;

    @Autowired
    KsuInterimClient ksuInterimClient;

    @Override
    public void addFifo(Fifo f, Long id) throws Exception {
        if (f.getAvr().getTypeAvr().getLibelle().toLowerCase().contains("vente")) {
//            f.setNumOrdreVente(setNextNumOrdreVente());
        } else {
//            f.setNumOrdreAchat(setNextNumOrdreAchat());
        }
        f.setDetailAgr(id);

        f.setDateCreate(new Date());

        f.setDateUpdate(new Date());


        fifoRepository.save(f);

        //changement();

        //comparer();
    }

    public Fifo addFifoV2(Fifo f, Long id) throws Exception {
//        if (f.getAvr().getTypeAvr().getLibelle().toLowerCase().contains("vente")) {
//            f.setNumOrdreVente(setNextNumOrdreVente());
//        } else {
//            f.setNumOrdreAchat(setNextNumOrdreAchat());
//        }
        f.setDetailAgr(id);

//        f.setDateCreate(new Date());
//
//        f.setDateUpdate(new Date());

        return fifoRepository.save(f);

        //changement();

        //comparer();
    }

    @Override
    public Fifo addFifoByCategorie(Fifo f) {
        return null;
    }

    @Override
    public Long getKsuByDetailAgr(Long detailAgr) {
        return null;
    }

    @Override
    public Fifo getFifoById(Long id) {
        return fifoRepository.findById(id).orElse(null);
    }

    @Override
    public Fifo updateFifo(Fifo f) {

        return fifoRepository.save(f);
    }

    @Override
    public void deleteFifo(Long id) {
        fifoRepository.deleteById(id);
    }

    @Override
    public List<Fifo> listFifo() {
        return fifoRepository.findAll();
    }

    @Override
    public List<Fifo> ListFifoActif() {

        return fifoRepository.ListFifoActif();
    }

    @Override
    public List<Fifo> ListFifoInactif() {
        return fifoRepository.ListFifoInactif();
    }

    @Override
    public List<Fifo> ListFifoByDetailAgrId(Long id) {
        return fifoRepository.ListFifoByDetailAgrId(id);
    }

    @Override
    public List<Fifo> ListFifoVenteActif() {
        return fifoRepository.ListFifoVenteActif();
    }

    @Override
    public List<Fifo> ListFifoAchatActif() {
        return fifoRepository.ListFifoAchatActif();
    }

    @Override
    public List<Fifo> ListFifoVenteInactif() {
        return fifoRepository.ListFifoVenteInactif();
    }

    @Override
    public List<Fifo> ListFifoAchatInactif() {
        return fifoRepository.ListFifoAchatInactif();
    }

    /*@Override
    public List<Fifo> ListFifoByFicheODDId(Long id) {
        return fifoRepository.ListFifoByFicheODDId(id);
    }

    @Override
    public List<Fifo> ListFifoByByDetailAgrIdAndFicheODDId(Long id, Long id2) {
        return fifoRepository.ListFifoByByDetailAgrIdAndFicheODDId(id, id2);
    }*/

    @Override
    public List<Fifo> ListFifoByTypeAvr(String libelle) {
        return fifoRepository.ListFifoByTypeAvr(libelle);
    }

    @Override
    public Fifo echangeFifo(int numOrdre) {
        return null;
    }

    @Override
    public List<Fifo> ListFifoByTypeAvrAchat() {
        return fifoRepository.ListFifoByTypeAvrAchat();
    }

    @Override
    public List<Fifo> ListFifoByTypeAvrVente() {
        return fifoRepository.ListFifoByTypeAvrVente();
    }


//    public int setNextNumOrdreVente() {
//        Fifo last = fifoRepository.dernierFifoVente();
//
//        if (last == null) {
//            int NextNum = 1;
//            return NextNum;
//        }
//        int lastNumOrdre = last.getNumOrdre();
//        int NextNum = lastNumOrdre + 1;
//        return NextNum;
//    }

//    public int setNextNumOrdreVente() {
//        int NextNum = fifoRepository.findAll()
//                .map(Fifo::getNumOrdre)
//                .map(lastNumOrdre -> lastNumOrdre + 1)
//                .orElse(1);
//        return NextNum;
//    }

    public int setNextNumOrdreVente() {
        int last = fifoRepository.dernierFifoVente();
        int NextNum = 1;
        if (last != 0) {
            NextNum = last + 1;
        }
        return NextNum;
    }

    public int setNextNumOrdreAchat() {
        int last = fifoRepository.dernierFifoAchat();
        int NextNum = 1;
        if (last != 0) {
            NextNum = last + 1;
        }
        return NextNum;
    }


//    public int setNextNumOrdreAchat() {
////        Fifo last = fifoRepository.dernierFifoAchat();
//        if (last == null) {
//            int NextNum = 1;
//            return NextNum;
//        }
//        int lastNumOrdre = last.getNumOrdreAchat();
//        int NextNum = lastNumOrdre + 1;
//        return NextNum;
//    }

    /*public void desactivation( Fifo fifo){
        Avr a = fifo.getAvr();
         a.setEtat(Boolean.FALSE);
    }*/

    /*public void echange(Fifo fifoachat, Fifo fifovente, DetailSMAvr d){

        Double montantAchat =  fifoachat.getAvr().getQuantite()*fifoachat.getAvr().getPrixUnitaire();

        Double montantVente =  fifovente.getAvr().getQuantite()*fifovente.getAvr().getPrixUnitaire();

        //Création des variables locales
        Avr a1 = fifovente.getAvr();
        int val = Math.toIntExact(a1.getId());
        SMAvr s1 = smAvrRepository.GetSmavrByLibelle("bon de commande");

        final long MILLIS_PER_DAY = 24 * 3600 * 1000;









        //Contrainte delais
        //Récupérer obligatoirement la somme des bons
        double som = detailSMAvrRepository..();
        if (som<7000000){
            double reste = 7000000 - som;
            if (montantAchat<= reste){


                d.setDateCreate(new Date());
                d.setQuantite(1);
                d.setPrixUnitaire(reste);
                d.setDateUpdate(new Date());
                d.setAvr(fifovente.getAvr());
                d.setAcheteur(val);
                d.setSmAvr(s1);
                detailSMAvrRepository.save(d);
            }else{


            }
        }


        if (montantAchat>0 && montantAchat<=7000000){
                //Envoyer un bon
                *//*fifoachat.getAvr().getDetailAgr();
                DetailSMAvr detailSMAvr = new DetailSMAvr();
                detailSMAvr.setAvr();
                detailSMAvr.setSmAvr();
                detailSMAvrRepository.save(detailSMAvr);*//*



                d.setDateCreate(new Date());
                d.setQuantite(1);
                d.setPrixUnitaire(montantAchat);
                d.setDateUpdate(new Date());
                d.setAvr(fifovente.getAvr());
                d.setAcheteur(val);
                d.setSmAvr(s1);
                detailSMAvrRepository.save(d);


                if (montantAchat >= montantVente){
                   montantAchat = montantAchat - montantVente;
                   fifoachat.getAvr().setPrixUnitaire(montantAchat);
                   fifoRepository.save(fifoachat);
                   *//*fifoachat.getAvr().*//*
                }
        }
    }*/

    /*public int decrNumOrdre(){
    return null;
    }*/

    @Override
    public void changement() {

        List<Fifo> list_achat = fifoRepository.ListFifoByTypeAvrAchat();
        /*list_achat.forEach(System.out::println);*/
        Double montantPlafond = 35000000.0;

        for (Fifo fa : list_achat) {

            Double montantAchat = fa.getAvr().getPrixUnitaire();
            System.out.println(montantAchat);

            Double sommeTotalVente = 0.0;

            if (montantAchat < montantPlafond) {
                Double montant = 0.0;
                while (montant < montantAchat) {
                    List<Fifo> list_vente = fifoRepository.ListFifoByTypeAvrVente();

                    //Avr du Fifo de type Achat
                    Avr avrAchat = getById(fa.getAvr().getId());

                    //Récupération somme total des vendeurs
                    sommeTotalVente = fifoRepository.sommeAchat();


                    if (sommeTotalVente > 0) {
                        //Parcourir liste des vendeurs
                        for (Fifo fv : list_vente) {

                            //Récupération de l'Avr du vendeur
                            Avr avrVente = getById(fv.getAvr().getId());


                            System.out.println("somme total: " + sommeTotalVente);

                            if (avrVente.getPrixUnitaire() > 0) {
                                if (avrVente.getPrixUnitaire() >= montantAchat) {
                                    avrVente.setPrixUnitaire(avrVente.getPrixUnitaire() - montantAchat);
                                    avrRepository.save(avrVente);
                                    avrVente.setDateUpdate(new Date());
                                    montantAchat = 0.0;
                                    fa.getAvr().setEtat(false);
                                    avrAchat.setEtat(false);
                                    avrAchat.setDateUpdate(new Date());
                                    avrAchat.setPrixUnitaire(0.0);
                                    avrRepository.save(avrAchat);

                                    break;
                                } else {
                                    montantAchat = montantAchat - fv.getAvr().getPrixUnitaire();
                                    avrVente.setPrixUnitaire(fv.getAvr().getPrixUnitaire() - fv.getAvr().getPrixUnitaire());
                                    System.out.println(montant);
                                    System.out.println(montantAchat);
                                    avrVente.setEtat(false);
                                    avrRepository.save(avrVente);
                                    avrAchat.setPrixUnitaire(montantAchat);
                                    avrRepository.save(avrAchat);

                                    break;
                                }

                            }

                        }
                    } else {
                        montantAchat = 0.0;
                        break;
                    }

                }
            }

        }

    }

    @Override
    public Long getKsu(Long idDetailAgr) {
        return fifoRepository.getKsu(idDetailAgr, PageRequest.of(0, 1));
    }

    @Override
    public Fifo getFirstAchatFromExtern(boolean b, Long idKsu) {
        List<Fifo> fifoList = fifoRepository.getFirstAchatFromExtern(b, idKsu, PageRequest.of(0, 1));
        if (fifoList.size() > 0) {
            return fifoList.get(0);
        }
        return null;
    }


    public Avr getById(Long id) {
        Optional<Avr> det = avrRepository.findById(id);
        return det.get();
    }

    //Comparaison des fifos ventes et fifos achat pour trouver des correspondances
    public void comparer() {
        List<Fifo> fifoActifs = fifoRepository.ListFifoActif();

        if (!fifoActifs.isEmpty()) {


            for (Fifo fa : fifoActifs) {

                System.out.println(fa);

                if (fa.getAvr().getTypeAvr().getLibelle().toLowerCase().contains("vente") && fa.getAvr().getTypeAvr().getLibelle().toLowerCase().contains("fifo")) {
                    //si le fifo est de type vente on, le essaie de le vendre
                    System.out.println("vente");


                    if (montantRestantAvantDepassementVente(fa.getDetailAgr()) > 0) {
                        vendre(fa, fifoActifs);
                    }


                }

            }

        }

    }


    public void vendre(Fifo f, List<Fifo> achetables) {

        DetailsAgr detailsAgrVendeur = detailAgrClient.getDetailById(f.getAvr().getDetailAgr());
        Ksu ksuVendeur = ksuClient.getById(detailsAgrVendeur.getKsu());

        for (Fifo fa : achetables) {
            DetailsAgr detailsAgrAcheteur = detailAgrClient.getDetailById(fa.getAvr().getDetailAgr());
            Ksu ksuAcheteur = ksuClient.getById(detailsAgrAcheteur.getKsu());

            if (ksuVendeur != ksuAcheteur) {
                if (fa.getAvr().getTypeAvr().getLibelle().toLowerCase().contains("achat") && fa.getAvr().getTypeAvr().getLibelle().toLowerCase().contains("fifo")) {

                    System.out.println(fa);

                    DetailsAgr detailsAgr = detailAgrClient.getDetailById(fa.getAvr().getDetailAgr());

                    Long id = detailsAgr.getKsu();

                    double cloture = montantRestantAvantDepassementAchat(fa.getDetailAgr());

                    Avr etatvente = avrRepository.findById(f.getAvr().getId()).orElse(null);
                    /*double montantrestanventesuravr = restantVente(f.getAvr());*/
                    int quantiterestantenvente = quantiterestantenvente(f.getAvr());

                    Avr etatachat = avrRepository.findById(fa.getAvr().getId()).orElse(null);
                    double montantrestantaachete = restantAchat(fa.getAvr());
                    int quantiterestantachat = quantiterestantachat(fa.getAvr());

                    assert etatachat != null;
                    if (etatachat.getEtat().equals(true)) {
                        assert etatvente != null;
                        if (etatvente.getEtat().equals(true)) {

                            if (cloture > 0) {
                                if (cloture >= montantrestantaachete) {

                                    if (quantiterestantenvente == quantiterestantachat) {
                                        if (quantiterestantachat * fa.getAvr().getPrixUnitaire() >= cloture) {

                                            System.out.println("Je suis ici 2");

                                            int quantachetable = (int) (cloture / fa.getAvr().getPrixUnitaire());

                                            Avr avrAchat = fa.getAvr();

                                            avrAchat.setEtat(false);

                                            avrAchat.setDateUpdate(new Date());

                                            avrRepository.save(avrAchat);

                                            //Création des bons
                                            CreateBon("BC", fa, quantiterestantenvente, f);
                                            CreateBon("BL", f, quantiterestantenvente, fa);
                                            CreateBon("BRBL", fa, quantiterestantenvente, f);
                                            CreateBon("FD", f, quantiterestantenvente, fa);
                                            CreateBon("BESM", fa, quantiterestantenvente, f);
                                            CreateBon("BRBE", f, quantiterestantenvente, fa);


                                        } else {
                                            System.out.println("Je suis ici 1");

                                            Avr avrVente = f.getAvr();

                                            avrVente.setEtat(false);

                                            avrVente.setDateUpdate(new Date());

                                            avrRepository.save(avrVente);

                                            Avr avrAchat = fa.getAvr();

                                            avrAchat.setEtat(false);

                                            //Création des bons
                                            avrRepository.save(avrAchat);

                                            //Création des bons
                                            CreateBon("BC", fa, quantiterestantenvente, f);
                                            CreateBon("BL", f, quantiterestantenvente, fa);
                                            CreateBon("BRBL", fa, quantiterestantenvente, f);
                                            CreateBon("FD", f, quantiterestantenvente, fa);
                                            CreateBon("BESM", fa, quantiterestantenvente, f);
                                            CreateBon("BRBE", f, quantiterestantenvente, fa);


                                        }


                                    } else if (quantiterestantenvente < quantiterestantachat) {
                                        System.out.println("Je suis ici 3");

                                        Avr avrVente = f.getAvr();

                                        avrVente.setEtat(false);

                                        avrVente.setDateUpdate(new Date());

                                        avrRepository.save(avrVente);

                                        //Création des bons
                                        CreateBon("BC", fa, quantiterestantenvente, f);
                                        CreateBon("BL", f, quantiterestantenvente, fa);
                                        CreateBon("BRBL", fa, quantiterestantenvente, f);
                                        CreateBon("FD", f, quantiterestantenvente, fa);
                                        CreateBon("BESM", fa, quantiterestantenvente, f);
                                        CreateBon("BRBE", f, quantiterestantenvente, fa);

                                    } else {

                                        if ((quantiterestantachat * fa.getAvr().getPrixUnitaire()) > cloture) {

                                            System.out.println("Je suis ici 6");

                                            int quantachetable = (int) (cloture / fa.getAvr().getPrixUnitaire());

                                            Avr avrAchat = fa.getAvr();

                                            avrAchat.setEtat(false);

                                            avrAchat.setDateUpdate(new Date());

                                            avrRepository.save(avrAchat);

                                            //Création des bons
                                            CreateBon("BC", fa, quantiterestantenvente, f);
                                            CreateBon("BL", f, quantiterestantenvente, fa);
                                            CreateBon("BRBL", fa, quantiterestantenvente, f);
                                            CreateBon("FD", f, quantiterestantenvente, fa);
                                            CreateBon("BESM", fa, quantiterestantenvente, f);
                                            CreateBon("BRBE", f, quantiterestantenvente, fa);


                                        } else {
                                            System.out.println("Je suis ici 4");
                                            Avr avrAchat = f.getAvr();
                                            avrAchat.setEtat(false);
                                            avrAchat.setDateUpdate(new Date());
                                            avrRepository.save(avrAchat);

                                            //Création des bons
                                            CreateBon("BC", fa, quantiterestantenvente, f);
                                            CreateBon("BL", f, quantiterestantenvente, fa);
                                            CreateBon("BRBL", fa, quantiterestantenvente, f);
                                            CreateBon("FD", f, quantiterestantenvente, fa);
                                            CreateBon("BESM", fa, quantiterestantenvente, f);
                                            CreateBon("BRBE", f, quantiterestantenvente, fa);

                                        }

                                    }


                                } else {
                                    System.out.println("Je suis ici 5");

                                    Avr avrVente = f.getAvr();

                                    avrVente.setEtat(false);

                                    avrVente.setDateUpdate(new Date());

                                    avrRepository.save(avrVente);

                                    //Création des bons
                                    CreateBon("BC", fa, quantiterestantenvente, f);
                                    CreateBon("BL", f, quantiterestantenvente, fa);
                                    CreateBon("BRBL", fa, quantiterestantenvente, f);
                                    CreateBon("FD", f, quantiterestantenvente, fa);
                                    CreateBon("BESM", fa, quantiterestantenvente, f);
                                    CreateBon("BRBE", f, quantiterestantenvente, fa);
                                }
                            }
                        }
                    }


                }

            }

        }
    }

    public double restantVente(Avr avrEnAchat) {
        double montant = 0;
        List<DetailSMAvr> bons = detailSMAvrRepository.DetailByAvrOfFifoAcheteur(avrEnAchat);
        for (DetailSMAvr det : bons) {
            montant = montant + (det.getQuantite() * det.getPrixUnitaire());

        }
        return montant;

    }

    int quantiterestantenvente(Avr vente) {
        int quant = 0;
        List<DetailSMAvr> bons = detailSMAvrRepository.DetailByAvrOfFifoAcheteur(vente);
        for (DetailSMAvr det : bons) {
            quant = quant + det.getQuantite();

        }
        return (vente.getQuantite() - quant);

    }

    int quantiterestantachat(Avr achat) {
        int quant = 0;
        List<DetailSMAvr> bons = detailSMAvrRepository.DetailByAvrOfFifoAcheteur(achat);
        for (DetailSMAvr det : bons) {
            quant = quant + det.getQuantite();
        }
        return (achat.getQuantite() - quant);
    }

    public double restantAchat(Avr avrEnVente) {
        double montant = 0;
        List<DetailSMAvr> bons = detailSMAvrRepository.DetailByAvrOfFifoVendeur(avrEnVente);
        for (DetailSMAvr det : bons) {
            montant = montant + (det.getQuantite() * det.getPrixUnitaire());


        }
        return montant;

    }


    //fonction de creation de bon
    public void CreateBon(String bon, Fifo prenant, int quantite, Fifo fifo) {

        // List<AffectationSMAvr> listSmAcheteur = affectationSMAvrRepository.getSmavr(EchangeConstant.sansProformat, TypeAvrConstant.acheteur);
        //  System.out.println("Liste des sm Acheteur : "+listSmAcheteur.size());

        //  List<AffectationSMAvr> listSmVendeur = affectationSMAvrRepository.getSmavr(EchangeConstant.sansProformat, TypeAvrConstant.vendeur);

        //  System.out.println("Liste des sm Vendeur : "+listSmVendeur.size());

        //on recupere le type de bon
        TypeSmAvr t = typeSmAvrService.DetailTypeBon(bon);
        Intrant in = new Intrant();
        Extrant ex = new Extrant();
        DetailsAgr detailsAgr = detailAgrClient.getDetailById(fifo.getAvr().getDetailAgr());
        Ksu ksu = ksuClient.getById(detailsAgr.getKsu());
        System.out.println("ksu : " + ksu.getId());


        System.out.println(t);
        //on genere le SMAVR
        SMAvr bongenere = new SMAvr();
        bongenere.setTypeSmAvr(t);
//        bongenere.set
        SMAvr a = smAvrRepository.save(bongenere);


        //on cree un detail SMAVR pour le SMAVR

        DetailSMAvr detailSMAvr = new DetailSMAvr();
        detailSMAvr.setSmAvr(a);
        //t   detailSMAvr.setAcheteur(prenant.getAvr());
        detailSMAvr.setPrixUnitaire(fifo.getAvr().getPrixUnitaire());
        detailSMAvr.setQuantite(quantite);

        ex.setAvr(prenant.getAvr());
        in.setAvr(fifo.getAvr());
        ex.setKsu(ksu.getId());
        in.setTypeSmAvr(t);
        ex.setTypeSmAvr(t);
        in.setKsu(ksu.getId());
        extrantRepository.save(ex);
        intrantRepository.save(in);

//        for (AffectationSMAvr affAcheteur : listSmAcheteur){
//            System.out.println("affAcheteur");
//            ex.setAvr(prenant.getAvr());
//            ex.setKsu(ksu.getId());
//            ex.setAffectationSMAvr(affAcheteur);
//            extrantRepository.save(ex);
//        }

//        for (AffectationSMAvr affVendeur : listSmVendeur){
//            System.out.println("affVendeur");
//            in.setAvr(fifo.getAvr());
//            in.setAffectationSMAvr(affVendeur);
//            in.setKsu(ksu.getId());
//            intrantRepository.save(in);
//        }

        System.out.println("la quantite est : " + quantite);
        //on affecte l'avr
        detailSMAvr.setAvr(fifo.getAvr());
        detailSMAvrRepository.save(detailSMAvr);

        /// ex.setAffectationSMAvr();

    }


    public void CreateBonV2(String bon, Fifo prenant, int quantite, Fifo fifo, Double MontantEnvoye, Double MontantRecu) {


        // List<AffectationSMAvr> listSmAcheteur = affectationSMAvrRepository.getSmavr(EchangeConstant.sansProformat, TypeAvrConstant.acheteur);
        //  System.out.println("Liste des sm Acheteur : "+listSmAcheteur.size());

        //  List<AffectationSMAvr> listSmVendeur = affectationSMAvrRepository.getSmavr(EchangeConstant.sansProformat, TypeAvrConstant.vendeur);

        //  System.out.println("Liste des sm Vendeur : "+listSmVendeur.size());

        //on recupere le type de bon
        TypeSmAvr t = typeSmAvrService.DetailTypeBon(bon);
        Intrant in = new Intrant();
        Extrant ex = new Extrant();

        DetailsAgr detailsAgr = agrInterimClient.getDetailById(fifo.getAvr().getDetailAgr());
        System.out.println("Chek Détail agr :" + fifo.getAvr().getDetailAgr());
        System.out.println(detailsAgr);
        Ksu ksu = ksuInterimClient.getById(detailsAgr.getKsu());
        System.out.println("ksu : " + ksu.getId());

        DetailsAgr detailsAgr1 = agrInterimClient.getDetailById(prenant.getAvr().getDetailAgr());
        Ksu ksu1 = ksuInterimClient.getById(detailsAgr1.getKsu());

        System.out.println(t);
        //on genere le SMAVR
        SMAvr bongenere = new SMAvr();
        bongenere.setTypeSmAvr(t);
//        bongenere.set
        bongenere.setTypeSmAvr(typeSmAvrService.DetailTypeBon(bon));
        //bongenere.setCodeSMAvr(bon);
        SMAvr a = smAvrRepository.save(bongenere);


        //on cree un detail SMAVR pour le SMAVR

        DetailSMAvr detailSMAvr = new DetailSMAvr();
        detailSMAvr.setSmAvr(a);
        //t   detailSMAvr.setAcheteur(prenant.getAvr());
        detailSMAvr.setPrixUnitaire(0.0);
        detailSMAvr.setMontantFirst(MontantEnvoye);
        detailSMAvr.setMontantSecond(MontantRecu);
        detailSMAvr.setQuantite(quantite);


        ex.setAvr(prenant.getAvr());
        in.setAvr(fifo.getAvr());
        in.setMontant(MontantEnvoye);
        ex.setKsu(ksu.getId());
        ex.setMontant(MontantRecu);
        in.setTypeSmAvr(t);
        ex.setTypeSmAvr(t);
        in.setKsu(ksu1.getId());

        System.out.println(ex);
        System.out.println(in);
        // try{
        extrantRepository.save(ex);
        intrantRepository.save(in);
        /*}catch (Exception e){
            System.out.println(e.getMessage());
        }*/


        System.out.println("la quantite est : " + quantite);
        //on affecte l'avr
        detailSMAvr.setAvr(fifo.getAvr());
        detailSMAvrRepository.save(detailSMAvr);

    }


    /***
     *
     *
     *
     *
     */


    public Extrant CreateBonV2_with_agr_no_cron(String bon, Fifo recepteur, int quantite, Fifo envoyeur, Double MontantEnvoye, Double MontantRecu, Avr avr, Long refer) {

        //on recupere le type de bon
        TypeSmAvr t = typeSmAvrService.DetailTypeBon(bon);
        Intrant in = new Intrant();
        Extrant ex = new Extrant();

        DetailsAgr detailsAgr = detailAgrClient.getDetailById(envoyeur.getAvr().getDetailAgr());
        System.out.println("Chek Détail agr :" + envoyeur.getAvr().getDetailAgr());
        System.out.println(detailsAgr);
        Ksu ksuEnvoyeur = ksuClient.getById(detailsAgr.getKsu());
        System.out.println("ksu : " + ksuEnvoyeur.getId());

        DetailsAgr detailsAgr1 = detailAgrClient.getDetailById(recepteur.getAvr().getDetailAgr());
        Ksu ksuRecepteur = ksuClient.getById(detailsAgr1.getKsu());

        System.out.println(t);
        //on genere le SMAVR
        SMAvr bongenere = new SMAvr();
        bongenere.setTypeSmAvr(t);
//        bongenere.set
        bongenere.setTypeSmAvr(typeSmAvrService.DetailTypeBon(bon));
        //bongenere.setCodeSMAvr(bon);
        SMAvr a = smAvrRepository.save(bongenere);


        //on cree un detail SMAVR pour le SMAVR

        DetailSMAvr detailSMAvr = new DetailSMAvr();
        detailSMAvr.setSmAvr(a);
        //t   detailSMAvr.setAcheteur(prenant.getAvr());
        detailSMAvr.setPrixUnitaire(MontantEnvoye);
        detailSMAvr.setMontantFirst(MontantRecu);
        detailSMAvr.setMontantSecond(MontantRecu);
        detailSMAvr.setQuantite(quantite);

        ex.setAvr(avr);
        in.setAvr(avr);

        in.setMontant(MontantEnvoye);
        ex.setMontant(MontantRecu);

        in.setKsuEmetteur(ksuEnvoyeur.getId());
        in.setDetailAgrEmetteur(detailsAgr.getId());
        in.setKsuRecepteur(ksuRecepteur.getId());
        in.setDetailAgrRecepteur(detailsAgr1.getId());

        ex.setKsuEmetteur(ksuEnvoyeur.getId());
        ex.setDetailAgrEmetteur(detailsAgr.getId());
        ex.setKsuRecepteur(ksuRecepteur.getId());
        ex.setDetailAgrRecepteur(detailsAgr1.getId());


        in.setRefer(refer);
        ex.setRefer(refer);

        in.setTypeSmAvr(t);
        ex.setTypeSmAvr(t);

        extrantRepository.save(ex);
        intrantRepository.save(in);

        System.out.println("la quantite est : " + quantite);
        //on affecte l'avr
        detailSMAvr.setAvr(avr);
        detailSMAvrRepository.save(detailSMAvr);
        return ex;

    }

    public Extrant CreateBonV3_with_agr_no_cron(String bon, Long idDetailAgrVente, int quantite, Long idDetailAgrAchat, Double montant, Avr avr, Long refer) {
        //on recupere le type de bon
        TypeSmAvr t = typeSmAvrService.DetailTypeBon(bon);
        Intrant in = new Intrant();
        Extrant ex = new Extrant();

        DetailsAgr detailsAgr = detailAgrClient.getDetailById(idDetailAgrVente);
        Ksu ksuEnvoyeur = ksuClient.getById(detailsAgr.getKsu());

        //System.out.println("ksu : " + ksu.getId());

        DetailsAgr detailsAgr1 = detailAgrClient.getDetailById(idDetailAgrAchat);
        Ksu ksuRecepteur = ksuClient.getById(detailsAgr1.getKsu());

        System.out.println(ksuEnvoyeur);
        System.out.println(ksuRecepteur);
        System.out.println(idDetailAgrAchat);
        System.out.println(idDetailAgrVente);

        System.out.println(t);
        //on genere le SMAVR
        SMAvr bongenere = new SMAvr();
        bongenere.setTypeSmAvr(t);
//        bongenere.set
        bongenere.setTypeSmAvr(typeSmAvrService.DetailTypeBon(bon));
        //bongenere.setCodeSMAvr(bon);
        SMAvr a = smAvrRepository.save(bongenere);


        //on cree un detail SMAVR pour le SMAVR

        DetailSMAvr detailSMAvr = new DetailSMAvr();
        detailSMAvr.setSmAvr(a);
        //t   detailSMAvr.setAcheteur(prenant.getAvr());
        detailSMAvr.setPrixUnitaire(montant);
        detailSMAvr.setMontantFirst(montant);
        detailSMAvr.setMontantSecond(montant);
        detailSMAvr.setQuantite(quantite);


        ex.setAvr(avr);
        in.setAvr(avr);

        ex.setRefer(refer);
        in.setRefer(refer);

        in.setMontant(montant);
        ex.setMontant(montant);

        /*ex.setKsu(ksu.getId());
        in.setKsu(ksu1.getId());*/

        in.setKsuEmetteur(ksuEnvoyeur.getId());
        in.setDetailAgrEmetteur(detailsAgr.getId());
        in.setKsuRecepteur(ksuRecepteur.getId());
        in.setDetailAgrRecepteur(detailsAgr1.getId());

        ex.setKsuEmetteur(ksuEnvoyeur.getId());
        ex.setDetailAgrEmetteur(detailsAgr.getId());
        ex.setKsuRecepteur(ksuRecepteur.getId());
        ex.setDetailAgrRecepteur(detailsAgr1.getId());

        in.setTypeSmAvr(t);
        ex.setTypeSmAvr(t);


        System.out.println(ex);
        System.out.println(in);
        // try{
        extrantRepository.save(ex);
        intrantRepository.save(in);
        /*}catch (Exception e){
            System.out.println(e.getMessage());
        }*/


        //System.out.println("la quantite est : "+quantite);
        //on affecte l'avr
        detailSMAvr.setAvr(avr);
        detailSMAvrRepository.save(detailSMAvr);

        return ex;

    }

    public Extrant CreateBonV2_with_agr(String bon, Fifo recepteur, int quantite, Fifo envoyeur, Double MontantEnvoye, Double MontantRecu, Avr avr, Long refer) {


        // List<AffectationSMAvr> listSmAcheteur = affectationSMAvrRepository.getSmavr(EchangeConstant.sansProformat, TypeAvrConstant.acheteur);
        //  System.out.println("Liste des sm Acheteur : "+listSmAcheteur.size());

        //  List<AffectationSMAvr> listSmVendeur = affectationSMAvrRepository.getSmavr(EchangeConstant.sansProformat, TypeAvrConstant.vendeur);

        //  System.out.println("Liste des sm Vendeur : "+listSmVendeur.size());

        //on recupere le type de bon
        TypeSmAvr t = typeSmAvrService.DetailTypeBon(bon);
        Intrant in = new Intrant();
        Extrant ex = new Extrant();

        DetailsAgr detailsAgr = agrInterimClient.getDetailById(envoyeur.getAvr().getDetailAgr());
        System.out.println("Chek Détail agr :" + envoyeur.getAvr().getDetailAgr());
        System.out.println(detailsAgr);
        Ksu ksuEnvoyeur = ksuInterimClient.getById(detailsAgr.getKsu());
        System.out.println("ksu : " + ksuEnvoyeur.getId());

        DetailsAgr detailsAgr1 = agrInterimClient.getDetailById(recepteur.getAvr().getDetailAgr());
        Ksu ksuRecepteur = ksuInterimClient.getById(detailsAgr1.getKsu());

        System.out.println(t);
        //on genere le SMAVR
        SMAvr bongenere = new SMAvr();
        bongenere.setTypeSmAvr(t);
//        bongenere.set
        bongenere.setTypeSmAvr(typeSmAvrService.DetailTypeBon(bon));
        //bongenere.setCodeSMAvr(bon);
        SMAvr a = smAvrRepository.save(bongenere);


        //on cree un detail SMAVR pour le SMAVR

        DetailSMAvr detailSMAvr = new DetailSMAvr();
        detailSMAvr.setSmAvr(a);
        //t   detailSMAvr.setAcheteur(prenant.getAvr());
        detailSMAvr.setPrixUnitaire(MontantEnvoye);
        detailSMAvr.setMontantFirst(MontantRecu);
        detailSMAvr.setMontantSecond(MontantRecu);
        detailSMAvr.setQuantite(quantite);

        ex.setAvr(avr);
        in.setAvr(avr);

        in.setMontant(MontantEnvoye);
        ex.setMontant(MontantRecu);

        /*ex.setKsu(ksu.getId());
        in.setKsu(ksu1.getId());*/

        in.setKsuEmetteur(ksuEnvoyeur.getId());
        in.setDetailAgrEmetteur(detailsAgr.getId());
        in.setKsuRecepteur(ksuRecepteur.getId());
        in.setDetailAgrRecepteur(detailsAgr1.getId());

        ex.setKsuEmetteur(ksuEnvoyeur.getId());
        ex.setDetailAgrEmetteur(detailsAgr.getId());
        ex.setKsuRecepteur(ksuRecepteur.getId());
        ex.setDetailAgrRecepteur(detailsAgr1.getId());


        in.setRefer(refer);
        ex.setRefer(refer);

        in.setTypeSmAvr(t);
        ex.setTypeSmAvr(t);


        System.out.println(ex);
        System.out.println(in);
        // try{
        extrantRepository.save(ex);
        intrantRepository.save(in);
        /*}catch (Exception e){
            System.out.println(e.getMessage());
        }*/


        System.out.println("la quantite est : " + quantite);
        //on affecte l'avr
        detailSMAvr.setAvr(avr);
        detailSMAvrRepository.save(detailSMAvr);
        return ex;

    }

    public Extrant CreateBonV3_with_agr(String bon, Long idDetailAgrVente, int quantite, Long idDetailAgrAchat, Double montant, Avr avr, Long refer) {


        // List<AffectationSMAvr> listSmAcheteur = affectationSMAvrRepository.getSmavr(EchangeConstant.sansProformat, TypeAvrConstant.acheteur);
        //  System.out.println("Liste des sm Acheteur : "+listSmAcheteur.size());

        //  List<AffectationSMAvr> listSmVendeur = affectationSMAvrRepository.getSmavr(EchangeConstant.sansProformat, TypeAvrConstant.vendeur);

        //  System.out.println("Liste des sm Vendeur : "+listSmVendeur.size());

        //on recupere le type de bon
        TypeSmAvr t = typeSmAvrService.DetailTypeBon(bon);
        Intrant in = new Intrant();
        Extrant ex = new Extrant();

        DetailsAgr detailsAgr = agrInterimClient.getDetailById(idDetailAgrVente);
        Ksu ksuEnvoyeur = ksuInterimClient.getById(detailsAgr.getKsu());

        //System.out.println("ksu : " + ksu.getId());

        DetailsAgr detailsAgr1 = agrInterimClient.getDetailById(idDetailAgrAchat);
        Ksu ksuRecepteur = ksuInterimClient.getById(detailsAgr1.getKsu());

        System.out.println(ksuEnvoyeur);
        System.out.println(ksuRecepteur);
        System.out.println(idDetailAgrAchat);
        System.out.println(idDetailAgrVente);


        /*DetailsAgr detailsAgr = agrInterimClient.getDetailById(envoyeur.getAvr().getDetailAgr());
        System.out.println("Chek Détail agr :" + envoyeur.getAvr().getDetailAgr());
        System.out.println(detailsAgr);
        Ksu ksuEnvoyeur = ksuInterimClient.getById(detailsAgr.getKsu());
        System.out.println("ksu : " + ksuEnvoyeur.getId());

        DetailsAgr detailsAgr1 = agrInterimClient.getDetailById(recepteur.getAvr().getDetailAgr());
        Ksu ksuRecepteur = ksuInterimClient.getById(detailsAgr1.getKsu());*/


        System.out.println(t);
        //on genere le SMAVR
        SMAvr bongenere = new SMAvr();
        bongenere.setTypeSmAvr(t);
//        bongenere.set
        bongenere.setTypeSmAvr(typeSmAvrService.DetailTypeBon(bon));
        //bongenere.setCodeSMAvr(bon);
        SMAvr a = smAvrRepository.save(bongenere);


        //on cree un detail SMAVR pour le SMAVR

        DetailSMAvr detailSMAvr = new DetailSMAvr();
        detailSMAvr.setSmAvr(a);
        //t   detailSMAvr.setAcheteur(prenant.getAvr());
        detailSMAvr.setPrixUnitaire(montant);
        detailSMAvr.setMontantFirst(montant);
        detailSMAvr.setMontantSecond(montant);
        detailSMAvr.setQuantite(quantite);


        ex.setAvr(avr);
        in.setAvr(avr);

        ex.setRefer(refer);
        in.setRefer(refer);

        in.setMontant(montant);
        ex.setMontant(montant);

        /*ex.setKsu(ksu.getId());
        in.setKsu(ksu1.getId());*/

        in.setKsuEmetteur(ksuEnvoyeur.getId());
        in.setDetailAgrEmetteur(detailsAgr.getId());
        in.setKsuRecepteur(ksuRecepteur.getId());
        in.setDetailAgrRecepteur(detailsAgr1.getId());

        ex.setKsuEmetteur(ksuEnvoyeur.getId());
        ex.setDetailAgrEmetteur(detailsAgr.getId());
        ex.setKsuRecepteur(ksuRecepteur.getId());
        ex.setDetailAgrRecepteur(detailsAgr1.getId());

        in.setTypeSmAvr(t);
        ex.setTypeSmAvr(t);


        System.out.println(ex);
        System.out.println(in);
        // try{
        extrantRepository.save(ex);
        intrantRepository.save(in);
        /*}catch (Exception e){
            System.out.println(e.getMessage());
        }*/


        //System.out.println("la quantite est : "+quantite);
        //on affecte l'avr
        detailSMAvr.setAvr(avr);
        detailSMAvrRepository.save(detailSMAvr);

        return ex;

    }

    public void CreateBonV3(String bon, Long idDetailAgrVente, int quantite, Long idDetailAgrAchat, Double montant, Avr avr) {

        //on recupere le type de bon
        TypeSmAvr t = typeSmAvrService.DetailTypeBon(bon);
        Intrant in = new Intrant();
        Extrant ex = new Extrant();

        DetailsAgr detailsAgr = agrInterimClient.getDetailById(idDetailAgrVente);
        Ksu ksu = ksuInterimClient.getById(detailsAgr.getKsu());
        System.out.println("ksu : " + ksu.getId());

        DetailsAgr detailsAgr1 = agrInterimClient.getDetailById(idDetailAgrAchat);
        Ksu ksu1 = ksuInterimClient.getById(detailsAgr1.getKsu());


        System.out.println(t);
        //on genere le SMAVR
        SMAvr bongenere = new SMAvr();
        bongenere.setTypeSmAvr(t);
//        bongenere.set
        bongenere.setTypeSmAvr(typeSmAvrService.DetailTypeBon(bon));
        //bongenere.setCodeSMAvr(bon);
        SMAvr a = smAvrRepository.save(bongenere);

        //on cree un detail SMAVR pour le SMAVR

        DetailSMAvr detailSMAvr = new DetailSMAvr();
        detailSMAvr.setSmAvr(a);
        //t   detailSMAvr.setAcheteur(prenant.getAvr());
        detailSMAvr.setPrixUnitaire(0.0);
        detailSMAvr.setMontantFirst(montant);
        detailSMAvr.setMontantSecond(montant);
        detailSMAvr.setQuantite(quantite);

        ex.setAvr(avr);
        in.setAvr(avr);
        in.setMontant(montant);
        ex.setKsu(ksu.getId());
        ex.setMontant(montant);
        in.setTypeSmAvr(t);
        ex.setTypeSmAvr(t);
        in.setKsu(ksu1.getId());
        extrantRepository.save(ex);
        intrantRepository.save(in);

        System.out.println("la quantite est : " + quantite);
        //on affecte l'avr
        detailSMAvr.setAvr(avr);
        detailSMAvrRepository.save(detailSMAvr);

    }

    //fonction de recuperation du montant restant avant que la personne depasse sa limite de 72 heures en tant que vendeur
    public double montantRestantAvantDepassementVente(Long idDetailAgr) {
        List<Fifo> fifoKsu = fifoRepository.ListFifoByDetailAgrId(idDetailAgr);
        double cloture = PLAFOND_VENDEUR;
        for (Fifo fv : fifoKsu) {
            if (differenceDaysBetweenDateAndNow(fv.getDateCreate()) <= 3) {
                List<DetailSMAvr> dejaconso = detailSMAvrRepository.DetailByAvrOfFifoVendeur(fv.getAvr());
                for (DetailSMAvr detailSMAvr : dejaconso) {
                    cloture = cloture - (detailSMAvr.getQuantite() * detailSMAvr.getPrixUnitaire());

                }

            }

        }

        return cloture;
    }


    //fonction de recuperation du montant restant avant que la personne depasse sa limite de 72 heures en tant que acheteur
    public double montantRestantAvantDepassementAchat(Long idDetailAgr) {
        System.out.println("Les details");
        List<Fifo> fifoKsu = fifoRepository.ListFifoByDetailAgrId(idDetailAgr);
        System.out.println(fifoKsu);
        double cloture = PLAFOND_ACHETEUR;
        for (Fifo fv : fifoKsu) {
            if (differenceDaysBetweenDateAndNow(fv.getDateCreate()) <= 3) {
                List<DetailSMAvr> dejaconso = detailSMAvrRepository.DetailByAvrOfFifoAcheteur(fv.getAvr());
                for (DetailSMAvr detailSMAvr : dejaconso) {
                    cloture = cloture - (detailSMAvr.getQuantite() * detailSMAvr.getPrixUnitaire());
                    System.out.println("il reste " + cloture + "avant depassement achat");

                }

            }

        }
        return cloture;

    }

    public int differenceDaysBetweenDateAndNow(Date date2) {
        /*LocalDate a= LocalDate.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println(ChronoUnit.DAYS.between(LocalDate.parse((new StringBuilder().append(a.getYear()).append("-").append(a.getMonth().getValue()).append("-").append(a.getDayOfMonth()).toString()), dtf), LocalDate.parse(date2.toString(), dtf))+" Jours");
        return ChronoUnit.DAYS.between(a, LocalDate.parse(date2.toString(), dtf));*/
        LocalDate currentDate = LocalDate.now();
        LocalDate dbDate = LocalDate.of(1900 + date2.getYear(), 1 + date2.getMonth(), date2.getDate());
        int days = currentDate.compareTo(dbDate);
        System.out.println("Nombre de jours : " + days);
        return days;

    }


    // @Scheduled(cron = "0/20 * * * * * ")
    public void returnFifoToTrue() {
        List<Fifo> fifoInactif = fifoRepository.ListFifoInactif();
        List<VendeursEnAttenteDeQuota> enAttente = new ArrayList<>();

        for (Fifo fif : fifoInactif) {
            if (differenceDaysBetweenDateAndNow(fif.getAvr().getDateUpdate()) >= 3) {

                if (fif.getAvr().getTypeAvr().getLibelle().toLowerCase().contains("fifo") && fif.getAvr().getTypeAvr().getLibelle().toLowerCase().contains("vente")) {
                    if (montantRestantAvantDepassementVente(fif.getDetailAgr()) <= 0) {
                        if (restantVente(fif.getAvr()) > 0) {
                            VendeursEnAttenteDeQuota vendeur = new VendeursEnAttenteDeQuota();
                            System.out.println("Vente recence");
                            vendeur.setFifo(fif);
                            vendeur.setMontantTotal(fif.getAvr().getPrixUnitaire() * fif.getAvr().getQuantite());
                            enAttente.add(vendeur);
                            System.out.println("Setting Etat vendeur of :" + fif);
                            fif.getAvr().setEtat(true);
                            fif.setDateUpdate(new Date());
                            fif.getAvr().setDateUpdate(new Date());
                            fifoRepository.save(fif);
                            avrRepository.save(fif.getAvr());

                        }
                    }
                } else {
                    if (montantRestantAvantDepassementAchat(fif.getDetailAgr()) <= 0) {
                        if (restantAchat(fif.getAvr()) > 0) {
                            System.out.println("Achat recense");
                            VendeursEnAttenteDeQuota acheteur = new VendeursEnAttenteDeQuota();
                            acheteur.setFifo(fif);
                            acheteur.setMontantTotal(fif.getAvr().getPrixUnitaire() * fif.getAvr().getQuantite());
                            enAttente.add(acheteur);
                            System.out.println("Setting Etat acheteur of :" + fif);
                            fif.getAvr().setEtat(true);
                            fif.setDateUpdate(new Date());
                            fif.getAvr().setDateUpdate(new Date());
                            fifoRepository.save(fif);
                            avrRepository.save(fif.getAvr());
                        }
                    }

                }
            }

        }
    }


    // Vendeur en attente
    public List<VendeursEnAttenteDeQuota> vendeursEnAttenteDeQuotas() {
        List<Fifo> fifos = fifoRepository.ListFifoInactif();
        List<VendeursEnAttenteDeQuota> enAttente = new ArrayList<>();

        for (Fifo fif : fifos) {
            if (fif.getAvr().getTypeAvr().getLibelle().toLowerCase().contains("fifo") && fif.getAvr().getTypeAvr().getLibelle().toLowerCase().contains("vente")) {
                if (montantRestantAvantDepassementVente(fif.getDetailAgr()) <= 0) {
                    if (restantVente(fif.getAvr()) > 0) {
                        VendeursEnAttenteDeQuota vendeur = new VendeursEnAttenteDeQuota();
                        System.out.println("Vente recence");
                        vendeur.setFifo(fif);
                        vendeur.setMontantTotal(fif.getAvr().getPrixUnitaire() * fif.getAvr().getQuantite());
                        enAttente.add(vendeur);

                    }
                }
            } else {
                if (montantRestantAvantDepassementAchat(fif.getDetailAgr()) <= 0) {
                    if (restantAchat(fif.getAvr()) > 0) {
                        System.out.println("Achat recense");
                        VendeursEnAttenteDeQuota acheteur = new VendeursEnAttenteDeQuota();
                        acheteur.setFifo(fif);
                        acheteur.setMontantTotal(fif.getAvr().getPrixUnitaire() * fif.getAvr().getQuantite());
                        enAttente.add(acheteur);
                    }
                }

            }
        }
        return enAttente;
    }

    /***************************************************************************************************************
     *
     *
     *              PRINCE PATRICE dkp 21/07/2022
     *
     *
     * **/


    public Fifo saveFifo(Fifo fifo) {
        return fifoRepository.save(fifo);
    }

    public Fifo findFifoById(Long id) {
        return fifoRepository.findById(id).get();
    }

    public List<Fifo> getAll() {
        return fifoRepository.findAll();
    }

    public List<Fifo> getAllByType(boolean isBuy) {
        return fifoRepository.getAllByType(isBuy);
    }

    public Fifo getFirst(boolean isBuy) {
        return getFirst(fifoRepository.getLastByType(isBuy, PageRequest.of(0, 1)));
    }

    public Fifo getFirstDiffKsu(Long idKsu) {
        return getFirst(fifoRepository.getLastByTypeDiffKsu(idKsu, PageRequest.of(0, 1)));
    }


    public Fifo getFirst(List<Fifo> fifoList) {
        if (fifoList.size() > 0) {
            return fifoList.get(0);
        }
        return null;
    }

    public void removeById(Long id) {
        fifoRepository.deleteById(id);
    }

    //Get Fifo by avr and type

    public Fifo getFirstOrAvrByType(boolean isBuy, Long idAvr) {
        return fifoRepository.getLastByTypeAndAvr(isBuy, idAvr, PageRequest.of(0, 1));
    }

    @Override
    public List<Fifo> getFifiByIdDetailsAgr(Long id) {

        return fifoRepository.getListByIdDetailAgr(id);
    }

    /***************************************************************************************************************
     *
     *
     *              JamesLeMaitre  01/02/2023
     *
     *
     * **/
    @Override
    public void setNumOrder(Vague vague, KsuType ksuType) {
        int count = findAllByKsuType(ksuType).size();
        switch (vague.getLabel()) {
            case "Vague1" : {
                if (vague.getStatus()) {
                    if (KsuType.SELLER == ksuType) {
                        List<Fifo> fifos = findAllByKsuType(ksuType);
                        for (Fifo fifo : fifos) {
                            fifo.setNumOrdre(count++);
                            fifoRepository.save(fifo);
                        }
                    } else if (ksuType == KsuType.BUYER) {
                        List<Fifo> fifos = findAllByKsuType(ksuType);
                        for (Fifo fi : fifos) {
                            fi.setNumOrdre(count++);
                            fifoRepository.save(fi);
                        }
                    }
                }
            }
            case "Vague2" :{
                if (!vague.getStatus()) {
                    if (KsuType.SELLER == ksuType) {
                        List<Fifo> fifos = findAllByKsuType(ksuType);
                        for (Fifo fifo : fifos) {
                            fifo.setNumOrdre(count++);
                            fifoRepository.save(fifo);
                        }
                    } else if (ksuType == KsuType.BUYER) {
                        List<Fifo> fifos = findAllByKsuType(ksuType);
                        for (Fifo fi : fifos) {
                            fi.setNumOrdre(count++);
                            fifoRepository.save(fi);
                        }
                    }
                }
            }
        }

    }

    @Override
    public List<Fifo> findAllByKsuType(KsuType ksuType) {
        return fifoRepository.findAllByKsuTypeAndTreated(ksuType,false);
    }

    @Override
    public Fifo getNumOrdre(Long id,Long ksuType) {
        if(ksuType == 1L){
            Fifo fifo = fifoRepository.getFifoByKsuAndKsuType(id,KsuType.BUYER);
            return fifo;
        } else if(ksuType== 2L){
            Fifo fifo = fifoRepository.getFifoByKsuAndKsuType(id,KsuType.SELLER);
            return fifo;
        }
        return null;
    }


    @Override
    public boolean checkIfExistInTour(Long idKsu, Long ksuType){
        if(ksuType == 1L){ // ACHETEUR
            Fifo fifo = fifoRepository.getFifoByKsuAndKsuType(idKsu,KsuType.BUYER);
            return fifo != null;
        } else if(ksuType== 2L){ // VENDEUR
            Fifo fifo = fifoRepository.getFifoByKsuAndKsuType(idKsu,KsuType.SELLER);
            return fifo != null;
        }
        return false;
    }


//    public int checkNumberTour(Long igKsu,Long ksuType){
//
//    }

}
