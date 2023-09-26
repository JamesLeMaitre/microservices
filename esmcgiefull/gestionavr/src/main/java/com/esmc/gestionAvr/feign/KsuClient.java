package com.esmc.gestionAvr.feign;


import com.esmc.feign.FeignClientCronConfiguration;
import com.esmc.models.Ksu;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "KSU-SERVICE", configuration = FeignClientCronConfiguration.class)
public interface KsuClient {
    @GetMapping("api/ksus/getById/{id}")
    Ksu getById(@PathVariable("id") Long id);

    /*@GetMapping("api/ksus/")
    Ksu getKsuIdByDetailAgr(@PathVariable Long id);*/
}
