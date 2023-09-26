package com.esmc.gestionAgr.feign;

import com.esmc.feign.FeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "TE-SERVICE", configuration = FeignClientConfiguration.class)
public interface TerminalEnchangeRestClient {

    @GetMapping("api/terminalEchange/detailAgr/{id}")
    public void affectation(@PathVariable("id") Long id);

}
