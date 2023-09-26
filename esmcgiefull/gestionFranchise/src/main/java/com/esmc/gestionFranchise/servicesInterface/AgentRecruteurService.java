package com.esmc.gestionFranchise.servicesInterface;

import com.esmc.gestionFranchise.entities.AgentRecruteur;

import java.util.List;

public interface AgentRecruteurService {
    List<AgentRecruteur> getAgentRecruteur();
    List<AgentRecruteur> getTitleAgentRecruteur(Long id);
    AgentRecruteur ajouterAgentRecruteur(AgentRecruteur AgentRecruteur);
    AgentRecruteur getAgentRecruteurbyId(Long id);
    void deleteAgentRecruteur(Long id);

}
