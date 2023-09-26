package com.esmc.gestionte.feign;

import com.esmc.feign.FeignClientConfiguration;
import com.esmc.input.CheckTraitementInput;
import com.esmc.models.CheckTraitement;
import com.esmc.models.DetailsAgr;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "CERTIFICATION-SERVICE", configuration = FeignClientConfiguration.class)
public interface CertificationRestClient {

    @GetMapping("api/checkTraitement/save")
    public CheckTraitement savecheck(@RequestBody CheckTraitementInput CheckTraitementInput);
}
