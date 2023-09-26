package com.esmc.gestionAvr.tokens.services;

import com.esmc.gestionAvr.entities.Avr;
import com.esmc.gestionAvr.entities.ReferenceTokenOp;
import com.esmc.gestionAvr.tokens.entities.ReferencToken;
import com.esmc.gestionAvr.tokens.model.ReferenceModel;
import com.esmc.gestionAvr.tokens.model.TokenEntity;

import java.util.List;

/**
 * @author Amemorte9
 */
public interface ReferencTokenInterface {
    ReferencToken creatRef(TokenEntity tokenEntity);

    ReferenceTokenOp creatRefOp(TokenEntity tokenEntity);

    List<ReferencToken> getByIdAVR(Long id);

    ReferencToken getById(Long id);

    ReferencToken getByCoderef(String code);


    ReferenceModel getByRefTokenFrontref(String code);

    //    By wonder
    Boolean getByRefTokenFromFrontAndCheckAmount(String tokenCode, Long enteredValue);

    List<ReferencToken> getAll();

    ReferencToken fusionToken(Long[] idReferencToken, Long idDetailAgr) throws Exception;

    ReferencToken segmentationTokenDadSonVendeurSupAcheteur(Long idRef, Long idKSUFils, Double amount, Long idDetailAgr);

    ReferencToken segmentationTokenDadSonAcheteurSupVendeur(Long idRef, Long idKSUFils, Double amount, Long idDetailAgr);


    ReferencToken[] divisionToken(Long idRef, Long idKsu, Long idDetailAgr, Double amoutBasket, Double amoutTour);

    void tokenGIE();

    ReferencToken tokenGIE_Division_filsClient(Long idRef, Long idKsu, Double amount, Avr avrClient);

    ReferenceTokenOp tokenGIE_Division_filsClientAchatOP(Long idRef, Long idKsu, Double amount, Avr avrClient);
}
