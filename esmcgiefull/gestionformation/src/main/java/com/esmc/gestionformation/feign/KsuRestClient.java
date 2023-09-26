package com.esmc.gestionformation.feign;

import com.esmc.feign.FeignClientConfiguration;
import com.esmc.models.Ksu;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "KSU-SERVICE", configuration = FeignClientConfiguration.class)
public interface KsuRestClient {

    @GetMapping("api/ksus/current")
    public Ksu getCurrentKsuById();

    @GetMapping("api/ksus/getById/{id}")
    public Ksu getById(@PathVariable Long id);

    @PostMapping("save")
    public Ksu saveKsu(Ksu ksu);

}
