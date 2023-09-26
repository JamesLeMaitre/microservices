package com.esmc.gestionAchatFranchise.feign;

import com.esmc.feign.FeignClientConfiguration;
import com.esmc.models.Ksu;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="KSU-SERVICE", configuration = FeignClientConfiguration.class)
public interface KsuRestClient {
    @GetMapping("api/ksus/getById/{id}")
    public Ksu getKsuById(@PathVariable("id") Long id);
}