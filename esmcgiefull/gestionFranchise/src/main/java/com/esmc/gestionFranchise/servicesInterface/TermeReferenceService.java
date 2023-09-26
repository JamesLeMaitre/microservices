package com.esmc.gestionFranchise.servicesInterface;

import com.esmc.gestionFranchise.entities.TermeReference;

import java.util.List;

public interface TermeReferenceService {
    List<TermeReference> getTermeReference();
    TermeReference ajouterTermeReference(TermeReference TermeReference);
    TermeReference getTermeReferencebyId(Long id);
    void deleteTermeReference(Long id);
    List<TermeReference> listTermeReference(Long id);
}
