package com.esmc.gestionKsu.feign;

import com.esmc.feign.FeignClientConfiguration;
import com.esmc.models.TerminalEchange;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "TE-SERVICE", configuration = FeignClientConfiguration.class)
public interface TeRestClient {

    @GetMapping("api/terminalEchange/detailAgr/{id}")
    public TerminalEchange affectationTeParAgr(@PathVariable Long id);

}
