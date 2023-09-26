package com.esmc.gestionKsu.feign;


import com.esmc.feign.FeignClientConfiguration;
import com.esmc.models.DetailsAgr;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "AGR-SERVICE", configuration = FeignClientConfiguration.class)
public interface AgrRestClient {

    @GetMapping("api/detailsagrs/typeMaBanksu/{idType}/ksu/{idKsu}")
    public DetailsAgr affectationAgrAuKsu(@PathVariable("idType") Long idType, @PathVariable("idKsu") Long idKsu);

    @GetMapping("api/detailsagrs/getKSU/{id}")
    public List<DetailsAgr> getDetailAgrByKSU(@PathVariable("id") Long id);

}
