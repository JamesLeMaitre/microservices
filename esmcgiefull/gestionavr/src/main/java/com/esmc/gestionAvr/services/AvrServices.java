package com.esmc.gestionAvr.services;

import com.esmc.gestionAvr.InterimFeign.AgrInterimClient;
import com.esmc.gestionAvr.constant.AvrRequest;
import com.esmc.gestionAvr.controllers.AutomateControllerV2;
import com.esmc.gestionAvr.entities.*;
import com.esmc.gestionAvr.feign.DetailAgrClient;
import com.esmc.gestionAvr.feign.KsuClient;
import com.esmc.gestionAvr.feign.TeClient;
import com.esmc.gestionAvr.repositories.*;
import com.esmc.gestionAvr.servicesInterfaces.AvrServiceInterface;
import com.esmc.gestionAvr.servicesInterfaces.PanierFifoInterface;
import com.esmc.gestionAvr.servicesInterfaces.UploadFileInterface;
import com.esmc.gestionAvr.servicesInterfaces.VagueServiceInterface;
import com.esmc.gestionAvr.tokens.entities.ReferencToken;
import com.esmc.gestionAvr.tokens.model.TokenEntity;

import com.esmc.gestionAvr.tokens.repositories.ReferenceTokenRepository;
import com.esmc.gestionAvr.tokens.service.TokenServiceImpl;
import com.esmc.gestionAvr.tokens.services.ReferencTokenInterface;
import com.esmc.gestionAvr.utils.enums.KsuType;
import com.esmc.models.DetailsAgr;
import com.esmc.models.Ksu;
import com.esmc.models.TerminalEchange;
import constants.AccountConstant;
import constants.SupportMarchandConstant;
import constants.javaConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Transactional
public class AvrServices implements AvrServiceInterface {
    @Autowired
    private ReferenceTokenRepository referenceTokenRepository;
    @Autowired
    private KsuRepository ksuRepository;

    @Autowired
    private final AvrRepository avrRepository;

    @Autowired
    private final UploadFileInterface uploadFileInterface;

    @Autowired
    private final CategorieAvrRepository categorieAvrRepository;
    @Autowired
    private final TypeAvrRepository typeAvrRepository;

    @Autowired
    private IntrantRepository intrantRepository;

    @Autowired
    private ExtrantRepository extrantRepository;

    @Autowired
    private TypeSmAvrService typeSmAvrService;

    @Autowired
    private AffectationSMAvrRepository affectationSMAvrRepository;

    @Autowired
    private ProductRegistryValueRepository productRegistryValueRepository;

    @Autowired
    private KsuClient ksuClient;

    @Autowired
    private DetailAgrClient detailAgrClient;

    @Autowired
    private TeClient teClient;

    @Autowired
    private FifoService fifoService;

    @Autowired
    private PanierFifoInterface panierFifoInterface;

    @Autowired
    private PanierFifoRepository panierFifoRepository;

    @Autowired
    private VagueServiceInterface vagueServiceInterface;

    @Autowired
    private VagueRepo vagueRepository;

    @Autowired
    AgrInterimClient agrInterimClient;

    @Autowired
    private DetailSMAvrRepository detailSMAvrRepository;

    @Autowired
    private SmAvrRepository smAvrRepository;

    @Autowired
    AutomateControllerV2 automateControllerV2;

    @Autowired
    private TourService tourService;

    @Autowired
    private TokenServiceImpl tokenService;



    @Autowired
    private ReferencTokenInterface referencTokenInterface;

    @Autowired
    private ManageTourService manageTourService;

    @Autowired
    private FifoAdminService fifoAdminService;


    public AvrServices(AvrRepository avrRepository, UploadFileInterface uploadFileInterface,
                       CategorieAvrRepository categorieAvrRepository, TypeAvrRepository typeAvrRepository) {
        this.avrRepository = avrRepository;
        this.uploadFileInterface = uploadFileInterface;
        this.categorieAvrRepository = categorieAvrRepository;
        this.typeAvrRepository = typeAvrRepository;

    }

    /**
     * @param a1
     * @return
     */
    @Override
    public Avr addAvrs(AvrRequest a1, Long id) throws Exception {

        Fifo f = new Fifo();
        CategorieAvr categorieAvr = categorieAvrRepository.findById(a1.getIdCategoryAvr()).orElseThrow(() -> new Exception());
        TypeAvr typeAvr = typeAvrRepository.findById(a1.getIdTypeAvr()).orElseThrow(() -> new Exception());
        //   teClient.mutationFondDisponibleUsed(id, a1.getPrixUnitaire());
        Avr a = new Avr();
        BeanUtils.copyProperties(a1, a);
        a.setTypeAvr(typeAvr);
        a.setCategorieAvr(categorieAvr);
        a.setDetailAgr(id);
        f.setAvr(a);
        /*f.setNumOrdre(fifoService.setNextNumOrdre());*/
        f.setDetailAgr(id);
        fifoService.addFifo(f, id);

        return avrRepository.save(a);

    }

    @Override
    public Avr addAvr(AvrRequest a1, Long id) throws Exception {
        System.out.println(a1);
        CategorieAvr categorieAvr = categorieAvrRepository.findById(a1.getIdCategoryAvr()).orElseThrow(Exception::new);
        TypeAvr typeAvr = typeAvrRepository.findById(a1.getIdTypeAvr()).orElseThrow(Exception::new);
        System.out.println(typeAvr);
        Avr a = new Avr();
        BeanUtils.copyProperties(a1, a);
        a.setEtat(true);
        a.setCategorieAvr(categorieAvr);
        a.setTypeAvr(typeAvr);
        a.setDetailAgr(id);
        Avr avrPersist = avrRepository.save(a);
        //Upload Media File
        if (a.getTypeAvr().getLibelle().toLowerCase().contains("fifo")) {
            Fifo f = new Fifo();
            f.setAvr(a);
            fifoService.addFifo(f, id);

        } else {
            /*uploadFileInterface.save(a1.getPhoto(), avrPersist.getId());*/
        }
        return a;
    }


    @Override
    public Avr updateAvr(Avr a) {
        return avrRepository.save(a);
    }

    @Override
    public void deleteAvr(Long id) throws Exception {
        Avr avr = avrRepository.findById(id).orElseThrow(Exception::new);
        /*uploadFileInterface.deleteExitFile(avr.getPhoto());*/
        avrRepository.delete(avr);
    }

    @Override
    public List<Avr> listAvr() {
        return avrRepository.findAll();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Avr getAvrById(Long id) {
        return avrRepository.findById(id).orElse(null);
    }

    @Override
    public List<Avr> listAvrByDetailAgrId(Long id) {
        return avrRepository.listAvrByDetailAgrId(id);
    }

    @Override
    public List<Avr> listAvrByDetailAgrIdAndType(Long id, Long type) {
        return avrRepository.listAvrByDetailAgrIdAndType(id, type);
    }

    @Override
    public List<Avr> listBesoinsAvr(String libelle, Long type, Long categorie) {
        return avrRepository.listBesoinsAvr(libelle, type, categorie);
    }

    @Override
    public List<Avr> listAvrByType(Long id) {
        return avrRepository.listAvrByType(id);
    }

    /**
     * @param id1
     * @param id2
     * @return
     */
    @Override
    public List<Avr> listAvrByCategorieAvrIdAndType(Long id1, Long id2, Long id3) {
        return avrRepository.listAvrByCategorieAvrIdAndType(id1, id2, id3);
    }

    @Override
    public Avr getAvrByKsu(Long id) {
        return avrRepository.findById(ksuClient.getById(id));
    }

    /**
     * @param id
     * @return
     */
    @Override
    public List<Avr> listAvrByCategorie(Long id) {
        return avrRepository.listAvrByCategorie(id);
    }

    /**
     * @param id Fonction pour recuperer le vendeur d'un produit
     */

    @Override
    public Ksu getVendeur(Long id) {

        Ksu k = null;

        Avr a = avrRepository.findById(id).orElse(null);

        if (a != null) {
            DetailsAgr d = detailAgrClient.getDetailById(a.getDetailAgr());

            if (d != null) {
                System.out.println("idAgr : " + d.getId());
                k = ksuClient.getById(d.getKsu());
            }
        }
        return k;

    }

    @Override
    public Avr saveAvr(Avr avr) {
        return null;
    }


    /***************************************************************************************************************
     *
     *
     *              PRINCE PATRICE dkp 21/07/2022
     *
     *
     * **/

    @Override
    public Double amountBciAvailable(Long idKsu) {
        Double amount = this.productRegistryValueRepository.amountBciAvailable(idKsu);
        return amount;
    }

    public Avr addAvrV2s(AvrRequest a1, Long id) throws Exception {

        CategorieAvr categorieAvr = categorieAvrRepository.findById(a1.getIdCategoryAvr()).orElse(null);
        TypeAvr typeAvr = typeAvrRepository.findById(a1.getIdTypeAvr()).orElse(null);
        Vague vague = vagueRepository.findById(a1.getIdVague()).orElse(null);
        if (vague == null) return null;
        boolean isSell = typeAvr.getLibelle().toLowerCase().contains("vente");

        System.out.println("=============IS SELL==============");
        System.out.println(isSell);


        Double disponibilite = 0.0;
        Double amount = a1.getPrixUnitaire();
        TerminalEchange te = teClient.getTeByDetailAgr(id);
        Long idTe = te.getId();
        DetailsAgr d = detailAgrClient.getDetailById(id);
        Long idKsu = d.getKsu();

        ProductRegistryValue productRegistryValue1 = null;

//       System.out.println("=========AMOUNT=========");
//       System.out.println(amount);
        if (isSell) {
            disponibilite = amountBciAvailable(idKsu);
//           System.out.println("=============================================================================================================");
//           System.out.println(disponibilite);
//           System.out.println(amount);

            if (disponibilite != null && disponibilite < amount) {
                return null;
            }


            //fusionner les productregistry value pour atteindre la valeur actuelle

            List<ProductRegistryValue> registryValueListAvailable = productRegistryValueRepository.productRegistryValueList(idKsu);
//           System.out.println("=========REGISTRY VALUE===============");
//           System.out.println(registryValueListAvailable);
//           System.out.println("=======================");

            Double montantToBeUsed = amount;
            for (ProductRegistryValue p : registryValueListAvailable) {
                if (p.getValue() <= montantToBeUsed) {
                    Double value = p.getValue();
                    p.setStatus(false);
                    p.setDescription("fusion");
                    p.setValue(-1 * value);
                    productRegistryValueRepository.save(p);
                    montantToBeUsed -= value;
                } else {

                    double rest = p.getValue() - montantToBeUsed;
                    p.setStatus(false);
                    p.setDescription("partial fusion");
                    p.setValue(-1 * montantToBeUsed);
                    productRegistryValueRepository.save(p);

                    ProductRegistryValue productRegistryValueCumule = new ProductRegistryValue();
                    productRegistryValueCumule.setValue(rest);
                    ProductRegistryValue productRegistryValueCumuleV1 = productRegistryValueRepository.save(productRegistryValueCumule);
                    productRegistryValueCumuleV1.setIdentifant("0000" + productRegistryValueCumuleV1.getId());
                    productRegistryValueCumuleV1.setIdKsu(idKsu);
                    productRegistryValueRepository.save(productRegistryValueCumuleV1);
                }
                if (montantToBeUsed == 0) {
                    break;
                }
            }

            ProductRegistryValue productRegistryValue = new ProductRegistryValue();
            productRegistryValue.setValue(a1.getPrixUnitaire());
            ProductRegistryValue productRegistryValueV1 = productRegistryValueRepository.save(productRegistryValue);
            productRegistryValueV1.setIdentifant("0000" + productRegistryValueV1.getId());
            productRegistryValueV1.setIdKsu(idKsu);
            productRegistryValue1 = productRegistryValueRepository.save(productRegistryValueV1);
        } else {
            disponibilite = teClient.checkSmTotalCurrent(SupportMarchandConstant.QsupportMarchandBan, te.getId());
            System.out.println(disponibilite);
            //System.out.println(disponibilite);
            if (disponibilite < amount) {
                return null;
            }
        }

        Avr a = new Avr();
        BeanUtils.copyProperties(a1, a);
        a.setTypeAvr(typeAvr);
        a.setPrixUnitaire(a1.getPrixUnitaire());
        Avr avr1 = avrRepository.save(a);
        Fifo fifo1 = new Fifo();


        if (a.getTypeAvr().getLibelle().toLowerCase().contains("fifo")) {
            Double firstAmount = 0.0;
            Double secondAcount = 0.0;

            // boolean isSell = a.getTypeAvr().getLibelle().toLowerCase().contains("vente");
            if (isSell) {
                secondAcount = a.getPrixUnitaire();
                firstAmount = Double.valueOf(vagueServiceInterface.convertBciBan(secondAcount));

                System.out.println("SECOND AMOUNT FISRT AMOUT");
                System.out.println(secondAcount + "/" + firstAmount);
            } else {
                firstAmount = a.getPrixUnitaire();
                secondAcount = Double.valueOf(vagueServiceInterface.convertBanBci(firstAmount));
            }

            //Verificqtion de l'operation a effectue
            ///si le avr a un fifopanier complete le montant
            PanierFifo panierFifo = panierFifoInterface.getFirstOrAvrByType(!isSell, a.getId());

            if (panierFifo != null) {
                //Ajout du montant au panier existant
                panierFifo.setDefaultAmountFirstType(panierFifo.getDefaultAmountFirstType() + firstAmount);
                panierFifo.setActionAmountFirstType(panierFifo.getActionAmountFirstType() + firstAmount);
                panierFifo.setDefaultAmountSecondType(panierFifo.getDefaultAmountSecondType() + secondAcount);
                panierFifo.setActionAmountSecondType(panierFifo.getActionAmountSecondType() + secondAcount);
                panierFifo.setDetailAgr(id);
                panierFifoRepository.save(panierFifo);
            } else {

                Fifo checkFifo = fifoService.getFirstOrAvrByType(!isSell, a.getId());
                if (checkFifo != null) {
                    PanierFifo panierFifo1 = new PanierFifo();
                    panierFifo1.setIsBuy(!isSell);
                    panierFifo1.setDefaultAmountFirstType(firstAmount);
                    panierFifo1.setAvr(avr1);
                    panierFifo1.setActionAmountFirstType(firstAmount);
                    panierFifo1.setDefaultAmountSecondType(secondAcount);
                    panierFifo1.setActionAmountSecondType(secondAcount);
                    panierFifo1.setDetailAgr(id);
                    panierFifoRepository.save(panierFifo1);

                } else {

                    //recuperation des valeurs maximales
                    Double valMaxFirst = 0.0;
                    Double valMaxSecond = 0.0;

                    if (isSell) {
                        valMaxSecond = javaConst.venteBCIMaxAmount;
                        valMaxFirst = Double.valueOf(vagueServiceInterface.convertBciBan(valMaxSecond));
                    } else {
                        valMaxFirst = javaConst.achatBAnMaxAmount;
                        valMaxSecond = Double.valueOf(vagueServiceInterface.convertBanBci(valMaxFirst));
                    }

                    Fifo f = new Fifo();
                    f.setIsBuy(!isSell);
                    f.setKsuType(KsuType.SELLER);
                    f.setVague(vague);


                    Double valBan = 0.0;
                    if (valMaxFirst < firstAmount) {

                        // creation des fifo avec les montant maximales

                        valBan = valMaxFirst;

                        f.setDefaultAmountFirstType(valMaxFirst);
                        f.setActionAmountFirstType(valMaxFirst);
                        f.setDefaultAmountSecondType(valMaxSecond);
                        f.setActionAmountSecondType(valMaxSecond);

                        //mise du reste dans un panier
                        PanierFifo panierFifo2 = new PanierFifo();
                        panierFifo2.setIsBuy(!isSell);
                        panierFifo2.setDefaultAmountFirstType(firstAmount - valMaxFirst);
                        panierFifo2.setAvr(avr1);
                        panierFifo2.setActionAmountFirstType(firstAmount - valMaxFirst);
                        panierFifo2.setDefaultAmountSecondType(secondAcount - valMaxSecond);
                        panierFifo2.setActionAmountSecondType(secondAcount - valMaxSecond);
                        panierFifo2.setDetailAgr(id);
                        panierFifoRepository.save(panierFifo2);

                        System.out.println("PANIER FIFO");
                        System.out.println(panierFifo2);

                    } else {
                        valBan = firstAmount;
                        f.setDefaultAmountFirstType(firstAmount);
                        f.setActionAmountFirstType(firstAmount);
                        f.setDefaultAmountSecondType(secondAcount);
                        f.setActionAmountSecondType(secondAcount);
                    }
//                    fifoService.addFifo(f, id);
                    f.setAvr(avr1);
                    fifo1 = fifoService.addFifoV2(f, id);


                    tourService.addTour(fifo1);

                    if (!isSell) {
                        teClient.depotAInterim(SupportMarchandConstant.QsupportMarchandBan, idTe, valBan);
                    }

                }
            }
        }
        //automateControllerV2.operateTransactionv3();

        TokenEntity tokenEntitie = new TokenEntity();
        tokenEntitie.setLibelle(a.getLibelle());
        tokenEntitie.setIdKsu(idKsu);
        tokenEntitie.setAvr(a);
        tokenEntitie.setValue(a.getPrixUnitaire());
        ReferencToken referencToken = referencTokenInterface.creatRef(tokenEntitie);
        fifo1.setReferencToken(referencToken);
        fifoService.addFifoV2(fifo1, id);

        return a;
    }

    public Avr addAvrV2sVente(AvrRequest a1, Long id) throws Exception {

        CategorieAvr categorieAvr = categorieAvrRepository.findById(a1.getIdCategoryAvr()).orElse(null);
        TypeAvr typeAvr = typeAvrRepository.findById(a1.getIdTypeAvr()).orElse(null);
        Vague vague = vagueRepository.findById(a1.getIdVague()).orElse(null);
        if (vague == null) return null;
        boolean isSell = typeAvr.getLibelle().toLowerCase().contains("vente");

        System.out.println("=============IS SELL==============");
        System.out.println(isSell);

        Double disponibilite = 0.0;
        Double amount = a1.getPrixUnitaire();
        TerminalEchange te = teClient.getTeByDetailAgr(id);
        Long idTe = te.getId();
        DetailsAgr d = detailAgrClient.getDetailById(id);
        Long idKsu = d.getKsu();

        ProductRegistryValue productRegistryValue1 = null;


//        if (isSell) {
//            disponibilite = amountBciAvailable(idKsu);
//            if (disponibilite != null && disponibilite < amount) {
//                return null;
//            }
//        } else {
//            disponibilite = teClient.checkSmTotalCurrent(SupportMarchandConstant.QsupportMarchandBan, te.getId());
//            System.out.println(disponibilite);
//            //System.out.println(disponibilite);
//            if (disponibilite < amount) {
//                return null;
//            }
//        }

        Avr a = new Avr();
//        BeanUtils.copyProperties(a1, a);
        a.setLibelle(a1.getLibelle());
        a.setCategorieAvr(categorieAvr);
        a.setTypeAvr(typeAvr);
        a.setPrixUnitaire(a1.getPrixUnitaire());
        a.setDetailAgr(id);
        Avr avr1 = avrRepository.save(a);

        Fifo fifo1 = new Fifo();

        if (a.getTypeAvr().getLibelle().toLowerCase().contains("fifo")) {
            Double firstAmount = 0.0;
            Double secondAcount = 0.0;

            if (isSell) {
                secondAcount = avr1.getPrixUnitaire();
                firstAmount = Double.valueOf(vagueServiceInterface.convertBciBan(secondAcount));

                System.out.println("BCi AMOUNT  - BAn AMOUT");
                System.out.println(secondAcount + "/" + firstAmount);

            } else {
                firstAmount = avr1.getPrixUnitaire();
                secondAcount = Double.valueOf(vagueServiceInterface.convertBanBci(firstAmount));
            }

            PanierFifo panierFifo = panierFifoInterface.getFirstOrAvrByType(!isSell, a.getId());

            if (panierFifo != null) {
                //Ajout du montant au panier existant
                panierFifo.setDefaultAmountFirstType(panierFifo.getDefaultAmountFirstType() + firstAmount);
                panierFifo.setActionAmountFirstType(panierFifo.getActionAmountFirstType() + firstAmount);
                panierFifo.setDefaultAmountSecondType(panierFifo.getDefaultAmountSecondType() + secondAcount);
                panierFifo.setActionAmountSecondType(panierFifo.getActionAmountSecondType() + secondAcount);
                panierFifo.setDetailAgr(id);
                panierFifoRepository.save(panierFifo);
            } else {
                    // isSell = vente
                Fifo checkFifo = fifoService.getFirstOrAvrByType(!isSell, a.getId());
                if (checkFifo != null) {
                    PanierFifo panierFifo1 = new PanierFifo();
                    panierFifo1.setIsBuy(!isSell);
                    panierFifo1.setDefaultAmountFirstType(firstAmount);
                    panierFifo1.setAvr(avr1);
                    panierFifo1.setActionAmountFirstType(firstAmount);
                    panierFifo1.setDefaultAmountSecondType(secondAcount);
                    panierFifo1.setActionAmountSecondType(secondAcount);
                    panierFifo1.setDetailAgr(id);
                    panierFifoRepository.save(panierFifo1);

                }
                else {
                    //recuperation des valeurs maximales
                    Double valMaxFirst = 0.0; // Ban
                    Double valMaxSecond = 0.0; // Bci

                    if (isSell) {
                        valMaxSecond = javaConst.venteBCIMaxAmount;
                        valMaxFirst = Double.valueOf(vagueServiceInterface.convertBciBan(valMaxSecond));
                    } else {
                        valMaxFirst = javaConst.achatBAnMaxAmount;
                        valMaxSecond = Double.valueOf(vagueServiceInterface.convertBanBci(valMaxFirst));
                    }

                    Fifo f = new Fifo();
                    f.setIsBuy(!isSell);
                    f.setKsuType(KsuType.SELLER);
                    f.setVague(vague);


                    Double valBan = 0.0;
                    if (valMaxFirst < firstAmount) {

                        // creation des fifo avec les montant maximales

                        valBan = valMaxFirst;

                        f.setDefaultAmountFirstType(valMaxFirst);
                        f.setActionAmountFirstType(valMaxFirst);
                        f.setDefaultAmountSecondType(valMaxSecond);
                        f.setActionAmountSecondType(valMaxSecond);

                        //mise du reste dans un panier
                        PanierFifo panierFifo2 = new PanierFifo();
                        panierFifo2.setIsBuy(!isSell);
                        panierFifo2.setDefaultAmountFirstType(firstAmount - valMaxFirst);
                        panierFifo2.setAvr(avr1);
                        panierFifo2.setActionAmountFirstType(firstAmount - valMaxFirst);
                        panierFifo2.setDefaultAmountSecondType(secondAcount - valMaxSecond);
                        panierFifo2.setActionAmountSecondType(secondAcount - valMaxSecond);
                        panierFifo2.setDetailAgr(id);
                        panierFifoRepository.save(panierFifo2);

                        System.out.println("PANIER FIFO");
                        System.out.println(panierFifo2);

                    } else {
                        valBan = firstAmount;
                        f.setDefaultAmountFirstType(firstAmount);
                        f.setActionAmountFirstType(firstAmount);
                        f.setDefaultAmountSecondType(secondAcount);
                        f.setActionAmountSecondType(secondAcount);

                    }
//                    fifoService.addFifo(f, id);
                    f.setAmount(secondAcount);
                    f.setAvr(avr1);
                    f.setNumOrdre(fifoService.setNextNumOrdreVente());
                    fifo1 = fifoService.addFifoV2(f, id);

                    FifoAdmin fifoAdmin = new FifoAdmin();
                    fifoAdmin.setUserAmount(fifo1.getActionAmountSecondType());
                    fifoAdmin.setKsu(fifo1.getKsu());
                    fifoAdmin.setNumOrdre(fifo1.getNumOrdre());
                    fifoAdmin.setTourTotal(fifoAdminService.calculNumberTour(fifo1.getActionAmountSecondType(), fifo1.getKsuType()));


                }
            }
        }

        ReferencToken referencToken = referencTokenInterface.getById(a1.getIdTokenRef());

        TokenEntity tokenEntitie = tokenService.setFieldsFromDecryptedString(referencToken.getCodeRef());
        log.info("Token value : {} || Vente value :{}",tokenEntitie.getValue(),a1.getPrixUnitaire());
        System.out.println(a1);
        Double difference = tokenEntitie.getValue() - a1.getPrixUnitaire();
        log.info("Difference : {}",difference);
        ReferencToken tokenP = new ReferencToken();
        if(difference<0){
            return null;
        } else if(difference == 0.0){
            tokenP =  referencTokenInterface.segmentationTokenDadSonAcheteurSupVendeur(a1.getIdTokenRef(), idKsu,  tokenEntitie.getValue(), id);

        } else {
             tokenP =   referencTokenInterface.segmentationTokenDadSonVendeurSupAcheteur(a1.getIdTokenRef(),  idKsu, a1.getPrixUnitaire(), id);
        }
        fifo1.setAvr(tokenP.getAvr());
        fifo1.setReferencToken(tokenP);
        fifo1.setKsu(ksuRepository.findKsuById(idKsu));
        fifoService.addFifoV2(fifo1, id);

        manageTourService.manageTourSeller(fifo1);

        return a;
    }

    @Override
    public Avr addAvrV2sAchat(AvrRequest a1, Long id) throws Exception {

        CategorieAvr categorieAvr = categorieAvrRepository.findById(a1.getIdCategoryAvr()).orElse(null);
        TypeAvr typeAvr = typeAvrRepository.findById(a1.getIdTypeAvr()).orElse(null);

        Vague vague = vagueRepository.findById(a1.getIdVague()).orElse(null);
        if (vague == null) return null;

        boolean isSell = typeAvr.getLibelle().toLowerCase().contains("vente");

        System.out.println("=============IS Achat==============");
        System.out.println(!isSell);

        Double disponibilite = 0.0;
        Double amount = a1.getPrixUnitaire();
        TerminalEchange te = teClient.getTeByDetailAgr(id);
        Long idTe = te.getId();
        DetailsAgr d = detailAgrClient.getDetailById(id);
        Long idKsu = d.getKsu();
        com.esmc.gestionAvr.entities.Ksu ksu = ksuRepository.findKsuById(idKsu);

        ProductRegistryValue productRegistryValue1 = null;


        if (!isSell) {
            disponibilite = teClient.checkSmTotalCurrent(SupportMarchandConstant.QsupportMarchandBan, te.getId());
            System.out.println(disponibilite);
            //System.out.println(disponibilite);
            if (disponibilite < amount) {
                return null;
            }
        }

        Avr a = new Avr();
//        BeanUtils.copyProperties(a1, a);

        a.setTypeAvr(typeAvr);
        a.setLibelle(a1.getLibelle());
        a.setCategorieAvr(categorieAvr);
        a.setPrixUnitaire(a1.getPrixUnitaire());
        Avr avr1 = avrRepository.save(a);
        Fifo fifo1 = new Fifo();


        if (avr1.getTypeAvr().getLibelle().toLowerCase().contains("fifo")) {
            Double firstAmount = 0.0;
            Double secondAcount = 0.0;

            // boolean !isSell = a.getTypeAvr().getLibelle().toLowerCase().contains("vente"); ==> ACHAT
            if (!isSell) {
                firstAmount = a.getPrixUnitaire();
                secondAcount = Double.valueOf(vagueServiceInterface.convertBanBci(firstAmount));
            }

            //Verificqtion de l'operation a effectue
            ///si le avr a un fifopanier complete le montant
            PanierFifo panierFifo = panierFifoInterface.getFirstOrAvrByType(!isSell, a.getId());

            if (panierFifo != null) {
                //Ajout du montant au panier existant
                panierFifo.setDefaultAmountFirstType(panierFifo.getDefaultAmountFirstType() + firstAmount);
                panierFifo.setActionAmountFirstType(panierFifo.getActionAmountFirstType() + firstAmount);
                panierFifo.setDefaultAmountSecondType(panierFifo.getDefaultAmountSecondType() + secondAcount);
                panierFifo.setActionAmountSecondType(panierFifo.getActionAmountSecondType() + secondAcount);
                panierFifo.setDetailAgr(id);
                panierFifoRepository.save(panierFifo);
            } else {

                Fifo checkFifo = fifoService.getFirstOrAvrByType(!isSell, a.getId());
                if (checkFifo != null) {
                    PanierFifo panierFifo1 = new PanierFifo();
                    panierFifo1.setIsBuy(!isSell);
                    panierFifo1.setDefaultAmountFirstType(firstAmount);
                    panierFifo1.setAvr(avr1);
                    panierFifo1.setActionAmountFirstType(firstAmount);
                    panierFifo1.setDefaultAmountSecondType(secondAcount);
                    panierFifo1.setActionAmountSecondType(secondAcount);
                    panierFifo1.setDetailAgr(id);
                    panierFifoRepository.save(panierFifo1);

                } else {

                    //recuperation des valeurs maximales
                    Double valMaxFirst = 0.0;
                    Double valMaxSecond = 0.0;

                    if (!isSell) {
                        valMaxFirst = javaConst.achatBAnMaxAmount;
                        valMaxSecond = Double.valueOf(vagueServiceInterface.convertBanBci(valMaxFirst));
                    }

                    Fifo f = new Fifo();
                    f.setIsBuy(!isSell);
                    f.setVague(vague);
                    f.setAvr(a);

                    Double valBan = 0.0;
                    if (valMaxFirst < firstAmount) {

                        // creation des fifo avec les montant maximales
                        valBan = valMaxFirst;

                        f.setDefaultAmountFirstType(valMaxFirst);
                        f.setActionAmountFirstType(valMaxFirst);
                        f.setDefaultAmountSecondType(valMaxSecond);
                        f.setActionAmountSecondType(valMaxSecond);


                        //mise du reste dans un panier
                        PanierFifo panierFifo2 = new PanierFifo();
                        panierFifo2.setIsBuy(!isSell);
                        panierFifo2.setDefaultAmountFirstType(firstAmount - valMaxFirst);
                        panierFifo2.setAvr(avr1);
                        panierFifo2.setActionAmountFirstType(firstAmount - valMaxFirst);
                        panierFifo2.setDefaultAmountSecondType(secondAcount - valMaxSecond);
                        panierFifo2.setActionAmountSecondType(secondAcount - valMaxSecond);
                        panierFifo2.setDetailAgr(id);
                        panierFifoRepository.save(panierFifo2);

                        System.out.println("PANIER FIFO");
                        System.out.println(panierFifo2);

                    } else {
                        valBan = firstAmount;
                        f.setDefaultAmountFirstType(firstAmount);
                        f.setActionAmountFirstType(firstAmount);
                        f.setDefaultAmountSecondType(secondAcount);
                        f.setActionAmountSecondType(secondAcount);
                    }
                    f.setDefaultAmountFirstType(firstAmount);
                    f.setActionAmountFirstType(firstAmount);
                    f.setDefaultAmountSecondType(secondAcount);
                    f.setActionAmountSecondType(secondAcount);
                    f.setAmount(avr1.getPrixUnitaire());
                    f.setKsu(ksu);
//                  fifo1 =   fifoService.addFifo(f, id);

                    f.setKsuType(KsuType.BUYER);
                    f.setNumOrdre(fifoService.setNextNumOrdreAchat());
                    f.setAvr(avr1);
                   fifo1 =  fifoService.addFifoV2(f,id);

//                    tourService.addTour(fifo1);

                    manageTourService.manageTourAchat(fifo1);
//                    fifoService.setNumOrder(fifo1.getVague(),fifo1.getKsuType());
//                    fifo1 = fifoService.addFifoV2(f,id);

                    if (!isSell) {
                        teClient.depotAInterim(SupportMarchandConstant.QsupportMarchandBan, idTe, valBan);
                    }

                }
            }
        }
        //automateControllerV2.operateTransactionv3();

        return a;
    }


    public Avr addAvrV2s_stable(AvrRequest a1, Long id) throws Exception {
        System.out.println(a1);
        CategorieAvr categorieAvr = categorieAvrRepository.findById(a1.getIdCategoryAvr()).orElse(null);
        TypeAvr typeAvr = typeAvrRepository.findById(a1.getIdTypeAvr()).orElse(null);
        System.out.println(typeAvr);

        ProductRegistryValue productRegistryValue = new ProductRegistryValue();
        productRegistryValue.setValue(a1.getPrixUnitaire());
        ProductRegistryValue productRegistryValue1 = productRegistryValueRepository.save(productRegistryValue);
        productRegistryValue1.setIdentifant("0000" + productRegistryValue1.getId());
        productRegistryValueRepository.save(productRegistryValue1);

        Avr a = new Avr();
        BeanUtils.copyProperties(a1, a);
        a.setEtat(true);
        a.setCategorieAvr(categorieAvr);
        a.setProductRegistryValue(productRegistryValue);
        a.setTypeAvr(typeAvr);
        a.setDetailAgr(id);
        Avr avrPersist = avrRepository.save(a);
       /*if(a1.getPhoto() != null){
           uploadFileInterface.save(a1.getPhoto(), avrPersist.getId());
       }*/
        //Upload Media File
        if (a.getTypeAvr().getLibelle().toLowerCase().contains("fifo")) {
            Double firstAmount = 0.0;
            Double secondAcount = 0.0;

            boolean isSell = a.getTypeAvr().getLibelle().toLowerCase().contains("vente");
            if (isSell) {
                secondAcount = a.getPrixUnitaire();
                firstAmount = Double.valueOf(vagueServiceInterface.convertBciBan(secondAcount));
            } else {
                firstAmount = a.getPrixUnitaire();
                secondAcount = Double.valueOf(vagueServiceInterface.convertBanBci(firstAmount));
            }

            //Verificqtion de l'operation a effectue
            ///si le avr a un fifopanier complete le montant
            PanierFifo panierFifo = panierFifoInterface.getFirstOrAvrByType(!isSell, a.getId());
            if (panierFifo != null) {
                //Ajout du montant au panier existant
                panierFifo.setDefaultAmountFirstType(panierFifo.getDefaultAmountFirstType() + firstAmount);
                panierFifo.setActionAmountFirstType(panierFifo.getActionAmountFirstType() + firstAmount);
                panierFifo.setDefaultAmountSecondType(panierFifo.getDefaultAmountSecondType() + secondAcount);
                panierFifo.setActionAmountSecondType(panierFifo.getActionAmountSecondType() + secondAcount);
                panierFifo.setDetailAgr(id);
                panierFifoRepository.save(panierFifo);
            } else {

                Fifo checkFifo = fifoService.getFirstOrAvrByType(!isSell, a.getId());
                if (checkFifo != null) {
                    PanierFifo panierFifo1 = new PanierFifo();
                    panierFifo1.setIsBuy(!isSell);
                    panierFifo1.setDefaultAmountFirstType(firstAmount);
                    panierFifo1.setAvr(avrPersist);
                    panierFifo1.setActionAmountFirstType(firstAmount);
                    panierFifo1.setDefaultAmountSecondType(secondAcount);
                    panierFifo1.setActionAmountSecondType(secondAcount);
                    panierFifo1.setDetailAgr(id);
                    panierFifoRepository.save(panierFifo1);
                } else {

                    //recuperation des valeurs maximales
                    Double valMaxFirst = 0.0;
                    Double valMaxSecond = 0.0;

                    if (isSell) {
                        valMaxSecond = javaConst.venteBCIMaxAmount;
                        valMaxFirst = Double.valueOf(vagueServiceInterface.convertBciBan(valMaxSecond));
                    } else {
                        valMaxFirst = javaConst.achatBAnMaxAmount;
                        valMaxSecond = Double.valueOf(vagueServiceInterface.convertBanBci(valMaxFirst));
                    }

                    Fifo f = new Fifo();
                    f.setIsBuy(!isSell);
                    f.setAvr(avrPersist);

                    if (valMaxFirst < firstAmount) {

                        // creation des fifo avec les montant maximales
                        f.setDefaultAmountFirstType(valMaxFirst);
                        f.setActionAmountFirstType(valMaxFirst);
                        f.setDefaultAmountSecondType(valMaxSecond);
                        f.setActionAmountSecondType(valMaxSecond);

                        //mise du reste dans un panier
                        PanierFifo panierFifo2 = new PanierFifo();
                        panierFifo2.setIsBuy(!isSell);
                        panierFifo2.setDefaultAmountFirstType(firstAmount - valMaxFirst);
                        panierFifo2.setAvr(avrPersist);
                        panierFifo2.setActionAmountFirstType(firstAmount - valMaxFirst);
                        panierFifo2.setDefaultAmountSecondType(secondAcount - valMaxSecond);
                        panierFifo2.setActionAmountSecondType(secondAcount - valMaxSecond);
                        panierFifo2.setDetailAgr(id);
                        panierFifoRepository.save(panierFifo2);

                    } else {
                        f.setDefaultAmountFirstType(firstAmount);
                        f.setActionAmountFirstType(firstAmount);
                        f.setDefaultAmountSecondType(secondAcount);
                        f.setActionAmountSecondType(secondAcount);
                    }
                    fifoService.addFifo(f, id);
                }
            }
        }
        return a;
    }

    public Extrant CreateBonV3_with_agr(String bon, Long idDetailAgrVente,
                                        int quantite, Long idDetailAgrAchat,
                                        Double montant, Avr avr, Long refer) {

        //on recupere le type de bon
        TypeSmAvr t = typeSmAvrService.DetailTypeBon(bon);
        System.out.println("=============Type SM AVR==============");
        System.out.println(t);
        System.out.println("===========================");
        Intrant in = new Intrant();
        Extrant ex = new Extrant();

       /*DetailsAgr detailsAgr = agrInterimClient.getDetailById(idDetailAgrVente);
        Ksu ksu = ksuClient.getById(detailsAgr.getKsu());
        System.out.println("ksu : " + ksu.getId());

        DetailsAgr detailsAgr1 = agrInterimClient.getDetailById(idDetailAgrAchat);
        Ksu ksu1 = ksuClient.getById(detailsAgr1.getKsu());*/

        DetailsAgr detailsAgr = detailAgrClient.getDetailById(idDetailAgrVente);
        System.out.println("=============AGR VENDEUR==============");
        System.out.println(detailsAgr);
        System.out.println("===========================");
        Ksu ksuEnvoyeur = ksuClient.getById(detailsAgr.getKsu());
        System.out.println("=============KSU VENDEUR==============");
        System.out.println(ksuEnvoyeur);
        System.out.println("===========================");
        //System.out.println("ksu : " + ksu.getId());

        DetailsAgr detailsAgr1 = detailAgrClient.getDetailById(idDetailAgrAchat);
        System.out.println("=============AGR ACHETEUR==============");
        System.out.println(detailsAgr1);
        System.out.println("===========================");
        Ksu ksuRecepteur = ksuClient.getById(detailsAgr1.getKsu());
        System.out.println("=============KSU ACHETEUR==============");
        System.out.println(ksuRecepteur);
        System.out.println("===========================");


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



       /* ex.setAvr(avr);
        in.setAvr(avr);
        in.setMontant(montant);
        ex.setKsu(ksu.getId());
        ex.setMontant(montant);
        in.setTypeSmAvr(t);
        ex.setTypeSmAvr(t);
        in.setKsu(ksu1.getId());*/


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
        extrantRepository.save(ex);
        intrantRepository.save(in);

        System.out.println("la quantite est : " + quantite);
        //on affecte l'avr
        detailSMAvr.setAvr(avr);
        detailSMAvrRepository.save(detailSMAvr);

        return ex;
    }

    @Override
    @Transactional
    public Avr addAvrAchatSimple(AvrRequest a1, Long id) throws Exception {
        Long currentTe = teClient.getTeByDetailAgr(id).getId();

        DetailsAgr detailsAgr = detailAgrClient.getDetailById(currentTe);

        Ksu k = ksuClient.getById(detailsAgr.getKsu());

//        Double amount =  teClient.amountBciRiTe(currentTe);

        Double amount = a1.getPrixUnitaire();

//        System.out.println("=========================Montant=======================");
//        System.out.println(amount);
//        System.out.println("=================================================");

        Boolean status = teClient.achatDesendettement(currentTe);

        if (status) {
            //System.out.println(a1);

            CategorieAvr categorieAvr = categorieAvrRepository.findById(a1.getIdCategoryAvr()).orElseThrow(Exception::new);
            TypeAvr typeAvr = typeAvrRepository.findById(a1.getIdTypeAvr()).orElseThrow(Exception::new);

            ProductRegistryValue productRegistryValue = new ProductRegistryValue();
            productRegistryValue.setValue(amount);
            ProductRegistryValue productRegistryValue1 = productRegistryValueRepository.save(productRegistryValue);
            productRegistryValue1.setIdentifant("0000" + productRegistryValue1.getId());
            productRegistryValue1.setIdKsu(k.getId());
            productRegistryValueRepository.save(productRegistryValue1);

            Avr a = new Avr();
            BeanUtils.copyProperties(a1, a);
            a.setEtat(true);
            a.setCategorieAvr(categorieAvr);
            a.setPrixUnitaire(amount);
            a.setTypeAvr(typeAvr);
            a.setProductRegistryValue(productRegistryValue);
            a.setDetailAgr(id);
            Avr avrPersist = avrRepository.save(a);

            System.out.println("=========================AVR=======================");
            System.out.println(avrPersist);
            System.out.println("=================================================");

            Long venteDetailAgr = AccountConstant.TeEsmcGieFranchiseZeroOBPS;
            Long achatDetailAgr = currentTe;

            Extrant ex = CreateBonV3_with_agr("BC",venteDetailAgr, 1,achatDetailAgr,amount,avrPersist,null);
            Extrant ex1 = CreateBonV3_with_agr("BL",achatDetailAgr, 1,venteDetailAgr,amount,avrPersist, ex.getId());
            Extrant ex2 = CreateBonV3_with_agr("BRBL",venteDetailAgr, 1,achatDetailAgr,amount,avrPersist, ex1.getId());
            Extrant ex3 = CreateBonV3_with_agr("FD",achatDetailAgr, 1,venteDetailAgr,amount,avrPersist, ex2.getId());
            Extrant ex4 = CreateBonV3_with_agr("BESM",venteDetailAgr, 1,achatDetailAgr,amount,avrPersist, ex3.getId());
            Extrant ex5 = CreateBonV3_with_agr("BRBE",achatDetailAgr, 1,venteDetailAgr,amount,avrPersist, ex4.getId());


            //       =================================================================

         ReferencToken GIE=   referenceTokenRepository.getByIdDetailAgrGIE();


         referencTokenInterface.tokenGIE_Division_filsClient(GIE.getId(),k.getId(),avrPersist.getPrixUnitaire(),avrPersist);

            return a;
        }
        return null;
    }

    @Override
    public void CreateBonSmvAvrProcess(Long idDetailAgrVente, int quantite,
                                       Long idDetailAgrAchat, Double montant, String produit) {
        CategorieAvr categorieAvr = categorieAvrRepository.findById(4L).orElse(null);
        TypeAvr typeAvrAchat = typeAvrRepository.findById(2L).orElse(null);
        TypeAvr typeAvrVente = typeAvrRepository.findById(1L).orElse(null);

        Avr avrVente = new Avr();
        avrVente.setCategorieAvr(categorieAvr);
        avrVente.setTypeAvr(typeAvrVente);
        avrVente.setEtat(true);
        avrVente.setPrixUnitaire(montant);
        avrVente.setLibelle(produit);
        avrVente.setDetailAgr(idDetailAgrVente);
        Avr avrVenteSaved = avrRepository.save(avrVente);

        Avr avrAchat = new Avr();
        avrAchat.setCategorieAvr(categorieAvr);
        avrAchat.setTypeAvr(typeAvrAchat);
        avrAchat.setEtat(true);
        avrAchat.setPrixUnitaire(montant);
        avrAchat.setLibelle(produit);
        avrAchat.setDetailAgr(idDetailAgrAchat);
        Avr avrAchatSaved = avrRepository.save(avrVente);

        DetailsAgr detailsAgr = detailAgrClient.getDetailById(idDetailAgrVente);
        Ksu ksuVente = ksuClient.getById(detailsAgr.getKsu());
        Long idKsuVente = ksuVente.getId();

        DetailsAgr detailsAgr1 = detailAgrClient.getDetailById(idDetailAgrAchat);
        Ksu ksuAchat = ksuClient.getById(detailsAgr1.getKsu());
        Long idKsuAchat = ksuAchat.getId();

        Extrant ex = CreateBonSmvAvr("BC", idDetailAgrVente, idKsuVente, 1, idDetailAgrAchat, idKsuAchat, montant, avrVenteSaved, avrAchatSaved, null);
        Extrant ex1 = CreateBonSmvAvr("BL", idDetailAgrAchat, idKsuAchat, 1, idDetailAgrVente, idKsuVente, montant, avrAchatSaved, avrVenteSaved, ex.getId());

        Extrant ex2 = CreateBonSmvAvr("BRBL", idDetailAgrVente, idKsuVente, 1, idDetailAgrAchat, idKsuAchat, montant, avrVenteSaved, avrAchatSaved, ex1.getId());
        Extrant ex3 = CreateBonSmvAvr("FD", idDetailAgrAchat, idKsuAchat, 1, idDetailAgrVente, idKsuVente, montant, avrAchatSaved, avrVenteSaved, ex2.getId());

        Extrant ex4 = CreateBonSmvAvr("BESM", idDetailAgrVente, idKsuVente, 1, idDetailAgrAchat, idKsuAchat, montant, avrVenteSaved, avrAchatSaved, ex3.getId());
        Extrant ex5 = CreateBonSmvAvr("BRBE", idDetailAgrAchat, idKsuAchat, 1, idDetailAgrVente, idKsuVente, montant, avrAchatSaved, avrVenteSaved, ex4.getId());
    }

    @Override
    public void CreateBonSmvAvrProcessV3(Long idDetailAgrVente, Long posteRecepteur, int quantite, Long idDetailAgrAchat, Double montant, String produit) {
        CategorieAvr categorieAvr = categorieAvrRepository.findById(4L).orElse(null);
        TypeAvr typeAvrAchat = typeAvrRepository.findById(2L).orElse(null);
        TypeAvr typeAvrVente = typeAvrRepository.findById(1L).orElse(null);

        Avr avrVente = new Avr();
        avrVente.setCategorieAvr(categorieAvr);
        avrVente.setTypeAvr(typeAvrVente);
        avrVente.setEtat(true);
        avrVente.setPrixUnitaire(montant);
        avrVente.setLibelle(produit);
        avrVente.setDetailAgr(idDetailAgrVente);
        Avr avrVenteSaved = avrRepository.save(avrVente);

        Avr avrAchat = new Avr();
        avrAchat.setCategorieAvr(categorieAvr);
        avrAchat.setTypeAvr(typeAvrAchat);
        avrAchat.setEtat(true);
        avrAchat.setPrixUnitaire(montant);
        avrAchat.setLibelle(produit);
        avrAchat.setDetailAgr(idDetailAgrAchat);
        Avr avrAchatSaved = avrRepository.save(avrVente);

        DetailsAgr detailsAgr = detailAgrClient.getDetailById(idDetailAgrVente);
        Ksu ksuVente = ksuClient.getById(detailsAgr.getKsu());
        Long idKsuVente = ksuVente.getId();

        DetailsAgr detailsAgr1 = detailAgrClient.getDetailById(idDetailAgrAchat);
        Ksu ksuAchat = ksuClient.getById(detailsAgr1.getKsu());
        Long idKsuAchat = ksuAchat.getId();

        Extrant ex = CreateBonSmvAvrV2("BC", idDetailAgrVente, idKsuVente, posteRecepteur, 1, idDetailAgrAchat, idKsuAchat, montant, avrVenteSaved, avrAchatSaved, null);
        teClient.depotAInterim(2L, idDetailAgrAchat, montant);
        Extrant ex1 = CreateBonSmvAvrV2("BL", idDetailAgrAchat, idKsuAchat, posteRecepteur, 1, idDetailAgrVente, idKsuVente, montant, avrAchatSaved, avrVenteSaved, ex.getId());
//        Extrant ex2 =CreateBonSmvAvr("BRBL",idDetailAgrVente,idKsuVente, 1,idDetailAgrAchat,idKsuAchat,montant,avrVenteSaved ,avrAchatSaved , ex1.getId());
//        Extrant ex3 =CreateBonSmvAvr("FD",idDetailAgrAchat,idKsuAchat, 1,idDetailAgrVente,idKsuVente,montant,avrAchatSaved ,avrVenteSaved , ex2.getId());
//        Extrant ex4 =CreateBonSmvAvr("BESM",idDetailAgrVente,idKsuVente, 1,idDetailAgrAchat,idKsuAchat,montant,avrVenteSaved ,avrAchatSaved , ex3.getId());
//        Extrant ex5 = CreateBonSmvAvr("BRBE",idDetailAgrAchat,idKsuAchat, 1,idDetailAgrVente,idKsuVente,montant,avrAchatSaved ,avrVenteSaved , ex4.getId());
    }


    @Override
    public void CreateBonSmvAvrProcessV2(Long idDetailAgrVente, int quantite, Long idDetailAgrAchat, Double montant, String produit) {
        CategorieAvr categorieAvr = categorieAvrRepository.findById(4L).orElse(null);
        TypeAvr typeAvrAchat = typeAvrRepository.findById(2L).orElse(null);
        TypeAvr typeAvrVente = typeAvrRepository.findById(1L).orElse(null);

        Avr avrVente = new Avr();
        avrVente.setCategorieAvr(categorieAvr);
        avrVente.setTypeAvr(typeAvrVente);
        avrVente.setEtat(true);
        avrVente.setPrixUnitaire(montant);
        avrVente.setLibelle(produit);
        avrVente.setDetailAgr(idDetailAgrVente);
        Avr avrVenteSaved = avrRepository.save(avrVente);

        Avr avrAchat = new Avr();
        avrAchat.setCategorieAvr(categorieAvr);
        avrAchat.setTypeAvr(typeAvrAchat);
        avrAchat.setEtat(true);
        avrAchat.setPrixUnitaire(montant);
        avrAchat.setLibelle(produit);
        avrAchat.setDetailAgr(idDetailAgrAchat);
        Avr avrAchatSaved = avrRepository.save(avrVente);

        DetailsAgr detailsAgr = detailAgrClient.getDetailById(idDetailAgrVente);
        Ksu ksuVente = ksuClient.getById(detailsAgr.getKsu());
        Long idKsuVente = ksuVente.getId();

        DetailsAgr detailsAgr1 = detailAgrClient.getDetailById(idDetailAgrAchat);
        Ksu ksuAchat = ksuClient.getById(detailsAgr1.getKsu());
        Long idKsuAchat = ksuAchat.getId();

        Extrant ex = CreateBonSmvAvr("BC", idDetailAgrVente, idKsuVente, 1, idDetailAgrAchat, idKsuAchat, montant, avrVenteSaved, avrAchatSaved, null);
        teClient.depotAInterim(2L, idDetailAgrAchat, montant);
        Extrant ex1 = CreateBonSmvAvr("BL", idDetailAgrAchat, idKsuAchat, 1, idDetailAgrVente, idKsuVente, montant, avrAchatSaved, avrVenteSaved, ex.getId());
//        Extrant ex2 =CreateBonSmvAvr("BRBL",idDetailAgrVente,idKsuVente, 1,idDetailAgrAchat,idKsuAchat,montant,avrVenteSaved ,avrAchatSaved , ex1.getId());
//        Extrant ex3 =CreateBonSmvAvr("FD",idDetailAgrAchat,idKsuAchat, 1,idDetailAgrVente,idKsuVente,montant,avrAchatSaved ,avrVenteSaved , ex2.getId());
//        Extrant ex4 =CreateBonSmvAvr("BESM",idDetailAgrVente,idKsuVente, 1,idDetailAgrAchat,idKsuAchat,montant,avrVenteSaved ,avrAchatSaved , ex3.getId());
//        Extrant ex5 = CreateBonSmvAvr("BRBE",idDetailAgrAchat,idKsuAchat, 1,idDetailAgrVente,idKsuVente,montant,avrAchatSaved ,avrVenteSaved , ex4.getId());
    }


    public Extrant CreateBonSmvAvr(String bon, Long idDetailAgrEmetteur, Long idKsuEmetteur,
                                   int quantite, Long idDetailAgrRecepteur, Long idKsuRecepteur,
                                   Double montant, Avr avrEnvoyeur, Avr avrRecepteur, Long refer) {

        TypeSmAvr t = typeSmAvrService.DetailTypeBon(bon);
        Intrant in = new Intrant();
        Extrant ex = new Extrant();

        SMAvr bongenere = new SMAvr();
        bongenere.setTypeSmAvr(t);
        bongenere.setTypeSmAvr(typeSmAvrService.DetailTypeBon(bon));
        SMAvr a = smAvrRepository.save(bongenere);


        //on cree un detail SMAVR pour le SMAVR
        DetailSMAvr detailSMAvrEnvoyeur = new DetailSMAvr();
        detailSMAvrEnvoyeur.setPrixUnitaire(montant);
        detailSMAvrEnvoyeur.setMontantFirst(montant);
        detailSMAvrEnvoyeur.setMontantSecond(montant);
        detailSMAvrEnvoyeur.setQuantite(quantite);
        detailSMAvrEnvoyeur.setAvr(avrEnvoyeur);
        detailSMAvrRepository.save(detailSMAvrEnvoyeur);

        DetailSMAvr detailSMAvrRecepteur = new DetailSMAvr();
        detailSMAvrRecepteur.setPrixUnitaire(montant);
        detailSMAvrRecepteur.setMontantFirst(montant);
        detailSMAvrRecepteur.setMontantSecond(montant);
        detailSMAvrRecepteur.setQuantite(quantite);
        detailSMAvrRecepteur.setAvr(avrRecepteur);
        detailSMAvrRepository.save(detailSMAvrRecepteur);


        ex.setAvr(avrEnvoyeur);
        in.setAvr(avrRecepteur);

        ex.setRefer(refer);
        in.setRefer(refer);

        in.setMontant(montant);
        ex.setMontant(montant);

        /*ex.setKsu(ksu.getId());
        in.setKsu(ksu1.getId());*/

        in.setKsuEmetteur(idKsuEmetteur);
        in.setDetailAgrEmetteur(idDetailAgrEmetteur);
        in.setKsuRecepteur(idKsuRecepteur);
        in.setDetailAgrRecepteur(idDetailAgrRecepteur);

        ex.setKsuEmetteur(idKsuEmetteur);
        ex.setDetailAgrEmetteur(idDetailAgrEmetteur);
        ex.setKsuRecepteur(idKsuRecepteur);
        ex.setDetailAgrRecepteur(idDetailAgrRecepteur);

        in.setTypeSmAvr(t);
        ex.setTypeSmAvr(t);

        System.out.println(ex);
        System.out.println(in);

        extrantRepository.save(ex);
        intrantRepository.save(in);

        return ex;
    }

    // CREATION DE BON D'EXTRANT POUR ACHAT DE KSU
    public Extrant CreateBonSmvAvrV2(String bon, Long idDetailAgrEmetteur, Long idKsuEmetteur, Long posteRecepeteur, int quantite, Long idDetailAgrRecepteur, Long idKsuRecepteur, Double montant, Avr avrEnvoyeur, Avr avrRecepteur, Long refer) {

        TypeSmAvr t = typeSmAvrService.DetailTypeBon(bon);
        Intrant in = new Intrant();
        Extrant ex = new Extrant();

        SMAvr bongenere = new SMAvr();
        bongenere.setTypeSmAvr(t);
        bongenere.setTypeSmAvr(typeSmAvrService.DetailTypeBon(bon));
        SMAvr a = smAvrRepository.save(bongenere);


        //on cree un detail SMAVR pour le SMAVR

        DetailSMAvr detailSMAvrEnvoyeur = new DetailSMAvr();
        detailSMAvrEnvoyeur.setPrixUnitaire(montant);
        detailSMAvrEnvoyeur.setMontantFirst(montant);
        detailSMAvrEnvoyeur.setMontantSecond(montant);
        detailSMAvrEnvoyeur.setQuantite(quantite);
        detailSMAvrEnvoyeur.setAvr(avrEnvoyeur);
        detailSMAvrRepository.save(detailSMAvrEnvoyeur);

        DetailSMAvr detailSMAvrRecepteur = new DetailSMAvr();
        detailSMAvrRecepteur.setPrixUnitaire(montant);
        detailSMAvrRecepteur.setMontantFirst(montant);
        detailSMAvrRecepteur.setMontantSecond(montant);
        detailSMAvrRecepteur.setQuantite(quantite);
        detailSMAvrRecepteur.setAvr(avrRecepteur);
        detailSMAvrRepository.save(detailSMAvrRecepteur);


        ex.setAvr(avrEnvoyeur);
        in.setAvr(avrRecepteur);

        ex.setRefer(refer);
        in.setRefer(refer);

        in.setMontant(montant);
        ex.setMontant(montant);

        /*ex.setKsu(ksu.getId());
        in.setKsu(ksu1.getId());*/

        in.setKsuEmetteur(idKsuEmetteur);
        in.setDetailAgrEmetteur(idDetailAgrEmetteur);
        in.setKsuRecepteur(idKsuRecepteur);
        in.setDetailAgrRecepteur(idDetailAgrRecepteur);
        in.setPosteReceveur(posteRecepeteur);

        ex.setKsuEmetteur(idKsuEmetteur);
        ex.setDetailAgrEmetteur(idDetailAgrEmetteur);
        ex.setKsuRecepteur(idKsuRecepteur);
        ex.setDetailAgrRecepteur(idDetailAgrRecepteur);
        ex.setPosteReceveur(posteRecepeteur);

        in.setTypeSmAvr(t);
        ex.setTypeSmAvr(t);

        System.out.println(ex);
        System.out.println(in);

        extrantRepository.save(ex);
        intrantRepository.save(in);

        return ex;
    }


    @Override
    public Avr findAvrById(Long id) {
        return avrRepository.findById(id).get();
    }

    @Override
    public String listAvrProduit(Long idPro) {

        List<ProductRegistryValue> productRegistryValueList = productRegistryValueRepository.productRegistryValueList(idPro);

        List<Avr> listAvr = new ArrayList<Avr>();

        Avr a = null;
        String produit = "";

        if (!productRegistryValueList.isEmpty()) {
            for (ProductRegistryValue p : productRegistryValueList) {
                a = avrRepository.listAvrProductFifo(p.getId());
                listAvr.add(a);
            }
        }

        for (Avr av : listAvr) {
            if (av != null) {
                produit = av.getLibelle();
            } else {
                produit = "Inexistant";
            }
        }
        return produit;
    }


}
