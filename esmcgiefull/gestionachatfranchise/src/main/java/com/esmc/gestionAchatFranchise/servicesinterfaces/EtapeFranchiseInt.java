package com.esmc.gestionAchatFranchise.servicesinterfaces;

import com.esmc.gestionAchatFranchise.Exceptions.ExceptionsHandler;
import com.esmc.gestionAchatFranchise.entities.EtapeFranchise;
import com.esmc.gestionAchatFranchise.request.EtapeFranchiseRequest;

import java.util.List;

public interface EtapeFranchiseInt {

    List<EtapeFranchise> getEtapeFranchise();
    EtapeFranchise getEtapeFranchiseById(Long id);
    EtapeFranchise getEtapeFranchiseByIdKsu(Long id);
    EtapeFranchise addEtapeFranchise(EtapeFranchise etapeFranchise);
    EtapeFranchise addEtapeFranchisev2(EtapeFranchiseRequest etapeFranchise);
    EtapeFranchise  updateEtapeFranchise( EtapeFranchise etapeFranchise) ;

    Boolean checkExist(Long id, EtapeFranchiseRequest request);

    void deleteEtapeFranchise(Long etapeFranchiseId);
    public EtapeFranchise getById(Long id) throws ExceptionsHandler;
    EtapeFranchise updateEtapeFranchiseByKsu(Long id, EtapeFranchiseRequest request) throws Exception;
    EtapeFranchise supreme(Long id,EtapeFranchiseRequest request);

}