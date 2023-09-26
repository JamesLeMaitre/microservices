package com.esmc.gestionKsu.feign;

import com.esmc.feign.FeignClientConfiguration;
import com.esmc.models.AgentPoste;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "FRANCHISE-SERVICE", configuration = FeignClientConfiguration.class)
public interface FranchiseRestClient {

    @GetMapping("api/agentPoste/list_agent_poste{id}")
    public List<AgentPoste> listAgentBosteByIdDetailAgrFranchise(@PathVariable Long id);

}
