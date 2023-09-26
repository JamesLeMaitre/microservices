package com.esmc.gestionCertification.feign;

import com.esmc.feign.FeignClientConfiguration;
import com.esmc.models.DetailsAgr;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "AGR-SERVICE", configuration = FeignClientConfiguration.class)
public interface DetailAgrRestClient {

    @GetMapping("api/detailsagrs/detail_agr/byId/{id}")
    public DetailsAgr findDetailAgrById(@PathVariable("id") Long id);

}
