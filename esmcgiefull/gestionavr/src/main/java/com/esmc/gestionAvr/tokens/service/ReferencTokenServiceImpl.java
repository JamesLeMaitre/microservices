package com.esmc.gestionAvr.tokens.service;


import com.esmc.gestionAvr.entities.Avr;
import com.esmc.gestionAvr.entities.ReferenceTokenOp;
import com.esmc.gestionAvr.tokens.entities.ReferencToken;
import com.esmc.gestionAvr.tokens.model.ReferenceModel;
import com.esmc.gestionAvr.tokens.model.TokenEntity;
import com.esmc.gestionAvr.tokens.repositories.ReferencTokenRepo;
import com.esmc.gestionAvr.tokens.services.ReferencTokenInterface;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service

public class ReferencTokenServiceImpl  {

//
//    private final   ReferencTokenRepo referencTokenRepo;
//    private final TokenServiceImpl tokenService;
//
//    public ReferencTokenServiceImpl(ReferencTokenRepo referencTokenRepo, TokenServiceImpl tokenService) {
//        this.referencTokenRepo = referencTokenRepo;
//        this.tokenService = tokenService;
//    }
//
//
//    private AtomicLong counter = new AtomicLong();
//    private final char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
//
//    public String generateSerialCode1() {
//        long value = counter.incrementAndGet();
//        int charIndex = (int) (value % alphabet.length);
//
//        return "AAQS-" + String.format("%04d", value) + "-AZ" + alphabet[charIndex] + "9-0000-ESMC";
//    }
//
//
//    public String generateSerialCode() {
//        int counter = referencTokenRepo.Counter();
//
//
////        int charIndex1 = (counter / alphabet.length / alphabet.length) % alphabet.length;
////        int charIndex2 = (counter / alphabet.length) % alphabet.length;
////        int charIndex3 = counter % alphabet.length;
//
//
//        int charIndex1 = (int) ((counter / alphabet.length / alphabet.length/alphabet.length/ alphabet.length/ alphabet.length/ alphabet.length/ alphabet.length) % alphabet.length);
//        int charIndex2 = (int) ((counter / alphabet.length / alphabet.length/ alphabet.length/ alphabet.length/ alphabet.length/ alphabet.length) % alphabet.length);
//        int charIndex3 = (int) ((counter / alphabet.length/ alphabet.length/ alphabet.length/ alphabet.length/ alphabet.length) % alphabet.length);
//        int charIndex4 = (int) ((counter / alphabet.length/ alphabet.length/ alphabet.length/ alphabet.length) % alphabet.length);
//        int charIndex5 = (int) ((counter / alphabet.length/ alphabet.length/ alphabet.length) % alphabet.length);
//        int charIndex6 = (int) ((counter / alphabet.length/ alphabet.length) % alphabet.length);
//
//
//        int modulo = counter / 10000;
//
//
//        if(counter>=10000){
//            counter = counter - (modulo * 10000);
//
//        }
//        return "AA"  + alphabet[charIndex5] + alphabet[charIndex6] +"-"+ String.format("%04d", counter) + "-AZ"  + alphabet[charIndex1] + alphabet[charIndex2] + alphabet[charIndex3] + alphabet[charIndex4] + "9-0000-ESMC";
//
//       // return "AAQS-" + String.format("%04d", counter) + "-AZ"  + alphabet[charIndex1] + alphabet[charIndex2] + alphabet[charIndex3]  + "9-0000-ESMC";
//    }
//    @Override
//    public ReferencToken creatRef(TokenEntity tokenEnitie) {
//
//
//
//
//           String codeFront=generateSerialCode();
//
//            String ref= tokenService.generateCode2(tokenEnitie);
//           ReferencToken  referencToken=new ReferencToken();
//           referencToken.setCodeRef(ref);
//           referencToken.setAvr(tokenEnitie.getAvr());
//           referencToken.setRefTokenFront(codeFront);
//            referencTokenRepo.save(referencToken);
//
//        ReferencToken  referencToken1=new ReferencToken();
//       return  referencToken1;
//    }
//
//    @Override
//    public ReferenceTokenOp creatRefOp(TokenEntity tokenEntity) {
//        return null;
//    }
//
//    @Override
//    public List<ReferencToken> getByIdAVR(Long id) {
//        return null;
//    }
//
////    @Override
////    public ReferencToken getByIdAVR(Long id) {
////        return referencTokenRepo.getByIdAvr(id);
////    }
//
//    @Override
//    public ReferencToken getById(Long id) {
//        return referencTokenRepo.getById(id);
//    }
//
//    @Override
//    public ReferencToken getByCoderef(String code) {
//        return referencTokenRepo.getByCodeRef(code);
//    }
//
//    @Override
//    public ReferenceModel getByRefTokenFrontref(String code) {
//
//        ReferenceModel referenceModel=new ReferenceModel();
//
//        ReferencToken referencToken=referencTokenRepo.getByRefTokenFront(code);
//        TokenEntity tokenEnitie=tokenService.setFieldsFromDecryptedString(referencToken.getCodeRef());
//
//        BeanUtils.copyProperties(referencToken,referenceModel);
//        BeanUtils.copyProperties(tokenEnitie,referenceModel);
//
//
//
//        return referenceModel;
//    }
//
//
////    By wonder
//    @Override
//    public Boolean  getByRefTokenFromFrontAndCheckAmount(String tokenCode, Long enteredValue) {
//        ReferenceModel referenceModel=new ReferenceModel();
//
//        ReferencToken referencToken=referencTokenRepo.getByRefTokenFront(tokenCode);
//        TokenEntity tokenEnitie=tokenService.setFieldsFromDecryptedString(referencToken.getCodeRef());
//
//        BeanUtils.copyProperties(referencToken,referenceModel);
//        BeanUtils.copyProperties(tokenEnitie,referenceModel);
//
//        if(referenceModel.getValue()<enteredValue){
//            return false;
//        }
//        else {
//            return true;
//        }
//
//    }
//
//    @Override
//    public List<ReferencToken> getAll() {
//        return referencTokenRepo.findAll();
//    }
//
//    @Override
//    public ReferencToken fusionToken(Long[] idReferencToken, Long idDetailAgr) throws Exception {
//        return null;
//    }
//
//    @Override
//    public ReferencToken segmentationTokenDadSonVendeurSupAcheteur(Long idRef, Long idKSUFils, Double amount, Long idDetailAgr) {
//        return null;
//    }
//
//    @Override
//    public ReferencToken segmentationTokenDadSonAcheteurSupVendeur(Long idRef, Long idKSUFils, Double amount, Long idDetailAgr) {
//        return null;
//    }
//
//    @Override
//    public ReferencToken[] divisionToken(Long idRef, Long idKsu, Long idDetailAgr, Double amoutBasket, Double amoutTour) {
//        return new ReferencToken[0];
//    }
//
//    @Override
//    public void tokenGIE() {
//
//    }
//
//    @Override
//    public ReferencToken tokenGIE_Division_filsClient(Long idRef, Long idKsu, Double amount, Avr avrClient) {
//        return null;
//    }
//
//    @Override
//    public ReferenceTokenOp tokenGIE_Division_filsClientAchatOP(Long idRef, Long idKsu, Double amount, Avr avrClient) {
//        return null;
//    }


}
