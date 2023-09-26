package com.esmc.gestionAgr.fifo.services;


import com.esmc.gestionAgr.fifo.entities.ReferencToken;
import com.esmc.gestionAgr.fifo.models.ReferenceModel;
import com.esmc.gestionAgr.fifo.models.TokenEntity;

import java.util.List;

public interface ReferenceTokenService {

    ReferencToken creatRef(TokenEntity tokenEntity);

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
}
