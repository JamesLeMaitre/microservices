package com.esmc.gestionAgr.fifo.services.impls;


import com.esmc.gestionAgr.fifo.entities.*;
import com.esmc.gestionAgr.fifo.models.ReferenceModel;
import com.esmc.gestionAgr.fifo.models.TokenEntity;
import com.esmc.gestionAgr.fifo.repositories.*;
import com.esmc.gestionAgr.fifo.services.AvrService;
import com.esmc.gestionAgr.fifo.services.ReferenceTokenService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j


/**
 * @author James
 */
public class ReferenceTokenImpl implements ReferenceTokenService {
    private final ReferenceTokenRepository referenceTokenRepository;
    private final TokenServiceImpl tokenService;


    @Lazy
    private final AvrService avrService;
    private final CategoryAvrRepository categoryAvrRepository;

    private final ProductRegistryValueRepository productRegistryValueRepository;


    private final TypeAvrRepository typeAvrRepository;

    private final AvrRepository avrRepository;


    private final KsuRepository ksuRepository;


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
    public List<ReferencToken> getByIdAVR(Long id) {
        List<Avr> avrs = avrService.listAvrByDetailAgrId(id);

        List<ReferencToken> result = new ArrayList<>();
        for (Avr a : avrs) {
            ReferencToken obj = referenceTokenRepository.getByIdAvr(a.getId());
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


        }

        System.out.println("avr  " + avr);


        tokenEntitie.setValue(val);

        System.out.println("valeur " + avr);
        CategorieAvr categorieAvr = categoryAvrRepository.findById(tokenEntitie.getAvr().getCategorieAvr().getId()).orElseThrow(() -> new Exception());
        TypeAvr typeAvr = typeAvrRepository.findById(tokenEntitie.getAvr().getTypeAvr().getId()).orElseThrow(() -> new Exception());

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

//    @Override
//    public ReferencToken segmentationTokenDadSonVendeurSupAcheteur(Long idRef, Long idKSUFils, Double amount, Long idDetailAgr) {
//
//
//        ReferencToken token2=referenceTokenRepository.findById(idRef).orElse(null);
//
//        if(token2.isEtat()==false){
//
//            ReferencToken token3=referenceTokenRepository.findByIdParent(idRef);
//
//            idRef=token3.getId();
//
//        }
//        //recuperation du refernce
//        ReferencToken token = referenceTokenRepository.getByIdAvr(idRef);
//
//
//        //decomposition du coutenu (token)
//        TokenEntity tokenParentV1 = tokenService.setFieldsFromDecryptedString(token.getCodeRef());
//
//        if (!token.isEtat()) {
//            return null;
//        }
//
//        //disable parentV1
//        token.setEtat(false);
//        referenceTokenRepository.save(token);
//
//
//
//        Avr avr2 = token.getAvr();
//
//        avr2.setPrixUnitaire(token.getAvr().getPrixUnitaire() - amount);
//
//        avrRepository.save(avr2);
//
//        TokenEntity tokenParentV2 = new TokenEntity();
//        tokenParentV2.setValue(tokenParentV1.getValue() - amount);
//        double d = tokenParentV1.getValue() - amount;
//        System.out.println(d);
//        tokenParentV2.setIdKsu(tokenParentV1.getIdKsu());
//        tokenParentV2.setIdRefParent(tokenParentV1.getIdRefParent());
//        tokenParentV2.setLibelle(tokenParentV1.getLibelle());
//        tokenParentV2.setAvr(avr2);
//        ReferencToken referencToken = creatRef(tokenParentV2);
//        if(Objects.equals(tokenParentV1.getValue(), amount)){
//                referencToken.setEtat(false);
//        }
//
//        referencToken.setIdParent(idRef);
//        referenceTokenRepository.save(referencToken);
//        //product
//        ProductRegistryValue productRegistryValue = new ProductRegistryValue();
//        productRegistryValue.setValue(amount);
//        ProductRegistryValue productRegistryValue1 = productRegistryValueRepository.save(productRegistryValue);
//        productRegistryValue1.setIdentifant("0000" + productRegistryValue1.getId());
//        productRegistryValue1.setIdKsu(idKSUFils);
//        productRegistryValueRepository.save(productRegistryValue1);
//        //avr
//        Avr a = new Avr();
//        a.setLibelle(tokenParentV1.getLibelle());
//        a.setEtat(true);
//        a.setQuantite(1);
//        a.setDescription(tokenParentV1.getLibelle());
//
//        a.setCategorieAvr(token.getAvr().getCategorieAvr());
//
//        a.setPrixUnitaire(amount);
//        a.setTypeAvr(token.getAvr().getTypeAvr());
//        a.setProductRegistryValue(productRegistryValue);
//        a.setDetailAgr(idDetailAgr);
//        Avr avrPersist = avrRepository.save(a);
//
//
//        TokenEntity tokenFils = new TokenEntity();
//        tokenFils.setValue(amount);
//        tokenFils.setLibelle(tokenParentV1.getLibelle());
//        tokenFils.setIdKsu(idKSUFils);
//        tokenFils.setIdRefParent(tokenParentV1.getIdRefParent());
//        tokenFils.setAvr(avrPersist);
//       ReferencToken referencToken1 =  creatRef(tokenFils);
//       referencToken1.setAvr(avrPersist);
//
//        return referencToken1;
//
//
//    }

    @Override
    public ReferencToken segmentationTokenDadSonVendeurSupAcheteur(Long idRef, Long idKSUFils, Double amount, Long idDetailAgr) {
        ReferencToken token = referenceTokenRepository.findTokenWithParentAndAvr(idRef);

        if (token == null || !token.isEtat()) {
            return null;
        }

        TokenEntity tokenParentV1 = tokenService.setFieldsFromDecryptedString(token.getCodeRef());
        token.setEtat(false);

        Avr avr2 = token.getAvr();
        avr2.setPrixUnitaire(avr2.getPrixUnitaire() - amount);
        avrRepository.save(avr2);

        TokenEntity tokenParentV2 = new TokenEntity() {{
            setValue(tokenParentV1.getValue() - amount);
            setIdKsu(tokenParentV1.getIdKsu());
            setIdRefParent(tokenParentV1.getIdRefParent());
            setLibelle(tokenParentV1.getLibelle());
            setAvr(avr2);
        }};

        ReferencToken referencToken = creatRef(tokenParentV2);
        if (tokenParentV1.getValue() == amount) {
            referencToken.setEtat(false);
        }

        referencToken.setIdParent(idRef);
        referenceTokenRepository.save(referencToken);

        ProductRegistryValue productRegistryValue = new ProductRegistryValue();
        productRegistryValue.setValue(amount);
        productRegistryValue.setIdentifant("0000" + productRegistryValue.getId());
        productRegistryValue.setIdKsu(idKSUFils);
        ProductRegistryValue productRegistryValue1 = productRegistryValueRepository.save(productRegistryValue);

        Avr avrPersist = avrRepository.save(new Avr() {{
            setLibelle(tokenParentV1.getLibelle());
            setEtat(true);
            setQuantite(1);
            setDescription(tokenParentV1.getLibelle());
            setCategorieAvr(token.getAvr().getCategorieAvr());
            setPrixUnitaire(amount);
            setTypeAvr(token.getAvr().getTypeAvr());
            setProductRegistryValue(productRegistryValue1);
            setDetailAgr(idDetailAgr);
        }});

        ReferencToken referencToken1 = creatRef(new TokenEntity() {{
            setValue(amount);
            setLibelle(tokenParentV1.getLibelle());
            setIdKsu(idKSUFils);
            setIdRefParent(tokenParentV1.getIdRefParent());
            setAvr(avrPersist);
        }});

        referencToken1.setAvr(avrPersist);

        return referencToken1;
    }


    @Override
    public ReferencToken segmentationTokenDadSonAcheteurSupVendeur(Long idRef, Long idKSUFils, Double amount, Long idDetailAgr) {
        ReferencToken token2=referenceTokenRepository.findById(idRef).orElse(null);

        if(!token2.isEtat()){

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
        tokenTour.setAvr(tokenEntity.getAvr());
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

}
