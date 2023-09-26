package com.esmc.gestionAvr.tokens.services;


import com.esmc.gestionAvr.entities.*;
import com.esmc.gestionAvr.repositories.*;
import com.esmc.gestionAvr.services.AvrServices;
import com.esmc.gestionAvr.tokens.entities.ReferencToken;
import com.esmc.gestionAvr.tokens.model.ReferenceModel;
import com.esmc.gestionAvr.tokens.model.TokenEntity;
import com.esmc.gestionAvr.tokens.repositories.ReferenceTokenRepository;
import com.esmc.gestionAvr.tokens.service.TokenServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j


/**
 * @author Amemorte9
 */
public class ReferenceTokenImpl implements ReferencTokenInterface {

    @Autowired
    private  ReferenceTokenRepository referenceTokenRepository;

    @Autowired
    private  TokenServiceImpl tokenService;


    @Autowired
    private  AvrServices avrService;

    @Autowired
    private  CategorieAvrRepository categoryAvrRepository;


    @Autowired
    private  ProductRegistryValueRepository productRegistryValueRepository;


    @Autowired
    private  TypeAvrRepository typeAvrRepository;

    @Autowired
    private  AvrRepository avrRepository;

    @Autowired
    private  ReferenceTokenOpRepository referenceTokenOpRepository;


    @Autowired
    private  KsuRepository ksuRepository;


    //    private AtomicLong counter = new AtomicLong();
    private final char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

//    public String generateSerialCode1() {
//        long value = counter.incrementAndGet();
//        int charIndex = (int) (value % alphabet.length);
//
//        return "AAQS-" + String.format("%04d", value) + "-AZ" + alphabet[charIndex] + "9-0000-ESMC";
//    }


    public String generateSerialCode() {
        int counter = referenceTokenRepository.Counter();


//        int charIndex1 = (counter / alphabet.length / alphabet.length) % alphabet.length;
//        int charIndex2 = (counter / alphabet.length) % alphabet.length;
//        int charIndex3 = counter % alphabet.length;


        int charIndex1 = (int) ((counter / alphabet.length / alphabet.length / alphabet.length / alphabet.length / alphabet.length / alphabet.length / alphabet.length) % alphabet.length);
        int charIndex2 = (int) ((counter / alphabet.length / alphabet.length / alphabet.length / alphabet.length / alphabet.length / alphabet.length) % alphabet.length);
        int charIndex3 = (int) ((counter / alphabet.length / alphabet.length / alphabet.length / alphabet.length / alphabet.length) % alphabet.length);
        int charIndex4 = (int) ((counter / alphabet.length / alphabet.length / alphabet.length / alphabet.length) % alphabet.length);
        int charIndex5 = (int) ((counter / alphabet.length / alphabet.length / alphabet.length) % alphabet.length);
        int charIndex6 = (int) ((counter / alphabet.length / alphabet.length) % alphabet.length);


        int modulo = counter / 10000;


        if (counter >= 10000) {
            counter = counter - (modulo * 10000);

        }
        return "AA" + alphabet[charIndex5] + alphabet[charIndex6] + "-" + String.format("%04d", counter) + "-AZ" + alphabet[charIndex1] + alphabet[charIndex2] + alphabet[charIndex3] + alphabet[charIndex4] + "9-0000-ESMC";

    }

    @Override
    public ReferencToken creatRef(TokenEntity tokenEntity) {

        String codeFront = generateSerialCode();

        String ref = tokenService.generateCode2(tokenEntity);
        ReferencToken referencToken = new ReferencToken();
        referencToken.setCodeRef(ref);
        referencToken.setAvr(tokenEntity.getAvr());
        referencToken.setRefTokenFront(codeFront);
        referenceTokenRepository.save(referencToken);

        return referencToken;
    }

    @Override
    public ReferenceTokenOp creatRefOp(TokenEntity tokenEntity) {

        String codeFront = generateSerialCode();

        String ref = tokenService.generateCode2(tokenEntity);
        ReferenceTokenOp referenceTokenOp = new ReferenceTokenOp();
        referenceTokenOp.setCodeRef(ref);
        referenceTokenOp.setAvr(tokenEntity.getAvr());
        referenceTokenOp.setRefTokenFront(codeFront);
        referenceTokenOpRepository.save(referenceTokenOp);

        return referenceTokenOp;
    }

    @Override
    public List<ReferencToken> getByIdAVR(Long id) {
        List<Avr> avrs = avrService.listAvrByDetailAgrId(id);

        List<ReferencToken> result = new ArrayList<>();
        for (Avr a : avrs) {
            ReferencToken obj = referenceTokenRepository.getByIdAvr2(a.getId());
            if (obj != null) {
                result.add(obj);
            }
//            log.info("List of Result : {}",result);
        }
        return result;
    }

    @Override
    public ReferencToken getById(Long id) {
        return referenceTokenRepository.getById(id);
    }

    @Override
    public ReferencToken getByCoderef(String code) {
        return referenceTokenRepository.getByCodeRef(code);
    }

    @Override
    public ReferenceModel getByRefTokenFrontref(String code) {

        ReferenceModel referenceModel = new ReferenceModel();

        ReferencToken referencToken = referenceTokenRepository.getByRefTokenFront(code);
        TokenEntity tokenEntitie = tokenService.setFieldsFromDecryptedString(referencToken.getCodeRef());

        BeanUtils.copyProperties(referencToken, referenceModel);
        BeanUtils.copyProperties(tokenEntitie, referenceModel);

        return referenceModel;
    }


    //    By wonder
    @Override
    public Boolean getByRefTokenFromFrontAndCheckAmount(String tokenCode, Long enteredValue) {
        ReferenceModel referenceModel = new ReferenceModel();
        ReferencToken referencToken = referenceTokenRepository.getByRefTokenFront(tokenCode);
        TokenEntity tokenEntity = tokenService.setFieldsFromDecryptedString(referencToken.getCodeRef());
        BeanUtils.copyProperties(referencToken, referenceModel);
        BeanUtils.copyProperties(tokenEntity, referenceModel);
        if (referenceModel.getValue() < enteredValue) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public List<ReferencToken> getAll() {
        return referenceTokenRepository.findAll();
    }

    @Override
    public ReferencToken fusionToken(Long[] idReferencToken, Long idDetailAgr) throws Exception {

        Ksu k = ksuRepository.findKsuById(idDetailAgr);
        List<ReferencToken> result = new ArrayList<>();
        for (Long id : idReferencToken) {
            ReferencToken obj = referenceTokenRepository.getById(id);
            result.add(obj);
        }
        TokenEntity tokenEntitie = new TokenEntity();
        Double val = 0.0;
        Avr avr = new Avr();

        for (ReferencToken r : result) {
            TokenEntity tokenDet = tokenService.setFieldsFromDecryptedString(r.getCodeRef());
            val = val + tokenDet.getValue();


            r.setEtat(false);
            referenceTokenRepository.save(r);

            System.out.println("val " + val);


            avr = r.getAvr();
            tokenEntitie.setAvr(avr);


        }

        System.out.println("avr  " + avr);


        tokenEntitie.setValue(val);

        System.out.println("valeur " + avr);
        CategorieAvr categorieAvr = categoryAvrRepository.findById(tokenEntitie.getAvr().getCategorieAvr().getId()).orElse(null);
        TypeAvr typeAvr = typeAvrRepository.findById(tokenEntitie.getAvr().getTypeAvr().getId()).orElse(null);

        ProductRegistryValue productRegistryValue = new ProductRegistryValue();
        productRegistryValue.setValue(tokenEntitie.getValue());
        ProductRegistryValue productRegistryValue1 = productRegistryValueRepository.save(productRegistryValue);
        productRegistryValue1.setIdentifant("0000" + productRegistryValue1.getId());
        productRegistryValue1.setIdKsu(k.getId());
        productRegistryValueRepository.save(productRegistryValue1);

        Avr a = new Avr();
        a.setLibelle(typeAvr.getLibelle());
        a.setEtat(true);
        a.setQuantite(1);
        a.setDescription(categorieAvr.getLibelle());
        a.setCategorieAvr(categorieAvr);
        a.setPrixUnitaire(tokenEntitie.getValue());
        a.setTypeAvr(typeAvr);
        a.setProductRegistryValue(productRegistryValue);
        a.setDetailAgr(idDetailAgr);
        Avr avrPersist = avrRepository.save(a);


        tokenEntitie.setAvr(avrPersist);
        tokenEntitie.setIdKsu(k.getId());
        tokenEntitie.setLibelle(avrPersist.getCategorieAvr().getLibelle());

        ReferencToken referencToken = creatRef(tokenEntitie);


        System.out.println(" referencToken 1   " + referencToken);

         referencToken.setReferencTokens(result);

        referenceTokenRepository.save(referencToken);


        System.out.println(" referencToken 2   " + referencToken);


        return referencToken;
    }

    @Override
    public ReferencToken segmentationTokenDadSonVendeurSupAcheteur(Long idRef, Long idKSUFils, Double amount, Long idDetailAgr) {


        ReferencToken token2=referenceTokenRepository.findById(idRef).orElse(null);

        if(token2.isEtat()==false){

            ReferencToken token3=referenceTokenRepository.findByIdParent(idRef);

            idRef=token3.getId();

        }
        //recuperation du refernce
        ReferencToken token = referenceTokenRepository.getByIdAvr(idRef);


        //decomposition du coutenu (token)
        TokenEntity tokenParentV1 = tokenService.setFieldsFromDecryptedString(token.getCodeRef());

        if (!token.isEtat()) {
            return null;
        }

        //disable parentV1
        token.setEtat(false);
        referenceTokenRepository.save(token);



        //parent V2 creation


        Double difference=tokenParentV1.getValue() - amount;

        TokenEntity tokenFils = new TokenEntity();//fils token

        ReferencToken fils=new ReferencToken();


        if(difference>0.0) {

            Avr avr2 = token.getAvr();

            avr2.setPrixUnitaire(token.getAvr().getPrixUnitaire() - amount);

            avrRepository.save(avr2);

            TokenEntity tokenParentV2 = new TokenEntity();
            tokenParentV2.setValue(tokenParentV1.getValue() - amount);
            tokenParentV2.setIdKsu(tokenParentV1.getIdKsu());
            tokenParentV2.setIdRefParent(tokenParentV1.getIdRefParent());
            tokenParentV2.setLibelle(tokenParentV1.getLibelle());
            tokenParentV2.setAvr(avr2);
            ReferencToken referencToken = creatRef(tokenParentV2);

            referencToken.setIdParent(idRef);
            referenceTokenRepository.save(referencToken);
            //product
            ProductRegistryValue productRegistryValue = new ProductRegistryValue();
            productRegistryValue.setValue(amount);
            ProductRegistryValue productRegistryValue1 = productRegistryValueRepository.save(productRegistryValue);
            productRegistryValue1.setIdentifant("0000" + productRegistryValue1.getId());
            productRegistryValue1.setIdKsu(idKSUFils);
            productRegistryValueRepository.save(productRegistryValue1);
            //avr
            Avr a = new Avr();
            a.setLibelle(tokenParentV1.getLibelle());
            a.setEtat(true);
            a.setQuantite(1);
            a.setDescription(tokenParentV1.getLibelle());

            a.setCategorieAvr(token.getAvr().getCategorieAvr());

            a.setPrixUnitaire(amount);
            a.setTypeAvr(token.getAvr().getTypeAvr());
            a.setProductRegistryValue(productRegistryValue);
            a.setDetailAgr(idDetailAgr);
            Avr avrPersist = avrRepository.save(a);


//tokent



            tokenFils.setValue(amount);
            tokenFils.setLibelle(tokenParentV1.getLibelle());
            tokenFils.setIdKsu(idKSUFils);
            tokenFils.setIdRefParent(tokenParentV1.getIdRefParent());
            tokenFils.setAvr(avrPersist);
            ReferencToken referencToken1 =  creatRef(tokenFils);
            referencToken1.setAvr(avrPersist);
            fils=  referencToken1;

        }else{

            //product
            ProductRegistryValue productRegistryValue = new ProductRegistryValue();
            productRegistryValue.setValue(amount);
            ProductRegistryValue productRegistryValue1 = productRegistryValueRepository.save(productRegistryValue);
            productRegistryValue1.setIdentifant("0000" + productRegistryValue1.getId());
            productRegistryValue1.setIdKsu(idKSUFils);
            productRegistryValueRepository.save(productRegistryValue1);
            Avr a = new Avr();
            a.setLibelle(tokenParentV1.getLibelle());
            a.setEtat(true);
            a.setQuantite(1);
            a.setDescription(tokenParentV1.getLibelle());

            a.setCategorieAvr(token.getAvr().getCategorieAvr());

            a.setPrixUnitaire(amount);
            a.setTypeAvr(token.getAvr().getTypeAvr());
            a.setProductRegistryValue(productRegistryValue);
            a.setDetailAgr(idDetailAgr);
            Avr avrPersist = avrRepository.save(a);


//tokent



            tokenFils.setValue(amount);
            tokenFils.setLibelle(tokenParentV1.getLibelle());
            tokenFils.setIdKsu(idKSUFils);
            tokenFils.setIdRefParent(tokenParentV1.getIdRefParent());
            tokenFils.setAvr(avrPersist);
            ReferencToken referencToken1 =  creatRef(tokenFils);
            referencToken1.setAvr(avrPersist);
            fils=  referencToken1;

        }


        return fils;


    }

//    @Override
//    public ReferencToken segmentationTokenDadSonVendeurSupAcheteur(Long idRef, Long idKSUFils, Double amount, Long idDetailAgr) {
//        ReferencToken token = referenceTokenRepository.findTokenWithParentAndAvr(idRef);
//
//        if (token == null || !token.isEtat()) {
//            return null;
//        }
//
//        TokenEntity tokenParentV1 = tokenService.setFieldsFromDecryptedString(token.getCodeRef());
//        token.setEtat(false);
//
//        Avr avr2 = token.getAvr();
//        avr2.setPrixUnitaire(avr2.getPrixUnitaire() - amount);
//        avrRepository.save(avr2);
//
//        TokenEntity tokenParentV2 = new TokenEntity() {{
//            setValue(tokenParentV1.getValue() - amount);
//            setIdKsu(tokenParentV1.getIdKsu());
//            setIdRefParent(tokenParentV1.getIdRefParent());
//            setLibelle(tokenParentV1.getLibelle());
//            setAvr(avr2);
//        }};
//
//        ReferencToken referencToken = creatRef(tokenParentV2);
//        if (tokenParentV1.getValue() == amount) {
//            referencToken.setEtat(false);
//        }
//
//        referencToken.setIdParent(idRef);
//        referenceTokenRepository.save(referencToken);
//
//        ProductRegistryValue productRegistryValue = new ProductRegistryValue();
//        productRegistryValue.setValue(amount);
//        productRegistryValue.setIdentifant("0000" + productRegistryValue.getId());
//        productRegistryValue.setIdKsu(idKSUFils);
//        ProductRegistryValue productRegistryValue1 = productRegistryValueRepository.save(productRegistryValue);
//
//        Avr avrPersist = avrRepository.save(new Avr() {{
//            setLibelle(tokenParentV1.getLibelle());
//            setEtat(true);
//            setQuantite(1);
//            setDescription(tokenParentV1.getLibelle());
//            setCategorieAvr(token.getAvr().getCategorieAvr());
//            setPrixUnitaire(amount);
//            setTypeAvr(token.getAvr().getTypeAvr());
//            setProductRegistryValue(productRegistryValue1);
//            setDetailAgr(idDetailAgr);
//        }});
//
//        TokenEntity tokenEntity = new TokenEntity();
//        tokenEntity.setValue(amount);
//        tokenEntity.setLibelle(tokenParentV1.getLibelle());
//        tokenEntity.setIdKsu(idKSUFils);
//        tokenEntity.setIdRefParent(tokenParentV1.getIdRefParent());
//        tokenEntity.setAvr(avrPersist);
//        ReferencToken referencToken1 = creatRef(tokenEntity);
//
//        referencToken1.setAvr(avrPersist);
//
//        return referencToken1;
//    }

    @Override
    public ReferencToken segmentationTokenDadSonAcheteurSupVendeur(Long idRef, Long idKSUFils, Double amount, Long idDetailAgr) {
        ReferencToken token2=referenceTokenRepository.findById(idRef).orElse(null);

        if(token2.isEtat()==false){

            ReferencToken token3=referenceTokenRepository.findByIdParent(idRef);

            idRef=token3.getId();

        }
        //recuperation du refernce
        ReferencToken token = referenceTokenRepository.getByIdAvr(idRef);

        if (!token.isEtat() && token.getAvr() == null) return null;
        //decomposition du coutenu (token)
        TokenEntity tokenParentVendeur = tokenService.setFieldsFromDecryptedString(token.getCodeRef());

        //disable parentV1
        token.setEtat(false);
        referenceTokenRepository.save(token);

        //product
        ProductRegistryValue productRegistryValue = new ProductRegistryValue();
        productRegistryValue.setValue(amount);
        ProductRegistryValue productRegistryValue1 = productRegistryValueRepository.save(productRegistryValue);
        productRegistryValue1.setIdentifant("0000" + productRegistryValue1.getId());
        productRegistryValue1.setIdKsu(idKSUFils);
        productRegistryValueRepository.save(productRegistryValue1);
//avr
        Avr a = new Avr();
        a.setLibelle(tokenParentVendeur.getLibelle());
        a.setEtat(true);
        a.setQuantite(1);
        a.setDescription(tokenParentVendeur.getLibelle());

        a.setCategorieAvr(token.getAvr().getCategorieAvr());

        a.setPrixUnitaire(tokenParentVendeur.getValue());
        a.setTypeAvr(token.getAvr().getTypeAvr());
        a.setProductRegistryValue(productRegistryValue);
        a.setDetailAgr(idDetailAgr);
        Avr avrPersist = avrRepository.save(a);
//tokent

        TokenEntity tokenParentAcheteur = new TokenEntity();
        tokenParentAcheteur.setValue(amount);
        tokenParentAcheteur.setIdKsu(tokenParentVendeur.getIdKsu());
        tokenParentAcheteur.setIdRefParent(tokenParentVendeur.getIdRefParent());
        tokenParentAcheteur.setLibelle(tokenParentVendeur.getLibelle());
        tokenParentAcheteur.setAvr(avrPersist);
        ReferencToken referencToken = creatRef(tokenParentVendeur);
        referencToken.setAvr(avrPersist);
        referenceTokenRepository.save(referencToken);

        return referencToken;
    }

    @Override
    public ReferencToken[] divisionToken(Long idRef, Long idKsu, Long idDetailAgr, Double amoutBasket, Double amoutTour) {

     ReferencToken token=   referenceTokenRepository.getById(idRef);
     //disable parent
     token.setEtat(false);

     referenceTokenRepository.save(token);

     TokenEntity tokenEntity=tokenService.setFieldsFromDecryptedString(token.getCodeRef());

     Avr a = new Avr();
        a.setLibelle(tokenEntity.getLibelle());
        a.setEtat(true);
        a.setQuantite(1);
        a.setDescription(tokenEntity.getLibelle());
        a.setCategorieAvr(token.getAvr().getCategorieAvr());
        a.setPrixUnitaire(amoutTour);
        a.setTypeAvr(token.getAvr().getTypeAvr());
        //a.setProductRegistryValue(productRegistryValue);
       // a.setDetailAgr(idDetailAgr);
        Avr avrTour = avrRepository.save(a);




        TokenEntity tokenTour=new TokenEntity();

        tokenTour.setLibelle(tokenEntity.getLibelle());
        tokenTour.setAvr(avrTour);
        tokenTour.setIdRefParent(tokenEntity.getIdRefParent());
        tokenTour.setIdKsu(tokenEntity.getIdKsu());
        tokenTour.setValue(amoutTour);
        ReferencToken referencTokenTour=creatRef(tokenTour);


        referencTokenTour.setIdParent(idRef);
        referencTokenTour.setAvr(avrTour);
    ReferencToken tour=  referenceTokenRepository.save(referencTokenTour);


        Avr aBasket = new Avr();
        a.setLibelle(tokenEntity.getLibelle());
        a.setEtat(true);
        a.setQuantite(1);
        a.setDescription(tokenEntity.getLibelle());
        a.setCategorieAvr(token.getAvr().getCategorieAvr());
        a.setPrixUnitaire(amoutBasket);
        a.setTypeAvr(token.getAvr().getTypeAvr());
        //a.setProductRegistryValue(productRegistryValue);
        //a.setDetailAgr(idDetailAgr);
        Avr avrBasket = avrRepository.save(a);


        TokenEntity tokenEntityBasket=new TokenEntity();

        tokenEntityBasket.setLibelle(tokenEntity.getLibelle());
        tokenEntityBasket.setAvr(avrBasket);
        tokenEntityBasket.setIdRefParent(tokenEntity.getIdRefParent());
        tokenEntityBasket.setIdKsu(tokenEntity.getIdKsu());
        tokenEntityBasket.setValue(amoutBasket);
        ReferencToken referencTokenBasket=creatRef(tokenEntityBasket);




        referencTokenTour.setAvr(avrBasket);
        referencTokenBasket.setIdParent(idRef);
       ReferencToken basket= referenceTokenRepository.save(referencTokenBasket);




        return new ReferencToken[]{tour, basket};

    }



    @Override
    public void tokenGIE() {

        CategorieAvr categorieAvr= categoryAvrRepository.findById(1L).orElse(null);
        TypeAvr typeAvr=typeAvrRepository.findById(1L).orElse(null);


        Avr a = new Avr();
        a.setLibelle("FELM");
        a.setEtat(true);
        a.setQuantite(1);
        a.setDescription("GIE token");
        a.setCategorieAvr(categorieAvr);
        a.setPrixUnitaire(3242157447019290000000000000.0);
        a.setTypeAvr(typeAvr);
        a.setDetailAgr(6L);

        Avr avrGIE = avrRepository.save(a);
        TokenEntity tokenGIE=new TokenEntity();

        tokenGIE.setLibelle("FELM");
        tokenGIE.setAvr(avrGIE);
       // tokenGIE.setIdRefParent();
        tokenGIE.setIdKsu(2L);
        tokenGIE.setValue(3242157447019290000000000000.00);
        ReferencToken referencTokenGIE=creatRef(tokenGIE);


      //  referencTokenGIE.setIdParent(idRef);
        referencTokenGIE.setAvr(avrGIE);
        ReferencToken tour=  referenceTokenRepository.save(referencTokenGIE);

    }


    @Override
    public ReferencToken tokenGIE_Division_filsClient(Long idRef, Long idKsu, Double amount, Avr avrClient) {

        ReferencToken tokenGIE_old=referenceTokenRepository.findById(idRef).orElse(null);

        assert tokenGIE_old != null : "Token GIE Not found !";
        if(!tokenGIE_old.isEtat()){

            ReferencToken token3=referenceTokenRepository.findByIdParent(idRef);

            idRef=token3.getId();

        }
        //recuperation du refernce
        ReferencToken token = referenceTokenRepository.getByIdAvr(idRef);

        //decomposition du coutenu (token)
        TokenEntity tokenGIE_old2 = tokenService.setFieldsFromDecryptedString(token.getCodeRef());

        if (!token.isEtat()) {
            return null;
        }

        //disable parentV1
        token.setEtat(false);
        referenceTokenRepository.save(token);

        Avr avrGIE_new = token.getAvr();

        avrGIE_new.setPrixUnitaire(token.getAvr().getPrixUnitaire() - amount);

        avrRepository.save(avrGIE_new);

        TokenEntity tokenParentGIE_new = new TokenEntity();
        tokenParentGIE_new.setValue(tokenGIE_old2.getValue() - amount);
        tokenParentGIE_new.setIdKsu(tokenGIE_old2.getIdKsu());
        tokenParentGIE_new.setIdRefParent(tokenGIE_old2.getIdRefParent());
        tokenParentGIE_new.setLibelle(tokenGIE_old2.getLibelle());
        tokenParentGIE_new.setAvr(avrGIE_new);
        ReferencToken referencTokenGIE = creatRef(tokenParentGIE_new);

        referencTokenGIE.setIdParent(idRef);
        referenceTokenRepository.save(referencTokenGIE);

        TokenEntity tokenFils = new TokenEntity();
        tokenFils.setValue(amount);
        tokenFils.setLibelle(avrClient.getLibelle());
        tokenFils.setIdKsu(idKsu);
        tokenFils.setIdRefParent(idRef);
        tokenFils.setAvr(avrClient);
        ReferencToken referencTokenClient =  creatRef(tokenFils);
        referencTokenClient.setAvr(avrClient);

        return referencTokenClient;

    }

    @Override
    public ReferenceTokenOp tokenGIE_Division_filsClientAchatOP(Long idRef, Long idKsu, Double amount, Avr avrClient) {

        ReferencToken tokenGIE_old=referenceTokenRepository.findById(idRef).orElse(null);

        assert tokenGIE_old != null : "Token GIE Not found !";
        if(!tokenGIE_old.isEtat()){

            ReferencToken token3=referenceTokenRepository.findByIdParent(idRef);

            idRef=token3.getId();

        }
        //recuperation du refernce
        ReferencToken token = referenceTokenRepository.getByIdAvr(idRef);

        //decomposition du coutenu (token)
        TokenEntity tokenGIE_old2 = tokenService.setFieldsFromDecryptedString(token.getCodeRef());

        if (!token.isEtat()) {
            return null;
        }

        //disable parentV1
        token.setEtat(false);
        referenceTokenRepository.save(token);

        Avr avrGIE_new = token.getAvr();

        avrGIE_new.setPrixUnitaire(token.getAvr().getPrixUnitaire() - amount);

        avrRepository.save(avrGIE_new);

        TokenEntity tokenParentGIE_new = new TokenEntity();
        tokenParentGIE_new.setValue(tokenGIE_old2.getValue() - amount);
        tokenParentGIE_new.setIdKsu(tokenGIE_old2.getIdKsu());
        tokenParentGIE_new.setIdRefParent(tokenGIE_old2.getIdRefParent());
        tokenParentGIE_new.setLibelle(tokenGIE_old2.getLibelle());
        tokenParentGIE_new.setAvr(avrGIE_new);
        ReferencToken referencTokenGIE = creatRef(tokenParentGIE_new);

        referencTokenGIE.setIdParent(idRef);
        referenceTokenRepository.save(referencTokenGIE);

        TokenEntity tokenFils = new TokenEntity();
        tokenFils.setValue(amount);
        tokenFils.setLibelle(avrClient.getLibelle());
        tokenFils.setIdKsu(idKsu);
        tokenFils.setIdRefParent(idRef);
        tokenFils.setAvr(avrClient);
        ReferenceTokenOp referencTokenClient =  creatRefOp(tokenFils);
        referencTokenClient.setAvr(avrClient);

        return referencTokenClient;

    }

}
