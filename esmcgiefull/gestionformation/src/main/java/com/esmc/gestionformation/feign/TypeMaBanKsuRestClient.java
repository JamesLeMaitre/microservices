package com.esmc.gestionformation.feign;

import com.esmc.feign.FeignClientConfiguration;
import com.esmc.models.TypeMABanKSU;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ACHATKSU-SERVICE", configuration = FeignClientConfiguration.class)
public interface TypeMaBanKsuRestClient {

    @GetMapping("/api/TypeMABanKSU/get/{id}")
    public TypeMABanKSU getById(@PathVariable("id") Long id);
}
