package com.esmc.gestionCertification.feign;

import com.esmc.feign.FeignClientConfiguration;
import com.esmc.models.Formatter;
import com.esmc.models.Poste;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "FRANCHISE-SERVICE", configuration = FeignClientConfiguration.class)
public interface PosteRestClient {

    @GetMapping("api/posteOrgane/by/id/{id}")
    Formatter<Poste> getById(@PathVariable("id") Long id);

    @GetMapping("api/posteOrgane/getWithout/id/{id}")
    Formatter<Poste> getWithout(@PathVariable("id") Long id);

}
