package com.esmc.gestionContrat.feign;

import com.esmc.feign.FeignClientConfiguration;
import com.esmc.models.DetailsAgr;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "AGR-SERVICE", configuration = FeignClientConfiguration.class)
public interface DetailsAgrRestClient {

    @GetMapping("api/detailsagrs/{id}")
    DetailsAgr getDetailAgrById(@PathVariable Long id);
}
