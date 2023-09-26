package com.esmc.gestionContrat.feign;

import com.esmc.feign.FeignClientConfiguration;
import com.esmc.models.Ksu;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "KSU-SERVICE", configuration = FeignClientConfiguration.class)
public interface KsuRestClient {

    @GetMapping("api/ksus/getById/{id}")
    Ksu getById(@PathVariable Long id);

    @GetMapping("api/ksus/codeksu/{code}")
    Ksu ksuPar(@PathVariable("code") String code);


}