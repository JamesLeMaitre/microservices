package com.esmc.gestionFranchise.services.organev2;

import com.esmc.gestionFranchise.entities.organev2.AgentPoste;
import com.esmc.gestionFranchise.repositories.IntervenantRepo;
import com.esmc.gestionFranchise.repositories.organev2.AgentPosteRepo;
import com.esmc.gestionFranchise.servicesInterface.organev2.AgentPosteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AgentPosteServiceImpl implements AgentPosteService {

    @Autowired
    private AgentPosteRepo agentPosteRepo;

    @Autowired
    private IntervenantRepo intervenantRepo;

    @Override
    public List<AgentPoste> getAll() {
        return agentPosteRepo.findAll();
    }

    @Override
    public AgentPoste getAgentPosteById(Long id) {
        return agentPosteRepo.findById(id).orElse(null);
    }

    @Override
    public AgentPoste create(AgentPoste data) {
        return agentPosteRepo.save(data);
    }

    @Override
    public AgentPoste update(Long id, AgentPoste data) {
        AgentPoste agentPoste = new AgentPoste();
        agentPoste = agentPosteRepo.findById(id).orElse(null);
        assert agentPoste != null : "AgentPoste ID is null";
        //agentPoste.setIdPoste(data.getIdPoste());
        agentPoste.setAgentRecruteur(data.getAgentRecruteur());

        return agentPosteRepo.save(agentPoste);
    }

    @Override
    public AgentPoste disable(Long id) {
        return null;
    }

    @Override
    public AgentPoste enable(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {
        agentPosteRepo.deleteById(id);
    }

    @Override
    public List<AgentPoste> byIdDetailAgr(Long id) {
        return agentPosteRepo.getByIdDetailAgr(id);
    }

    @Override
    public List<AgentPoste> agentPosteListByDetailAgrFranchise(Long idDetailAgrFranchise) {
        return agentPosteRepo.listAgentPoste(idDetailAgrFranchise);
    }

}
