package com.esmc.gestionformation.feign;

import com.esmc.feign.FeignClientConfiguration;
import com.esmc.models.DetailsAgr;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "AGR-SERVICE", configuration = FeignClientConfiguration.class)
public interface AgrRestClient {

    @GetMapping("api/detailsagrs/detail_agr/byId/{id}")
    public DetailsAgr findDetailAgrById(@PathVariable("id") Long idDet);

}
