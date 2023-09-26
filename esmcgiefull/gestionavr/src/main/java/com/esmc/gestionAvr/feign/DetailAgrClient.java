package com.esmc.gestionAvr.feign;

import com.esmc.feign.FeignClientCronConfiguration;
import com.esmc.models.DetailsAgr;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@FeignClient(name = "AGR-SERVICE", configuration = FeignClientCronConfiguration.class)
public interface DetailAgrClient {

    @GetMapping("api/detailsagrs/getKSU/{id}")
    public List<DetailsAgr> listDetailAgrParKsu(@PathVariable("id") Long id);

    @GetMapping("api/detailsagrs/detail_agr/byId/{id}")
    public DetailsAgr getDetailById(@PathVariable("id") Long id);


}

