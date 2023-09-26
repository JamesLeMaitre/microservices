package com.esmc.gestionte.feign;

import com.esmc.feign.FeignClientConfiguration;
import com.esmc.models.DetailsAgr;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "AGR-SERVICE", configuration = FeignClientConfiguration.class)
public interface DetailAgrRestClient {

    @GetMapping("api/detailsagrs/getKSU/{id}")
    public List<DetailsAgr> listDetailAgrParKsu(@PathVariable Long id);

    @GetMapping("api/detailsagrs/detail_agr/{id}")
    public DetailsAgr getDetailById(@PathVariable Long id);

}
