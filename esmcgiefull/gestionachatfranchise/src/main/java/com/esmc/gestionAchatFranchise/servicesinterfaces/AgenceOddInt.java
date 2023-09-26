package com.esmc.gestionAchatFranchise.servicesinterfaces;

import com.esmc.gestionAchatFranchise.entities.AgenceOdd;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AgenceOddInt {

    List<AgenceOdd> getAgenceOdd();

    AgenceOdd getAgenceOddById(Long id);

    AgenceOdd addAgenceOdd(AgenceOdd agenceOdd);

    AgenceOdd updateAgenceOdd( AgenceOdd agenceOdd);

    void deleteAgenceOdd(Long agenceOddId);



}
