package com.esmc.gestionAvr.feign;

import com.esmc.feign.FeignClientCronConfiguration;

import com.esmc.models.Formatter;
import com.esmc.models.SupportUtilise;
import com.esmc.models.TypeSupport;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "FRANCHISE-SERVICE", configuration = FeignClientCronConfiguration.class)
public interface TypeSupportClient {

    @GetMapping("api/typeSupport/by/libelle/{id}")
    TypeSupport getTypeSupportById(@PathVariable("id") Long id);

    @GetMapping("api/typeSupport/by/id/{id}")
    String getLibelleById(@PathVariable("id") Long id);

    @GetMapping("api/typeSupport/get/{id}")
     TypeSupport getByTypeSupportId(@PathVariable("id") Long id);

    @GetMapping("api/SupportUtilise/get/{id}")
    SupportUtilise getSupportUtlise(@PathVariable("id") Long id);

}
