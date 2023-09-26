package com.esmc.gestionAgr.fifo.services.impls;

import com.esmc.gestionAgr.fifo.entities.*;
import com.esmc.gestionAgr.fifo.repositories.*;
import com.esmc.gestionAgr.fifo.services.*;
import com.esmc.gestionAgr.fifo.utils.AccountConstant;
import com.esmc.gestionAgr.fifo.utils.enums.KsuType;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;


@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class TourToBasketServiceImpl implements TourToBasketService {
    private final ReferenceTokenRepository referenceTokenRepository;
    private final AvrRepository avrRepository;

    private final FifoRepository fifoRepository;
    private final KsuRepository ksuRepository;
    private final HistoryFiFoRepository hFiFoRepository;

    private final TourService tourService;
    private final BasketService basketService;
    private final VagueService vagueService;
    private final TerminalEchangeService terminalEchangeService;
    private final DetailSMEchangeService detailSMEchangeService;
    private final HistoryGieAchatRepository historyGieAchatRepository;
    private final FifoService fifoService;

    private final ReferenceTokenService tokenService;
    private final TypeSmAvrService typeSmAvrService;
    private final BonSmAvrImpl bonService;
    private final CategoryAvrRepository categoryAvrRepository;
    private final TypeAvrRepository typeAvrRepository;
    private final SettingsService settingsService;
    private final ReferenceTokenImpl referenceTokenService;

//    private final FifoAdminService fifoService;


    /**
     * Complementary Variable
     * defaultAmountFirstType  //Ban Value (buyer init amount)
     * defaultAmountSecondType //Bci Value  (seller init amount)
     * actionAmountFirstType  (en Ban) // the amount to be used during action
     * actionAmountSecondType (en Bci) // the amount to be used during action
     */

    public void vagueManagementTour(Fifo fifo) throws Exception {
        Vague vague = vagueService.getVagueId(fifo.getVague().getId()).get();
        switch (vague.getLabel()) {
            case "Vague1", "Vague2", "Vague3", "Vague4", "Vague6", "Vague5" -> manageTour(fifo);
        }
    }

    // Set Amount to the tour if max set to basket
    public void manageTour(Fifo fifo) throws Exception {
        double MAX_TOUR_SELLER_BCI = Double.parseDouble(settingsService.getByCode("PARAM_MAX_TOUR_SELLER_BCI").getValue());
        double MAX_TOUR_BUYER_BAN = Double.parseDouble(settingsService.getByCode("PARAM_MAX_TOUR_BUYER_BAN").getValue());
        if (fifo.getActionAmountSecondType() == 0) {
            throw new Exception("getActionAmountSecondType Null");
        } else {
            if (fifo.getKsuType() == KsuType.SELLER) {
                // We work with BCI
                double secondMaxValueBan = fifo.getActionAmountFirstType();

                double difference = MAX_TOUR_SELLER_BCI - fifo.getAmount();


                if (MAX_TOUR_SELLER_BCI >= fifo.getAmount()) {
                    fifo.setDefaultAmountSecondType(fifo.getDefaultAmountSecondType());
                    fifo.setDefaultAmountFirstType(secondMaxValueBan);
                    fifo.setActionAmountFirstType(secondMaxValueBan);
                    fifo.setActionAmountSecondType(fifo.getDefaultAmountSecondType());
                    fifoRepository.save(fifo);
                    tourService.addTour(fifo);
                } else {

                    ReferencToken referencTokenTour=  referenceTokenService.divisionToken(fifo.getReferencToken().getId(),fifo.getKsu().getId(),fifo.getDetailAgr(),
                            Math.abs(difference),MAX_TOUR_SELLER_BCI)[0];
                    ReferencToken referencTokenBasket=  referenceTokenService.divisionToken(fifo.getReferencToken().getId(),fifo.getKsu().getId(),fifo.getDetailAgr(),
                            Math.abs(difference),MAX_TOUR_SELLER_BCI)[1];


                    fifo.setDefaultAmountSecondType(MAX_TOUR_SELLER_BCI);
                    fifo.setDefaultAmountFirstType(vagueService.convertBciBan(MAX_TOUR_SELLER_BCI));
                    fifo.setActionAmountFirstType(vagueService.convertBciBan(MAX_TOUR_SELLER_BCI));
                    fifo.setActionAmountSecondType(MAX_TOUR_SELLER_BCI);
                    fifo.setReferencToken(referencTokenTour);
                    fifo.setAmount(MAX_TOUR_SELLER_BCI);
                    fifoRepository.save(fifo);
                    tourService.addTour(fifo);
                    Fifo fifo1 = new Fifo();
                    fifo1.setKsu(fifo.getKsu());
                    fifo1.setDefaultAmountSecondType(Math.abs(difference));
                    fifo1.setDefaultAmountFirstType(vagueService.convertBciBan(Math.abs(difference)));
                    fifo1.setActionAmountFirstType(vagueService.convertBciBan(Math.abs(difference)));
                    fifo1.setActionAmountSecondType(Math.abs(difference));
                    fifo1.setKsuType(fifo.getKsuType());
                    fifo1.setAmount(Math.abs(difference));
                    fifo1.setReferencToken(referencTokenBasket);
                    fifo1.setAvr(fifo.getAvr());
                    fifo1.setVague(fifo.getVague());
                    fifo1.setPanierTour(true);
                    fifo1.setDetailAgr(fifo.getDetailAgr());
                    fifoRepository.save(fifo1);
                    basketService.addOrRemoveBasket(fifo1, true);
                }
            } else
                if (fifo.getKsuType() == KsuType.BUYER) {
                double secondMaxValueBci = vagueService.convertBanBci(fifo.getDefaultAmountFirstType());
                double difference = MAX_TOUR_BUYER_BAN - fifo.getAmount();
                if (MAX_TOUR_BUYER_BAN >= fifo.getAmount()) {
                    fifo.setDefaultAmountSecondType(secondMaxValueBci);
                    fifo.setDefaultAmountFirstType(fifo.getDefaultAmountFirstType());
                    fifo.setActionAmountFirstType(fifo.getActionAmountFirstType());
                    fifo.setActionAmountSecondType(secondMaxValueBci);
                    fifoRepository.save(fifo);
                    tourService.addTour(fifo);
                } else {
                    fifo.setDefaultAmountSecondType(vagueService.convertBanBci(MAX_TOUR_BUYER_BAN));
                    fifo.setDefaultAmountFirstType(MAX_TOUR_BUYER_BAN);
                    fifo.setActionAmountFirstType(MAX_TOUR_BUYER_BAN);
                    fifo.setActionAmountSecondType(vagueService.convertBanBci(MAX_TOUR_BUYER_BAN));
                    fifo.setAmount(MAX_TOUR_BUYER_BAN);
                    fifoRepository.save(fifo);
                    tourService.addTour(fifo);
                    Fifo fifo1 = new Fifo();
                    fifo1.setKsu(fifo.getKsu());
                    fifo1.setDefaultAmountSecondType(vagueService.convertBanBci(Math.abs(difference)));
                    fifo1.setDefaultAmountFirstType(Math.abs(difference));
                    fifo1.setActionAmountFirstType(Math.abs(difference));
                    fifo1.setActionAmountSecondType(vagueService.convertBanBci(Math.abs(difference)));
                    fifo1.setKsuType(fifo.getKsuType());
                    fifo1.setDetailAgr(fifo.getDetailAgr());
                    fifo1.setKsu(fifo.getKsu());
                    fifo1.setVague(fifo.getVague());
                    fifo1.setPanierTour(true);
                    fifo1.setAmount(Math.abs(difference));
                    fifoRepository.save(fifo1);
                    basketService.addOrRemoveBasket(fifo1, true);
                }
            }
        }
    }


    @Override
    public Tour getAll(String name){
       return tourService.getTourv1(name);
    }
    @Override
    public void preProcessing() throws Exception {
        Tour tour = tourService.getTour("Tour");

         Tour basket = tourService.getTour("Basket");

        Optional<Fifo> firstBuyer = tour.getBuyers().stream().findFirst();
        Long checkIfGIESeller = Stream.of(tour,basket).mapToLong(t-> t.getSellers().size()).sum();
        Fifo seller = Stream.of(tour, basket).flatMap(t -> t.getSellers().stream())
                .filter(x -> !x.isTreated())
                .findFirst().orElse(null);
        Fifo buyer = firstBuyer.orElse(null);
        if(buyer == null) return;

        if (seller != null) {
            if (buyer.getKsu() == seller.getKsu()) return;

            if(checkIfGIESeller>=2 && seller.getKsu().getId() == 2L){
                seller.setTreated(true);
                tourService.removeTour(seller);
            }
            if (seller.getKsu().getId() == 2L) {
                tourService.removeTour(seller);
                seller.setTreated(true);
            }
            amount(seller, buyer);
        } else {
            log.info("Seller not found.");
            Settings settings = settingsService.getByCode("PARAM_CHECK_IF_GIE_SELLER");
            if (Integer.parseInt(settings.getValue()) == 1) {
                Fifo GIE = new Fifo();

                ReferencToken tokenGIE = referenceTokenRepository.getByIdDetailAgrGIE();
                System.out.println("===================================REFERENCE TOKEN==============================");
                System.out.println(tokenGIE.getRefTokenFront());
                System.out.println("===================================REFERENCE TOKEN==============================");
                Avr avr = avrRepository.findById(tokenGIE.getAvr().getId()).orElse(null);
                GIE.setReferencToken(tokenGIE);
                GIE.setNumOrdre(fifoService.setNextNumOrdreVente());
                GIE.setAvr(avr);
                GIE.setKsu(ksuRepository.findKsuById(2L));
                GIE.setDetailAgr(6L);
                GIE.setDefaultAmountSecondType(avr.getPrixUnitaire());
                GIE.setActionAmountSecondType(avr.getPrixUnitaire());
                GIE.setActionAmountFirstType(vagueService.convertBciBan(avr.getPrixUnitaire()));
                GIE.setDefaultAmountFirstType(vagueService.convertBciBan(avr.getPrixUnitaire()));
                GIE.setKsuType(KsuType.SELLER);
                GIE.setVague(vagueService.getActiveVagueInfo());
                fifoRepository.save(GIE);

                amountGIE(GIE, buyer);
            }

        }
//         ensure loop is broken
//        if (tour.getBuyers().size() > 0) {
//            preProcessing();
//        }
    }


    // We work with the BCI : ActionAmountSecondType
    private void amount(Fifo seller, Fifo buyer) throws Exception {
        // We work with BCI
        double amountSeller = seller.getActionAmountSecondType();
        double amountBuyer = buyer.getActionAmountSecondType();
        double difference = Math.abs(amountSeller - amountBuyer);
        System.out.println("==============================DIFFERENCE=======================");
        System.out.println(difference);
        System.out.println("==============================DIFFERENCE=======================");
        if (difference == 0) {
            handleEqualAmounts(seller, buyer);
        } else if (amountSeller > amountBuyer) {
            handleSellerAmountGreater(seller, buyer, difference);
        } else {
            handleBuyerAmountGreater(buyer, seller, difference);
        }
    }

    private void amountGIESimple(Fifo GieSeller,Fifo buyer) throws Exception {

        // Paiement à GIE
        Long venteDetailAgr = AccountConstant.TeEsmcGieFranchiseZeroOBPS;
        double amount = buyer.getActionAmountSecondType();
        detailSMEchangeService.mutationPourAchatFifoAdmin(venteDetailAgr, venteDetailAgr, amount);


        //Historique
        Long buyerTE = terminalEchangeService.getTeByDetailAgr(buyer.getDetailAgr()).getId();
        HistoryGieAchat historyGieAchat = new HistoryGieAchat();
        historyGieAchat.setBciValue(amount);
        historyGieAchat.setIdTe(buyerTE);
        historyGieAchatRepository.save(historyGieAchat);



        setNegativeKsuAmount(buyer);
        setHistoryFifo(buyer);
        setProcessingDateType(buyer);
        setProcessingDateType(GieSeller);

        tourService.removeTour(buyer);

        // Creation de produit à l'acheteur
        Long idRef = GieSeller.getReferencToken().getId();
        Double amountBuyerProduct = Math.abs(buyer.getDefaultAmountSecondType());
        Long idKSUFils = buyer.getKsu().getId();
        Long idDetailAgr = buyer.getDetailAgr();

        tokenService.segmentationTokenDadSonVendeurSupAcheteur(idRef, idKSUFils, amountBuyerProduct, idDetailAgr);

//        Set de Position
        fifoService.setNumOrder(buyer.getVague(), KsuType.BUYER);

        GieSeller.setAmount(-1*buyer.getActionAmountSecondType());
        GieSeller.setDescription("fusion partial GIE");
        GieSeller.setNumOrdre(-1);
        GieSeller.setTreated(true);
        fifoRepository.save(GieSeller);
        tourService.removeTour(GieSeller);

    }

    private void amountGIE(Fifo Gie,Fifo buyer) throws Exception {
//        Fifo seller = new Fifo(new Ksu("GIE"), KsuType.SELLER, 6L);
        amountGIESimple(Gie,buyer);
    }


    public void handleEqualAmounts(Fifo seller, Fifo buyer) throws JsonProcessingException {
        Stream.of(seller, buyer).forEach(f -> {
            setNegativeKsuAmount(f);
            try {
                setHistoryFifo(f);
                if (f.getKsuType() == KsuType.BUYER) {
//                    Reception du produit
                    Long idRef = seller.getReferencToken().getId();
                    Double amount = f.getDefaultAmountSecondType();
                    Long idKSUFils = f.getKsu().getId();
                    Long idDetailAgr = f.getDetailAgr();
                    tokenService. segmentationTokenDadSonAcheteurSupVendeur(idRef, idKSUFils, amount, idDetailAgr);

                } else if (f.getKsuType() == KsuType.SELLER) {
                    // Envoi du BAn au vendeur
                    Double buyerAmountBan = buyer.getDefaultAmountFirstType();
                    Long sellerTE = terminalEchangeService.getTeByDetailAgr(seller.getDetailAgr()).getId();
                    detailSMEchangeService.mutationPourAchatFifo(AccountConstant.TeEsmcGieFranchiseZeroOBPS, sellerTE, buyerAmountBan);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

//        setNegativeAmountForFifo(seller, buyer);
        setProcessingDate(seller, buyer);
        // Set de Position
        fifoService.setNumOrder(seller.getVague(), KsuType.SELLER);
        fifoService.setNumOrder(buyer.getVague(), KsuType.BUYER);


        // Avr
        CategorieAvr categorieAvr = categoryAvrRepository.findById(4L).orElse(null);
        TypeAvr typeAvrAchat = typeAvrRepository.findById(2L).orElse(null);
        TypeAvr typeAvrVente = typeAvrRepository.findById(1L).orElse(null);

        Avr avrVente = new Avr();
        avrVente.setCategorieAvr(categorieAvr);
        avrVente.setTypeAvr(typeAvrVente);
        avrVente.setEtat(true);
        avrVente.setPrixUnitaire(seller.getDefaultAmountSecondType());
        avrVente.setLibelle(seller.getDescription());
//        avrVente.setDetailAgr(idDetailAgrVente);
        Avr avrVenteSaved = avrRepository.save(avrVente);

        Avr avrAchat = new Avr();
        avrAchat.setCategorieAvr(categorieAvr);
        avrAchat.setTypeAvr(typeAvrAchat);
        avrAchat.setEtat(true);
        avrAchat.setPrixUnitaire(buyer.getDefaultAmountSecondType());
        avrAchat.setLibelle(buyer.getDescription());
//        avrAchat.setDetailAgr(idDetailAgrAchat);
        Avr avrAchatSaved = avrRepository.save(avrVente);

        // Set BON
        Extrant ex = bonService.creationBon("BC", seller, 1, buyer, seller.getDefaultAmountSecondType(), buyer.getDefaultAmountSecondType(), avrAchatSaved, null);
        Extrant ex1 = bonService.creationBon("BL", seller, 1, seller, buyer.getDefaultAmountSecondType(), seller.getDefaultAmountSecondType(), avrVenteSaved, ex.getId());

        Extrant ex2 = bonService.creationBon("BRBL", seller, 1, buyer, seller.getDefaultAmountSecondType(), buyer.getDefaultAmountSecondType(), avrAchatSaved, ex1.getId());
        Extrant ex3 = bonService.creationBon("FD", seller, 1, seller, buyer.getDefaultAmountSecondType(), seller.getDefaultAmountSecondType(), avrVenteSaved, ex2.getId());

        Extrant ex4 = bonService.creationBon("BE", seller, 1, buyer, seller.getDefaultAmountSecondType(), buyer.getDefaultAmountSecondType(), avrAchatSaved, ex1.getId());
        Extrant ex5 = bonService.creationBon("BRBE", seller, 1, seller, buyer.getDefaultAmountSecondType(), seller.getDefaultAmountSecondType(), avrVenteSaved, ex2.getId());
    }

    private void handleSellerAmountGreater(Fifo seller, Fifo buyer, double difference) throws Exception {
        setPartialFusion(seller, difference);
        setNegativeKsuAmount(buyer);
        setHistoryFifo(buyer);
        manageTour(seller);
//        setProcessingDate(seller, buyer);
        setProcessingDateType(buyer);


        // Vendeur > Acheteur
        Double buyerAmountBan = Math.abs(buyer.getDefaultAmountFirstType());
        Long selletTE = terminalEchangeService.getTeByDetailAgr(seller.getDetailAgr()).getId();
        System.out.println("======================================== SELLER============================");
        System.out.println("seller te : "+selletTE);
        System.out.println("======================================== SELLER============================");


        System.out.println("======================================== AMOUNT Ban ============================");
        System.out.println("amount : "+buyerAmountBan);
        System.out.println("======================================== AMOUNT Ban============================");



        detailSMEchangeService.mutationPourAchatFifo(AccountConstant.TeEsmcGieFranchiseZeroOBPS, selletTE, buyerAmountBan);
        // Buyer reçoit les produits
        Long idRef = seller.getReferencToken().getId();
        Double amount = -1 * buyer.getDefaultAmountSecondType();
        Long idKSUFils = buyer.getKsu().getId();
        Long idDetailAgr = buyer.getDetailAgr();

        tokenService.segmentationTokenDadSonVendeurSupAcheteur(idRef, idKSUFils, amount, idDetailAgr);


        // Set de Position
        fifoService.setNumOrder(buyer.getVague(), KsuType.BUYER);

        // Avr
        CategorieAvr categorieAvr = categoryAvrRepository.findById(4L).orElse(null);
        TypeAvr typeAvrAchat = typeAvrRepository.findById(2L).orElse(null);
        TypeAvr typeAvrVente = typeAvrRepository.findById(1L).orElse(null);

        Avr avrVente = new Avr();
        avrVente.setCategorieAvr(categorieAvr);
        avrVente.setTypeAvr(typeAvrVente);
        avrVente.setEtat(true);
        avrVente.setPrixUnitaire(seller.getDefaultAmountSecondType());
        avrVente.setLibelle(seller.getDescription());
//        avrVente.setDetailAgr(idDetailAgrVente);
        Avr avrVenteSaved = avrRepository.save(avrVente);

        Avr avrAchat = new Avr();
        avrAchat.setCategorieAvr(categorieAvr);
        avrAchat.setTypeAvr(typeAvrAchat);
        avrAchat.setEtat(true);
        avrAchat.setPrixUnitaire(buyer.getDefaultAmountSecondType());
        avrAchat.setLibelle(buyer.getDescription());
//        avrAchat.setDetailAgr(idDetailAgrAchat);
        Avr avrAchatSaved = avrRepository.save(avrVente);

        // Set BON
        Extrant ex = bonService.creationBon("BC", seller, 1, buyer, seller.getDefaultAmountSecondType(), buyer.getDefaultAmountSecondType(), avrAchatSaved, null);
        Extrant ex1 = bonService.creationBon("BL", seller, 1, seller, buyer.getDefaultAmountSecondType(), seller.getDefaultAmountSecondType(), avrVenteSaved, ex.getId());

        Extrant ex2 = bonService.creationBon("BRBL", seller, 1, buyer, seller.getDefaultAmountSecondType(), buyer.getDefaultAmountSecondType(), avrAchatSaved, ex1.getId());
        Extrant ex3 = bonService.creationBon("FD", seller, 1, seller, buyer.getDefaultAmountSecondType(), seller.getDefaultAmountSecondType(), avrVenteSaved, ex2.getId());

        Extrant ex4 = bonService.creationBon("BE", seller, 1, buyer, seller.getDefaultAmountSecondType(), buyer.getDefaultAmountSecondType(), avrAchatSaved, ex1.getId());
        Extrant ex5 = bonService.creationBon("BRBE", seller, 1, seller, buyer.getDefaultAmountSecondType(), seller.getDefaultAmountSecondType(), avrVenteSaved, ex2.getId());



    }

    //    Buyer > 0
    private void handleBuyerAmountGreater(Fifo buyer, Fifo seller, double difference) throws Exception {

        setPartialFusion(buyer, difference);
        setNegativeKsuAmount(seller);
        setHistoryFifo(seller);
        manageTour(buyer);

        setProcessingDateType(seller);

        // Reception du BCi au Vendeur
        Double buyerAmountBan = Math.abs(seller.getDefaultAmountFirstType());
        Long buyerTE = terminalEchangeService.getTeByDetailAgr(seller.getDetailAgr()).getId();
        System.out.println("================================TE===================================");
        System.out.println(buyerTE);
        System.out.println("===================================================================");

        detailSMEchangeService.mutationPourAchatFifo(AccountConstant.TeEsmcGieFranchiseZeroOBPS, buyerTE, buyerAmountBan);

        // Acheteur > Vendeur
        //Reception du produit
        Long idRef = seller.getReferencToken().getId();
        Double amount = seller.getDefaultAmountSecondType();
        Long idKSUFils = buyer.getKsu().getId();
        Long idDetailAgr = buyer.getDetailAgr();
        tokenService.segmentationTokenDadSonAcheteurSupVendeur(idRef, idKSUFils, amount, idDetailAgr);



        // Set de Position
        fifoService.setNumOrder(seller.getVague(), KsuType.SELLER);


        // Avr
        CategorieAvr categorieAvr = categoryAvrRepository.findById(4L).orElse(null);
        TypeAvr typeAvrAchat = typeAvrRepository.findById(2L).orElse(null);
        TypeAvr typeAvrVente = typeAvrRepository.findById(1L).orElse(null);

        Avr avrVente = new Avr();
        avrVente.setCategorieAvr(categorieAvr);
        avrVente.setTypeAvr(typeAvrVente);
        avrVente.setEtat(true);
        avrVente.setPrixUnitaire(seller.getDefaultAmountSecondType());
        avrVente.setLibelle(seller.getDescription());
//        avrVente.setDetailAgr(idDetailAgrVente);
        Avr avrVenteSaved = avrRepository.save(avrVente);

        Avr avrAchat = new Avr();
        avrAchat.setCategorieAvr(categorieAvr);
        avrAchat.setTypeAvr(typeAvrAchat);
        avrAchat.setEtat(true);
        avrAchat.setPrixUnitaire(buyer.getDefaultAmountSecondType());
        avrAchat.setLibelle(buyer.getDescription());
//        avrAchat.setDetailAgr(idDetailAgrAchat);
        Avr avrAchatSaved = avrRepository.save(avrVente);

        // Set BON
        Extrant ex = bonService.creationBon("BC", seller, 1, buyer, seller.getDefaultAmountSecondType(), buyer.getDefaultAmountSecondType(), avrAchatSaved, null);
        Extrant ex1 = bonService.creationBon("BL", seller, 1, seller, buyer.getDefaultAmountSecondType(), seller.getDefaultAmountSecondType(), avrVenteSaved, ex.getId());
//
        Extrant ex2 = bonService.creationBon("BRBL", seller, 1, buyer, seller.getDefaultAmountSecondType(), buyer.getDefaultAmountSecondType(), avrAchatSaved, ex1.getId());
        Extrant ex3 = bonService.creationBon("FD", seller, 1, seller, buyer.getDefaultAmountSecondType(), seller.getDefaultAmountSecondType(), avrVenteSaved, ex2.getId());

        Extrant ex4 = bonService.creationBon("BE", seller, 1, buyer, seller.getDefaultAmountSecondType(), buyer.getDefaultAmountSecondType(), avrAchatSaved, ex1.getId());
        Extrant ex5 = bonService.creationBon("BRBE", seller, 1, seller, buyer.getDefaultAmountSecondType(), seller.getDefaultAmountSecondType(), avrVenteSaved, ex2.getId());

        // Paiement


    }

    public Ksu getCurrentKsu(Fifo fifo) {
        return ksuRepository.findFirstByCodeKsu(fifo.getKsu().getCodeKsu()).get();
    }

    public Optional<Fifo> getCurrentFifo(Fifo fifo) {
        return fifoRepository.findFifoByFifo(fifo.getId());
    }

    private void setProcessingDate(Fifo seller, Fifo buyer) {
        Stream.of(seller, buyer).filter(p -> p.getProcessingDate() == null).forEach(f -> {
            f.setProcessingDate(new Date());
            fifoRepository.save(f);
        });
    }

    private void setProcessingDateType(Fifo fifo) {
        Stream.of(fifo).filter(p -> p.getProcessingDate() == null).forEach(f -> {
            f.setProcessingDate(new Date());
            fifoRepository.save(f);
        });
    }

    public void setNegativeKsuAmount(Fifo fifo) {
        fifo.setNegativeAmount(-1 * fifo.getActionAmountSecondType());
        fifo.setDefaultAmountFirstType(-1* fifo.getDefaultAmountFirstType());

        fifo.setActionAmountFirstType(-1*fifo.getActionAmountFirstType());
        fifo.setDefaultAmountSecondType(-1*fifo.getDefaultAmountSecondType());
        fifo.setDescription("fusion complete");
        fifo.setTreated(true);
        fifoRepository.save(fifo);

    }


    public void setHistoryFifo(Fifo fifo) throws Exception {
        HistoryFiFo historyFiFoBuyer = new HistoryFiFo();
        double amount = fifo.getActionAmountSecondType();
        historyFiFoBuyer.setAmount(-1 * amount);

        fifo.setTreated(true);
        fifo.setNumOrdre(-1);
        fifoRepository.save(fifo);
        tourService.removeTour(fifo);

        historyFiFoBuyer.setKsu(fifo.getKsu());
        historyFiFoBuyer.setDetailAgr(fifo.getDetailAgr());
        historyFiFoBuyer.setAvr(fifo.getAvr());
        historyFiFoBuyer.setActionAmountFirstType(fifo.getActionAmountFirstType());
        historyFiFoBuyer.setActionAmountSecondType(fifo.getActionAmountSecondType());
        historyFiFoBuyer.setDetailAgr(fifo.getDetailAgr());
        historyFiFoBuyer.setDefaultAmountFirstType(fifo.getDefaultAmountFirstType());
        historyFiFoBuyer.setDefaultAmountSecondType(fifo.getDefaultAmountSecondType());
        historyFiFoBuyer.setProductRegistryValue(fifo.getProductRegistryValue());
        hFiFoRepository.save(historyFiFoBuyer);
    }

    public void setNegativeAmountForFifo(Fifo seller, Fifo buyer) {
        Stream.of(getCurrentFifo(seller), getCurrentFifo(buyer))
                .filter(k -> k.get().getNegativeAmount() == 0.0).
                forEach(k -> k.get()
                        .setNegativeAmount(-1 * (k.get().getKsu() == getCurrentKsu(seller) ? seller.getActionAmountSecondType() : buyer.getActionAmountSecondType()))

                );

    }

    public void setPartialFusion(Fifo fifo, double difference) {
        System.out.println("===========================PARTIAL FUSION========================");
        System.out.println("Montant restant : "+difference);
        System.out.println("===========================PARTIAL FUSION========================");
        fifo.setActionAmountSecondType(difference);
        fifo.setDefaultAmountSecondType(difference);
        // Now we will convert BCI to Ban and set it
        fifo.setActionAmountFirstType(vagueService.convertBciBan(difference));
        fifo.setDefaultAmountFirstType(vagueService.convertBciBan(difference));
        fifo.setDescription("partial fusion");
        fifoRepository.save(fifo);
    }

    // I want to check date
//    @Override
//    public void setBasketDate() {
//        Tour basket = tourService.getTour("Basket");
//        Stream.of(basket).flatMap(t -> Stream.concat(t.getSellers().stream(), t.getBuyers().stream())).filter(f -> f.getProcessingDate() == null).forEach(this::accept);
//
//
//    }
    @Override
    public void setBasketDate() {
        Tour basket = tourService.getTour("Basket");
        List<Fifo> fifos = Stream.concat(basket.getSellers().stream(), basket.getBuyers().stream()).filter(f -> f.getProcessingDate() == null).toList();

        Iterator<Fifo> iterator = fifos.iterator();
        while (iterator.hasNext()) {
            Fifo f = iterator.next();
            accept(f);
        }
    }



    private void acceptv2(Fifo f) {
        Date date = new Date();
        int parameter_date = Integer.parseInt(settingsService.getByCode("PARAM_DATE_FIFO").getValue());

        List<Fifo> filteredFifos = fifoRepository.getAllTreated();
        List<Fifo> filteredFifosNTreat = fifoRepository.getAllNoTreatedInBasket();

        for (Fifo fifo : filteredFifos) {
            for (Fifo fifo1 : filteredFifosNTreat) {
                if (fifo.getKsu() == fifo1.getKsu()) {
                    if ((date.getDate() - fifo.getProcessingDate().getDate()) > parameter_date) {
                        if(fifo1.getKsuType() == KsuType.SELLER) {
                            fifo1.setNumOrdre(fifoService.setNextNumOrdreVente());
                            fifo1.setDetailAgr(fifo.getDetailAgr());
                            fifoRepository.save(fifo1);
                        } else if(fifo1.getKsuType() == KsuType.BUYER){
                            fifo1.setNumOrdre(fifoService.setNextNumOrdreAchat());
                            fifoRepository.save(fifo1);
                        }
                        try {
                            basketService.addOrRemoveBasket(fifo1, false);

                            manageTour(fifo1);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }
    }

    private void accept(Fifo f) {
        Tour tour = tourService.getTour("Tour");
        int parameterDate = Integer.parseInt(settingsService.getByCode("PARAM_DATE_FIFO").getValue());
        System.out.println(parameterDate);
//        int counter =
        Fifo fifoTreated = fifoRepository.getAllFifoTreated(f.getKsuType(), f.getKsu().getId(), PageRequest.of(0,1));

        int buyersTour = tour.getBuyers().size();
        int sellersTour = tour.getSellers().size();

        if (buyersTour == 0 || sellersTour == 0) {
            parameterDate = 0;
        }

        int finalParameterDate = parameterDate;
        fifoTreated = Stream.of(fifoTreated)
                .filter(t -> t != null && dateTimeComparaison(t) >= finalParameterDate)
                .reduce((first, second) -> second).orElse(null);

        if (fifoTreated == null) {
            return;
        }

        if (f.getKsuType() == KsuType.BUYER && buyersTour == 0) {
            f.setPanierTour(false);
            f.setNumOrdre(fifoService.setNextNumOrdre(f.getKsuType()));
        } else if (f.getKsuType() == KsuType.SELLER && sellersTour == 0) {
            f.setNumOrdre(fifoService.setNextNumOrdre(f.getKsuType()));
            f.setPanierTour(false);
        } else {
            if (f.getKsuType() == KsuType.SELLER) {
                f.setNumOrdre(fifoService.setNextNumOrdre(f.getKsuType()));
                f.setPanierTour(false);
            } else if (f.getKsuType() == KsuType.BUYER) {
                f.setNumOrdre(fifoService.setNextNumOrdreAchat());
            }
        }

        fifoRepository.save(f);
        try {
            basketService.addOrRemoveBasket(f, false);
            manageTour(f);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    public int dateTimeComparaison(Fifo fifo) {
        LocalDateTime currentDate = LocalDateTime.now();
        LocalDateTime processingDate = LocalDateTime.parse(fifo.getProcessingDate().toString().substring(0,19), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        Duration duration = Duration.between(processingDate, currentDate);
        return (int) duration.toHours();
    }

}
