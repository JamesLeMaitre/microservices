package com.esmc.gestionAchatFranchise.servicesinterfaces;

import com.esmc.gestionAchatFranchise.Exceptions.ExceptionsHandler;
import com.esmc.gestionAchatFranchise.entities.TypeFranchise;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TypeFranchiseInt {
    List< TypeFranchise > getTypeFranchise();

    TypeFranchise getTypeFranchiseById(Long id);

    TypeFranchise addTypeFranchise(TypeFranchise typeFranchise);

    TypeFranchise updateTypeFranchise( TypeFranchise typeFranchise);

    void deleteTypeFranchise(Long typeFranchiseId);

    public String achatFranchiseGestionDelai(Long id)  throws ExceptionsHandler; //
    public TypeFranchise getById(Long id) throws  ExceptionsHandler;
}
