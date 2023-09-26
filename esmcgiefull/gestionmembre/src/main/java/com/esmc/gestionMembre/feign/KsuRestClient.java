package com.esmc.gestionMembre.feign;

import com.esmc.feign.FeignClientConfiguration;
import com.esmc.models.Formatter;
import com.esmc.models.Ksu;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "KSU-SERVICE", configuration = FeignClientConfiguration.class)
public interface KsuRestClient {

    @GetMapping("api/ksus/getById/{id}")
    public Ksu getById(@PathVariable("id") Long id);

    @GetMapping("api/ksus/codeksu/{code}")
    public Formatter<Ksu> ksuPar(@PathVariable("code") String code);

}
