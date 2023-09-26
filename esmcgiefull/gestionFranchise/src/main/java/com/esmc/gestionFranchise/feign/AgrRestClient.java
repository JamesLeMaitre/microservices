package com.esmc.gestionFranchise.feign;

import com.esmc.feign.FeignClientConfiguration;
import com.esmc.models.DetailsAgr;
import com.esmc.models.Formatter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "AGR-SERVICE", configuration = FeignClientConfiguration.class)
public interface AgrRestClient {

    @GetMapping("api/detailsagrs/detailAgr/{id}")
    Formatter<DetailsAgr> findDetailAgrByKSU(@PathVariable("id") Long id);


}
