package com.esmc.gestionFranchise.servicesInterface.organev2;

import com.esmc.gestionFranchise.entities.AgentRecruteur;
import com.esmc.gestionFranchise.entities.organev2.AgentPoste;
import com.esmc.models.FranchiseReferentiel;

import java.util.List;

public interface MonitorServiceInterface {
    AgentPoste franchise_master_generator(Long idFranchise, Long idPoste);

    AgentPoste franchise_master_generatorv2(Long idDetailsAgrFranchise, Long idPoste, Long idAgr);

    AgentPoste franchise_affectation_agent(Long idAgent, Long idKsu);

    List<AgentPoste> list_franchise_affectation_agent(Long idFranchise);

    AgentRecruteur get(Long idAgr, Long idDetailsAgrFranchiser, Long idPoste);

    AgentPoste get_franchise_by_codePouvoirFaire(String codePouvoirFaire);

    List<FranchiseReferentiel> list_franchise_by_idDetailAgr(Long idDetailAgr);

    List<AgentRecruteur> getCodePouvoirFaire(Long id);


}
