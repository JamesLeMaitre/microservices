package com.esmc.gestionformation.feign;

import com.esmc.feign.FeignClientConfiguration;
import com.esmc.models.Formatter;
import com.esmc.models.Poste;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "FRANCHISE-SERVICE", configuration = FeignClientConfiguration.class)
public interface FranchiseRestClient {
    @GetMapping("api/posteOrgane/by/id/{id}")
    Formatter<Poste> getById(@PathVariable("id") Long id);
}
