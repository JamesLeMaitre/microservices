package com.esmc.gestionFranchise.servicesInterface.organev2;

import com.esmc.gestionFranchise.entities.organev2.AgentPoste;
import com.esmc.gestionFranchise.entities.organev2.Poste;

import java.util.List;

public interface AgentPosteService {
    List<AgentPoste> getAll();
    AgentPoste  getAgentPosteById(Long id);

    AgentPoste  create(AgentPoste data);

    AgentPoste  update(Long id, AgentPoste data);

    AgentPoste disable(Long id);

    AgentPoste enable(Long id);

    void delete(Long id);

    List<AgentPoste> byIdDetailAgr(Long id);

    List<AgentPoste> agentPosteListByDetailAgrFranchise(Long idDetailAgrFranchise);
}
